package v2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import com.medicard.member.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import Sqlite.DatabaseHandler;
import mehdi.sakout.fancybuttons.FancyButton;
import utilities.AgeCorrector;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.ResetDatabase;
import utilities.SharedPref;

public class RequestButtonsActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHandler databaseHandler;
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
    LinearLayout ll_consult, ll_maternity, ll_basic, ll_other;
    FancyButton btn_back;
    Context context;
    public static String ORIGIN = "ORIGIN";
    public static String CONSULT = "CONSULT";
    public static String MATERNITY = "MATERNITY";
    public static String TO_DETAILS_ACT = "TO_DETAILS_ACT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_buttons);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        databaseHandler = new DatabaseHandler(context);

        ll_consult = (LinearLayout) findViewById(R.id.ll_consult);
        ll_maternity = (LinearLayout) findViewById(R.id.ll_maternity);
        ll_basic = (LinearLayout) findViewById(R.id.ll_basic);
        ll_other = (LinearLayout) findViewById(R.id.ll_other);
        btn_back = (FancyButton) findViewById(R.id.btn_back);

        ll_consult.setOnClickListener(this);
        ll_maternity.setOnClickListener(this);
        ll_basic.setOnClickListener(this);
        ll_other.setOnClickListener(this);
        btn_back.setOnClickListener(this);


        ResetDatabase.resetDB(context, databaseHandler);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.ll_basic:

                break;
            case R.id.ll_maternity:

                if (getIntent().getExtras().getString(Constant.GENDER).equals("1")) {
                    alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.maternity_not_available, 1);
                } else {
                    gotoRequest(MATERNITY);
                }


                break;
            case R.id.ll_consult:
                gotoRequest(CONSULT);
                break;
            case R.id.ll_other:

                break;
            case R.id.btn_back:
                finish();
                break;

        }

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
