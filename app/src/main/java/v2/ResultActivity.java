package v2;

import android.content.Context;
import com.medicard.member.R;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import InterfaceService.ScreenshotCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import fragments.ConsultationResult;
import fragments.MaternityResult;
import mehdi.sakout.fancybuttons.FancyButton;
import utilities.Constant;
import utilities.DateAddThreeDays;
import utilities.HeaderNameSetter;
import utilities.SharedPref;

public class ResultActivity extends AppCompatActivity {

    private String Approved = "Approved";


    FragmentTransaction fragmentTransaction;
    Fragment fragment = null;
    String origin;
    String valDate = "";
    String currentDate = "";
    Context context;
    TextView tv_header;
    @BindView(R.id.btn_click)
    FancyButton btn_click;
    ScreenshotCallback callback;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        ButterKnife.bind(this);

        tv_header = (TextView) findViewById(R.id.tv_header);
        origin = SharedPref.getStringValue(SharedPref.USER, SharedPref.DESTINATION, context);
        tv_header.setText(HeaderNameSetter.setHeader(origin));
        valDate = DateAddThreeDays.currentDate() + " to " + DateAddThreeDays.validityDate();
        currentDate = DateAddThreeDays.dateMMMdyyyFormat();


        if (origin.equals(RequestButtonsActivity.CONSULT)) {

            if (getIntent().getStringExtra(Constant.REQUEST).equals(Approved)) {
                fragment = new ConsultationResult().newInstance(
                        getIntent().getExtras().getString(Constant.REFERENCECODE),
                        getIntent().getStringExtra(Constant.MEMBER_ID),
                        getIntent().getStringExtra(Constant.AGE),
                        getIntent().getStringExtra(Constant.NAME),
                        getIntent().getStringExtra(Constant.GENDER),
                        getIntent().getStringExtra(Constant.COMPANY),
                        getIntent().getStringExtra(Constant.REMARK),
                        getIntent().getStringExtra(Constant.CONDITION),
                        getIntent().getStringExtra(Constant.REQUEST),
                        currentDate,
                        valDate
                        , getIntent().getStringExtra(Constant.DOCTOR_WITH_PROVIDER),
                        getIntent().getStringExtra(Constant.DOCTOR_U),
                        getIntent().getStringExtra(Constant.DOCTOR_ROOM),
                        getIntent().getStringExtra(Constant.HOSP_CONTACT),
                        getIntent().getStringExtra(Constant.HOSP_CONTACT_PER),
                        getIntent().getStringExtra(Constant.HOSP_U)

                );
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.f_layout, fragment);
                fragmentTransaction.commit();
            } else {
                fragment = new ConsultationResult().newInstance(
                        getIntent().getExtras().getString(Constant.REFERENCECODE),
                        getIntent().getStringExtra(Constant.MEMBER_ID),
                        getIntent().getStringExtra(Constant.AGE),
                        getIntent().getStringExtra(Constant.NAME),
                        getIntent().getStringExtra(Constant.GENDER),
                        getIntent().getStringExtra(Constant.COMPANY),
                        getIntent().getStringExtra(Constant.REMARK),
                        getIntent().getStringExtra(Constant.CONDITION),

                        getIntent().getStringExtra(Constant.REQUEST),
                        currentDate,
                        valDate
                        , getIntent().getStringExtra(Constant.DOCTOR_WITH_PROVIDER),
                        getIntent().getStringExtra(Constant.DOCTOR_U),
                        getIntent().getStringExtra(Constant.DOCTOR_ROOM),
                        getIntent().getStringExtra(Constant.HOSP_CONTACT),
                        getIntent().getStringExtra(Constant.HOSP_CONTACT_PER),
                        getIntent().getStringExtra(Constant.HOSP_U)

                );
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.f_layout, fragment);
                fragmentTransaction.commit();
            }
        } else if (origin.equals(RequestButtonsActivity.MATERNITY)) {

            if (getIntent().getStringExtra(Constant.REQUEST).equals(Approved)) {
                fragment = new MaternityResult().newInstance(
                        getIntent().getExtras().getString(Constant.REFERENCECODE),
                        getIntent().getStringExtra(Constant.MEMBER_ID),
                        getIntent().getStringExtra(Constant.AGE),
                        getIntent().getStringExtra(Constant.NAME),
                        getIntent().getStringExtra(Constant.GENDER),
                        getIntent().getStringExtra(Constant.COMPANY),
                        getIntent().getStringExtra(Constant.REMARK),
                        getIntent().getStringExtra(Constant.CONDITION),

                        getIntent().getStringExtra(Constant.REQUEST),
                        currentDate, valDate,
                        getIntent().getStringExtra(Constant.DOCTOR_WITH_PROVIDER),
                        getIntent().getStringExtra(Constant.DOCTOR_U),
                        getIntent().getStringExtra(Constant.DOCTOR_ROOM),
                        getIntent().getStringExtra(Constant.HOSP_CONTACT),
                        getIntent().getStringExtra(Constant.HOSP_CONTACT_PER),
                        getIntent().getStringExtra(Constant.HOSP_U)

                );
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.f_layout, fragment);
                fragmentTransaction.commit();
            } else {
                fragment = new MaternityResult().newInstance(
                        getIntent().getExtras().getString(Constant.REFERENCECODE),
                        getIntent().getStringExtra(Constant.MEMBER_ID),
                        getIntent().getStringExtra(Constant.AGE),
                        getIntent().getStringExtra(Constant.NAME),
                        getIntent().getStringExtra(Constant.GENDER),
                        getIntent().getStringExtra(Constant.COMPANY),
                        getIntent().getStringExtra(Constant.REMARK),
                        getIntent().getStringExtra(Constant.CONDITION),

                        getIntent().getStringExtra(Constant.REQUEST),
                        currentDate, valDate,
                        getIntent().getStringExtra(Constant.DOCTOR_WITH_PROVIDER),
                        getIntent().getStringExtra(Constant.DOCTOR_U),
                        getIntent().getStringExtra(Constant.DOCTOR_ROOM),
                        getIntent().getStringExtra(Constant.HOSP_CONTACT),
                        getIntent().getStringExtra(Constant.HOSP_CONTACT_PER),
                        getIntent().getStringExtra(Constant.HOSP_U)

                );
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.f_layout, fragment);
                fragmentTransaction.commit();
            }
        }


        setUI(getIntent().getStringExtra(Constant.REQUEST), btn_click);
        btn_click.setVisibility(View.GONE);
        callback = (ScreenshotCallback) fragment;
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onScreenShotListener();
            }
        });

    }

    private void setUI(String stringExtra, FancyButton btn_click) {

        if (Approved.equals(stringExtra))
            btn_click.setVisibility(View.VISIBLE);
        else btn_click.setVisibility(View.GONE);
    }

}
