package modules.requestnewapproval;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.medicard.member.NavigationActivity;
import com.medicard.member.R;
import com.medicard.member.TermsActivity;
import com.medicard.member.core.model.DiagnosisTests;
import com.medicard.member.core.session.ConsultSession;
import com.medicard.member.core.session.DiagnosisTestSession;
import com.medicard.member.core.session.DoctorSession;
import com.medicard.member.core.session.HospitalSession;
import com.medicard.member.core.utilities.TransitionHelper;
import com.medicard.member.module.diagnosistest.TestByDiagnosisActivity;
import com.medicard.member.module.doctor.DoctorActivity;
import com.medicard.member.module.hospital.HospitalActivity;
import com.medicard.member.module.termsandcondition.TermsAndCondition;
import com.tapadoo.alerter.Alerter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import constants.MedicardConfig;
import model.Attachment;
import model.HospitalList;
import model.newtest.DiagnosisDetails;
import model.newtest.DiagnosisProcedure;
import model.newtest.NewTestRequest;
import modules.base.activities.TestTrackableActivity;
import modules.prescriptionattachment.adapter.AttachmentAdapter;
import modules.requestnewapproval.adapter.DiagnosisDetailsAdapter;
import services.model.HospitalsToDoctor;
import services.model.Test;
import timber.log.Timber;
import utilities.AlertDialogCustom;
import utilities.DeviceUtils;
import utilities.ErrorMessage;
import utilities.FileUtils;
import utilities.Loader;
import utilities.SharedPref;
import utilities.ViewUtilities;

/**
 * // todo request new approval
 */
