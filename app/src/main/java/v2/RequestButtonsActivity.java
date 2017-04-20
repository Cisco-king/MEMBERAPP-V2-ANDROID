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

import Sqlite.DatabaseHandler;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import constants.ParcelableObject;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Member;
import modules.tests.TestsActivity;
import utilities.AgeCorrector;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.ResetDatabase;
import utilities.SharedPref;

public class RequestButtonsActivity extends AppCompatActivity {

    public static final String TAG =
            RequestButtonsActivity.class.getSimpleName();

    public static final String ORIGIN = "ORIGIN";
    public static final String CONSULT = "CONSULT";
    public static final String MATERNITY = "MATERNITY";
    public static final String TO_DETAILS_ACT = "TO_DETAILS_ACT";

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

        unbinder = ButterKnife.bind(this);

        context = this;
        databaseHandler = new DatabaseHandler(context);

        ResetDatabase.resetDB(context, databaseHandler);

        Log.d(TAG, "gotoRequest: memberID : " + getIntent().getExtras().getString(Constant.MEMBER_ID));
    }

    @OnClick(R.id.cvConsultanty)
    public void onStartConsultanty() {
        gotoRequest(CONSULT);
    }

    @OnClick(R.id.cvMaternityConsultation)
    public void onStartMaternityConsultation() {
        if (getIntent().getExtras().getString(Constant.GENDER).equals("1")) {
            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.maternity_not_available, 1);
        } else {
            gotoRequest(MATERNITY);
        }
    }

    @OnClick(R.id.cvTests)
    public void onStartTests() {
        try {
            Bundle bundle = getIntent().getExtras();
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

            startActivity(intent);

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

}
