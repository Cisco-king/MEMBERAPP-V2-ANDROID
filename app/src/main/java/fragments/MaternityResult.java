package fragments;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicard.member.R;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;

import InterfaceService.ScreenshotCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import constants.OutPatientConsultationForm;
import constants.FileGenerator;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import utilities.AgeCorrector;
import utilities.AlertDialogCustom;
import utilities.FileUtils;
import utilities.GenderPicker;
import utilities.PdfGenerator;
import utilities.Permission;
import utilities.PermissionUtililities;
import utilities.QrCodeCreator;
import utilities.ResultSetters;
import utilities.SharedPref;
import utilities.ViewUtilities;

public class MaternityResult extends Fragment implements ScreenshotCallback {

    @BindView(R.id.sv_whole)
    ScrollView sv_whole;

    @BindView(R.id.tv_validity_date)
    TextView tv_validity_date;
    @BindView(R.id.tv_effective_date)
    TextView tv_effective_date;
    @BindView(R.id.btn_shot)
    TextView btn_shot;

    @BindView(R.id.ll_approved_validity_date)
    LinearLayout ll_approved_validity_date;
    @BindView(R.id.ll_approved_effective_date)
    LinearLayout ll_approved_effective_date;

    @BindView(R.id.tvWithAppUser) TextView tvWithAppUser;
    @BindView(R.id.tvWithAppUser2) TextView tvWithAppUser2;


    LinearLayout ll_disapproved1, ll_disapproved3, ll_disapproved2;
    TextView tv_ref_code, tv_date_requested, tv_member_code, tv_member_name, tv_age, tv_gender, tv_company, tv_remarks;
    TextView tv_hospName, tv_hospAddress;
    TextView tv_doc_name, tv_doc_det, tv_contact_person, tv_sched, tv_contact, tv_spec, tv_sched_doctor;
    TextView tv_disapproved, tv_doc_app;
    TextView tv_condition, tv_date, tv_header, tv_title;
    Button btn_ok;
    Context context;
    QrCodeCreator qrCodeCreator = new QrCodeCreator();
    ImageView img_qrcode;
    ScreenshotCallback callback;
    AlertDialogCustom alertDialogCustom;

    private OutPatientConsultationForm.Builder loaFormBuilder;

    private static final String ARG_refCode = "refCode";
    private static final String ARG_memId = "memId";
    private static final String ARG_age = "age";
    private static final String ARG_name = "name";
    private static final String ARG_gender = "gender";
    private static final String ARG_company = "company";
    private static final String ARG_remark = "remark";
    private static final String ARG_condition = "condition";
    private static final String ARG_request = "request";
    private static final String ARG_reqDate = "reqDate";
    private static final String ARG_valDate = "valDate";
    private static final String ARG_withProvider = "wWithProvider";
    private static final String KEY_BATCH_CODE = "batchCode";

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

    String batchCode;

    public MaternityResult() {
        // Required empty public constructor
    }

