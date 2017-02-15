package v2;

import android.content.Context;
import android.content.Intent;
import android.medicard.com.medicard.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


import InterfaceService.ProvinceRetrieve;
import Sqlite.DatabaseHandler;
import adapter.CityAdapter;
import adapter.ProvinceAdapter;
import adapter.SpecializationAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

import model.CitiesAdapter;
import model.Provinces;
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

    DatabaseHandler handler;

    AlertDialogCustom alertDialogCustom;
    ProvinceAdapter adapter;
    CityAdapter adapterCity;
    SpecializationAdapter adapterSpecs;
    ProvinceRetrieve implement;
    private ArrayList<Provinces> arrayProvince = new ArrayList<>();
    ArrayList<CitiesAdapter> arrayCity = new ArrayList<>();
    private ArrayList<CitiesAdapter> selectedCity = new ArrayList<>();
    private ArrayList<CitiesAdapter> prevSelectedCity = new ArrayList<>();
    private ArrayList<Provinces> selectedProvince = new ArrayList<>();
    private ArrayList<SpecsAdapter> arraySpecialization = new ArrayList<>();
    private ArrayList<SpecsAdapter> selectedSpecialization = new ArrayList<>();
    private ArrayList<SpecsAdapter> prevSelectedSpecialization = new ArrayList<>();
    Context context;
    ProvinceAdapter.ProvinceInterface callback;
    String ORIGIN = "";
    String PROVINCE_CODE = "";

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


        rv_provinces.setLayoutManager(new StaggeredGridLayoutManager(2, 1));

        if (implement.testOriginFromCity(ORIGIN)) {
            tv_header.setText("City");
            PROVINCE_CODE = getIntent().getStringExtra(Constant.SELECT_CODE);
            arrayCity.addAll(implement.setArrayCity(handler, PROVINCE_CODE));
            prevSelectedCity = getIntent().getParcelableArrayListExtra(Constant.SELECTED_CITY);
            implement.setSelectedData(prevSelectedCity, arrayCity, selectedCity);
            adapterCity = new CityAdapter(context, arrayCity, selectedCity, callback);
            rv_provinces.setAdapter(adapterCity);
        } else if (implement.testOriginFromSpecialization(ORIGIN)) {
            tv_header.setText("Specialization");
            arraySpecialization.addAll(implement.setArraySpecs(handler));
            prevSelectedSpecialization = getIntent().getParcelableArrayListExtra(Constant.SELECTED_SPECIALIZATION);
            implement.setSelectedDataSpecs(prevSelectedSpecialization, arraySpecialization, selectedSpecialization);
            adapterSpecs = new SpecializationAdapter(context, arraySpecialization, selectedSpecialization, callback);
            rv_provinces.setAdapter(adapterSpecs);
        } else {
            tv_header.setText("Province");
            arrayProvince.addAll(implement.setArrayData(handler));
            adapter = new ProvinceAdapter(context, arrayProvince, callback);
            rv_provinces.setAdapter(adapter);
        }

        implement.setOkVISIBILITY(implement.testOriginFromCity(ORIGIN), implement.testOriginFromSpecialization(ORIGIN), btn_ok);


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
                    setResult(RESULT_OK, intent);
                    finish();
                    //  }
                } else if (implement.testOriginFromSpecialization(ORIGIN)) {
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra("SPECIALIZATION", selectedSpecialization);
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
        setResult(RESULT_OK, intent);
        Log.d("PROVINCE_SEL", selectedProvince.get(0).getProvinceName());
        finish();

    }


}
