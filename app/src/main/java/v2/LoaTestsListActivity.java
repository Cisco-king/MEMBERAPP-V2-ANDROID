package v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;

import InterfaceService.DoctorInterface;
import InterfaceService.DoctorRetrieve;
import Sqlite.DatabaseHandler;
import Sqlite.SetDoctorToDatabase;
import adapter.DoctorAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.CitiesAdapter;
import model.Doctors;
import model.GetDoctorsToHospital;
import model.ProvincesAdapter;
import model.SpecsAdapter;
import services.OnClicklistener;
import timber.log.Timber;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.ErrorMessage;
import utilities.Loader;
import utilities.SharedPref;

/**
 * Created by IPC on 1/10/2018.
 */

public class LoaTestsListActivity extends AppCompatActivity implements OnClicklistener, DoctorInterface {

    @BindView(R.id.btn_sort)
    FancyButton btn_sort;
    @BindView(R.id.btn_back)
    FancyButton btn_back;

    @BindView(R.id.rv_doctor)
    RecyclerView rv_doctor;

    @BindView(R.id.tv_no_doc)
    TextView tv_no_doc;
    @BindView(R.id.tv_header)
    TextView tv_header;

    @BindView(R.id.ed_searchDoctor)
    EditText ed_searchDoctor;

    @BindView(R.id.btn_proceed)
    Button btn_proceed;

    @BindView(R.id.ll_no_doctor_found)
    LinearLayout ll_no_doctor_found;


    LinearLayoutManager llm;

    Context context;

    DoctorAdapter doctorAdapter;
    DatabaseHandler databaseHandler;
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
    Loader loader;
    DoctorRetrieve implement;
    DoctorInterface callback;
    String origin = "";
    int DOCTOR_CALL = 100;
    ArrayList<GetDoctorsToHospital> array = new ArrayList<>();
    ArrayList<SpecsAdapter> selectedSpec = new ArrayList<>();
    ArrayList<CitiesAdapter> selectedCity = new ArrayList<>();
    ArrayList<ProvincesAdapter> selectedProvince = new ArrayList<>();
    private String search_string = "";
    private String sort_by = "";
    private String room_number = "";
    private String MEMBERSTATUS = "";
    private String hospital_sort_id = "";

    private String DERMATOLOGY = "DERMATOLOGY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        callback = this;
        ButterKnife.bind(this);
        databaseHandler = new DatabaseHandler(context);
        loader = new Loader(context);
        implement = new DoctorRetrieve(context, callback, databaseHandler);


        hospital_sort_id = getIntent().getExtras().getString(Constant.SELECT_HOSP_ID);

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);


//        implement.dropDoctors();