public class RequestNewActivity extends TestTrackableActivity
        implements RequestNewMvp.View, AttachmentAdapter.OnAttachmentClickListener {
    public static final String ATTACHMENT = "attachment";
    public static final int SELECT_ATTACHMENT = 1200;

    public static final String FROM_DOCTOR = "fromDoctor";
    public static final int FROM_DOCTOR_REQUEST = 100;

    public static final String FROM_HOSPITAL = "fromHospital";
    public static final int FROM_HOSPITAL_REQUEST = 200;

    public static final String FROM_TEST = "fromTest";
    public static final int FROM_TEST_REQUEST = 300;

    @BindView(R.id.etReasonForConsult) EditText etReasonForConsult;

    // clickable view
    @BindView(R.id.cvRequestDoctor) CardView cvRequestDoctor;
    @BindView(R.id.cvHospitalClinicForAvailment) CardView cvHospitalClinicForAvailment;
    @BindView(R.id.cvDiagnosis) CardView cvDiagnosis;
    @BindView(R.id.ibDiagnosis) ImageButton ibDiagnosis;

    @BindView(R.id.tvConforme) TextView tvConforme;

    @BindView(R.id.tvDoctorDetails) TextView tvDoctorDetails;
    @BindView(R.id.tvHospitalAvailment) TextView tvHospitalAvailment;

    @BindView(R.id.rvAttachments) RecyclerView rvAttachments;
    @BindView(R.id.cbTermsAndCondition) CheckBox cbTermsAndCondition;

    @BindView(R.id.btnAddAttachment) Button btnAddAttachment;

    @BindView(R.id.btnSubmitNewRequest) Button btnSubmitNewRequest;
    @BindView(R.id.btnCancelNewRequest) Button btnCancelNewRequest;

    @BindView(R.id.rvDiagnosisDetails) RecyclerView rvDiagnosisDetails;

    private AttachmentAdapter attachmentAdapter;
    private DiagnosisDetailsAdapter diagnosisDetailsAdapter;

    private Gson gson;
    private RequestNewMvp.Presenter presenter;

    private String reasonForConsult;
    private ArrayList<Attachment> attachments;

    private List<DiagnosisProcedure> diagnosisProcedures;
    private List<DiagnosisDetails> diagnosisDetails;

    private List<DiagnosisTests> diagnosisTests;

    private HospitalsToDoctor doctor;
    private HospitalList hospital;

    private AlertDialogCustom alertDialog;

    Loader loader;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_request_new;
    }

    @Override
    protected void initViews() {
        super.initViews();
        Timber.d("device id %s", DeviceUtils.getDeviceId(this));
        Timber.d("android id %s", DeviceUtils.getAndroidId(this));

        loader = new Loader(context);

        gson = new Gson();

        setupWindowAnimations();
        ViewUtilities.hideToInvisibleView(getBackView());

        Type founderListType = new TypeToken<ArrayList<DiagnosisProcedure>>(){}.getType();

        attachments = new ArrayList<>();
        diagnosisProcedures = new ArrayList<>();
        diagnosisTests = new ArrayList<>();
        diagnosisDetails = new ArrayList<>();

//        String doctorJson = SharedPref.getPreferenceByKey(context, SharedPref.KEY_DOCTOR);
//        doctor = gson.fromJson(doctorJson, HospitalsToDoctor.class);
        doctor = DoctorSession.getDoctor();

//        String hospitalJson = SharedPref.getPreferenceByKey(context, SharedPref.KEY_HOSPITAL);
//        HospitalList hospital = gson.fromJson(hospitalJson, HospitalList.class);
        hospital = HospitalSession.getHospital();

//        String diagnosisProcedureJson = SharedPref.getPreferenceByKey(context, SharedPref.KEY_PROCEDURE_DIAGNOSIS);
//        diagnosisProcedures = gson.fromJson(diagnosisProcedureJson, founderListType);

//        reasonForConsult = SharedPref.getPreferenceByKey(context, SharedPref.KEY_REASON_FOR_CONSULT);
        reasonForConsult = ConsultSession.getReasonForConsult();

        alertDialog = new AlertDialogCustom();

        attachments = getIntent().getParcelableArrayListExtra(ATTACHMENT);

        presenter = new RequestNewPresenter(this);
        presenter.attachView(this);

        etReasonForConsult.setText(reasonForConsult);
        tvHospitalAvailment.setText(hospital.getHospitalName());
        tvDoctorDetails.setText(doctor.getFullName());
        tvDoctorDetails.append(getDoctorDetails(doctor));

        rvAttachments.setLayoutManager(new LinearLayoutManager(this));
        rvDiagnosisDetails.setLayoutManager(new LinearLayoutManager(this));

        Timber.d("the diagnosisProcedures ### %s", diagnosisProcedures.size());
//        presenter.loadDiagnosisTest(diagnosisProcedures);
        presenter.loadDiagnosisTests();
        /*if (attachments != null && attachments.size() > 0) {*/
            attachmentAdapter = new AttachmentAdapter(attachments, this);
            rvAttachments.setAdapter(attachmentAdapter);
        /*}*/

        cbTermsAndCondition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ViewUtilities.enableDisableView(RequestNewActivity.this, btnSubmitNewRequest, isChecked);
            }
        });

    }

    @OnClick(R.id.btnSubmitNewRequest)
    public void onSubmitNewRequst() {
        if (cbTermsAndCondition.isChecked()) {
            String memberCode = SharedPref.getPreferenceByKey(RequestNewActivity.this, SharedPref.MEMBERCODE);
//            String username = SharedPref.getPreferenceByKey(RequestNewActivity.this, SharedPref.masterUSERNAME);

            reasonForConsult = etReasonForConsult.getText().toString();

            if (reasonForConsult == null && reasonForConsult.length() <= 0) {
                etReasonForConsult.setFocusable(true);
                etReasonForConsult.setError("This field is required.");
            } else {


                loader.startLad();
                loader.setMessage("Sending request...");

                NewTestRequest newTestRequest = new NewTestRequest();
                newTestRequest.setRequestDevice(DeviceUtils.getAndroidId(RequestNewActivity.this));
                newTestRequest.setDiagnosisProcedures(convertObjectToDiagnosisProcedure(diagnosisTests));
                newTestRequest.setDoctorCode(doctor.getDoctorCode());
                newTestRequest.setHospitalCode(doctor.getHospitalCode());
                newTestRequest.setMemberCode(memberCode);
                newTestRequest.setPrimaryComplaint(reasonForConsult);
                newTestRequest.setPrimaryDiagnosisCode(diagnosisTests.get(0).getDiagnosis().getDiagCode());
                newTestRequest.setRequestOrigin(MedicardConfig.APP_NAME);

                Gson gson = new Gson();
                Timber.d("submit result %s", gson.toJson(newTestRequest));
                presenter.submitNewRequest(newTestRequest);
            }
        } else {
            Alerter.create(this)
                    .setTitle(R.string.opps)
                    .setText(R.string.accept_terms_and_conditions)
                    .setBackgroundColor(R.color.orange_a200)
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_ATTACHMENT) {
            Uri selectImageUri = data.getData();
            Timber.d("uri : %s", selectImageUri.toString());
            if (selectImageUri != null) {
                try {
                    Bitmap picture = MediaStore.Images.Media.getBitmap(context.getContentResolver(), selectImageUri);
                    File file = new File(FileUtils.getPathFromURI(context, FileUtils.getImageUri(this, picture)));

                    Timber.d("file name %s", file.getName());
                    try {
                        attachments.add(
                                new Attachment.Builder()
                                        .fileName(file.getName())
                                        .uri(selectImageUri.toString())
                                        .build());
                        attachmentAdapter.update(attachments);

                    } catch (Exception e) {
                        Timber.d("error message %s", e.toString());
                    }
                } catch (IOException e) {
                    Timber.d(e.toString());
                }
            }
        }

        if (resultCode == RESULT_OK && requestCode == FROM_DOCTOR_REQUEST) {
            doctor = DoctorSession.getDoctor();
            tvDoctorDetails.setText("");
            tvDoctorDetails.setText(doctor.getFullName());
            tvDoctorDetails.append(getDoctorDetails(doctor));
        }

        if (resultCode == RESULT_OK && requestCode == FROM_HOSPITAL_REQUEST) {
            hospital = HospitalSession.getHospital();
            tvHospitalAvailment.setText(hospital.getHospitalName());
        }

        if (resultCode == RESULT_OK && requestCode == FROM_TEST_REQUEST) {
            Timber.d("from test requet was called");
            presenter.loadDiagnosisTests();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.detachCallback();
    }

    @OnClick(R.id.btnCancelNewRequest)
    public void onCancelRequest() {
        AlertDialogCustom.alertNotification(this, "Alert", R.string.alert_cancel_new_request, new AlertDialogCustom.OnDialogClickListener() {
            @Override
            public void onOkClick() {
                cancelNewRequest();
            }

            @Override
            public void onCancelClick() {

            }
        });
    }

    @Override
    public void onBackPressed() {
        onCancelRequest();
    }

    private void cancelNewRequest() {
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        transitionTo(intent);
    }

    @OnClick(R.id.btnAddAttachment)
    public void onAddAttachment() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_ATTACHMENT);
    }

    @OnClick({R.id.cvDiagnosis, R.id.ibDiagnosis})
    public void onRequestTest(View which) {
        boolean displayAll = DiagnosisTestSession.isDisplayAll();
        if (displayAll) {
            Intent intent = new Intent(this, TestByDiagnosisActivity.class);
            intent.putExtra(FROM_TEST, true);
            transitionToResult(intent, FROM_TEST_REQUEST);
        }
    }

    @OnClick(R.id.cvRequestDoctor)
    public void onReselectDoctor() {
        Intent intent = new Intent(this, DoctorActivity.class);
        intent.putExtra(FROM_DOCTOR, true);
        transitionToResult(intent, FROM_DOCTOR_REQUEST);
    }

    @OnClick(R.id.cvHospitalClinicForAvailment)
    public void onHospitalReselected() {
        Intent intent = new Intent(this, HospitalActivity.class);
        intent.putExtra(FROM_HOSPITAL, true);
        transitionToResult(intent, FROM_HOSPITAL_REQUEST);
    }

    @OnClick(R.id.tvConforme)
    public void onClickTermsAndCondition() {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, false,
                new Pair<>(tvConforme, getString(R.string.tv_conforme)));
        startActivityWithTransition(TermsAndCondition.class, pairs);
