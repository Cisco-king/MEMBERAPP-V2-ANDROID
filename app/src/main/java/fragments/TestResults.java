package fragments;

import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.BoolRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicard.member.AccountActivity;
import com.medicard.member.NavigationActivity;
import com.medicard.member.R;
import com.medicard.member.core.model.DiagnosisTests;
import com.medicard.member.core.session.DiagnosisTestSession;
import com.medicard.member.core.session.DoctorSession;
import com.medicard.member.core.session.HospitalSession;
import com.medicard.member.module.DiagnosisTallyActivity.fragment.DiagnosisMVP;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import InterfaceService.ScreenshotCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import constants.OutPatientConsultationForm;
import constants.FileGenerator;
import constants.PatientLaboratoryForm;
import model.Attachment;
import model.HospitalList;
import model.newtest.AttachmentSession;
import modules.prescriptionattachment.adapter.AttachmentAdapter;
import modules.prescriptionattachment.adapter.AttachmentForTestAdapter;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.model.HospitalsToDoctor;
import services.model.Test;
import timber.log.Timber;
import utilities.AgeCorrector;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.DateConverter;
import utilities.FileUtils;
import utilities.GenderPicker;
import utilities.ImageSaver;
import utilities.Loader;
import utilities.PdfGenerator;
import utilities.Permission;
import utilities.PermissionUtililities;
import utilities.QrCodeCreator;
import utilities.ResultSetters;
import utilities.Screenshot;
import utilities.SharedPref;
import utilities.ViewUtilities;
import v2.LoaPageActivity;
import v2.RequestButtonsActivity;

public class TestResults extends Fragment implements ScreenshotCallback {

    @BindView(R.id.sv_whole)
    ScrollView sv_whole;

    @BindView(R.id.btn_shot)
    TextView btn_shot;

    @BindView(R.id.ll_primaryDiagnosis)
    LinearLayout ll_primaryDiagnosis;
    @BindView(R.id.ll_doctor_details)
    LinearLayout ll_doctor_details;
    @BindView(R.id.ll_disapproved_req)
    LinearLayout ll_disapproved_req;
    @BindView(R.id.ll_pending)
    LinearLayout ll_pending;
    @BindView(R.id.ll_approved_validity)
    LinearLayout ll_approved_validity;
    @BindView(R.id.ll_approved_req)
    LinearLayout ll_approved_req;
    @BindView(R.id.ll_ref_code_details)
    LinearLayout ll_ref_code_details;
    @BindView(R.id.ll_approved_validity_date)
    LinearLayout ll_approved_validity_date;
    @BindView(R.id.ll_approved_effective_date)
    LinearLayout ll_approved_effective_date;
    @BindView(R.id.ll_hospital_details)
    LinearLayout ll_hospital_details;
    @BindView(R.id.tv_date_approved)
    TextView tv_date_approved;
    @BindView(R.id.tv_ref_code)
    TextView tv_ref_code;
    @BindView(R.id.tv_date_requested)
    TextView tv_date_requested;
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
    @BindView(R.id.tv_remarks)
    TextView tv_remarks;
    @BindView(R.id.tv_hospName)
    TextView tv_hospName;
    @BindView(R.id.tv_sub_title)
    TextView tv_sub_title;
    @BindView(R.id.tv_hospAddress)
    TextView tv_hospAddress;
    @BindView(R.id.tv_doc_name)
    TextView tv_doc_name;
    @BindView(R.id.tv_doc_det)
    TextView tv_doc_det;
    @BindView(R.id.tv_validity_date)
    TextView tv_validity_date;
    @BindView(R.id.tv_effective_date)
    TextView tv_effective_date;
    @BindView(R.id.ll_all_MEMDETAILS)
    LinearLayout ll_all_MEMDETAILS;