//        implement.getDoctors(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CODE, context));


        doctorAdapter = new DoctorAdapter(context, array);
        rv_doctor.setLayoutManager(llm);
        rv_doctor.setAdapter(doctorAdapter);

        getSearchDoctor(hospital_sort_id, doctorAdapter, "", selectedSpec, sort_by, "");

        alertDialogCustom.showMe(
                context,
                alertDialogCustom.HOLD_ON_title,
                getString(R.string.success_load_doctors),
                1);

        doctorAdapter.setClickListener(this);
        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
            }
        });

        tv_header.setText(this.getString(R.string.sort_loa));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ed_searchDoctor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getSearchDoctor(hospital_sort_id, doctorAdapter, String.valueOf(s), selectedSpec, sort_by, room_number);
                search_string = String.valueOf(s);
            }
        });

    }

    @OnClick({R.id.btn_sort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sort:
                Intent intent = new Intent(this, SortDoctorActivity.class);
                intent.putExtra(Constant.SEARCH_STRING, search_string);
                intent.putParcelableArrayListExtra(Constant.SELECTED_SPECIALIZATION, selectedSpec);
                intent.putExtra(Constant.SORT_BY, sort_by);
                intent.putExtra(Constant.ROOM_NUMBER, room_number);
                intent.putParcelableArrayListExtra(Constant.SELECTED_CITY, selectedCity);
                intent.putParcelableArrayListExtra(Constant.SELECTED_PROVINCE, selectedProvince);
                startActivityForResult(intent, DOCTOR_CALL);
                break;
        }
    }

    private void getSearchDoctor(String hospcode, DoctorAdapter doctorAdapter, String editable, ArrayList<SpecsAdapter> selectedSpec, String sort_by, String room_number) {

        array.clear();
        array.addAll(databaseHandler.retrieveDoctor(hospcode, selectedCity, selectedProvince, String.valueOf(editable), selectedSpec, implement.testSort(sort_by), room_number));

//        // get only the unique value from the set
//        Timber.d("original size with duplicate %s", array.size());
//        Set<GetDoctorsToHospital> uniqueSet = new LinkedHashSet<>(array);
//        array.clear();
//        array.addAll(uniqueSet);
//        Timber.d("new set without duplicate %s", array.size());

        if (array.size() == 0) {
            ll_no_doctor_found.setVisibility(View.VISIBLE);
            rv_doctor.setVisibility(View.GONE);
            tv_no_doc.setVisibility(View.GONE);
        } else {
            doctorAdapter.notifyDataSetChanged();
            rv_doctor.setVisibility(View.VISIBLE);
            ll_no_doctor_found.setVisibility(View.GONE);
            tv_no_doc.setVisibility(View.GONE);
        }
        doctorAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DOCTOR_CALL && resultCode == RESULT_OK) {
            selectedSpec = data.getParcelableArrayListExtra(Constant.SELECTED_SPECIALIZATION);
            search_string = data.getStringExtra(Constant.SEARCH_STRING);
            sort_by = data.getStringExtra(Constant.SORT_BY);
            room_number = data.getStringExtra(Constant.ROOM_NUMBER);
            selectedCity = data.getParcelableArrayListExtra(Constant.SELECTED_CITY);
            selectedProvince = data.getParcelableArrayListExtra(Constant.SELECTED_PROVINCE);
            getSearchDoctor(hospital_sort_id, doctorAdapter, search_string, selectedSpec, sort_by, room_number);
            implement.setSearchStringtoUI(search_string, ed_searchDoctor);
        }
    }


    @Override
    public void onDoctorError(String message) {
        Log.e("DOCTOR", message);
        loader.stopLoad();
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onDoctorSuccess(Doctors doctors) {
        SetDoctorToDatabase.insertToDb(databaseHandler, doctors, callback);
        loader.stopLoad();
        Timber.d("doctor list : %s", doctors.toString());
        if (doctors.getGetDoctorsToHospital() != null && doctors.getGetDoctorsToHospital().size() > 0) {
            alertDialogCustom.showMe(
                    context,
                    alertDialogCustom.HOLD_ON_title,
                    getString(R.string.success_load_doctors),
                    1);
        }
        getSearchDoctor(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CODE, context), doctorAdapter, "", selectedSpec, sort_by, "");
    }

    @Override
    public void noInternetConnection() {
        alertDialogCustom.showMe(context, alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);
    }

    @Override
    public void internetConnected(String hospCode) {
        loader.startLad();
        loader.setMessage("Getting Doctor List...");


        //hide temporarily for data testing of doctor list per hospital code - jhay
//        implement.getDoctorList(hospCode);
    }


    @Override
    public void onClickListener(int position) {
        if (array.get(position).getSpecDesc().equals(DERMATOLOGY)) {
            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.spec_not_good, 1);
        } else {
            Intent intent = new Intent();
            intent.putExtra(Constant.SELECT_DOCTOR, array.get(position).getDocLname() + ", " + array.get(position).getDocFname());
            intent.putExtra(Constant.SELECT_DOCTOR_ID, array.get(position).getDoctorCode());
            setResult(RESULT_OK, intent);
            finish();
        }


    }

    @Override
    public void onBackPressed() {

    }

}
