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
import android.support.v7.widget.ViewUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


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
import model.ProvincesAdapter;
import model.SimpleData;
import model.SpecsAdapter;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.ViewUtilities;


public class SelectProvinceActivity extends AppCompatActivity {
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

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    DatabaseHandler handler;

    AlertDialogCustom alertDialogCustom;
    ProvinceAdapter adapter;
    CityAdapter adapterCity;
    SpecializationAdapter adapterSpecs;
    LoaReqAdapter adapterLoa;

    ProvinceRetrieve implement;

    private ArrayList<ProvincesAdapter> arrayProvince = new ArrayList<>();
    ArrayList<CitiesAdapter> arrayCity = new ArrayList<>();
    private ArrayList<CitiesAdapter> selectedCity = new ArrayList<>();
    private ArrayList<CitiesAdapter> prevSelectedCity = new ArrayList<>();
    private ArrayList<ProvincesAdapter> prevSelectedProvince = new ArrayList<>();
    private ArrayList<ProvincesAdapter> selectedProvince = new ArrayList<>();
    private ArrayList<SpecsAdapter> arraySpecialization = new ArrayList<>();
    private ArrayList<SpecsAdapter> selectedSpecialization = new ArrayList<>();
    private ArrayList<SpecsAdapter> prevSelectedSpecialization = new ArrayList<>();

    // TO BE SEARCH
    private ArrayList<SimpleData> arrayHospitals = new ArrayList<>();
    private ArrayList<LoaFetch> arrayListMaster = new ArrayList<>();
    private ArrayList<SimpleData> prevSelectedHosp = new ArrayList<>();

    private ArrayList<SimpleData> prevSelectedDoctor = new ArrayList<>();
    private ArrayList<SimpleData> arrayDoctors = new ArrayList<>();

    Context context;

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

        alertDialogCustom = new AlertDialogCustom();
        ORIGIN = getIntent().getStringExtra(Constant.SELECT);
//        SPEC_SEARCH = getIntent().getStringExtra(Constant.SPEC_SEARCH);
        SPEC_SEARCH = "";