    @BindView(R.id.tv_condition)
    TextView tv_condition;
    @BindView(R.id.tv_primaryDiagnosis)
    TextView tv_primaryDiagnosis;
    @BindView(R.id.tv_date)
    TextView tv_date;

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_doc_app)
    TextView tv_doc_app;

    @BindView(R.id.tv_disclaimer)
    TextView tv_disclaimer;

    @BindView(R.id.tvWithAppUser2)
    TextView tvWithAppUser2;

    @BindView(R.id.tv_ref_code2)
    TextView tv_ref_code2;

    @BindView(R.id.btn_ok)
    Button btn_ok;

    @BindView(R.id.img_qrcode)
    ImageView img_qrcode;

    @BindView(R.id.tv_contact_person)
    TextView tv_contact_person;
    @BindView(R.id.tv_contact)
    TextView tv_contact;
    @BindView(R.id.tv_testNameResult)
    TextView tv_testNameResult;

    @BindView(R.id.tv_sched)
    TextView tv_sched;
    @BindView(R.id.tv_spec)
    TextView tv_spec;
    @BindView(R.id.tv_sched_doctor)
    TextView tv_sched_doctor;
    @BindView(R.id.tv_bday)
    TextView tv_bday;
    @BindView(R.id.rvAttachments)
    RecyclerView rvAttachments;

    //Updated August 13, 2017
    @BindView(R.id.cvRequestPhysician)
    CardView cvRequestPhysician;
    @BindView(R.id.cardView3)
    CardView cardView3;
    @BindView(R.id.cardViewTest)
    CardView cardViewTest;


    AttachmentForTestAdapter attachmentAdapter;
    Fragment fragment;
    GenderPicker genderPicker = new GenderPicker();

    private PatientLaboratoryForm.Builder patientLaboratoryBuilder;

    Context context;
    ///  TextView tv_contact_person, tv_sched, tv_contact, tv_spec, tv_sched_doctor;

    private static final String ARG_refCode = "refCode";
    private static final String ARG_memId = "memId";
    private static final String ARG_age = "age";
    private static final String ARG_BDAY = "BDAY";
    private static final String ARG_name = "name";
    private static final String ARG_gender = "gender";
    private static final String ARG_company = "company";
    private static final String ARG_remark = "remark";
    private static final String ARG_condition = "condition";
    private static final String ARG_request = "request";
    private static final String ARG_reqDate = "reqDate";
    private static final String ARG_valDate = "valDate";
    private static final String ARG_withProvider = "wWithProvider";
    private static final String ARG_DOCTORU = "DOCTOR_U";
    private static final String ARG_HOSPU = "HOSP_U";
    private static final String ARG_approved = "aproved";
    private static final String KEY_BATCH_CODE = "batchCode";
    private static final String ARG_ATTACHMENTS = "attachments";
    private static final String ARG_REASONFORCONSULT = "reasonForConsult";
    private static final String ARG_DIAGNOSISTESTS = "DIAGNOSISTESTS";

    String refCode;
    String memId;
    String age;
    String name;
    String gender;
    String company;
    String remark;
    String condition;
    String request;
    String reqDate;
    String valDate;
    String withProvider;
    String doctorU;
    String hospU;
    String batchCode;
    String bday;
    ArrayList<Attachment> attachments;
    String reasonForConsult;
    List<DiagnosisTests> diagnosisTestses;

    QrCodeCreator qrCodeCreator = new QrCodeCreator();
    ScreenshotCallback callback;
    AlertDialogCustom alertDialogCustom;
    Loader loader;
    HospitalsToDoctor doctor;
    HospitalList hospital;

    public TestResults() {
        // Required empty public constructor
    }


    public static TestResults newInstance(String getMemId,
                                          String getAge, String getName,
                                          String getGender, String getCompany,
                                          String getRequest,
                                          String getReqDate, String getValDate,
                                          String hosp_u,
                                          String bday, ArrayList<Attachment> attachmentArrayList

    ) {
        TestResults fragment = new TestResults();
        Bundle args = new Bundle();
        //args.putString(ARG_refCode, getRefCode);
        args.putString(ARG_memId, getMemId);
        args.putString(ARG_age, getAge);
        args.putString(ARG_name, getName);
        args.putString(ARG_gender, getGender);
        args.putString(ARG_company, getCompany);
        //  args.putString(ARG_remark, getRemark);
        //args.putString(ARG_condition, getCondition);
        args.putString(ARG_request, getRequest);
        args.putString(ARG_valDate, getValDate);
        args.putString(ARG_reqDate, getReqDate);
        //       args.putString(ARG_withProvider, getWithProvider);
        //args.putString(ARG_DOCTORU, doctor_u);
        args.putString(ARG_HOSPU, hosp_u);
        //args.putString(KEY_BATCH_CODE, batchCode);
        args.putString(ARG_BDAY, bday);
        args.putParcelableArrayList(ARG_ATTACHMENTS, attachmentArrayList);
        //args.putString(ARG_REASONFORCONSULT, getReasonForConsult);
        //args.putSerializable(ARG_DIAGNOSISTESTS, (Serializable) diagnosisTestsList);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //refCode = getArguments().getString(ARG_refCode);
            memId = getArguments().getString(ARG_memId);
            age = getArguments().getString(ARG_age);
            name = getArguments().getString(ARG_name);
            gender = getArguments().getString(ARG_gender);
            company = getArguments().getString(ARG_company);
            remark = getArguments().getString(ARG_remark);
            //condition = getArguments().getString(ARG_condition);
            request = getArguments().getString(ARG_request);
            valDate = getArguments().getString(ARG_valDate);
            reqDate = getArguments().getString(ARG_reqDate);
            //withProvider = getArguments().getString(ARG_withProvider);
            //doctorU = getArguments().getString(ARG_DOCTORU);
            hospU = getArguments().getString(ARG_HOSPU);
            //batchCode = getArguments().getString(KEY_BATCH_CODE);
            bday = getArguments().getString(ARG_BDAY);
            attachments = getArguments().getParcelableArrayList(ARG_ATTACHMENTS);
            //reasonForConsult = getArguments().getString(ARG_REASONFORCONSULT);
            //diagnosisTestses = (List<DiagnosisTests>) getArguments().getSerializable(ARG_DIAGNOSISTESTS);
            //doctor = DoctorSession.getDoctor();
            hospital = HospitalSession.getHospital();
        }

        callback = this;
        alertDialogCustom = new AlertDialogCustom();
        loader = new Loader(context);

        // init here

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_result, container, false);
        ButterKnife.bind(this, view);
        init();

        return view;
    }

    private void init() {
        context = getContext();
        // todo button ok change functionality
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogCustom.successDialog(context,
                        alertDialogCustom.CONGRATULATIONS_title,
                        alertDialogCustom.SAVE_LOA_REQUEST,
                        2,
                        new AlertDialogCustom.OnDialogClickListener() {
                            @Override
                            public void onOkClick() {

                                DiagnosisTestSession.releaseContent();
                                AttachmentSession.releaseContent();
                                Intent intent = new Intent(context, NavigationActivity.class);
                                startActivity(intent);
                            }


                            @Override
                            public void onCancelClick() {

                            }
                        });
            }
        });
        setDetails();

    }

    private void setDetails() {

//        tv_contact_person.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT_PERSON, context));
//        tv_contact.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT, context));
//        tv_sched.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_U, context));
//        tv_spec.setText(ResultSetters.specSetter(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_ROOM, context)));
//        tv_sched_doctor.setText(ResultSetters.schedSetter(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_U, context)));


//         tv_ref_code.setText("Reference No: " + refCode);
//        tv_ref_code2.setText(refCode);
        tv_hospName.append(getHospitalDetails(hospital));
//        tv_hospAddress.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_ADD, context));
        //      tv_hospAddress.setText(SharedPref.getPreferenceByKey(context, SharedPref.KEY_HOSPITAL_FULL_ADDRESS));

        //     tv_doc_det.setText(ResultSetters.descSetter(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_DESC, context)));
//        tv_doc_name.setText(doctor.getFullName());
//        tv_doc_name.append(getDoctorDetails(doctor));

        tv_validity_date.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.VAL_DATE, context));
        tv_effective_date.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.EFF_DATE, context));
        tv_member_code.setText(memId);
        tv_age.setText(age);
        tv_member_name.setText(name);
        tv_gender.setText(genderPicker.setGender((Integer.parseInt(gender))));
        tv_company.setText(company);
