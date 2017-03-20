package fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.medicard.com.medicard.R;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import InterfaceService.ScreenshotCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utilities.AlertDialogCustom;
import utilities.ImageSaver;
import utilities.Loader;
import utilities.PdfSaver;
import utilities.Permission;
import utilities.QrCodeCreator;
import utilities.ResultSetters;
import utilities.Screenshot;
import utilities.SharedPref;

public class ConsultationResult extends Fragment implements ScreenshotCallback {

    @BindView(R.id.sv_whole)
    ScrollView sv_whole;

    @BindView(R.id.btn_shot)
    TextView btn_shot;

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


    @BindView(R.id.tv_condition)
    TextView tv_condition;
    @BindView(R.id.tv_date)
    TextView tv_date;

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_doc_app)
    TextView tv_doc_app;

    @BindView(R.id.tv_disclaimer)
    TextView tv_disclaimer;
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

    @BindView(R.id.tv_sched)
    TextView tv_sched;
    @BindView(R.id.tv_spec)
    TextView tv_spec;
    @BindView(R.id.tv_sched_doctor)
    TextView tv_sched_doctor;


    Context context;
    ///  TextView tv_contact_person, tv_sched, tv_contact, tv_spec, tv_sched_doctor;

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
    private static final String ARG_approved = "aproved";


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

    QrCodeCreator qrCodeCreator = new QrCodeCreator();
    ScreenshotCallback callback;
    AlertDialogCustom alertDialogCustom;
    Loader loader;

    public ConsultationResult() {
        // Required empty public constructor
    }

    public static ConsultationResult newInstance(String getRefCode, String getMemId,
                                                 String getAge, String getName,
                                                 String getGender, String getCompany,
                                                 String getRemark, String getCondition,
                                                 String getRequest, String getReqDate,
                                                 String getValDate, String getWithProvider,
                                                 String doctor_u, String doc_room, String hosp_contact,
                                                 String hosp_contact_per, String hosp_u
    ) {
        ConsultationResult fragment = new ConsultationResult();
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

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
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
        }

        callback = this;
        alertDialogCustom = new AlertDialogCustom();
        loader = new Loader(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_approve, container, false);
        ButterKnife.bind(this, view);
        init();

        return view;
    }

    private void init() {

        context = getContext();
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        setDetails();


    }


    private void setDetails() {


        tv_contact_person.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT_PERSON, context));
        tv_contact.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT, context));
        tv_sched.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_U, context));
        tv_spec.setText(ResultSetters.specSetter(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_ROOM, context)));
        tv_sched_doctor.setText(ResultSetters.schedSetter(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_U, context)));


        tv_ref_code.setText("Reference No: " + refCode);
        tv_ref_code2.setText(refCode);
        tv_hospName.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_NAME, context));
        tv_hospAddress.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_ADD, context));
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
        tv_date_approved.setText(reqDate);

        tv_date_requested.setText(reqDate);
        tv_date.setText(valDate);
        img_qrcode.setVisibility(View.VISIBLE);
        img_qrcode.setImageBitmap(qrCodeCreator.getBitmapFromString(refCode));

        if (tv_title.getText().toString().trim().equals(ResultSetters.REQUEST_APPROVED)) {
            setApproved();
        } else if (tv_title.getText().toString().trim().equals(ResultSetters.REQUEST_DISAPPROVED)) {
            setDisapproved();
        } else if (tv_title.getText().toString().trim().equals(ResultSetters.REQUEST_APPROVAL)) {
            setPending();
        }

        ResultSetters.setDoctorWithProvider(withProvider, tv_doc_app);
    }


    private void setPending() {

        ll_disapproved_req.setVisibility(View.VISIBLE);
        ll_approved_req.setVisibility(View.VISIBLE);
        ll_ref_code_details.setVisibility(View.GONE);
        ll_pending.setVisibility(View.VISIBLE);
        tv_disclaimer.setVisibility(View.GONE);
        //   img_qrcode.setVisibility(View.GONE);
        ll_approved_validity.setVisibility(View.GONE);
        tv_sub_title.setVisibility(View.VISIBLE);
        ll_ref_code_details.setVisibility(View.GONE);
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

    }



    @OnClick(R.id.btn_shot)
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_shot:


                btn_ok.setVisibility(View.GONE);
                btn_shot.setVisibility(View.GONE);
                Bitmap bitmap = Screenshot.loadBitmapFromView(sv_whole);

                if (Permission.checkPermissionStorage(context)) {
                    new ImageSaver(context).
                            setFileName(refCode + "+Consultation.jpg").
                            setDirectoryName("Medicard")
                            .setExternal(false)
                            .save(bitmap, callback);
                }

                break;


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

        btn_ok.setVisibility(View.GONE);
        Bitmap bitmap = Screenshot.loadBitmapFromView(sv_whole);

        if (Permission.checkPermissionStorage(context)){
            new ImageSaver(context).
                    setFileName("Consultation.jpg").
                    setDirectoryName("Medicard")
                    .setExternal(false)
                    .save(bitmap, callback);

        }
    }

    @Override
    public void onSuccessfulScreenshot() {
        btn_ok.setVisibility(View.VISIBLE);
        btn_shot.setVisibility(View.VISIBLE);

        alertDialogCustom.showMe(getActivity(), alertDialogCustom.CONGRATULATIONS_title, alertDialogCustom.Saved_Screenshot, 2);
    }
}
