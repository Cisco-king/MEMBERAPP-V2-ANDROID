package v2;

import android.content.Context;
import android.content.Intent;
import android.medicard.com.medicard.R;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import InterfaceService.SortLoaReqCallback;
import InterfaceService.SortLoaReqImplement;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.LoaFetch;
import model.SimpleData;
import utilities.Constant;

public class SortLoaReqActivity extends AppCompatActivity implements SortLoaReqCallback {

    @BindView(R.id.btn_back)
    FancyButton btn_back;

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
    @BindView(R.id.tv_req_date)
    TextView tv_req_date;

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

        ArrayList<LoaFetch> temp;
        temp = getIntent().getParcelableArrayListExtra(Constant.LOA_REQUEST);
        arrayListMaster.addAll(temp);

    }

    @OnClick({R.id.tv_status, R.id.tv_sort_by, R.id.tv_service_type, R.id.tv_hosp_clinic, R.id.tv_doctor, R.id.tv_test,
            R.id.tv_diagnosis, R.id.tv_req_date, R.id.btn_reset, R.id.btn_show})
    public void onClick(View v) {


        switch (v.getId()) {

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
                Intent getList = new Intent(SortLoaReqActivity.this, SelectProvinceActivity.class);
                getList.putExtra(Constant.SELECT, Constant.SELECT_LOAREQUEST);
                getList.putParcelableArrayListExtra(Constant.LOA_REQUEST, arrayListMaster);
                getList.putParcelableArrayListExtra(Constant.SELECTED_REQUEST, prevSelected);
                startActivityForResult(getList, CALL_HOSPITALS);
                break;
            case R.id.tv_doctor:
                Intent getListDoc = new Intent(SortLoaReqActivity.this, SelectProvinceActivity.class);
                getListDoc.putExtra(Constant.SELECT, Constant.SELECT_DOCTOR);
                getListDoc.putParcelableArrayListExtra(Constant.DOCTOR_LIST, arrayListMaster);
                getListDoc.putParcelableArrayListExtra(Constant.SELECTED_DOCTOR, prevSelectedDoctor);
                startActivityForResult(getListDoc, CALL_DOCTORS);
                break;

            case R.id.tv_test:
                //
                break;

            case R.id.tv_diagnosis:
                //
                break;


            case R.id.tv_req_date:

                break;
            case R.id.btn_show:

                break;
            case R.id.btn_reset:

                break;


        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CALL_HOSPITALS && resultCode == RESULT_OK) {
            ArrayList<SimpleData> temp;
            temp = data.getParcelableArrayListExtra("LOA_REQUEST");
            prevSelected.clear();
            prevSelected.addAll(temp);
            implement.setFetchHospitals(tv_hosp_clinic, temp);
        } else if (requestCode == CALL_DOCTORS && resultCode == RESULT_OK) {
            ArrayList<SimpleData> temp;
            temp = data.getParcelableArrayListExtra("DOCTOR");
            prevSelectedDoctor.clear();
            prevSelectedDoctor.addAll(temp);
            implement.setFetchHospitals(tv_doctor, temp);
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
}
