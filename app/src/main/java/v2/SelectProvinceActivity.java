package v2;

import android.content.Context;
import android.content.Intent;
import com.medicard.member.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;


import InterfaceService.ProvinceRetrieve;
import Sqlite.DatabaseHandler;
import adapter.CityAdapter;
import adapter.LoaReqAdapter;
import adapter.ProvinceAdapter;
import adapter.SpecializationAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

import model.CitiesAdapter;
import model.LoaFetch;
import model.Provinces;
import model.SimpleData;
import model.SpecsAdapter;
import utilities.AlertDialogCustom;
import utilities.Constant;


public class SelectProvinceActivity extends AppCompatActivity implements ProvinceAdapter.ProvinceInterface {
    @BindView(R.id.rv_provinces)
    RecyclerView rv_provinces;
    @BindView(R.id.btn_ok)
    FancyButton btn_ok;
    @BindView(R.id.btn_back)
    FancyButton btn_back;
    @BindView(R.id.tv_header)
    TextView tv_header;
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.linearLayout3)
    LinearLayout linearLayout3;

    DatabaseHandler handler;

    AlertDialogCustom alertDialogCustom;
    ProvinceAdapter adapter;
    CityAdapter adapterCity;
    SpecializationAdapter adapterSpecs;
    LoaReqAdapter adapterLoa;
    ProvinceRetrieve implement;
    private ArrayList<Provinces> arrayProvince = new ArrayList<>();
    ArrayList<CitiesAdapter> arrayCity = new ArrayList<>();
    private ArrayList<CitiesAdapter> selectedCity = new ArrayList<>();
    private ArrayList<CitiesAdapter> prevSelectedCity = new ArrayList<>();
    private ArrayList<Provinces> selectedProvince = new ArrayList<>();
    private ArrayList<SpecsAdapter> arraySpecialization = new ArrayList<>();
    private ArrayList<SpecsAdapter> selectedSpecialization = new ArrayList<>();
    private ArrayList<SpecsAdapter> prevSelectedSpecialization = new ArrayList<>();

    private ArrayList<SimpleData> arrayHospitals = new ArrayList<>();
    private ArrayList<LoaFetch> arrayListMaster = new ArrayList<>();
    private ArrayList<SimpleData> prevSelectedHosp = new ArrayList<>();

    private ArrayList<SimpleData> prevSelectedDoctor = new ArrayList<>();
    private ArrayList<SimpleData> arrayDoctors = new ArrayList<>();

    Context context;
    ProvinceAdapter.ProvinceInterface callback;
    String ORIGIN = "";
    String PROVINCE_CODE = "";
    String SPEC_SEARCH = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_province);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        context = this;
        handler = new DatabaseHandler(context);
        implement = new ProvinceRetrieve(context);
        callback = this;
        alertDialogCustom = new AlertDialogCustom();
        ORIGIN = getIntent().getStringExtra(Constant.SELECT);
        implement.setOkVISIBILITY(implement.testOriginFromCity(ORIGIN), implement.testOriginFromSpecialization(ORIGIN), btn_ok);
        SPEC_SEARCH = getIntent().getStringExtra(Constant.SPEC_SEARCH);
        ed_search.setText(SPEC_SEARCH);
        if (implement.testOriginFromCity(ORIGIN)) {
            rv_provinces.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
            tv_header.setText("City");
            PROVINCE_CODE = getIntent().getStringExtra(Constant.SELECT_CODE);
            arrayCity.addAll(implement.setArrayCity(handler, PROVINCE_CODE, SPEC_SEARCH));
            prevSelectedCity = getIntent().getParcelableArrayListExtra(Constant.SELECTED_CITY);
            implement.setSelectedData(prevSelectedCity, arrayCity, selectedCity);
            adapterCity = new CityAdapter(context, arrayCity, selectedCity, callback);
            rv_provinces.setAdapter(adapterCity);
        } else if (implement.testOriginFromSpecialization(ORIGIN)) {
            rv_provinces.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
            tv_header.setText("Specialization");
            arraySpecialization.addAll(implement.setArraySpecs(handler, SPEC_SEARCH));
            prevSelectedSpecialization = getIntent().getParcelableArrayListExtra(Constant.SELECTED_SPECIALIZATION);
            implement.setSelectedDataSpecs(prevSelectedSpecialization, arraySpecialization, selectedSpecialization);
            adapterSpecs = new SpecializationAdapter(context, arraySpecialization, selectedSpecialization, callback);
            rv_provinces.setAdapter(adapterSpecs);
        } else if (implement.testOriginFromProvince(ORIGIN)) {
            rv_provinces.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
            tv_header.setText("Province");
            arrayProvince.addAll(implement.setArrayData(handler, SPEC_SEARCH));
            adapter = new ProvinceAdapter(context, arrayProvince, callback);
            rv_provinces.setAdapter(adapter);
        } else if (implement.testOriginFromLoaReq(ORIGIN)) {
            rv_provinces.setLayoutManager(new LinearLayoutManager(this));
            tv_header.setText("Hospital");
            ArrayList<LoaFetch> temp;
            temp = getIntent().getParcelableArrayListExtra(Constant.LOA_REQUEST);
            arrayListMaster.addAll(temp);
            Log.d("COUNT_LOA", arrayListMaster.size() + "");
            Log.d("HOSP_GET_NAME", arrayListMaster.get(0).getHospitalName());
            arrayHospitals.addAll(implement.getOnlyHospitalWithOneCount(arrayListMaster));
            prevSelectedHosp = getIntent().getParcelableArrayListExtra(Constant.SELECTED_REQUEST);
            implement.tagSelectedToMasterList(prevSelectedHosp, arrayHospitals);
            adapterLoa = new LoaReqAdapter(context, arrayHospitals, callback);
            rv_provinces.setAdapter(adapterLoa);
            implement.setOkVISIBILITY(true, true, btn_ok);
        } else if (implement.testOriginFromDoctors(ORIGIN)) {
            rv_provinces.setLayoutManager(new LinearLayoutManager(this));
            tv_header.setText("Doctors");
            ArrayList<LoaFetch> temp;
            temp = getIntent().getParcelableArrayListExtra(Constant.DOCTOR_LIST);
            arrayListMaster.addAll(temp);
            arrayDoctors.addAll(implement.getOnlyDoctorWithOneCount(arrayListMaster));
            prevSelectedDoctor = getIntent().getParcelableArrayListExtra(Constant.SELECTED_DOCTOR);
            implement.tagSelectedToMasterList(prevSelectedDoctor, arrayDoctors);
            adapterLoa = new LoaReqAdapter(context, arrayDoctors, callback);
            rv_provinces.setAdapter(adapterLoa);
            implement.setOkVISIBILITY(true, true, btn_ok);

        }


        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (implement.testOriginFromCity(ORIGIN)) {
                    SPEC_SEARCH = String.valueOf(charSequence);
                    arrayCity.clear();
                    arrayCity.addAll(implement.setArrayCity(handler, PROVINCE_CODE, SPEC_SEARCH));
                    implement.setSelectedData( arrayCity, selectedCity);
                    adapterCity.notifyDataSetChanged();
                } else if (implement.testOriginFromSpecialization(ORIGIN)) {
                    SPEC_SEARCH = String.valueOf(charSequence);
                    arraySpecialization.clear();

                    arraySpecialization.addAll(implement.setArraySpecs(handler, SPEC_SEARCH));
                    implement.setSelectedDataSpecs(prevSelectedSpecialization, arraySpecialization, selectedSpecialization);
                    adapterSpecs.notifyDataSetChanged();
                } else if (implement.testOriginFromProvince(ORIGIN)) {
                    SPEC_SEARCH = String.valueOf(charSequence);
                    arrayProvince.clear();

                    arrayProvince.addAll(implement.setArrayData(handler, SPEC_SEARCH));
                    implement.setSelectedDataSpecs(prevSelectedSpecialization, arraySpecialization, selectedSpecialization);
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @OnClick({R.id.btn_back, R.id.btn_ok})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_back:
                finish();

                break;
            case R.id.btn_ok:
                if (implement.testOriginFromCity(ORIGIN)) {
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra("CITY", selectedCity);
                    intent.putExtra(Constant.SPEC_SEARCH ,SPEC_SEARCH);
                    setResult(RESULT_OK, intent);
                    finish();
                    //  }
                } else if (implement.testOriginFromSpecialization(ORIGIN)) {
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra("SPECIALIZATION", selectedSpecialization);
                    setResult(RESULT_OK, intent);
                    finish();
                } else if (implement.testOriginFromLoaReq(ORIGIN)) {
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra("LOA_REQUEST", arrayHospitals);
                    setResult(RESULT_OK, intent);
                    finish();
                } else if (implement.testOriginFromDoctors(ORIGIN)) {
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra("DOCTOR", arrayDoctors);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;


        }
    }

    @Override
    public void onCLickItemListenerProvince(int position) {
        selectedProvince.add(arrayProvince.get(position));

        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("PROVINCE", selectedProvince);
        intent.putExtra(Constant.SPEC_SEARCH ,SPEC_SEARCH);
        setResult(RESULT_OK, intent);
        Log.d("PROVINCE_SEL", selectedProvince.get(0).getProvinceName());
        finish();

    }


}