    public static MaternityResult newInstance(String getRefCode, String getMemId,
                                              String getAge, String getName,
                                              String getGender, String getCompany,
                                              String getRemark, String getCondition,
                                              String getRequest, String getReqDate,
                                              String getValDate, String getWithProvider,
                                              String doctor_u, String doc_room, String hosp_contact,
                                              String hosp_contact_per, String hosp_u, String batchCode) {
        MaternityResult fragment = new MaternityResult();
        Bundle args = new Bundle();
        args.putString(ARG_refCode, getRefCode);
        args.putString(ARG_memId, getMemId);
        args.putString(ARG_age, getAge);
        args.putString(ARG_name, getName);
        args.putString(ARG_gender, getGender);
        args.putString(ARG_company, getCompany);
        args.putString(ARG_remark, getRemark);
        args.putString(ARG_condition, getCondition);
        args.putString(ARG_request, getRequest);
        args.putString(ARG_valDate, getValDate);
        args.putString(ARG_reqDate, getReqDate);
        args.putString(ARG_withProvider, getWithProvider);
        args.putString(KEY_BATCH_CODE, batchCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Timber.d("refCode %s", getArguments().getString(ARG_refCode));

            refCode = getArguments().getString(ARG_refCode);
            memId = getArguments().getString(ARG_memId);
            age = getArguments().getString(ARG_age);
            name = getArguments().getString(ARG_name);
            gender = getArguments().getString(ARG_gender);
            company = getArguments().getString(ARG_company);
            remark = getArguments().getString(ARG_remark);
            condition = getArguments().getString(ARG_condition);
            request = getArguments().getString(ARG_request);
            valDate = getArguments().getString(ARG_valDate);
            reqDate = getArguments().getString(ARG_reqDate);
            withProvider = getArguments().getString(ARG_withProvider);

            batchCode = getArguments().getString(KEY_BATCH_CODE);
        }

        alertDialogCustom = new AlertDialogCustom();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maternity_result, container, false);
        ButterKnife.bind(this, view);
        callback = this;
        init(view);
        return view;
    }

    private void init(View view) {

        context = getContext();
        tv_header = (TextView) view.findViewById(R.id.tv_header);
        tv_title = (TextView) view.findViewById(R.id.tv_title);

        ll_disapproved3 = (LinearLayout) view.findViewById(R.id.ll_disapproved3);
        ll_disapproved2 = (LinearLayout) view.findViewById(R.id.ll_disapproved2);
        ll_disapproved1 = (LinearLayout) view.findViewById(R.id.ll_disapproved1);

        img_qrcode = (ImageView) view.findViewById(R.id.img_qrcode);
        tv_contact_person = (TextView) view.findViewById(R.id.tv_contact_person);
        tv_contact = (TextView) view.findViewById(R.id.tv_contact);
        tv_sched = (TextView) view.findViewById(R.id.tv_sched);
        tv_spec = (TextView) view.findViewById(R.id.tv_spec);
        tv_sched_doctor = (TextView) view.findViewById(R.id.tv_sched_doctor);

        tv_disapproved = (TextView) view.findViewById(R.id.tv_disapproved);
        tv_ref_code = (TextView) view.findViewById(R.id.tv_ref_code);
        tv_date_requested = (TextView) view.findViewById(R.id.tv_date_requested);
        tv_member_code = (TextView) view.findViewById(R.id.tv_member_code);
        tv_doc_app = (TextView) view.findViewById(R.id.tv_doc_app);
        tv_member_name = (TextView) view.findViewById(R.id.tv_member_name);
        tv_age = (TextView) view.findViewById(R.id.tv_age);
        tv_gender = (TextView) view.findViewById(R.id.tv_gender);
        tv_company = (TextView) view.findViewById(R.id.tv_company);
        tv_remarks = (TextView) view.findViewById(R.id.tv_remarks);

        tv_hospName = (TextView) view.findViewById(R.id.tv_hospName);
        tv_hospAddress = (TextView) view.findViewById(R.id.tv_hospAddress);

        tv_doc_name = (TextView) view.findViewById(R.id.tv_doc_name);
        tv_doc_det = (TextView) view.findViewById(R.id.tv_doc_det);

        tv_condition = (TextView) view.findViewById(R.id.tv_condition);
        tv_date = (TextView) view.findViewById(R.id.tv_date);

        int position = valDate.indexOf("to");
        Timber.d("sxz %s", valDate);
        String validFrom = valDate.substring(0, position - 1);
        String validUntil = valDate.substring(position + 2);

        Timber.d("position %s, validFrom %s, validUntil %s", position, validFrom, validUntil);

        String remarkTemp = remark.replace("\n", ", ").replace("\r", "");

        loaFormBuilder = new OutPatientConsultationForm.Builder()
                .validFrom(validFrom)
                .validUntil(validUntil)
                .dateOfConsult(reqDate)
                .referenceNumber(refCode)
                .doctor(ResultSetters.nameSetter(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_NAME, context), context))
                .hospital(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_NAME, context))
                .memberName(name)
                .age(AgeCorrector.age(SharedPref.getStringValue(SharedPref.USER, SharedPref.AGE, getActivity())))
                .gender(GenderPicker.setGender(Integer.parseInt(SharedPref.getStringValue(SharedPref.USER, SharedPref.GENDER, getActivity()))))
                .memberId(memId)
                .company(company)
                .chiefComplaint(condition)
                .remarks(remarkTemp)
                .validityDate(SharedPref.getStringValue(SharedPref.USER, SharedPref.VAL_DATE, context))
                .dateEffectivity(SharedPref.getStringValue(SharedPref.USER, SharedPref.EFF_DATE, context))
                .serviceType(FileGenerator.MATERNITY_CONSULTATION)
                .bactchCode(batchCode);

        btn_ok = (Button) view.findViewById(R.id.btn_ok);

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
                                getActivity().finish();
                            }

                            @Override
                            public void onCancelClick() {

                            }
                        });
//                getActivity().finish();
            }
        });
        setDetails();

    }

    private void setDetails() {
        img_qrcode.setVisibility(View.VISIBLE);
        img_qrcode.setImageBitmap(qrCodeCreator.getBitmapFromString(refCode));

        tv_contact_person.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT_PERSON, context));
        tv_contact.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT, context));
        tv_sched.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_U, context));
        tv_spec.setText(ResultSetters.specSetter(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_ROOM, context)));
        tv_sched_doctor.setText(ResultSetters.schedSetter(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_U, context)));

        tv_ref_code.setText("Reference No: " + refCode);
        tv_hospName.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_NAME, context));