        if (implement.testOriginFromCity(ORIGIN)) {
            rv_provinces.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
            tv_header.setText("City");
            ed_search.setHint("Search City...");
            prevSelectedProvince = getIntent().getParcelableArrayListExtra(Constant.SELECT_CODE);
            arrayCity.addAll(implement.setArrayCity(handler, prevSelectedProvince, SPEC_SEARCH));
            prevSelectedCity = getIntent().getParcelableArrayListExtra(Constant.SELECTED_CITY);
            implement.setSelectedData(prevSelectedCity, arrayCity, selectedCity);
            adapterCity = new CityAdapter(context, arrayCity, selectedCity);
            rv_provinces.setAdapter(adapterCity);
        } else if (implement.testOriginFromSpecialization(ORIGIN)) {
            rv_provinces.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
            tv_header.setText("Specialization");
            ed_search.setHint("Search Specialization...");
            arraySpecialization.addAll(implement.setArraySpecs(handler, SPEC_SEARCH));
            prevSelectedSpecialization = getIntent().getParcelableArrayListExtra(Constant.SELECTED_SPECIALIZATION);
            implement.setSelectedDataSpecs(prevSelectedSpecialization, arraySpecialization, selectedSpecialization);
            adapterSpecs = new SpecializationAdapter(context, arraySpecialization, selectedSpecialization);
            rv_provinces.setAdapter(adapterSpecs);
        } else if (implement.testOriginFromProvince(ORIGIN)) {
            rv_provinces.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
            tv_header.setText("Province");
            ed_search.setHint("Search Province...");
            arrayProvince.addAll(implement.setArrayData(handler, SPEC_SEARCH));
            prevSelectedProvince = getIntent().getParcelableArrayListExtra(Constant.SELECTED_PROVINCE);
            implement.setSelectedProvinceData(prevSelectedProvince, arrayProvince, selectedProvince);
            adapter = new ProvinceAdapter(context, arrayProvince, selectedProvince);
            rv_provinces.setAdapter(adapter);
        } else if (implement.testOriginFromLoaReq(ORIGIN)) {
            rv_provinces.setLayoutManager(new LinearLayoutManager(this));
            tv_header.setText("Hospital");
            ed_search.setHint("Search Hospital...");
            ArrayList<LoaFetch> temp;
            temp = getIntent().getParcelableArrayListExtra(Constant.LOA_REQUEST);
            arrayListMaster.addAll(temp);
            Log.d("COUNT_LOA", arrayListMaster.size() + "");

            arrayHospitals.addAll(implement.getOnlyHospitalWithOneCount(arrayListMaster));
            prevSelectedHosp = getIntent().getParcelableArrayListExtra(Constant.SELECTED_REQUEST);

            implement.tagSelectedToMasterList(prevSelectedHosp, arrayHospitals);
            adapterLoa = new LoaReqAdapter(context, arrayHospitals);
            if (arrayHospitals != null) {
                if (arrayHospitals.size() != 0) {

                    ViewUtilities.hideView(tvNoData);


                    rv_provinces.setAdapter(adapterLoa);
                } else {
                    ViewUtilities.hideView(rv_provinces);
                    ViewUtilities.showView(tvNoData);
                }
            } else {
                ViewUtilities.hideView(rv_provinces);
                ViewUtilities.showView(tvNoData);
            }

            implement.setOkVISIBILITY(true, true, btn_ok);
        } else if (implement.testOriginFromDoctors(ORIGIN)) {
            rv_provinces.setLayoutManager(new LinearLayoutManager(this));
            tv_header.setText("Doctors");
            ed_search.setHint("Search Doctors...");
            ArrayList<LoaFetch> temp;
            temp = getIntent().getParcelableArrayListExtra(Constant.DOCTOR_LIST);
            arrayListMaster.addAll(temp);
            ArrayList<SimpleData> temp1;
            temp1 = getIntent().getParcelableArrayListExtra(Constant.SELECTED_HOSPITAL);
            prevSelectedHosp.clear();
            prevSelectedHosp.addAll(temp1);
            for (SimpleData simpleData : prevSelectedHosp) {
                Log.d("TAG_DATA", "onCreate: " + simpleData.getHospital());
            }
            arrayDoctors.addAll(implement.getOnlyDoctorWithOneCount(prevSelectedHosp, handler));
            prevSelectedDoctor = getIntent().getParcelableArrayListExtra(Constant.SELECTED_DOCTOR);
            implement.tagSelectedToMasterList(prevSelectedDoctor, arrayDoctors);
            adapterLoa = new LoaReqAdapter(context, arrayDoctors);
            rv_provinces.setAdapter(adapterLoa);


            if (arrayDoctors != null) {
                if (arrayDoctors.size() != 0) {

                    ViewUtilities.hideView(tvNoData);


                    rv_provinces.setAdapter(adapterLoa);
                } else {
                    ViewUtilities.hideView(rv_provinces);
                    ViewUtilities.showView(tvNoData);
                }
            } else {
                ViewUtilities.hideView(rv_provinces);
                ViewUtilities.showView(tvNoData);
            }


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
                    arrayCity.addAll(implement.setArrayCity(handler, prevSelectedProvince, SPEC_SEARCH));
                    implement.setSelectedData(arrayCity, selectedCity);
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
                    implement.setSelectedDataProvince(arrayProvince, selectedProvince);
                    adapter.notifyDataSetChanged();
                } else if (implement.testOriginFromLoaReq(ORIGIN)) {
                    SPEC_SEARCH = String.valueOf(charSequence);

                    adapterLoa.filterList(filter(arrayHospitals, SPEC_SEARCH));
                    implement.tagSelectedToMasterList(prevSelectedHosp, arrayHospitals);
                } else if (implement.testOriginFromDoctors(ORIGIN)) {

                    SPEC_SEARCH = String.valueOf(charSequence);

                    Log.d("filtertest", "onTextChanged: " + SPEC_SEARCH);
                    adapterLoa.filterList(filter(arrayDoctors, SPEC_SEARCH));
                    implement.tagSelectedToMasterList(prevSelectedDoctor, arrayDoctors);
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
                    intent.putExtra(Constant.SPEC_SEARCH, SPEC_SEARCH);
                    setResult(RESULT_OK, intent);
                    finish();
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
                } else if (implement.testOriginFromProvince(ORIGIN)) {
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra("PROVINCE", selectedProvince);
                    intent.putExtra(Constant.SPEC_SEARCH, SPEC_SEARCH);
                    setResult(RESULT_OK, intent);
                    finish();
                }

                break;
        }
    }

    private ArrayList<SimpleData> filter(ArrayList<SimpleData> list, String query) {
        Log.d("testfilter", "filter: " + list.size());
        query = query.toLowerCase();
        ArrayList<SimpleData> temp = new ArrayList<>();

        if (!query.isEmpty()) {
            Log.d("testfilter", "filter: not empty");
            for (SimpleData data : list) {
                final String text = data.getHospital().toLowerCase();
                if (text.contains(query)) {
                    temp.add(data);
                }
            }
        } else {
            Log.d("testfilter", "filter: empty");
            temp = list;
        }

        return temp;
    }


}
