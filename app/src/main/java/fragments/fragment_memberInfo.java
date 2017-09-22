package fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.medicard.member.R;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import adapter.AccountAdapter;
import model.Dependents;
import model.GetUSER;
import model.MemberInfo;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import timber.log.Timber;
import utilities.AlertDialogCustom;
import utilities.DialogAddDependence;
import utilities.ErrorMessage;
import utilities.NetworkTest;
import utilities.SharedPref;

public class fragment_memberInfo extends Fragment
        implements View.OnClickListener, Animation.AnimationListener {

    private Boolean isFabOpen = true;
    private FloatingActionButton fab, fab1, fab2, fab3;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward, fade_in, fade_out, flip;
    LinearLayout blackBG;
    TextView tv_print, tv_email, tv_view;
    private Animation animation1;
    private Animation animation2;


//    CircleImageView iv_user;
//    private Animation animation1;
//    private Animation animation2;
//    private boolean isBackOfCardShowing = true;


    RecyclerView rv_memberInfo;
    AccountAdapter accountAdapter;
    ArrayList<Dependents> arrayAccounts = new ArrayList<>();
    ArrayList<MemberInfo> header = new ArrayList<>();
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();

    final String TAG = "MEMBER_INFO";

    ProgressDialog progressDialog;
    SharedPref sharedPref = new SharedPref();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_info, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        progressDialog = new ProgressDialog(getContext(), R.style.MyTheme);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

        rv_memberInfo = (RecyclerView) view.findViewById(R.id.rv_memberInfo);
        accountAdapter = new AccountAdapter(header, arrayAccounts, getContext(), progressDialog, alertDialogCustom);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_memberInfo.setLayoutManager(mLayoutManager);

        tv_email = (TextView) view.findViewById(R.id.tv_email);
        tv_print = (TextView) view.findViewById(R.id.tv_print);
        tv_view = (TextView) view.findViewById(R.id.tv_view);

        blackBG = (LinearLayout) view.findViewById(R.id.blackBG);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) view.findViewById(R.id.fab3);

        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_animation);
        fade_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out_animation);
        rotate_forward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_backward);
        animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.to_middle);
        animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.from_middle);
        animation1.setAnimationListener(this);
        animation2.setAnimationListener(this);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        animateFAB();

        if (NetworkTest.isOnline(getContext())) {
            getUserData(
                    sharedPref.getStringValue(
                            sharedPref.USER,
                            sharedPref.MEMBERCODE,
                            getContext()
                    ));
        } else {
            alertDialogCustom.showMe(
                    getContext(),
                    alertDialogCustom.NO_Internet_title,
                    alertDialogCustom.NO_Internet,
                    1);
        }

        rv_memberInfo.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (isFabOpen) {
                    return true;
                } else
                    return false;
            }
        });
    }

    public void getUserData(final String id) {
        Timber.d("################################\n %s ###########################", id);
        progressDialog.show();
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getMemberInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GetUSER>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                        try {
                            Log.e(TAG, e.getMessage());


                            alertDialogCustom.showMe(getContext(), "Hold On", ErrorMessage.setErrorMessage(e.getMessage()), 1);
                            progressDialog.dismiss();
                        }catch (Exception error){


                            alertDialogCustom.showMe(getContext(), "Hold On", ErrorMessage.setErrorMessage(e.getMessage()), 1);
                            progressDialog.dismiss();

                            Log.e("Rx_ERROR" , error.getMessage());
                        }



                    }

                    @Override
                    public void onNext(GetUSER memberInfo) {
                        Log.d(TAG, memberInfo.toString());
                        Log.d(TAG, memberInfo.getMemberInfo().getPRIN_CODE());
                               header.add(memberInfo.getMemberInfo());
                        SharedPref.setStringValue(SharedPref.USER , SharedPref.AGE , header.get(0).getAGE() ,getContext());
                        SharedPref.setStringValue(SharedPref.USER , SharedPref.GENDER , header.get(0).getMEM_SEX() ,getActivity());

                        arrayAccounts.addAll(memberInfo.getDependents());

                        progressDialog.dismiss();
                        rv_memberInfo.setAdapter(accountAdapter);
                        accountAdapter.notifyDataSetChanged();

                        // setHeader();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_viewMember:
                Log.d("back is touch", "back is touch");
                break;

            case R.id.fab:

                animateFAB();
                break;
            case R.id.fab1:
                DialogAddDependence addDependence = new DialogAddDependence(getContext());
                addDependence.showMe(getContext(), sharedPref.getStringValue(sharedPref.USER, sharedPref.USERNAME, getContext()));

                animateFAB();

                break;
            case R.id.fab2:

                break;
            case R.id.fab3:

                break;
        }

    }


    public void animateFAB() {

        if (isFabOpen) {
            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);

            tv_email.setAnimation(fab_close);
            tv_print.setAnimation(fab_close);
            tv_view.setAnimation(fab_close);

            blackBG.startAnimation(fade_out);
            blackBG.setVisibility(View.GONE);

            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isFabOpen = false;

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);

            tv_email.setAnimation(fab_open);
            tv_print.setAnimation(fab_open);
            tv_view.setAnimation(fab_open);

            blackBG.startAnimation(fade_in);
            blackBG.setVisibility(View.VISIBLE);

            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isFabOpen = true;


        }
    }


    @Override
    public void onAnimationStart(Animation animation) {
//        if (animation == animation1) {
//            if (isBackOfCardShowing) {
//                iv_user.setImageResource(R.drawable.icon);
//            } else {
//                iv_user.setImageResource(R.drawable.imageplaceholder);
//            }
//            iv_user.clearAnimation();
//            iv_user.setAnimation(animation2);
//            iv_user.startAnimation(animation2);
//        } else {
//            isBackOfCardShowing = !isBackOfCardShowing;
//        }

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    @Override
    public void onResume() {

        super.onResume();
        this.onCreate(null);
        Log.d("REFRESH", "REFRESH");


        accountAdapter.notifyDataSetChanged();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(fragment_memberInfo.MessageEvent event) {

        Log.i("TRIGGER", "asdfasdfasd");

        arrayAccounts.clear();

        if (NetworkTest.isOnline(getContext())) {
            getUserData(sharedPref.getStringValue(sharedPref.USER, sharedPref.MEMBERCODE, getContext()));
        } else
            alertDialogCustom.showMe(getContext(), alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);

        accountAdapter.notifyDataSetChanged();

    }



    public static class MessageEvent{

        String s;

        public MessageEvent(String s){

            this.s = s;

        }

        public String getMessage(){


            return s;
        }

        public void setMessage(String s){

            this.s = s;

        }


    }
}
