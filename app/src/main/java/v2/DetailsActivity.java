package v2;

import android.content.Context;
import android.content.Intent;

import com.medicard.member.R;
import com.medicard.member.TermsActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import InterfaceService.DetailsActCallback;
import InterfaceService.DetailsRetieve;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.RequestResult;
import timber.log.Timber;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.DateAddThreeDays;
import utilities.ErrorMessage;
import utilities.GenderPicker;
import utilities.HeaderNameSetter;
import utilities.SharedPref;


public class DetailsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, DetailsActCallback, AlertDialogCustom.onClickDialogListener {

    @BindView(R.id.tv_sched)
    TextView tv_sched;
    @BindView(R.id.tv_contact_person)
    TextView tv_contact_person;
    @BindView(R.id.tv_contact)
    TextView tv_contact;
    @BindView(R.id.tv_spec)
    TextView tv_spec;
    @BindView(R.id.tv_sched_doctor)
    TextView tv_sched_doctor;
    @BindView(R.id.tv_terms_n_cond)
    TextView tv_terms_n_cond;
    @BindView(R.id.tv_no_doctor)
    TextView tv_no_doctor;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_hospAddress)
    TextView tv_hospAddress;
    @BindView(R.id.tv_header)
    TextView tv_header;
    @BindView(R.id.tv_hospName)
    TextView tv_hospName;
    @BindView(R.id.tv_doc_name)
    TextView tv_doc_name;
    @BindView(R.id.tv_doc_det)
    TextView tv_doc_det;

    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.btn_cancel)
    Button btn_cancel;
    @BindView(R.id.btn_back)
    FancyButton btn_back;

    @BindView(R.id.et_input_diagnosis)
    EditText et_input_diagnosis;
    @BindView(R.id.et_doctor)
    EditText et_doctor;

    @BindView(R.id.cb_confirm)
    CheckBox cb_confirm;

    @BindView(R.id.cv_hosp)
    CardView cv_hosp;
    @BindView(R.id.cv_doctor)
    CardView cv_doctor;

    @BindView(R.id.ll_doctor_not_found)
    LinearLayout ll_doctor_not_found;

    @BindView(R.id.tv_select_doc)
    TextView tv_select_doc;

    @BindView(R.id.img_arrow)
    ImageView img_arrow;

    DetailsRetieve implement;
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
    DetailsActCallback callback;


    private String MEMBERSTATUS = "";
    AlertDialogCustom.onClickDialogListener callbackDialog;
    Context context;
    String origin;
    String hospital_name, hospital_address, hospital_code;
    String doctor_name, doctor_desc, doctor_code;
    public static String END = "END";

    //User for disabling special characters
    String specialChars = "@/*!##$%^&*()\"{}_[]|\\?/<>,.:-'';§£¥...";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        callbackDialog = this;
        context = this;
        implement = new DetailsRetieve(context, this);
        callback = this;
        origin = getIntent().getExtras().getString(RequestButtonsActivity.ORIGIN);
        ButterKnife.bind(this);
        tv_header.setText(HeaderNameSetter.setHeader(SharedPref.getStringValue(SharedPref.USER, SharedPref.DESTINATION, context)));

        cb_confirm.setChecked(false);
        cb_confirm.setOnCheckedChangeListener(this);
        MEMBERSTATUS = getIntent().getExtras().getString(Constant.MEM_STATUS);

        et_input_diagnosis.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return true;
                }
                return false;
            }
        });
        et_input_diagnosis.setFilters(new InputFilter[]{filter});

        et_doctor.setImeOptions(EditorInfo.IME_ACTION_DONE);
        et_doctor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPref.setStringValue(SharedPref.USER, SharedPref.DOCTOR_CODE, et_doctor.getText().toString().trim(), context);
                doctor_code = String.valueOf(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        setDetails();
    }

    @OnClick({R.id.cv_hosp, R.id.cv_doctor, R.id.btn_cancel, R.id.btn_submit, R.id.btn_back, R.id.tv_terms_n_cond})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.cv_doctor:
                gotoDoctors();
                break;

            case R.id.cv_hosp:
                gotoHospital();
                break;

            case R.id.btn_submit:
                implement.testAndSubmitData(et_input_diagnosis, et_doctor, doctor_name, doctor_code);
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_terms_n_cond:
                startActivity(new Intent(DetailsActivity.this, TermsActivity.class));
                break;

            case R.id.btn_cancel:
                implement.showDisregardDialog();
                break;
        }

    }

    private void gotoDoctors() {

        Intent gotoDoctor = new Intent(context, DoctorListActivity.class);
        gotoDoctor.putExtra(RequestButtonsActivity.ORIGIN, END);
        gotoDoctor.putExtra(Constant.MEMBER_ID, getIntent().getExtras().getString(Constant.MEMBER_ID));
        gotoDoctor.putExtra(Constant.GENDER, getIntent().getExtras().getString(Constant.GENDER));
        gotoDoctor.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.NAME));
        gotoDoctor.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
        gotoDoctor.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
        gotoDoctor.putExtra(Constant.AGE, getIntent().getExtras().getString(Constant.AGE));
        startActivity(gotoDoctor);
    }
    /*
     **Mehtod used for validating input if has special character and emojis**
     */
    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int index = start; index < end; index++) {

                int type = Character.getType(source.charAt(index));

                if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL || type == Character.MATH_SYMBOL || specialChars.contains("" + source)) {
                    return "";
                }
            }
            return null;
        }
    };


    private void gotoHospital() {


        Intent gotoHosp = new Intent(context, HospitalListAcitivity.class);
        gotoHosp.putExtra(RequestButtonsActivity.ORIGIN, END);
        gotoHosp.putExtra(Constant.MEMBER_ID, getIntent().getExtras().getString(Constant.MEMBER_ID));
        gotoHosp.putExtra(Constant.GENDER, getIntent().getExtras().getString(Constant.GENDER));
        gotoHosp.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.NAME));
        gotoHosp.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
        gotoHosp.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
        gotoHosp.putExtra(Constant.AGE, getIntent().getExtras().getString(Constant.AGE));
        startActivity(gotoHosp);
    }

    private void setDetails() {
        hospital_name = SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_NAME, context);