//        tv_primaryDiagnosis.setText(condition);
        //      tv_remarks.setText(remark);
        //    tv_condition.setText(condition);


        //  tv_testNameResult.setText(diagnosisTestses.get(0).getTests().get(0).getProcedureName());

        tv_bday.setText(bday);

        tv_title.setText(ResultSetters.titleSetter(request));
        tv_date_approved.setText(reqDate);

        //img_qrcode.setImageBitmap(qrCodeCreator.getBitmapFromString(refCode));

        rvAttachments.setLayoutManager(new LinearLayoutManager(getContext()));
        attachmentAdapter = new AttachmentForTestAdapter(attachments);
        rvAttachments.setAdapter(attachmentAdapter);

        tv_date_requested.setText(reqDate);
        tv_date.setText(valDate);


        if (tv_title.getText().toString().trim().equals(ResultSetters.REQUEST_TEST_APPROVED)) {
            setApproved();
        } else if (tv_title.getText().toString().trim().equals(ResultSetters.REQUEST_DISAPPROVED)) {
            setDisapproved();
        } else if (tv_title.getText().toString().trim().equals(ResultSetters.REQUEST_APPROVAL)) {
            setPending();
        }

        int position = valDate.indexOf("to");
        Timber.d("sxz %s", valDate);
        String validFrom = valDate.substring(0, position - 1);
        String validUntil = valDate.substring(position + 2);


        Timber.d("position %s, validFrom %s, validUntil %s", position, validFrom, validUntil);