//        tv_hospAddress.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_ADD, context));
        tv_hospAddress.setText(SharedPref.getPreferenceByKey(context, SharedPref.KEY_HOSPITAL_FULL_ADDRESS));

        tv_doc_det.setText(ResultSetters.descSetter(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_DESC, context)));
        tv_doc_name.setText(ResultSetters.nameSetter(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_NAME, context), context));

        tv_validity_date.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.VAL_DATE, context));
        tv_effective_date.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.EFF_DATE, context));

        tv_member_code.setText(memId);
        tv_age.setText(age);
        tv_member_name.setText(name);
        tv_gender.setText(gender);
        tv_company.setText(company);
        tv_remarks.setText(remark);
        tv_condition.setText(condition);

        tv_title.setText(ResultSetters.titleSetter(request));


        tv_date_requested.setText(reqDate);
        tv_date.setText(valDate);
        if (tv_title.getText().toString().trim().equals(ResultSetters.REQUEST_APPROVED)) {
            ll_approved_effective_date.setVisibility(View.VISIBLE);
            ll_approved_validity_date.setVisibility(View.VISIBLE);
            ll_disapproved3.setVisibility(View.VISIBLE);
            ll_disapproved2.setVisibility(View.VISIBLE);
            ll_disapproved1.setVisibility(View.VISIBLE);
            tv_disapproved.setVisibility(View.GONE);
        } else {
            ll_approved_effective_date.setVisibility(View.GONE);
            ll_approved_validity_date.setVisibility(View.GONE);
            ll_disapproved3.setVisibility(View.GONE);
            ll_disapproved2.setVisibility(View.GONE);
            ll_disapproved1.setVisibility(View.GONE);
            tv_disapproved.setVisibility(View.VISIBLE);
        }

        ResultSetters.setDoctorWithProvider(withProvider, tv_doc_app);
        /*if (withProvider.equals(ResultSetters.WITHPROVIDER)) {
            ViewUtilities.hideView(tvWithAppUser2);
            tvWithAppUser.setText(getString(R.string.doctor_with_app));
        } else {
            ViewUtilities.showView(tvWithAppUser2);
            tvWithAppUser2.setText(getString(R.string.doctor_without_app2));
            tvWithAppUser.setText(getString(R.string.doctor_without_app));
        }*/

    }

    @OnClick(R.id.btn_shot)
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_shot:

                if (Permission.checkPermissionStorage(context)) {
                    btn_shot.setVisibility(View.GONE);
                    btn_ok.setVisibility(View.GONE);
                    /*Bitmap bitmap = Screenshot.loadBitmapFromView(sv_whole);
                    new ImageSaver(context).
                            setFileName(refCode + "_Maternity.jpg").
                            setDirectoryName("MediCard")
                            .setExternal(false)
                            .save(bitmap, callback);*/
                    if (Permission.checkPermissionStorage(context)) {
                        btn_ok.setVisibility(View.GONE);
                        btn_shot.setVisibility(View.GONE);

                        OutPatientConsultationForm build = loaFormBuilder.build();
                        if (FileUtils.fileExistance(build.getServiceType(), build.getReferenceNumber())) {
                            onShowNotifyExistingPdfDialog(build.getServiceType(), build.getReferenceNumber());
                        } else {
                            generateLoaForm(loaFormBuilder.build(), getResources().openRawResource(R.raw.loa_consultation_form));
                        }

                    }
                } else {
                    btn_shot.setVisibility(View.VISIBLE);
                    btn_ok.setVisibility(View.VISIBLE);
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

                    generateLoaForm(loaFormBuilder.build(), getResources().openRawResource(R.raw.loa_consultation_form));

                } else {
                    Timber.d("permission denied");
                }
            }

            return;
        }
    }

    private void generateLoaForm(final OutPatientConsultationForm build, final InputStream stream) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {

            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    PdfGenerator.generatePdfLoaConsultationForm(
                            build, stream);
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

        OutPatientConsultationForm build = loaFormBuilder.build();
        onShowNotifyExistingPdfDialog(build.getServiceType(), build.getReferenceNumber());

       /* alertDialogCustom.showMe(
                context,
                alertDialogCustom.CONGRATULATIONS_title,
                alertDialogCustom.LOA_GENERATE_PDF_SUCCESS,
                2);*/
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

    }

    @Override
    public void onSuccessfulScreenshot() {
        btn_ok.setVisibility(View.VISIBLE);
        btn_shot.setVisibility(View.VISIBLE);
        alertDialogCustom.showMe(getActivity(), alertDialogCustom.CONGRATULATIONS_title, alertDialogCustom.Saved_Screenshot, 2);
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