//        hospital_address = SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_ADD, context);
        hospital_address = SharedPref.getPreferenceByKey(context, SharedPref.KEY_HOSPITAL_FULL_ADDRESS);

        hospital_code = SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CODE, context);

        doctor_name = SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_NAME, context);
        doctor_code = SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_CODE, context);
        doctor_desc = SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_DESC, context);


        if (doctor_name.equals(Constant.NOT_FOUND)) {
            implement.setDoctorFieldEditText(true, ll_doctor_not_found, et_doctor);
            tv_no_doctor.setVisibility(View.GONE);
            tv_select_doc.setVisibility(View.VISIBLE);
            img_arrow.setVisibility(View.GONE);
        } else if (doctor_name.equals(Constant.NOT_SET)) {
            tv_no_doctor.setVisibility(View.VISIBLE);
            implement.setDoctorFieldEditText(true, ll_doctor_not_found, et_doctor);
            et_doctor.setVisibility(View.GONE);
            tv_select_doc.setVisibility(View.VISIBLE);
            img_arrow.setVisibility(View.GONE);
        } else {
            tv_no_doctor.setVisibility(View.GONE);
            implement.setDoctorFieldEditText(false, ll_doctor_not_found, et_doctor);
            tv_doc_name.setText(doctor_name);
            tv_doc_det.setText(doctor_desc);

            tv_select_doc.setVisibility(View.GONE);
            img_arrow.setVisibility(View.VISIBLE);
        }

        tv_contact_person.setText("");

        tv_sched.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_U, context));
        tv_contact.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT, context));


        tv_spec.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_ROOM, context));
        tv_sched_doctor.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_U, context));


        tv_hospName.setText(hospital_name);
        tv_hospAddress.setText(hospital_address);


