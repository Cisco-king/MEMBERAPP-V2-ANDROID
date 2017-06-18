package modules.requestnewapproval;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.medicard.member.R;

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
import timber.log.Timber;
import utilities.DeviceUtils;
import utilities.FileUtils;
import utilities.SharedPref;
import utilities.ViewUtilities;

/**
 * // todo request new approval
 */
public class RequestNewActivity extends TestTrackableActivity
        implements RequestNewMvp.View, AttachmentAdapter.OnAttachmentClickListener {
    public static final String ATTACHMENT = "attachment";
    public static final int SELECT_ATTACHMENT = 1200;

    @BindView(R.id.etReasonForConsult) EditText etReasonForConsult;

    @BindView(R.id.cvRequestDoctor) CardView cvRequestDoctor;
    @BindView(R.id.tvDoctorDetails) TextView tvDoctorDetails;

    @BindView(R.id.cvHospitalClinicForAvailment) CardView cvHospitalClinicForAvailment;
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

    private HospitalsToDoctor doctor;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_request_new;
    }

    @Override
    protected void initViews() {
        super.initViews();

        Timber.d("device id %s", DeviceUtils.getDeviceId(this));
        Timber.d("android id %s", DeviceUtils.getAndroidId(this));

        gson = new Gson();

        Type founderListType = new TypeToken<ArrayList<DiagnosisProcedure>>(){}.getType();

        attachments = new ArrayList<>();
        diagnosisProcedures = new ArrayList<>();

        diagnosisDetails = new ArrayList<>();

        String doctorJson = SharedPref.getPreferenceByKey(context, SharedPref.KEY_DOCTOR);
        doctor = gson.fromJson(doctorJson, HospitalsToDoctor.class);

        String hospitalJson = SharedPref.getPreferenceByKey(context, SharedPref.KEY_HOSPITAL);
        HospitalList hospital = gson.fromJson(hospitalJson, HospitalList.class);

        String diagnosisProcedureJson = SharedPref.getPreferenceByKey(context, SharedPref.KEY_PROCEDURE_DIAGNOSIS);
        diagnosisProcedures = gson.fromJson(diagnosisProcedureJson, founderListType);

        reasonForConsult = SharedPref.getPreferenceByKey(context, SharedPref.KEY_REASON_FOR_CONSULT);
        attachments = getIntent().getParcelableArrayListExtra(ATTACHMENT);

        presenter = new RequestNewPresenter(this);
        presenter.attachView(this);

        etReasonForConsult.setText(reasonForConsult);

        tvDoctorDetails.setText(doctor.getFullName());
        tvDoctorDetails.append(getDoctorDetails(doctor));

        tvHospitalAvailment.setText(hospital.getHospitalName());

        rvAttachments.setLayoutManager(new LinearLayoutManager(this));
        rvDiagnosisDetails.setLayoutManager(new LinearLayoutManager(this));

        Timber.d("the diagnosisProcedures ### %s", diagnosisProcedures.size());
        presenter.loadDiagnosisTest(diagnosisProcedures);

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

            NewTestRequest newTestRequest = new NewTestRequest();
            newTestRequest.setRequestDevice(DeviceUtils.getAndroidId(RequestNewActivity.this));
            newTestRequest.setDiagnosisProcedures(diagnosisProcedures);
            newTestRequest.setDoctorCode(doctor.getDoctorCode());
            newTestRequest.setHospitalCode(doctor.getHospitalCode());
            newTestRequest.setMemberCode(memberCode);
            newTestRequest.setPrimaryComplaint(reasonForConsult);
            newTestRequest.setPrimaryDiagnosisCode(diagnosisProcedures.get(0).getDiagnosisCode());
            newTestRequest.setRequestOrigin(MedicardConfig.APP_NAME);

            Gson gson = new Gson();
            Timber.d("submit result %s", gson.toJson(newTestRequest));
        } else {
            Timber.d("terms and condition didn't accept yet");
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.detachCallback();
    }

    @OnClick(R.id.btnAddAttachment)
    public void onAddAttachment() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_ATTACHMENT);
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
        this.diagnosisDetails = diagnosisDetails;

        diagnosisDetailsAdapter = new DiagnosisDetailsAdapter(diagnosisDetails);
        rvDiagnosisDetails.setAdapter(diagnosisDetailsAdapter);
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
