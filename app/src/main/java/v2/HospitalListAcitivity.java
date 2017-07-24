package v2;

import android.content.Context;
import android.content.Intent;
import com.medicard.member.R;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import InterfaceService.ApiHospCallback;
import InterfaceService.ExclusionRetrieve;
import InterfaceService.HospitalListRetrieve;
import Sqlite.DatabaseHandler;
import adapter.HospitalAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;
import model.CitiesAdapter;
import model.HospitalList;
import model.Provinces;
import model.ProvincesAdapter;
import services.OnClicklistener;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.HeaderNameSetter;
import utilities.NetworkTest;
import utilities.SharedPref;

public class HospitalListAcitivity extends AppCompatActivity implements OnClicklistener, ApiHospCallback {

    LinearLayoutManager llm;

    @BindView(R.id.rv_doctor)
    RecyclerView rv_hospital;

    @BindView(R.id.tv_header)
    TextView tv_header;
    @BindView(R.id.tv_hosp_not_found)
    TextView tv_hosp_not_found;
    @BindView(R.id.ed_searchHosp)
    EditText ed_searchHosp;
    @BindView(R.id.btn_back)
    FancyButton btn_back;
    @BindView(R.id.btn_sort)
    FancyButton btn_sort;

    Context context;
    HospitalListRetrieve implement;
    DatabaseHandler databaseHandler;
    HospitalAdapter hospitalAdapter;
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
    private int SORT_CALL = 100;
    String origin = " ";
    ArrayList<HospitalList> array = new ArrayList<>();

    String sortBy = "";
    ArrayList<CitiesAdapter> selectedCity = new ArrayList<>();
    ArrayList<ProvincesAdapter> selectedProvince = new ArrayList<>();
    private String search_string = "";
    private String isMedicardOnly = "false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list_acitivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        databaseHandler = new DatabaseHandler(context);
        implement = new HospitalListRetrieve(context, databaseHandler);
        ButterKnife.bind(this);
        origin = getIntent().getExtras().getString(RequestButtonsActivity.ORIGIN);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_hospital.setLayoutManager(llm);
        sortBy = context.getString(R.string.medicard_first);

        if (NetworkTest.isOnline(context)) {
            ExclusionRetrieve.getHospInPatient(context, databaseHandler, SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context), this);
        } else
            alertDialogCustom.showMe(context, alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);

        hospitalAdapter = new HospitalAdapter(context, array);
        rv_hospital.setAdapter(hospitalAdapter);

        retrieveHosp("");
        tv_header.setText(HeaderNameSetter.setHeader(SharedPref.getStringValue(SharedPref.USER, SharedPref.DESTINATION, context)));

        ed_searchHosp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                retrieveHosp(String.valueOf(s));
                search_string = String.valueOf(s);
            }
        });


        btn_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalListAcitivity.this, SortHospitalActivity.class);
                intent.putExtra(Constant.MEDICARD_ONLY, isMedicardOnly);
                intent.putExtra(Constant.SORT_BY, sortBy);
                intent.putParcelableArrayListExtra(Constant.SELECTED_CITY, selectedCity);
                intent.putParcelableArrayListExtra(Constant.SELECTED_PROVINCE, selectedProvince);
                intent.putExtra(Constant.SEARCH_STRING, search_string);
                startActivityForResult(intent, SORT_CALL);
            }
        });
        hospitalAdapter.setClickListener(this);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void retrieveHosp(String s) {
        System.out.println("isMedicardOnly " + isMedicardOnly);
        System.out.println("selectedProvince " + selectedProvince);
        System.out.println("sortBy " + sortBy);
        System.out.println("selectedCity " + selectedCity);
        System.out.println("hospitalAdapter " + hospitalAdapter);
        System.out.println("s " + s);

        implement.updateList(isMedicardOnly,selectedProvince, sortBy, selectedCity, hospitalAdapter, array, s);
        implement.updateListUI(array, rv_hospital, tv_hosp_not_found);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SORT_CALL && resultCode == RESULT_OK) {
            isMedicardOnly = data.getStringExtra(Constant.MEDICARD_ONLY);
            sortBy = data.getStringExtra(Constant.SORT_BY);
            selectedCity = data.getParcelableArrayListExtra(Constant.SELECTED_CITY);
            selectedProvince = data.getParcelableArrayListExtra(Constant.SELECTED_PROVINCE);
            search_string = data.getStringExtra(Constant.SEARCH_STRING);


//            Log.d("sort_by", sortBy);
//            for (int x = 0; x < selectedCity.size(); x++) {
//                Log.d("selected_city", selectedCity.get(x).getCityName());
//            }
            ed_searchHosp.setText(search_string);
            retrieveHosp(search_string);
        }
    }

    @Override
    public void onClickListener(int position) {

        SharedPref.setStringValue(SharedPref.USER, SharedPref.HOSPITAL_NAME, array.get(position).getHospitalName(), context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.HOSPITAL_CODE, array.get(position).getHospitalCode(), context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.HOSPITAL_ADD, array.get(position).getStreetAddress(), context);

        SharedPref.setAppPreference(context, SharedPref.KEY_HOSPITAL_FULL_ADDRESS, array.get(position).getFullAddress());

        SharedPref.setStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT, array.get(position).getPhoneNo(), context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.HOSPITAL_CONTACT_PERSON, array.get(position).getContactPerson(), context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.HOSPITAL_U, "", context);

        SharedPref.setStringValue(SharedPref.USER, SharedPref.DOCTOR_NAME, Constant.NOT_SET, context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.DOCTOR_CODE, Constant.NOT_SET, context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.DOCTOR_DESC, Constant.NOT_SET, context);

        SharedPref.setStringValue(SharedPref.USER, SharedPref.DOCTOR_U, Constant.NOT_SET, context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.DOCTOR_ROOM, Constant.NOT_SET, context);

        Log.d(SharedPref.HOSPITAL_NAME, array.get(position).getHospitalName());
        Log.d(SharedPref.HOSPITAL_CODE, array.get(position).getHospitalCode());
        Log.d(SharedPref.HOSPITAL_ADD, array.get(position).getStreetAddress());


        Intent gotoDoctor = new Intent(context, DoctorListActivity.class);
        gotoDoctor.putExtra(RequestButtonsActivity.ORIGIN, origin);
        gotoDoctor.putExtra(Constant.MEMBER_ID, getIntent().getExtras().getString(Constant.MEMBER_ID));
        gotoDoctor.putExtra(Constant.GENDER, getIntent().getExtras().getString(Constant.GENDER));
        gotoDoctor.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.NAME));
        gotoDoctor.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
        gotoDoctor.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
        gotoDoctor.putExtra(Constant.AGE, getIntent().getExtras().getString(Constant.AGE));

        startActivity(gotoDoctor);
        finish();


    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onSuccess() {
        retrieveHosp("");
        /*alertDialogCustom.showMe(
                context,
                alertDialogCustom.HOLD_ON_title,
                getString(R.string.info_hospital),
                1);*/

    }


    @Override
    public void onBackPressed() {

    }
}
