package v2;

import android.content.Context;
import android.content.Intent;

import com.medicard.member.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import InterfaceService.SortLoaReqCallback;
import InterfaceService.SortLoaReqImplement;
import Sqlite.DatabaseHandler;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.LoaFetch;
import model.SimpleData;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.DateConverter;

public class SortLoaReqActivity extends AppCompatActivity implements SortLoaReqCallback {

    @BindView(R.id.btn_back)
    FancyButton btn_back;

    @BindView(R.id.tv_header)
    TextView tv_header;

    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.tv_sort_by)
    TextView tv_sort_by;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_service_type)
    TextView tv_service_type;
    @BindView(R.id.tv_hosp_clinic)
    TextView tv_hosp_clinic;
    @BindView(R.id.tv_doctor)
    TextView tv_doctor;
    @BindView(R.id.tv_test)
    TextView tv_test;
    @BindView(R.id.tv_diagnosis)
    TextView tv_diagnosis;
    @BindView(R.id.tv_req_date_start)
    TextView tv_req_date_start;
    @BindView(R.id.tv_req_date_end)
    TextView tv_req_date_end;

    @BindView(R.id.btn_reset)
    FancyButton btn_reset;
    @BindView(R.id.btn_show)
    FancyButton btn_show;

    SortLoaReqImplement implement;
    SortLoaReqCallback callback;
    Context context;
    private final int CALL_HOSPITALS = 100;
    private final int CALL_DOCTORS = 200;
    ArrayList<LoaFetch> arrayListMaster = new ArrayList<>();
    ArrayList<SimpleData> prevSelected = new ArrayList<>();

    ArrayList<SimpleData> prevSelectedDoctor = new ArrayList<>();
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();

    //SORTING DATA
    String seachedData = "";
    String sort_by = "";
    String status_sort = "";
    String service_type_sort = "";
    String doctor_sort = "";
    String doctor_sort_id = "";
    String hospital_sort = "";
    String hospital_sort_id = "";// holds the id of the hosp
    String test_sort = "";
    String diag_sort = "";
    String date_start_sort = "";
    String date_end_sort = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_loa_req);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        context = this;
        callback = this;
        implement = new SortLoaReqImplement(context, callback);

//        ArrayList<LoaFetch> temp;
//        temp = getIntent().getParcelableArrayListExtra(Constant.LOA_REQUEST);
//        arrayListMaster.addAll(temp);

        //GET DATA
        seachedData = getIntent().getStringExtra(Constant.SEARCHED_DATA);
        sort_by = getIntent().getStringExtra(Constant.SORT_BY);
        status_sort = getIntent().getStringExtra(Constant.STATUS);
        service_type_sort = getIntent().getStringExtra(Constant.SERVICE_TYPE);
        doctor_sort = getIntent().getStringExtra(Constant.SELECT_DOCTOR);
        hospital_sort = getIntent().getStringExtra(Constant.SELECT_HOSP);
        hospital_sort_id = getIntent().getStringExtra(Constant.SELECT_HOSP_ID);
        test_sort = getIntent().getStringExtra(Constant.SELECT_TEST);
        diag_sort = getIntent().getStringExtra(Constant.SELECT_DIAG);
        date_start_sort = getIntent().getStringExtra(Constant.SELECTED_START_DATE);
        date_end_sort = getIntent().getStringExtra(Constant.SELECTED_END_DATE);

//        ArrayList<SimpleData> temp1 = getIntent().getParcelableArrayListExtra(Constant.SELECTED_HOSPITAL);
//        implement.replaceData(prevSelected, temp1);
//        temp1 = getIntent().getParcelableArrayListExtra(Constant.SELECT_DOCTOR);
//        implement.replaceData(prevSelectedDoctor, temp1);
//
        //SET DATA
        et_search.setText(seachedData);
        tv_sort_by.setText(sort_by);
        tv_status.setText(status_sort);
        tv_service_type.setText(service_type_sort);
        tv_hosp_clinic.setText(hospital_sort);
        tv_doctor.setText(doctor_sort);
        tv_test.setText(test_sort);
        tv_diagnosis.setText(diag_sort);
        tv_req_date_start.setText(date_start_sort);
        tv_req_date_end.setText(date_end_sort);

