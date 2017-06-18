package v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.medicard.member.R;
import com.medicard.member.module.test.TestActivity;

import Sqlite.DatabaseHandler;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import constants.ParcelableObject;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Member;
import modules.tests.TestsActivity;
import timber.log.Timber;
import utilities.AgeCorrector;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.ResetDatabase;
import utilities.SharedPref;
import utilities.ViewUtilities;

public class RequestButtonsActivity extends AppCompatActivity {

    public static final String TAG =
            RequestButtonsActivity.class.getSimpleName();

    public static final String MALE = "1";

    public static final String ORIGIN = "ORIGIN";
    public static final String CONSULT = "CONSULT";
    public static final String MATERNITY = "MATERNITY";
    public static final String TEST = "TEST";
    public static final String TO_DETAILS_ACT = "TO_DETAILS_ACT";

    public static final int OLD_LAYOUT = 0;
    public static final int NEW_LAYOUT = 1;


    @BindView(R.id.cvConsultanty) CardView cvConsultanty;
    @BindView(R.id.cvMaternityConsultation) CardView cvMaternityConsultation;
    @BindView(R.id.cvTests) CardView cvTests;

    @BindView(R.id.btn_back) FancyButton btn_back;

    Unbinder unbinder;

    DatabaseHandler databaseHandler;
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_buttons);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boolean hasMaternity = SharedPref.getBooleanValue(this, SharedPref.KEY_HAS_MATERNITY);
        String gender = getIntent().getExtras().getString(Constant.GENDER);

        unbinder = ButterKnife.bind(this);

        context = this;
        databaseHandler = new DatabaseHandler(context);

        ResetDatabase.resetDB(context, databaseHandler);

        isMaternityIsVisibleForThisUser(hasMaternity, gender);

        Log.d(TAG, "gotoRequest: memberID : " + getIntent().getExtras().getString(Constant.MEMBER_ID));

//        ViewUtilities.hideView(cvTests);
    }

    private void isMaternityIsVisibleForThisUser(boolean hasMaternity, String gender) {
        if (!hasMaternity || gender.equals(MALE)) {
            cvMaternityConsultation.setVisibility(View.GONE);
        } else {
            cvMaternityConsultation.setVisibility(View.VISIBLE);
        }
    }


    @OnClick(R.id.cvConsultanty)
    public void onStartConsultanty() {
        gotoRequest(CONSULT);
    }

    @OnClick(R.id.cvMaternityConsultation)
    public void onStartMaternityConsultation() {
        /*if (getIntent().getExtras().getString(Constant.GENDER).equals("1")) {
            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.maternity_not_available, 1);
        } else if (!SharedPref.getBooleanValue(this, SharedPref.KEY_HAS_MATERNITY)) {
            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, getString(R.string.maternity_consultation_not_available), 1);
        } else {*/
            gotoRequest(MATERNITY);
//        }
    }

    @OnClick(R.id.cvTests)
    public void onStartTests() {
        try {
            startTest(NEW_LAYOUT);
           /* Bundle bundle = getIntent().getExtras();
            Member member = new Member.Builder()
                    .name(bundle.getString(Constant.NAME))
                    .memberId(bundle.getString(Constant.MEMBER_ID))
                    .company(bundle.getString(Constant.COMPANY))
                    .gender(bundle.getString(Constant.GENDER))
                    .age(AgeCorrector.age(bundle.getString(Constant.GENDER)))
                    .remarks(bundle.getString(Constant.REMARK))
                    .build();

            Intent intent = new Intent(this, TestsActivity.class);
            intent.putExtra(ParcelableObject.MEMBER, member);

            startActivity(intent);*/

        } catch (Exception e) {
            // do some loggable thing here
        }

    }

    @OnClick(R.id.btn_back)
    public void back() {
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    // todo copy this for test
    private void gotoRequest(String DESTINATION) {

        SharedPref.setStringValue(SharedPref.USER, SharedPref.DESTINATION, DESTINATION, context);
        AgeCorrector ageCorrector = new AgeCorrector();
        Intent gotoMaternity = new Intent(context, HospitalListAcitivity.class);
        gotoMaternity.putExtra(ORIGIN, TO_DETAILS_ACT);
        gotoMaternity.putExtra(Constant.MEMBER_ID, getIntent().getExtras().getString(Constant.MEMBER_ID));
        gotoMaternity.putExtra(Constant.GENDER, getIntent().getExtras().getString(Constant.GENDER));
        gotoMaternity.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.NAME));
        gotoMaternity.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
        gotoMaternity.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
        gotoMaternity.putExtra(Constant.AGE, ageCorrector.age(getIntent().getExtras().getString(Constant.AGE)));
        startActivity(gotoMaternity);

    }

    public void startTest(int content) {
        if (content == NEW_LAYOUT) {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, TestsActivity.class);
            Bundle bundle = getIntent().getExtras();
            Member member = new Member.Builder()
                    .name(bundle.getString(Constant.NAME))
                    .memberId(bundle.getString(Constant.MEMBER_ID))
                    .company(bundle.getString(Constant.COMPANY))
                    .gender(bundle.getString(Constant.GENDER))
                    .age(AgeCorrector.age(bundle.getString(Constant.GENDER)))
                    .remarks(bundle.getString(Constant.REMARK))
                    .build();
            startActivity(intent);
        }
    }

}
