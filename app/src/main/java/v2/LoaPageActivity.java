package v2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.medicard.member.R;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import InterfaceService.LoaPageInterface;
import InterfaceService.LoaPageRetieve;
import InterfaceService.ScreenshotCallback;
import Sqlite.DatabaseHandler;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import constants.OutPatientConsultationForm;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Doctor;
import model.GetUSER;
import model.HospitalList;
import model.LoaFetch;
import model.MemberInfo;
import timber.log.Timber;
import utilities.AgeCorrector;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.DateConverter;
import utilities.ErrorMessage;
import utilities.GenderPicker;
import utilities.Loader;
import utilities.NetworkTest;
import utilities.PermissionUtililities;
import utilities.QrCodeCreator;
import utilities.SharedPref;
import utilities.ViewUtilities;
import v2.module.loapage.LoaPage;
import v2.module.loapage.LoaPagePresenter;

public class LoaPageActivity extends AppCompatActivity
        implements LoaPage.View, ScreenshotCallback, LoaPageInterface {


    public static final String REFERENCE_NUMBER = "Reference No : ";

    @BindView(R.id.content_loa_page)
    ScrollView content_loa_page;

    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_approval_code)
    TextView tv_approval_code;
    @BindView(R.id.tv_member_code)
    TextView tv_member_code;
    @BindView(R.id.tv_member_name)
    TextView tv_member_name;
    @BindView(R.id.tv_age)
    TextView tv_age;
    @BindView(R.id.tv_gender)
    TextView tv_gender;
    @BindView(R.id.tv_company)
    TextView tv_company;
    @BindView(R.id.tv_date_approved)
    TextView tv_date_approved;

    @BindView(R.id.tv_doc_name)
    TextView tv_doc_name;
    @BindView(R.id.tv_spec)
    TextView tv_spec;

    @BindView(R.id.tv_diagnosis)
    TextView tv_diagnosis;

    @BindView(R.id.tv_hospname)
    TextView tv_hospname;

    @BindView(R.id.tv_procedure)
    TextView tv_procedure;
    @BindView(R.id.tv_validity_date)
    TextView tv_validity_date;

    @BindView(R.id.tv_problem)
    TextView tv_problem;

    @BindView(R.id.tv_header)
    TextView tv_header;


    @BindView(R.id.btn_download)
    FancyButton btn_download;
    @BindView(R.id.btn_cancel_req)
    FancyButton btn_cancel_req;
    @BindView(R.id.btn_cancel)
    FancyButton btn_cancel;

    @BindView(R.id.ivQrApprovalNumber)
    ImageView ivQrApprovalNumber;
    @BindView(R.id.tvReferenceNumber)
    TextView tvReferenceNumber;

    @BindView(R.id.tvValidityDate)
    TextView tvValidityDate;
    @BindView(R.id.tvEffectiveDate)
    TextView tvEffectiveDate;
    @BindView(R.id.tvDateApproved)
    TextView tvDateApproved;
    @BindView(R.id.tvRemarks)
    TextView tvRemakrs;

    @BindView(R.id.tvHospitalClinicName)
    TextView tvHospitalClinicName;
    @BindView(R.id.tvHopitalClinicLocation)
    TextView tvHopitalClinicLocation;
    @BindView(R.id.tvHopitalClinicContacts)
    TextView tvHopitalClinicContacts;
    @BindView(R.id.tvHopitalClinicDoctorName)
    TextView tvHopitalClinicDoctorName;

    @BindView(R.id.tvDoctorName)
    TextView tvDoctorName;
    @BindView(R.id.tvDoctorInfo)
    TextView tvDoctorInfo;

    @BindView(R.id.tvServiceType)
    TextView tvServiceType;

    @BindView(R.id.cvHospitalClinic) CardView cvHospitalClinic;

    @BindView(R.id.tvDisclaimerInfo) TextView tvDisclaimerInfo;

    private int RESULT_GETTER;
    int position;
    ArrayList<LoaFetch> loaList = new ArrayList<>();
    Context context;
    ScreenshotCallback screenshotCallback;
    LoaFetch loa;
    AlertDialogCustom alertDialogCustom;
    LoaPageInterface callback;
    LoaPageRetieve implement;
    Loader loader;

    private OutPatientConsultationForm outPatientForm;
    OutPatientConsultationForm.Builder loaFormBuilder;

    private LoaPagePresenter presenter;
    private GetUSER userInformation;

    String serviceType;

    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loa_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        context = this;
        screenshotCallback = this;
        callback = this;

        dbHandler = new DatabaseHandler(context);

        loader = new Loader(context);

        implement = new LoaPageRetieve(context, callback);
        loader = new Loader(context);
        alertDialogCustom = new AlertDialogCustom();

        position = Integer.parseInt(getIntent().getStringExtra(Constant.POSITION));
        ArrayList<LoaFetch> temp;
        temp = getIntent().getParcelableArrayListExtra(Constant.DATA_SEARCHED);
        loaList.addAll(temp);
        temp.clear();

        presenter = new LoaPagePresenter();
        presenter.attachView(this);

        if (NetworkTest.isOnline(this)) {

            String memberId = SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, this);
            Log.d("UserInformationxxx", "onCreate: " + memberId);

            presenter.initUserInformation(
                    SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, this)
            );
        } else {
            alertDialogCustom.showMe(
                    this,
                    alertDialogCustom.NO_Internet_title,
                    alertDialogCustom.NO_Internet, 1);
        }


        init(loaList, position);

    }

    private void init(ArrayList<LoaFetch> loaList, int position) {

        loa = loaList.get(position);

        serviceType = "( " + loa.getRemarks() + " )";

        /*loader.startLad();
        loader.setMessage("Loading...");*/
//        presenter.requestDoctorByCode(loa.getDoctorCode());
        String changeFormat = DateConverter.convertDatetoMMMddyyy(loa.getApprovalDate());
        // todo init lao form data

        HospitalList hospital =
                dbHandler.getHospitalContact(loa.getHospitalCode());

        Timber.d("serviceType : %s approval code : %s batchCode %s", loa.getRemarks(), loa.getApprovalNo(), loa.getBatchCode());


        loaFormBuilder = new OutPatientConsultationForm.Builder()
                .validFrom(DateConverter.convertDateToMMddyyyy(changeFormat))
                .validUntil(DateConverter.validityDatePLusDay(changeFormat, 3))
                .dateOfConsult(changeFormat)
                .referenceNumber(loa.getApprovalNo())
                .doctor(loa.getDoctorName())
                .hospital(hospital.getHospitalName())
                .memberName(loa.getMemFname() + " " + loa.getMemLname())
                .age(AgeCorrector.age(SharedPref.getStringValue(SharedPref.USER, SharedPref.AGE, this)))
                .gender(GenderPicker.setGender(Integer.parseInt(SharedPref.getStringValue(SharedPref.USER, SharedPref.GENDER, this))))
                .memberId(loa.getMemberCode())
                .company(loa.getMemCompany())
                .remarks(loa.getRemarks())
                .chiefComplaint(loa.getPrimaryComplaint())
                .serviceType(loa.getRemarks())
                .bactchCode(loa.getBatchCode());


        ivQrApprovalNumber.setImageBitmap(QrCodeCreator.getBitmapFromString2(loa.getApprovalNo()));
        tvReferenceNumber.setText(REFERENCE_NUMBER.concat(loa.getApprovalNo()));

        implement.setExpiredStatus(btn_download, btn_cancel_req, loa.getStatus());
        tv_header.setText(loa.getRemarks());
        tv_status.setText(implement.getStatus(loa.getStatus()));
        tv_approval_code.setText(loa.getApprovalNo());
        tv_member_code.setText(loa.getMemberCode());
        tv_member_name.setText(loa.getMemFname() + " " + loa.getMemLname());
        tv_age.setText(AgeCorrector.age(SharedPref.getStringValue(SharedPref.USER, SharedPref.AGE, this)));
        tv_gender.setText(GenderPicker.setGender(Integer.parseInt(
                SharedPref.getStringValue(SharedPref.USER, SharedPref.GENDER, this))));
        tv_company.setText(loa.getMemCompany());

        tv_date_approved.setText(DateConverter.convertDatetoMMMddyyy(loa.getApprovalDate())); // view is invisible

        /*tvValidityDate.setText(changeFormat);
        tvEffectiveDate.setText(DateConverter.validityDatePLusDay(changeFormat, 3));
        tvDateApproved.setText(DateConverter.convertDatetoMMMddyyy(loa.getApp));*/
//        tvEffectiveDate.setText();
        tvDateApproved.setText(changeFormat);

        tv_doc_name.setText(loa.getDoctorName());
        tv_problem.setText(loa.getPrimaryComplaint());

        tvServiceType.setText(serviceType);


        tv_validity_date.setText(
                getString(R.string.this_req_is_valid_from).concat("\n" +
                        DateConverter.convertDateToMMddyyyy(changeFormat) + " to " +
                        DateConverter.validityDatePLusDay(changeFormat, 3)));

        tv_spec.setText(testData(loa.getDoctorSpec()));

        if (hospital.getHospitalName() != null && !hospital.getHospitalName().isEmpty()) {
            cvHospitalClinic.setVisibility(View.VISIBLE);
            tvHospitalClinicName.setText(hospital.getHospitalName());
            tvHopitalClinicLocation.setText(hospital.getFullAddress());
            tvHopitalClinicContacts.setText(hospital.getPhoneNo());
            tvHopitalClinicDoctorName.setText(hospital.getContactPerson());
        } else {
            cvHospitalClinic.setVisibility(View.GONE);
        }

        tvDoctorName.setText(loa.getDoctorName());

        String doctorInfo = new StringBuilder()
                .append(loa.getDoctorSpec())
                .append(!(loa.getRoom() == null || loa.getRoom().equals("null")) ? "\n\n" + loa.getRoom() : "")
                .append(!(loa.getSchedule() == null || loa.getSchedule().equals("null")) ? "\n\n" + loa.getSchedule() : "")
                .toString();

        Timber.d("doctorSpec %s, loaRoom %s, loaSchedule %s", loa.getDoctorSpec(), loa.getRoom(), loa.getSchedule());

        if (!doctorInfo.trim().isEmpty()) {
            tvDoctorInfo.setText(doctorInfo);
        } else {
            tvDoctorInfo.setVisibility(View.GONE);
        }

        if (isCancelledORExpired(loa.getStatus())) {
            ViewUtilities.hideView(tvDisclaimerInfo);
        } else {
            // todo with provider
            ViewUtilities.showView(tvDisclaimerInfo);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick({R.id.btn_download, R.id.btn_cancel_req, R.id.btn_cancel})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_download:
                screenshotCallback.onScreenShotListener();
                break;

            case R.id.btn_cancel:
                Intent intent = new Intent();
                setResult(RESULT_GETTER, intent);
                finish();
                break;

            case R.id.btn_cancel_req:
                implement.showCancelConfirmation();
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Timber.d("request code for storate %s permission response %s", PermissionUtililities.READ_WRITE_PERMISSION, requestCode);
        switch (requestCode) {
            case PermissionUtililities.REQUESTCODE_STORAGE_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    presenter.generateLoaForm(
                            loaFormBuilder.build(),
                            getResources().openRawResource(R.raw.loa_consultation_form));
                } else {
                    Timber.d("permission denied");
                }
            }

            return;
        }
    }

    private String testData(String doctorSpec) {
        return doctorSpec.equals("") ? "Specialization Not Specified" : doctorSpec;
    }


    @Override
    public void onScreenShotListener() {
        Log.d("TRIGGERED", "TRIGGERED");
        // todo generate pdf form
//        if (Permission.checkPermissionStorage(context)) {
        if (PermissionUtililities.hasPermissionToReadAndWriteStorage(this)) {
            generateLoaConsultationForm();

            /*Bitmap bitmap = Screenshot.loadBitmapFromView(content_loa_page);
            new ImageSaver(context).
                    setFileName(loa.getApprovalNo()
                            + "_" + loa.getRemarks() + ".jpg")
                    .setDirectoryName("MediCard")
                    .setExternal(false)
                    .save(bitmap, screenshotCallback);*/
        }
    }

    private void generateLoaConsultationForm() {
        btn_download.setVisibility(View.GONE);
        btn_cancel.setVisibility(View.GONE);
        btn_cancel_req.setVisibility(View.GONE);

        String text = tvRemakrs.getText().toString();
        text = text.replace("\n", ", ").replace("\r", "");
        loaFormBuilder.remarks(text);
        presenter.generateLoaForm(
                loaFormBuilder.build(),
                getResources().openRawResource(R.raw.loa_consultation_form));
    }

    @Override
    public void onSuccessfulScreenshot() {
        btn_cancel.setVisibility(View.VISIBLE);
        btn_cancel_req.setVisibility(View.VISIBLE);
        btn_download.setVisibility(View.VISIBLE);

        alertDialogCustom.showMe(context, alertDialogCustom.CONGRATULATIONS_title, alertDialogCustom.Saved_Screenshot, 2);
    }

    @Override
    public void onError(String message) {
        Log.e("ERROR", message);
        loader.stopLoad();
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
        RESULT_GETTER = implement.setToLoadList(false);
    }

    @Override
    public void noInternet() {
        loader.stopLoad();
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.NO_Internet, 1);
        RESULT_GETTER = implement.setToLoadList(false);
    }

    @Override
    public void onSuccess() {
        tv_status.setText("REQUEST CANCELLED");
        btn_download.setVisibility(View.GONE);
        btn_cancel_req.setVisibility(View.GONE);
        tvDisclaimerInfo.setVisibility(View.GONE);

        loader.stopLoad();
        alertDialogCustom.showMe(context, alertDialogCustom.success, alertDialogCustom.data_cancelled, 2);
        RESULT_GETTER = implement.setToLoadList(true);
    }

    @Override
    public void onCancelRequestListener() {
        loader.startLad();
        loader.setMessage("Cancelling Request");
        implement.cancelRequest(loa.getBatchCode());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_GETTER, intent);
        finish();
    }

    @Override
    public void userInformation(GetUSER getUSER) {
        Log.d("UserInformationxxx", "userInformation: " + getUSER.getMemberInfo().getVAL_DATE());

        MemberInfo memberInfo = getUSER.getMemberInfo();

        tvValidityDate.setText(memberInfo.getVAL_DATE());
        tvEffectiveDate.setText(memberInfo.getEFF_DATE());

        loaFormBuilder.validityDate(memberInfo.getVAL_DATE())
                .dateEffectivity(memberInfo.getEFF_DATE());
    }

    @Override
    public void displayRemarks(String remark) {
        tvRemakrs.append(remark + "\n");
    }

    @Override
    public void onNetworkError() {
        Log.d("UserInformationxxx", "onNetworkError:");
//        loader.stopLoad();
    }

    @Override
    public void displayDoctor(Doctor doctor) {
        Log.d("testtesttest", "displayDoctor: " + doctor.getSpecDesc());
//        loader.stopLoad();
//        tvDoctorName.setText(loa.getDoctorName());
//        String doctorInfo = new StringBuilder()
//                .append(doctor.getSpecDesc())
//                .append(!(doctor.getRoomNo() == null || doctor.getRoomNo().equals("null"))
//                        ? "\n\n" + doctor.getRoomNo() : "")
//                .append(!(doctor.getSchedule() == null || doctor.getSchedule().equals("null"))
//                        ? "\n\n" + doctor.getSchedule() : "")
//                .toString();
//
//        tvDoctorInfo.setText(doctorInfo);
    }

    @Override
    public void onGenerateLoaFormSuccess() {
        btn_cancel.setVisibility(View.VISIBLE);
        btn_cancel_req.setVisibility(View.VISIBLE);
        btn_download.setVisibility(View.VISIBLE);

        alertDialogCustom.showMe(
                context,
                alertDialogCustom.CONGRATULATIONS_title,
                alertDialogCustom.LOA_GENERATE_PDF_SUCCESS,
                2);
    }

    @Override
    public void onGenerateLoaFormError() {
        Timber.d("generated loa form encounter an error!");
    }

    private boolean isCancelledORExpired(String loaStatus) {
        return (loaStatus.equals(Constant.CANCELLED) || loaStatus.equals(Constant.EXPIRED));
    }

}