//        implement.setFetchHospitals(tv_doctor, prevSelectedDoctor);
//        implement.setFetchHospitals(tv_hosp_clinic, prevSelected);


    }

    @OnClick({R.id.btn_back, R.id.tv_status, R.id.tv_sort_by, R.id.tv_service_type, R.id.tv_hosp_clinic, R.id.tv_doctor, R.id.tv_test,
            R.id.tv_diagnosis, R.id.tv_req_date_end, R.id.tv_req_date_start, R.id.btn_reset, R.id.btn_show})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;

            case R.id.tv_sort_by:
                implement.showSortBy();
                break;

            case R.id.tv_status:
                implement.showStatusSort();
                break;
            case R.id.tv_service_type:
                implement.showServiceType();
                break;

            case R.id.tv_hosp_clinic:
                Intent getList = new Intent(SortLoaReqActivity.this, LoaHospitalListActivity.class);
                startActivityForResult(getList, CALL_HOSPITALS);
                break;
            case R.id.tv_doctor:
                Intent getListDoc = new Intent(SortLoaReqActivity.this, LoaDoctorListActivity.class);
                getListDoc.putExtra(Constant.SELECT_HOSP_ID, hospital_sort_id);
                startActivityForResult(getListDoc, CALL_DOCTORS);
                break;

            case R.id.tv_test:
                alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.data_not_avilable, 1);
                break;

            case R.id.tv_diagnosis:
                alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.data_not_avilable, 1);
                break;


            case R.id.tv_req_date_end:
                int[] dateStarter = implement.getDateStarter(tv_req_date_start);
                if (tv_req_date_start.getText().toString().equals(""))
                    alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.pick_start_Date, 1);
                else {
                    implement.showDatePicker(tv_req_date_start, true, callback, tv_req_date_end, dateStarter);
                }
                break;

            case R.id.tv_req_date_start:
                int[] dateStarter1 = implement.getDateStarter(tv_req_date_start);


                implement.showDatePicker(tv_req_date_start, false, callback, tv_req_date_end, dateStarter1);

                break;
            case R.id.btn_show:
                Intent intent = new Intent();
                intent.putExtra(Constant.SEARCHED_DATA, et_search.getText().toString().trim());
                intent.putExtra(Constant.SORT_BY, implement.getTextTrimmed(tv_sort_by));
                intent.putExtra(Constant.STATUS, implement.getTextTrimmed(tv_status));
                intent.putExtra(Constant.SERVICE_TYPE, implement.getTextTrimmed(tv_service_type));
                intent.putExtra(Constant.SELECT_HOSP, implement.getTextTrimmed(tv_hosp_clinic));
                intent.putExtra(Constant.SELECT_HOSP_ID, hospital_sort_id); // holds the id of the current hosp
                intent.putExtra(Constant.SELECT_DOCTOR, implement.getTextTrimmed(tv_doctor));
                intent.putExtra(Constant.SELECT_DOCTOR_ID, doctor_sort_id);
                intent.putExtra(Constant.SELECT_TEST, implement.getTextTrimmed(tv_test));
                intent.putExtra(Constant.SELECT_DIAG, implement.getTextTrimmed(tv_diagnosis));
                intent.putExtra(Constant.SELECTED_START_DATE, implement.getTextTrimmed(tv_req_date_start));
                intent.putExtra(Constant.SELECTED_END_DATE, implement.getTextTrimmed(tv_req_date_end));
                setResult(RESULT_OK, intent);
                finish();

                break;
            case R.id.btn_reset:

                seachedData = "";
                sort_by = getString(R.string.request_date);
                status_sort = "";
                service_type_sort = "";
                doctor_sort = "";
                hospital_sort = "";
                hospital_sort_id = "";// holds the id of the hosp
                test_sort = "";
                diag_sort = "";
                date_start_sort = "";
                date_end_sort = "";


                //SET DATA
                et_search.setText(seachedData);
                tv_sort_by.setText(sort_by);
                tv_status.setText(status_sort);
                tv_service_type.setText(service_type_sort);
                tv_hosp_clinic.setText(hospital_sort);
                tv_doctor.setText(doctor_sort);
                tv_test.setText(test_sort);
                tv_diagnosis.setText(diag_sort);
                tv_req_date_start.setText(date_start_sort);
                tv_req_date_end.setText(date_end_sort);

                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CALL_HOSPITALS && resultCode == RESULT_OK) {

            hospital_sort_id = data.getStringExtra(Constant.SELECT_HOSP_ID);
            tv_hosp_clinic.setText(data.getStringExtra(Constant.SELECT_HOSP));

        } else if (requestCode == CALL_DOCTORS && resultCode == RESULT_OK) {
            doctor_sort_id = data.getStringExtra(Constant.SELECT_DOCTOR_ID);
            tv_doctor.setText(data.getStringExtra(Constant.SELECT_DOCTOR));
        }
    }

    @Override
    public void onSortListener(String sortBy) {
        tv_sort_by.setText(sortBy);
    }

    @Override
    public void onSortStatus(String sortBy) {
        tv_status.setText(sortBy);
    }

    @Override
    public void onSortServiceType(String sortBy) {
        tv_service_type.setText(sortBy);
    }

    @Override
    public void datePickerEndDateError() {
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.end_must_greater, 1);
    }

    @Override
    public void datePickerStartDateError() {
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.start_must_lesser, 1);
    }

}
