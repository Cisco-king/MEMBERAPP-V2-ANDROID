package modules.requestnewapproval;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.pdf.codec.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import constants.MedicardConfig;
import model.Attachment;
import model.HospitalList;
import model.RequestResult;
import model.TestsModel;
import model.newtest.AttachmentSession;
import model.newtest.DiagnosisDetails;
import model.newtest.DiagnosisProcedure;
import model.newtest.NewTestRequest;
import modules.base.activities.TestTrackableActivity;
import modules.diagnosis.adapter.DiagnosisAdapter;
import modules.diagnosistest.adapter.DiagnosisTestAdapter;
import modules.prescriptionattachment.adapter.AttachmentAdapter;
import modules.requestnewapproval.adapter.DiagnosisDetailsAdapter;
import modules.requestnewapproval.adapter.OtherDiagnosisAdapter;
import services.model.Diagnosis;
import services.model.DiagnosisTest;
import services.model.DiagnosisTestRequest;
import services.model.HospitalsToDoctor;
import services.model.Test;
import services.response.MaceRequestResponse;
import timber.log.Timber;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.DateUtils;
import utilities.DeviceUtils;
import utilities.DiagnosisUtils;
import utilities.DialogUtils;
import utilities.ErrorMessage;
import utilities.FileUtils;
import utilities.GenderPicker;
import utilities.Loader;
import utilities.PermissionUtililities;
import utilities.SharedPref;
import utilities.StringUtilities;
import utilities.ViewUtilities;
import v2.DetailsActivity;
import v2.RequestButtonsActivity;
import v2.ResultActivity;

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
    private String encodedString;
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();


    private int tempCounter =  1; // Use for counting the Attachments

    DialogUtils.OnDiaglogClickListener listener;
    DialogUtils dialogutils;

    AttachmentSession attachmentSession;

    @BindView(R.id.etReasonForConsult)
    EditText etReasonForConsult;

    @BindView(R.id.ll_reasonForConsult)
    LinearLayout ll_reasonForConsult;
    @BindView(R.id.ll_otherDiagnosis)
    LinearLayout ll_otherDiagnosis;
    @BindView(R.id.ll_primaryDiagnosisResult)
    LinearLayout ll_primaryDiagnosisResult;
    @BindView(R.id.ll_doctorResult)
    LinearLayout ll_doctorResult;

    // clickable view
    @BindView(R.id.cvRequestDoctor)
    CardView cvRequestDoctor;
    @BindView(R.id.cvHospitalClinicForAvailment)
    CardView cvHospitalClinicForAvailment;
    @BindView(R.id.cvDiagnosis)
    CardView cvDiagnosis;
    @BindView(R.id.ibDiagnosis)
    ImageButton ibDiagnosis;

    @BindView(R.id.tvConforme)
    TextView tvConforme;

    @BindView(R.id.tvDoctorDetails)
    TextView tvDoctorDetails;
    @BindView(R.id.tvHospitalAvailment)
    TextView tvHospitalAvailment;

    @BindView(R.id.rvAttachments)
    RecyclerView rvAttachments;
    @BindView(R.id.cbTermsAndCondition)
    CheckBox cbTermsAndCondition;

    @BindView(R.id.btnAddAttachment)
    Button btnAddAttachment;

    @BindView(R.id.btnSubmitNewRequest)
    Button btnSubmitNewRequest;


    @BindView(R.id.rvDiagnosisDetails)
    RecyclerView rvDiagnosisDetails;

    @BindView(R.id.rvOtherDiagnosis)
    RecyclerView rvOtherDiagnosis;

    private AttachmentAdapter attachmentAdapter;
    private DiagnosisDetailsAdapter diagnosisDetailsAdapter;
    private OtherDiagnosisAdapter otherDiagnosisAdapter;

    private Gson gson;
    private RequestNewMvp.Presenter presenter;

    private String reasonForConsult;
    private ArrayList<Attachment> attachments;

    private List<DiagnosisProcedure> diagnosisProcedures;
    private List<DiagnosisDetails> diagnosisDetails;

    private List<DiagnosisTests> diagnosisTestsLists;

    private HospitalsToDoctor doctor;
    private HospitalList hospital;

    private AlertDialogCustom alertDialog;

    private ProgressDialog pd;


    //Loader loader;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_request_new;
    }

    @Override
    protected void initViews() {
        super.initViews();
//        Timber.d("device id %s", DeviceUtils.getDeviceId(this));
//        Timber.d("android id %s", DeviceUtils.getAndroidId(this));
//
        pd = new ProgressDialog(context,R.style.MyTheme);
        pd.setCancelable(false);
//
//
//        gson = new Gson();
//
//        setupWindowAnimations();
//        ViewUtilities.hideToInvisibleView(getBackView());
//
//        Type founderListType = new TypeToken<ArrayList<DiagnosisProcedure>>() {
//        }.getType();

        attachments = new ArrayList<>();
//        diagnosisProcedures = new ArrayList<>();
//        diagnosisTestsLists = new ArrayList<>();
//        diagnosisDetails = new ArrayList<>();

//        String doctorJson = SharedPref.getPreferenceByKey(context, SharedPref.KEY_DOCTOR);
//        doctor = gson.fromJson(doctorJson, HospitalsToDoctor.class);
//        doctor = DoctorSession.getDoctor();

//        String hospitalJson = SharedPref.getPreferenceByKey(context, SharedPref.KEY_HOSPITAL);
//        HospitalList hospital = gson.fromJson(hospitalJson, HospitalList.class);
        hospital = HospitalSession.getHospital();

//        String diagnosisProcedureJson = SharedPref.getPreferenceByKey(context, SharedPref.KEY_PROCEDURE_DIAGNOSIS);
//        diagnosisProcedures = gson.fromJson(diagnosisProcedureJson, founderListType);

//        reasonForConsult = SharedPref.getPreferenceByKey(context, SharedPref.KEY_REASON_FOR_CONSULT);
//        reasonForConsult = ConsultSession.getReasonForConsult();

        alertDialog = new AlertDialogCustom();

        attachments = getIntent().getParcelableArrayListExtra(ATTACHMENT);

        presenter = new RequestNewPresenter(this);
        presenter.attachView(this);


//        etReasonForConsult.setText(reasonForConsult);
//        ll_reasonForConsult.setVisibility(View.VISIBLE);
        tvHospitalAvailment.setText(hospital.getHospitalName());
//        tvDoctorDetails.setText(doctor.getFullName());
//        tvDoctorDetails.append(getDoctorDetails(doctor));

        rvAttachments.setLayoutManager(new LinearLayoutManager(this));
//        rvDiagnosisDetails.setLayoutManager(new LinearLayoutManager(this));
//        rvOtherDiagnosis.setLayoutManager(new LinearLayoutManager(this));

//        Timber.d("the diagnosisProcedures ### %s", diagnosisProcedures.size());
//        presenter.loadDiagnosisTest(diagnosisProcedures);
//        presenter.loadDiagnosisTests();
        /*if (attachments != null && attachments.size() > 0) {*/
        attachmentAdapter = new AttachmentAdapter(context,attachments, this);
        rvAttachments.setAdapter(attachmentAdapter);
        /*}*/

        cbTermsAndCondition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ViewUtilities.enableDisableView(RequestNewActivity.this, btnSubmitNewRequest, isChecked);
            }
        });

        attachmentSession = new AttachmentSession();

        //Hiding the cardviews in the app
        ll_doctorResult.setVisibility(View.GONE);
        ll_otherDiagnosis.setVisibility(View.GONE);
        ll_primaryDiagnosisResult.setVisibility(View.GONE);
        ll_reasonForConsult.setVisibility(View.GONE);

    }

    @OnClick(R.id.btnSubmitNewRequest)
    public void onSubmitNewRequst() {

        if (cbTermsAndCondition.isChecked()) {
            String memberCode = SharedPref.getPreferenceByKey(RequestNewActivity.this, SharedPref.MEMBERCODE);
            String username = SharedPref.getPreferenceByKey(RequestNewActivity.this, SharedPref.masterUSERNAME);


            if (attachments.isEmpty() && attachments.size() == 0) {
                rvAttachments.setFocusable(true);
                Alerter.create(this)
                        .setText("Please upload Attachment(s) to proceed.")
                        .setBackgroundColor(R.color.orange_a200)
                        .show();
                cbTermsAndCondition.setChecked(false);
            }


            final TestsModel newTestRequest = new TestsModel();
            try {
                newTestRequest.setHospitalCode(hospital.getHospitalCode());
                newTestRequest.setMemberCode(memberCode);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            gson.toJson(newTestRequest);

            if (!cbTermsAndCondition.isChecked()) {

                if (attachments.isEmpty() && attachments.size() == 0) {
                    Alerter.create(this)
                            .setText("Please upload Attachment(s) to proceed.")
                            .setBackgroundColor(R.color.orange_a200)
                            .show();
                }
            } else {
                final Dialog dialog = new Dialog(this.context);
                final Button btn_proceed, btn_cancel;

                TextView tv_title;
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_confirmloa);
                btn_proceed = (Button) dialog.findViewById(R.id.btn_proceed);
                btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);


                btn_proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        pd.setMessage("Submitting Request");
                        pd.setCancelable(false);
                        pd.show();
                        presenter.checkOnline(newTestRequest);

                    }
                });

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });


                dialog.show();

                DisplayMetrics metrics = this.context.getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;
                dialog.getWindow().setLayout(width, Toolbar.LayoutParams.WRAP_CONTENT);
            }
        } else {
            Timber.d("need to accept terms and conditions");
            Alerter.create(this)
                    .setTitle(R.string.opps)
                    .setText(R.string.accept_terms_and_conditions)
                    .setBackgroundColor(R.color.orange_a200)
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionUtililities.REQUESTCODE_MANAGE_DOCUMENT_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    submitNewTest();

                } else {
                    PermissionUtililities.ashForManageDocumentPermission(this);
                    Timber.d("permission denied");
                }
            }

            return;
        }
    }

    @Override
    public void internetConnected(TestsModel testsModelToPass) {

        presenter.requestForTests(testsModelToPass);
    }

    @Override
    public void noInternetConnection() {
        pd.dismiss();
        alertDialogCustom.showMe(context, alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);
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

                    ByteArrayOutputStream bao = new ByteArrayOutputStream();
                    picture.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                    byte[] ba = bao.toByteArray();

                    try {
                        encodedString = Base64.encodeBytes(ba, 0);
                    } catch (Exception e1) {
                    }

                    Timber.d("file name %s", file.getName());
                    try {
                        attachments.add(
                                new Attachment.Builder()
                                        .fileName(file.getName())
                                        .content(encodedString.toString())
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
        try {
            if (resultCode == RESULT_OK && requestCode == FROM_DOCTOR_REQUEST) {
                doctor = DoctorSession.getDoctor();
                tvDoctorDetails.setText("");
                tvDoctorDetails.setText(doctor.getFullName());
                tvDoctorDetails.append(getDoctorDetails(doctor));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (resultCode == RESULT_OK && requestCode == FROM_HOSPITAL_REQUEST) {
            hospital = HospitalSession.getHospital();
            tvHospitalAvailment.setText(hospital.getHospitalName());
        }
        try {
            if (resultCode == RESULT_OK && requestCode == FROM_TEST_REQUEST) {
                Timber.d("from test requet was called");
                presenter.loadDiagnosisTests();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        DiagnosisTestSession.releaseContent();
        transitionTo(intent);
    }

    @OnClick(R.id.btnAddAttachment)
    public void onAddAttachment() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_ATTACHMENT);
    }

    @OnClick(R.id.cvDiagnosis)
    public void onRequestTest() {
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
        this.diagnosisTestsLists = diagnosisTests;
        List<DiagnosisTests> diagTestsToPass = new ArrayList<>();
        List<DiagnosisTests> initialList = new ArrayList<>();
        initialList.add(diagnosisTests.get(0));
        diagnosisDetailsAdapter = new DiagnosisDetailsAdapter(initialList);
        rvDiagnosisDetails.setAdapter(diagnosisDetailsAdapter);

        if (!diagnosisTests.isEmpty())
            for (int i = 1; i < diagnosisTests.size(); i++) {
                diagTestsToPass.add(diagnosisTests.get(i));
            }
        displayOtherDiagnosis(diagTestsToPass);
    }

    public void displayOtherDiagnosis(List<DiagnosisTests> diagnosisTestsList) {
        if (diagnosisTestsList.isEmpty() && diagnosisTestsList.size() == 0) {
            ll_otherDiagnosis.setVisibility(View.GONE);
        } else {
            otherDiagnosisAdapter = new OtherDiagnosisAdapter(diagnosisTestsList);
            rvOtherDiagnosis.setAdapter(otherDiagnosisAdapter);
        }

    }

    @Override
    public void onRequestError(String message) {
        pd.dismiss();
        alertDialog.showMe(
                context, alertDialog.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
        attachmentSession.releaseContent();
    }

    @Override
    public void onRequestSuccess(MaceRequestResponse maceRequestResponse) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void gotoResult() {

        try {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(Constant.RVATTACHMENTS, attachments);
            Intent gotoResult = new Intent(this, ResultActivity.class);
            gotoResult.putExtra(Constant.BUNDLEFORATTACHEMENTS, bundle);
            gotoResult.putExtra(Constant.REQUEST, "PENDING");
            gotoResult.putExtra(Constant.BIRTHDAY, getIntent().getExtras().getString(Constant.BIRTHDAY));
            // gotoResult.putExtra(Constant.DOCTOR_WITH_PROVIDER, implement.setNull(data.getWithProvider()));
            gotoResult.putExtra(RequestButtonsActivity.ORIGIN, RequestButtonsActivity.TEST);
            gotoResult.putExtra(Constant.MEMBER_ID, getIntent().getExtras().getString(Constant.MEMBER_ID));
            gotoResult.putExtra(Constant.GENDER, getIntent().getExtras().getString(Constant.GENDER));
            gotoResult.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.NAME));
            gotoResult.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
            //gotoResult.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
            gotoResult.putExtra(Constant.AGE, getIntent().getExtras().getString(Constant.AGE));
            //    gotoResult.putExtra(Constant.CONDITION, tvPrimaryDiagnosisName.getText().toString());
            //gotoResult.putExtra(Constant.REASONFORCONSULT, reasonForConsult);
            // gotoResult.putExtra(Constant.PRIMARY_DIAGNOSIS, data.getData().getDiagnosisProcedures().get(0).getMaceRequestOpDiag().getDiagDesc());
            //gotoResult.putExtra(Constant.DOCTOR_U, doctor.getFullName());

//        gotoResult.putExtra(Constant.DOCTOR_ROOM, tv_spec.getText().toString());
//        gotoResult.putExtra(Constant.HOSP_CONTACT, tv_contact.getText().toString());
//        gotoResult.putExtra(Constant.HOSP_CONTACT_PER, tv_contact_person.getText().toString());
            gotoResult.putExtra(Constant.HOSP_U, hospital.getHospitalName());

          //  gotoResult.putExtra(Constant.BATCH_CODE, data.getBatchCode());

            startActivity(gotoResult);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private List<DiagnosisTest> convertObjectToDiagnosisTest(List<DiagnosisTests> diagnosisTests) {
        List<DiagnosisTest> container = new ArrayList<>();
        if (diagnosisTests.size() > 0) {
            for (DiagnosisTests diagnosisTest : diagnosisTests) { // loop all diagnosis
                for (Test test : diagnosisTest.getTests()) { // loop all the test
                    container.add(
                            new DiagnosisTest(
                                    diagnosisTest.getDiagnosis().getDiagCode(), test.getProcCode())); // serve as OP-Test
                }
            }
        }

        return container;
    }

    public String getDoctorDetails(HospitalsToDoctor doctor) {
        return new StringBuilder(doctor.getFullName())
                .append("\n")
                .append(doctor.getHospitalName())
                .append("\n")
                .append(doctor.getSpecDesc())
                .toString();
    }

    /*
        API used for the method requestLoaForTests
        expected return if successful (String requestCode)
        if null show a alertDialog
     */
    @Override
    public void onSuccessTestsResponse(String requestCode) {
        if (null == requestCode) {
            //TODO if null
        } else {
            int totalSize = attachmentSession.getAllAttachments().size() -1;
            for (int i = 0; i < attachmentSession.getAllAttachments().size(); i++) {
                if(i < attachmentSession.getAllAttachments().size() -1){
                    pd.setMessage("Uploading File(s) " + tempCounter + "/" + totalSize);
                    pd.setCancelable(false);
                    presenter.submitAttachments(attachmentSession.getAllAttachments().get(i), requestCode);
                    tempCounter++;
                }else{
                    presenter.submitFinalAttachment(attachmentSession.getAllAttachments().get(i),requestCode);
                }

            }
        }
    }

     /*
        API used for the method addAttachmentByRequestCode
        expected return if successful (int attachmentId)
        if null show a alertDialog
     */

    @Override
    public void onSuccessAttachmentResponse() {
        try {
            pd.dismiss();
            gotoResult();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void submitNewTest() {
        String memberCode = SharedPref.getPreferenceByKey(RequestNewActivity.this, SharedPref.MEMBERCODE);
        String username = SharedPref.getPreferenceByKey(RequestNewActivity.this, SharedPref.masterUSERNAME);

        reasonForConsult = etReasonForConsult.getText().toString();

        DiagnosisTestRequest testRequest = new DiagnosisTestRequest.Builder()
                .consultationReason(reasonForConsult)
                .consultationDate(DateUtils.getCurrentDate())
                .requestDeviceId(DeviceUtils.getAndroidId(this))
                .diagnosisTests(convertObjectToDiagnosisTest(diagnosisTestsLists))
                .doctorCode(doctor.getDoctorCode())
                .hospitalCode(hospital.getHospitalCode())
                .memberCode(memberCode)
                .requestBy(username)
                .primaryDiagnosisCode(diagnosisTestsLists.get(0).getDiagnosis().getDiagCode())
                .requestOrigin(MedicardConfig.APP_NAME)
                .build();

        Gson gson = new Gson();
        Timber.d("submit result %s", gson.toJson(testRequest));
        pd.setMessage("Submitting request...");
        pd.show();

        presenter.submitTestRequest(testRequest, attachments);
    }

}