//        tv_date.setText(DateAddThreeDays.currentDate() + " to " + DateAddThreeDays.validityDate()); commented September 4 2017 not needed anymore
        implement.setSubmitButtonDisabled(cb_confirm, btn_submit);
    }


    @Override
    public void onError(String message) {
        Log.e("ERROR", message);
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onSuccess(RequestResult data) {
        try {
            // Log.d("SUCCESS", data.getRemarks());
            gotoResult(data);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void gotoResult(RequestResult data) {

        try {
            Timber.d("batchCode %s", data.getBatchCode());
            Intent gotoResult = new Intent(DetailsActivity.this, ResultActivity.class);
            gotoResult.putExtra(Constant.REFERENCECODE, data.getApprovalNo());
            gotoResult.putExtra(Constant.REQUEST, "APPROVED");
            gotoResult.putExtra(Constant.DOCTOR_WITH_PROVIDER, implement.setNull(data.getWithProvider()));
            gotoResult.putExtra(RequestButtonsActivity.ORIGIN, origin);
            gotoResult.putExtra(Constant.MEMBER_ID, getIntent().getExtras().getString(Constant.MEMBER_ID));
            gotoResult.putExtra(Constant.GENDER, GenderPicker.setGender(Integer.parseInt(getIntent().getExtras().getString(Constant.GENDER))));
            gotoResult.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.NAME));
            gotoResult.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
            gotoResult.putExtra(Constant.MEM_STATUS, getIntent().getExtras().getString(Constant.MEM_STATUS));
            gotoResult.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
            gotoResult.putExtra(Constant.AGE, getIntent().getExtras().getString(Constant.AGE));
            gotoResult.putExtra(Constant.CONDITION, et_input_diagnosis.getText().toString());

            gotoResult.putExtra(Constant.DOCTOR_U, tv_sched_doctor.getText().toString());
            gotoResult.putExtra(Constant.DOCTOR_ROOM, tv_spec.getText().toString());
            gotoResult.putExtra(Constant.HOSP_CONTACT, tv_contact.getText().toString());
            gotoResult.putExtra(Constant.HOSP_CONTACT_PER, tv_contact_person.getText().toString());
            gotoResult.putExtra(Constant.HOSP_U, tv_sched.getText().toString());
            gotoResult.putExtra(Constant.BATCH_CODE, data.getBatchCode());


            startActivity(gotoResult);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void emptyFieldsListener() {
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorEmptyFields, 1);
        cb_confirm.setChecked(false);
        implement.setSubmitButtonDisabled(cb_confirm, btn_submit);
    }

    @Override
    public void cancelRequest() {
        finish();

    }

    @Override
    public void onDuplicateRequest(RequestResult requestResult) {
        alertDialogCustom.showMeValidateReq(requestResult, callbackDialog, context);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        implement.setSubmitButtonDisabled(cb_confirm, btn_submit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDetails();
    }

    @Override
    public void onTermsAndConditionListener() {
        finish();
    }

    @Override
    public void onBackPressed() {
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.close_loa, 1, callbackDialog);
    }

    @Override
    public void onOkPress() {
        finish();
    }

    @Override
    public void onRequestDupliate() {

    }

    @Override
    public void loaApprovedListener(RequestResult requestResult) {
        implement.sendConfirmConsult(requestResult);
    }

    @Override
    public void onErrorConfirm(String message) {
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onBlockRequest(String message) {
        // todo on message for blocking
        alertDialogCustom.showMe(
                context,
                alertDialogCustom.HOLD_ON_title,
                message,
                1);
    }

    @Override
    public void onSuccessConfirm(RequestResult requestResult) {
        gotoResult(requestResult);
    }
}
