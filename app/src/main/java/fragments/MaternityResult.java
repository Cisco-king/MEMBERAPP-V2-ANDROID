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

import InterfaceService.MaternityCallback;
import InterfaceService.ScreenshotCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.ImageSaver;
import utilities.Permission;
import utilities.QrCodeCreator;
import utilities.ResultSetters;
import utilities.Screenshot;
import utilities.SharedPref;

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
                                              String hosp_contact_per, String hosp_u) {
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

        btn_ok = (Button) view.findViewById(R.id.btn_ok);


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
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

    }

    @OnClick(R.id.btn_shot)
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_shot:

                btn_shot.setVisibility(View.GONE);
                btn_ok.setVisibility(View.GONE);
                Bitmap bitmap = Screenshot.loadBitmapFromView(sv_whole);

                if (Permission.checkPermissionStorage(context)) {
                    new ImageSaver(context).
                            setFileName("Maternity.jpg").
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

    }

    @Override
    public void onSuccessfulScreenshot() {
        btn_ok.setVisibility(View.VISIBLE);
        btn_shot.setVisibility(View.VISIBLE);
        alertDialogCustom.showMe(getActivity(), alertDialogCustom.CONGRATULATIONS_title, alertDialogCustom.Saved_Screenshot, 2);
    }
}