//        startActivity(new Intent(this, TermsActivity.class));
    }

    private void startActivityWithTransition(Class target, Pair<View, String>[] pairs) {
        Intent i = new Intent(this, target);
        i.putExtra(TermsAndCondition.TITLE, "Terms and Condition");
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }

    @Override
    public void onRemoveAttachment(int position) {
        attachments.remove(position);
        attachmentAdapter.update(attachments);
    }

    @Override
    public void displayDoctorDetails(String doctorDetails) {

    }

    @Override
    public void displayDiagnosisDetails(List<DiagnosisDetails> diagnosisDetails) {
        // method is not use anymore
       /* this.diagnosisDetails = diagnosisDetails;

        diagnosisDetailsAdapter = new DiagnosisDetailsAdapter(diagnosisDetails);
        rvDiagnosisDetails.setAdapter(diagnosisDetailsAdapter);*/
    }

    @Override
    public void displayDiagnosisTests(List<DiagnosisTests> diagnosisTests) {
        this.diagnosisTests = diagnosisTests;
        diagnosisDetailsAdapter = new DiagnosisDetailsAdapter(diagnosisTests);
        rvDiagnosisDetails.setAdapter(diagnosisDetailsAdapter);
    }

    @Override
    public void onRequestError(String message) {
        loader.stopLoad();
        alertDialog.showMe(
                context, alertDialog.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onRequestSuccess() {
        loader.stopLoad();
        Timber.d("new request successfully submitted");
        cancelNewRequest();
    }

    private List<DiagnosisProcedure> convertObjectToDiagnosisProcedure(List<DiagnosisTests> diagnosisTests) {
        List<DiagnosisProcedure> diagnosisProcedures = new ArrayList<>();
        if (diagnosisTests.size() > 0) { // make sure that dianosis test is not zero
            for (DiagnosisTests diagnosisTest : diagnosisTests) { // loop all diagnosis
                for (Test test : diagnosisTest.getTests()) { // loop all the test
                    diagnosisProcedures.add(
                            new DiagnosisProcedure(
                                    test.getAmount(), // test amount
                                    diagnosisTest.getDiagnosis().getDiagCode(), // the diagnosis code
                                    test.getProcCode(), // the procedure code
                                    2)); // serve as OP-Test
                }
            }
        }

        return diagnosisProcedures;
    }

    public String getDoctorDetails(HospitalsToDoctor doctor) {
        return new StringBuilder(doctor.getFullName())
                .append("\n")
                .append(doctor.getHospitalName())
                .append("\n")
                .append(doctor.getSpecDesc())
                .toString();
    }

}
