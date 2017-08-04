package v2;

import android.content.Context;

import com.medicard.member.R;
import com.medicard.member.core.model.DiagnosisTests;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import InterfaceService.ScreenshotCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import fragments.ConsultationResult;
import fragments.MaternityResult;
import fragments.TestResults;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Attachment;
import model.newtest.DiagnosisDetails;
import modules.requestnewapproval.RequestNewMvp;
import modules.requestnewapproval.RequestNewPresenter;
import services.model.DiagnosisTest;
import services.model.MaceRequest;
import services.response.MaceRequestResponse;
import timber.log.Timber;
import utilities.Constant;
import utilities.DateAddThreeDays;
import utilities.HeaderNameSetter;
import utilities.SharedPref;

import static com.medicard.member.module.diagnosis.DiagnosisActivity.diagnosisBundle;

public class ResultActivity extends AppCompatActivity implements RequestNewMvp.View {

    private String Approved = "APPROVED";
    private String Pending = "PENDING";


    FragmentTransaction fragmentTransaction;
    private RequestNewMvp.Presenter presenter;
    Fragment fragment = null;

    List<DiagnosisTests> diagnosisTestsList = new ArrayList<>();
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

        presenter = new RequestNewPresenter(this);
        presenter.attachView(this);

        presenter.loadDiagnosisTests();


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
                        getIntent().getStringExtra(Constant.HOSP_U),
                        getIntent().getStringExtra(Constant.BATCH_CODE)

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
                        getIntent().getStringExtra(Constant.HOSP_U),
                        getIntent().getStringExtra(Constant.BATCH_CODE)

                );
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.f_layout, fragment);
                fragmentTransaction.commit();
            }
        } else if (origin.equals(RequestButtonsActivity.MATERNITY)) {

            if (getIntent().getStringExtra(Constant.REQUEST).equals(Approved)) {

                Timber.d("reference number : %s", getIntent().getExtras().getString(Constant.REFERENCECODE));

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
                        getIntent().getStringExtra(Constant.HOSP_U),
                        getIntent().getStringExtra(Constant.BATCH_CODE)

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
                        getIntent().getStringExtra(Constant.HOSP_U),
                        getIntent().getStringExtra(Constant.BATCH_CODE)

                );
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.f_layout, fragment);
                fragmentTransaction.commit();
            }
        } else if (origin.equals(RequestButtonsActivity.TEST)) {
            System.out.println("========================= TRUE");
            if (getIntent().getStringExtra(Constant.REQUEST).equalsIgnoreCase(Approved)) {
                System.out.println("========================= TRUE");
                Bundle args = getIntent().getBundleExtra(Constant.BUNDLEFORATTACHEMENTS);
                ArrayList<Attachment> attachments = args.getParcelableArrayList(Constant.RVATTACHMENTS);
                List<DiagnosisTests> diagnosisTests = (List<DiagnosisTests>) args.getSerializable(Constant.TESTSRESULTS);
                fragment = new TestResults().newInstance(
                        getIntent().getExtras().getString(Constant.REFERENCECODE),
                        getIntent().getStringExtra(Constant.MEMBER_ID),
                        getIntent().getStringExtra(Constant.AGE),
                        getIntent().getStringExtra(Constant.NAME),
                        getIntent().getStringExtra(Constant.GENDER),
                        getIntent().getStringExtra(Constant.COMPANY),
                        getIntent().getStringExtra(Constant.PRIMARY_DIAGNOSIS),
                        getIntent().getStringExtra(Constant.REQUEST),
                        currentDate, valDate,
                        getIntent().getStringExtra(Constant.DOCTOR_U),
                        getIntent().getStringExtra(Constant.HOSP_U),
                        getIntent().getStringExtra(Constant.BATCH_CODE),
                        getIntent().getStringExtra(Constant.BIRTHDAY),
                        attachments,
                        getIntent().getStringExtra(Constant.REASONFORCONSULT),
                        diagnosisTests
                );
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.f_layout, fragment);
                fragmentTransaction.commit();
            } else if (getIntent().getStringExtra(Constant.REQUEST).equalsIgnoreCase(Pending)) {
                Bundle args = getIntent().getBundleExtra(Constant.BUNDLEFORATTACHEMENTS);
                ArrayList<Attachment> attachments = args.getParcelableArrayList(Constant.RVATTACHMENTS);
                List<DiagnosisTests> diagnosisTests = (List<DiagnosisTests>) args.getSerializable(Constant.TESTSRESULTS);
                System.out.println("========================= PENDING");
                fragment = new TestResults().newInstance(
                        getIntent().getExtras().getString(Constant.REFERENCECODE),
                        getIntent().getStringExtra(Constant.MEMBER_ID),
                        getIntent().getStringExtra(Constant.AGE),
                        getIntent().getStringExtra(Constant.NAME),
                        getIntent().getStringExtra(Constant.GENDER),
                        getIntent().getStringExtra(Constant.COMPANY),
                        getIntent().getStringExtra(Constant.PRIMARY_DIAGNOSIS),
                        getIntent().getStringExtra(Constant.REQUEST),
                        currentDate, valDate,
                        getIntent().getStringExtra(Constant.DOCTOR_U),
                        getIntent().getStringExtra(Constant.HOSP_U),
                        getIntent().getStringExtra(Constant.BATCH_CODE),
                        getIntent().getStringExtra(Constant.BIRTHDAY),
                        attachments,
                        getIntent().getStringExtra(Constant.REASONFORCONSULT),
                        diagnosisTests
                );
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.f_layout, fragment);
                fragmentTransaction.commit();
            } else {
                Bundle args = getIntent().getBundleExtra(Constant.BUNDLEFORATTACHEMENTS);
                ArrayList<Attachment> attachments = args.getParcelableArrayList(Constant.RVATTACHMENTS);
                List<DiagnosisTests> diagnosisTests = (List<DiagnosisTests>) args.getSerializable(Constant.TESTSRESULTS);
                System.out.println("========================= DISAPPROVED");
                fragment = new TestResults().newInstance(
                        getIntent().getExtras().getString(Constant.REFERENCECODE),
                        getIntent().getStringExtra(Constant.MEMBER_ID),
                        getIntent().getStringExtra(Constant.AGE),
                        getIntent().getStringExtra(Constant.NAME),
                        getIntent().getStringExtra(Constant.GENDER),
                        getIntent().getStringExtra(Constant.COMPANY),
                        getIntent().getStringExtra(Constant.PRIMARY_DIAGNOSIS),
                        getIntent().getStringExtra(Constant.REQUEST),
                        currentDate, valDate,
                        getIntent().getStringExtra(Constant.DOCTOR_U),
                        getIntent().getStringExtra(Constant.HOSP_U),
                        getIntent().getStringExtra(Constant.BATCH_CODE),
                        getIntent().getStringExtra(Constant.BIRTHDAY),
                        attachments,
                        getIntent().getStringExtra(Constant.REASONFORCONSULT),
                        diagnosisTests

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


    @Override
    public void displayDoctorDetails(String doctorDetails) {

    }

    @Override
    public void displayDiagnosisDetails(List<DiagnosisDetails> diagnosisDetails) {

    }

    @Override
    public void displayDiagnosisTests(List<DiagnosisTests> diagnosisTests) {
        this.diagnosisTestsList = diagnosisTests;
    }

    @Override
    public void onRequestError(String message) {

    }

    @Override
    public void onRequestSuccess(MaceRequestResponse maceRequestResponse) {

    }

}
