package utilities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import com.medicard.member.R;
import com.medicard.member.RegistrationActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import de.hdodenhof.circleimageview.CircleImageView;
import fragments.fragment_memberInfo;
import model.AddDepenceResponse;
import model.AddDependence;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;

import static fragments.fragment_memberInfo.*;

/**
 * Created by mpx-pawpaw on 11/24/16.
 */

public class DialogAddDependence {

    SharedPref sharedPref = new SharedPref();
    EditText et_username, et_mem_account;
    Button btn_accept, btn_cancel;
    Context context;
    Dialog dialog;

    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();

    public DialogAddDependence(Context context) {
        this.context = context;
    }

    public void showMe(final Context context, String username) {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_dependence);
        dialog.getWindow().setWindowAnimations(R.style.CustomDialogAnimation);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);


        btn_accept = (Button) dialog.findViewById(R.id.btn_accept);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        et_mem_account = (EditText) dialog.findViewById(R.id.et_mem_account);
        et_username = (EditText) dialog.findViewById(R.id.et_username);

        et_username.setText(username);
        et_username.setFocusableInTouchMode(false);
        et_username.setFocusable(false);


        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String memberID = et_mem_account.getText().toString();
                String username = et_username.getText().toString();


                if (memberID.equals("") || username.equals("")) {
                    alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.warninginputMemberId, 1);
                } else {

                    if (NetworkTest.isOnline(context)) {
                        sendNewDependent(memberID, username);
                    } else {
                        alertDialogCustom.showMe(context, alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);
                    }


                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.80);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = width;
        dialog.getWindow().setAttributes(lp);

    }

    private void sendNewDependent(String dependentmemberID, final String username) {
        String memberCode = sharedPref.getStringValue(sharedPref.USER, sharedPref.MEMBERCODE, context);


        AddDependence addDependence = new AddDependence();
        addDependence.setDepMemberCode(dependentmemberID);
        addDependence.setMemberCode(memberCode);
        addDependence.setUsername(username);

        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.addDependence(addDependence)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<AddDepenceResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                        try {
                            Log.d("ADD_RETURN", e.getMessage());
                            alertDialogCustom.showMe(context ,alertDialogCustom.HOLD_ON_title , alertDialogCustom.unknown_msg , 1);
                        }catch (Exception error){
                            dialog.dismiss();

                            alertDialogCustom.showMe(context ,alertDialogCustom.HOLD_ON_title , alertDialogCustom.unknown_msg , 1);

                            Log.e("Rx_ERROR" , error.getMessage());
                        }


                    }

                    @Override
                    public void onNext(AddDepenceResponse addDepenceResponse) {
                        Log.d("ADD_RETURN", addDepenceResponse.toString());
                        //dialog.dismiss();
                        if (addDepenceResponse.getResponseCode().equals("200")) {
                            alertDialogCustom.showMe(context, alertDialogCustom.success, alertDialogCustom.successfullyAddedDep, 2);

                            EventBus.getDefault().post(new fragment_memberInfo.MessageEvent("reloadDependent"));

                            dialog.dismiss();

                        }else if(addDepenceResponse.getResponseCode().equals("230")){

                            alertDialogCustom.showMe(context, alertDialogCustom.unknown, alertDialogCustom.addDependenceAlreadyAdded, 1);

                        }else if (addDepenceResponse.getResponseCode().equals("231")){

                            alertDialogCustom.showMe(context, alertDialogCustom.unknown, alertDialogCustom.addDependenceNotDependent, 1);

                        }else{

                            alertDialogCustom.showMe(context, alertDialogCustom.unknown, alertDialogCustom.unknown_msg, 1);

                        }
                    }
                });
    }
}
