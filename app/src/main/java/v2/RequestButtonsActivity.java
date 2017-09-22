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


    /*
        Declaration
     */
    public static final String TAG =
            RequestButtonsActivity.class.getSimpleName();

    public static final String MALE = "1";


    //********************************************
    public static final String ORIGIN = "ORIGIN";
    public static final String CONSULT = "CONSULT";
    public static final String MATERNITY = "MATERNITY";
    public static final String TEST = "TEST";
    public static final String TO_DETAILS_ACT = "TO_DETAILS_ACT";
    //********************************************

    public static final int OLD_LAYOUT = 0;
    public static final int NEW_LAYOUT = 1;

    private String MEMBERSTATUS = "";


    @BindView(R.id.cvConsultanty)
    CardView cvConsultanty;
    @BindView(R.id.cvMaternityConsultation)
    CardView cvMaternityConsultation;
    @BindView(R.id.cvTests)
    CardView cvTests;

    @BindView(R.id.btn_back)
    FancyButton btn_back;

    Unbinder unbinder;

    DatabaseHandler databaseHandler;
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_buttons);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        unbinder = ButterKnife.bind(this);

        context = this;

        setSupportActionBar(toolbar);

        boolean hasMaternity = SharedPref.getBooleanValue(this, SharedPref.KEY_HAS_MATERNITY);

        MEMBERSTATUS = getIntent().getExtras().getString(Constant.MEM_STATUS);

        String gender = getIntent().getExtras().getString(Constant.GENDER);


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
        try{
            gotoRequest(CONSULT);
        }catch (Exception e){
            e.printStackTrace();
            onStartConsultanty(); //Retry again to do a Consultation Result
        }

    }


    /*
        Onlick Move to Request Maternity
     */
    @OnClick(R.id.cvMaternityConsultation)
    public void onStartMaternityConsultation() {
        try {
            gotoMaternity(MATERNITY);
        } catch (Exception e) {
            e.printStackTrace();
            onStartMaternityConsultation(); //Retry again to do a Maternity Consultation Result
        }
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
        Intent gotoConsult = new Intent(context, HospitalListAcitivity.class);
        gotoConsult.putExtra(ORIGIN, TO_DETAILS_ACT);
        gotoConsult.putExtra(Constant.MEMBER_ID, getIntent().getExtras().getString(Constant.MEMBER_ID));
        gotoConsult.putExtra(Constant.GENDER, getIntent().getExtras().getString(Constant.GENDER));
        gotoConsult.putExtra(Constant.BIRTHDAY, getIntent().getExtras().getString(Constant.BIRTHDAY));
        gotoConsult.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.NAME));
        gotoConsult.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
        gotoConsult.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
        gotoConsult.putExtra(Constant.AGE, ageCorrector.age(getIntent().getExtras().getString(Constant.AGE)));
        gotoConsult.putExtra(Constant.MEM_STATUS, getIntent().getExtras().getString(Constant.MEM_STATUS));
        startActivity(gotoConsult);

    }


    private void gotoMaternity(String DESTINATION) {
        SharedPref.setStringValue(SharedPref.USER, SharedPref.DESTINATION, DESTINATION, context);
        AgeCorrector ageCorrector = new AgeCorrector();
        Intent gtoMaternity = new Intent(context, HospitalListAcitivity.class);
        gtoMaternity.putExtra(ORIGIN, TO_DETAILS_ACT);
        gtoMaternity.putExtra(Constant.MEMBER_ID, getIntent().getExtras().getString(Constant.MEMBER_ID));
        gtoMaternity.putExtra(Constant.GENDER, getIntent().getExtras().getString(Constant.GENDER));
        gtoMaternity.putExtra(Constant.BIRTHDAY, getIntent().getExtras().getString(Constant.BIRTHDAY));
        gtoMaternity.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.NAME));
        gtoMaternity.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
        gtoMaternity.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
        gtoMaternity.putExtra(Constant.AGE, ageCorrector.age(getIntent().getExtras().getString(Constant.AGE)));
        gtoMaternity.putExtra(Constant.MEM_STATUS, MEMBERSTATUS);
        startActivity(gtoMaternity);
    }

    public void startTest(int content) {
        if (content == NEW_LAYOUT) {
            AgeCorrector ageCorrector = new AgeCorrector();
            Intent intent = new Intent(this, TestActivity.class);
            SharedPref.setStringValue(SharedPref.USER, SharedPref.DESTINATION, TEST, context);
            intent.putExtra(Constant.MEMBER_ID, getIntent().getExtras().getString(Constant.MEMBER_ID));
            intent.putExtra(Constant.GENDER, getIntent().getExtras().getString(Constant.GENDER));
            intent.putExtra(Constant.BIRTHDAY, getIntent().getExtras().getString(Constant.BIRTHDAY));
            intent.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.NAME));
            intent.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
            intent.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
            intent.putExtra(Constant.AGE, ageCorrector.age(getIntent().getExtras().getString(Constant.AGE)));
            intent.putExtra(Constant.MEM_STATUS, MEMBERSTATUS);
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