//
//        String remarkTemp = remark.replace("\n", ", ").replace("\r", "");

        patientLaboratoryBuilder = new PatientLaboratoryForm.Builder()
                .validFrom(validFrom)
                .validUntil(validUntil)
                //              .referenceNumber(refCode)
//                .doctor(doctor.getFullName())
                .hospital(hospital.getHospitalName())
                .memberName(name)
                .age(AgeCorrector.age(SharedPref.getStringValue(SharedPref.USER, SharedPref.AGE, getActivity())))
                .gender(GenderPicker.setGender(Integer.parseInt(SharedPref.getStringValue(SharedPref.USER, SharedPref.GENDER, getActivity()))))
                .company(company)
                //  .remarks(remarkTemp)
                .serviceType(FileGenerator.TEST)
                .validityDate(SharedPref.getStringValue(SharedPref.USER, SharedPref.VAL_DATE, context))
                .dateEffectivity(SharedPref.getStringValue(SharedPref.USER, SharedPref.EFF_DATE, context));
        //            .diagnosis(condition)
        //          .procedure(diagnosisTestses.get(0).getTests().get(0).getProcedureName());


//        ResultSetters.setDoctorWithProvider(withProvider, tv_doc_app);

       /* if (withProvider.equals(ResultSetters.WITHPROVIDER)) {
            ViewUtilities.hideView(tvWithAppUser2);
            tv_disclaimer.setText(getString(R.string.doctor_with_app));
        } else {
            ViewUtilities.showView(tvWithAppUser2);
            tvWithAppUser2.setText(getString(R.string.doctor_without_app2));
            tv_disclaimer.setText(getString(R.string.doctor_without_app));
        }*/
    }

    public String getDoctorDetails(HospitalsToDoctor doctor) {
        return new StringBuilder(doctor.getFullName())
                .append("\n\n")
                .append(doctor.getHospitalName())
                .append("\n\n")
                .append(doctor.getSpecDesc())
                .toString();
    }

    public String getHospitalDetails(HospitalList hospital) {
        return new StringBuilder(hospital.getHospitalName())
                .append("\n\n")
                .append(hospital.getFullAddress())
                .append("\n\n")
                .append(hospital.getPhoneNo())
                .toString();
    }


    private void setPending() {

        ll_disapproved_req.setVisibility(View.GONE);
        ll_approved_req.setVisibility(View.GONE);
        ll_ref_code_details.setVisibility(View.GONE);
        ll_pending.setVisibility(View.VISIBLE);
        cvRequestPhysician.setVisibility(View.GONE);
        cardView3.setVisibility(View.GONE);
        cardViewTest.setVisibility(View.GONE);
        btn_shot.setVisibility(View.GONE);

//        tv_disclaimer.setVisibility(View.GONE);
        //   img_qrcode.setVisibility(View.GONE);
        ll_approved_validity.setVisibility(View.GONE);
        ll_all_MEMDETAILS.setVisibility(View.VISIBLE);
        ll_hospital_details.setVisibility(View.VISIBLE);
        tv_sub_title.setVisibility(View.VISIBLE);
        ll_ref_code_details.setVisibility(View.GONE);
        ll_doctor_details.setVisibility(View.GONE);
        ll_primaryDiagnosis.setVisibility(View.GONE);
        tv_sub_title.setText(Constant.SUBTITLEPENDING);

//        if (withProvider.equals(ResultSetters.WITHPROVIDER)) {
//            tv_disclaimer.setText(getString(R.string.doctor_with_app));
//        } else {
//            tv_disclaimer.setText(getString(R.string.doctor_without_app));
//        }
    }

    private void setDisapproved() {
        ll_approved_effective_date.setVisibility(View.GONE);
        ll_approved_validity_date.setVisibility(View.GONE);
        ll_approved_validity.setVisibility(View.GONE);
        ll_disapproved_req.setVisibility(View.GONE);
        ll_approved_req.setVisibility(View.GONE);
        ll_ref_code_details.setVisibility(View.GONE);
    }

    private void setApproved() {
        ll_approved_effective_date.setVisibility(View.VISIBLE);
        ll_approved_validity_date.setVisibility(View.VISIBLE);
        ll_disapproved_req.setVisibility(View.GONE);
        ll_approved_validity.setVisibility(View.VISIBLE);
        ll_approved_req.setVisibility(View.VISIBLE);
        ll_ref_code_details.setVisibility(View.VISIBLE);
        tv_sub_title.setText(Constant.SUBTITLEAPPROVED);


    }


    @OnClick(R.id.btn_shot)
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_shot:


                Bitmap bitmap = Screenshot.loadBitmapFromView(sv_whole);

                // todo download loa
                if (Permission.checkPermissionStorage(context)) {
                    btn_ok.setVisibility(View.GONE);
                    btn_shot.setVisibility(View.GONE);

                    /*new ImageSaver(context).
                            setFileName(refCode + "_Consultation.jpg").
                            setDirectoryName("MediCard")
                            .setExternal(false)
                            .save(bitmap, callback);*/

                    PatientLaboratoryForm build = patientLaboratoryBuilder.build();
                    if (FileUtils.fileExistance(build.getServiceType(), build.getReferenceNumber())) {
                        onShowNotifyExistingPdfDialog(build.getServiceType(), build.getReferenceNumber());
                    } else {
                        generateLoaForm(patientLaboratoryBuilder.build(), getResources().openRawResource(R.raw.test_form));
                    }

                }

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

                    generateLoaForm(patientLaboratoryBuilder.build(), getResources().openRawResource(R.raw.test_form));

                } else {
                    Timber.d("permission denied");
                }
            }

            return;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onScreenShotListener() {
        Log.d("TRIGGERED", "TRIGGERED");

        if (Permission.checkPermissionStorage(context)) {
            btn_shot.setVisibility(View.GONE);
            btn_ok.setVisibility(View.GONE);
            Bitmap bitmap = Screenshot.loadBitmapFromView(sv_whole);
            new ImageSaver(context).
                    setFileName(refCode + "_Consultation.jpg").
                    setDirectoryName("MediCard")
                    .setExternal(false)
                    .save(bitmap, callback);
        } else {
            btn_shot.setVisibility(View.VISIBLE);
            btn_ok.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSuccessfulScreenshot() {
        btn_ok.setVisibility(View.VISIBLE);
        btn_shot.setVisibility(View.VISIBLE);

        alertDialogCustom.showMe(getActivity(), alertDialogCustom.CONGRATULATIONS_title, alertDialogCustom.Saved_Screenshot, 2);
    }

    public void generateLoaForm(final PatientLaboratoryForm patientLaboratoryForm, final InputStream stream) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {

            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    PdfGenerator.generatePdfLoaLabForm(
                            patientLaboratoryForm, stream);
                    subscriber.onNext(Boolean.TRUE);
                } catch (Exception e) {
                    Timber.d("error %s", e.toString());
                    subscriber.onNext(Boolean.FALSE);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("completed process");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("error message %s", e.toString());

                    }

                    @Override
                    public void onNext(Boolean success) {
                        Timber.d("onNext");
                        if (success) {
                            onGenerateLoaFormSuccess();
                        } else {

                        }
                    }
                });
    }

    public void onGenerateLoaFormSuccess() {
        btn_ok.setVisibility(View.VISIBLE);
        btn_shot.setVisibility(View.VISIBLE);
        PatientLaboratoryForm build = patientLaboratoryBuilder.build();
        onShowNotifyExistingPdfDialog(build.getServiceType(), build.getReferenceNumber());
        /*alertDialogCustom.showMe(
                context,
                alertDialogCustom.CONGRATULATIONS_title,
                alertDialogCustom.LOA_GENERATE_PDF_SUCCESS,
                2);*/
    }

    public void showLoaForm(Context context, String serviceType, String referenceNumber) {

        try {
            File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM), "MediCard");

            String loaFileName = FileGenerator.genFileName(serviceType, referenceNumber);
            File loaFormFile = new File(pdfFolder, loaFileName + ".pdf");

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(loaFormFile), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No PDF Viewer Installed", Toast.LENGTH_LONG).show();
        }
    }

    public void showLoaFormNoServicetype(Context context, String referenceNumber) {

        try {
            File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM), "MediCard");

            String loaFileName = FileGenerator.genFileNameNoServiceType(referenceNumber);
            File loaFormFile = new File(pdfFolder, loaFileName + ".pdf");

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(loaFormFile), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No PDF Viewer Installed", Toast.LENGTH_LONG).show();
        }
    }

    public void onShowNotifyExistingPdfDialog(final String serviceType, final String referenceNumber) {
        btn_ok.setVisibility(View.VISIBLE);
        btn_shot.setVisibility(View.VISIBLE);
        alertDialogCustom.showMe(context, alertDialogCustom.CONGRATULATIONS_title, alertDialogCustom.LOA_GENERATE_PDF_SUCCESS, 2,
                new AlertDialogCustom.OnCustomDialogClickListener() {
                    @Override
                    public void onOkClick() {
                        Timber.d("on ok with cancel functionality was clicked");
                    }

                    @Override
                    public void onViewPdf() {
                        showLoaForm(context, serviceType, referenceNumber);
                    }
                });
    }


}
