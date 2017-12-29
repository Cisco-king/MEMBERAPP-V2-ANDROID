package com.medicard.member.module.hospital.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.core.session.HospitalSession;
import com.medicard.member.module.base.BaseFragment;
import com.medicard.member.module.hospital.HospitalNavigator;

import java.util.ArrayList;
import java.util.List;

import InterfaceService.HospitalListRetrieve;
import Sqlite.DatabaseHandler;
import butterknife.BindView;
import core.callback.RecyclerViewOnClickListener;
import mehdi.sakout.fancybuttons.FancyButton;
import model.CitiesAdapter;
import model.HospitalList;
import model.ProvincesAdapter;
import modules.hospital.adapter.HospitalDoctorAdapter;
import utilities.Constant;
import v2.SortHospitalActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by casjohnpaul on 6/18/2017.
 */

public class HospitalFragment extends BaseFragment
        implements HospitalMvp.View, RecyclerViewOnClickListener, View.OnClickListener {

    public static final String FROM_NEW_REQUEST = "fromNewRequest";

    @BindView(R.id.edSearchHospitalClinic)
    EditText edSearchHospitalClinic;
    @BindView(R.id.rvHospitalClinic)
    RecyclerView rvHospitalClinic;
    @BindView(R.id.btn_sort)
    FancyButton btn_sort;
    @BindView(R.id.tv_hosp_not_found)
    TextView tv_hosp_not_found;

    private HospitalMvp.Presenter presenter;
    private HospitalNavigator navigator;

    private HospitalDoctorAdapter hospitalAdapter;
    private List<HospitalList> hospitals;

    private boolean fromNewRequest = false;

    ArrayList<CitiesAdapter> selectedCity = new ArrayList<>();
    ArrayList<ProvincesAdapter> selectedProvince = new ArrayList<>();
    ArrayList<HospitalList> arraylistHospital = new ArrayList<>();
    private String isMedicardOnly = "false";
    String sortBy = "";
    private int SORT_CALL = 100;
    private String search_string = "";


    HospitalListRetrieve implement;
    DatabaseHandler databaseHandler;

    public static HospitalFragment newInstance(boolean fromHospital) {

        Bundle args = new Bundle();
        args.putBoolean(FROM_NEW_REQUEST, fromHospital);

        HospitalFragment fragment = new HospitalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public HospitalFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HospitalNavigator) {
            navigator = (HospitalNavigator) context;
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_hospital;
    }

    @Override
    protected void initComponents(View view, Bundle savedInstanceState) {
        super.initComponents(view, savedInstanceState);
        fromNewRequest = getArguments().getBoolean(FROM_NEW_REQUEST, false);
        databaseHandler = new DatabaseHandler(context);
        implement = new HospitalListRetrieve(context, databaseHandler);
        presenter = new HospitalPresenter(context);
        presenter.attachView(this);

        hospitals = new ArrayList<>();

        rvHospitalClinic.setLayoutManager(new LinearLayoutManager(context));

        presenter.loadHospitalClinic();

        edSearchHospitalClinic.addTextChangedListener(new Search());
        btn_sort.setOnClickListener(this);

    }


    @Override
    public void displayHospitalClinic(List<HospitalList> hospitals) {
        this.hospitals = hospitals;
        hospitalAdapter = new HospitalDoctorAdapter(context, hospitals, this);
        rvHospitalClinic.setAdapter(hospitalAdapter);
    }

    @Override
    public void displayFilterHospitalClinics(List<HospitalList> hospitalLists) {
        hospitalAdapter.update(hospitalLists);
    }

    @Override
    public void onItemClick(int position) {
        HospitalList hospital = hospitalAdapter.getHospital(position);
//        HospitalList hospital = hospitals.get(position);
        HospitalSession.setHospital(hospital);
        if (fromNewRequest) {
            navigator.onHospitalReselected();
        } else {
            navigator.onHospitalSelected();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sort:
                Intent intent = new Intent(getActivity(), SortHospitalActivity.class);
                intent.putExtra(Constant.MEDICARD_ONLY, isMedicardOnly);
                intent.putExtra(Constant.SORT_BY, sortBy);
                intent.putParcelableArrayListExtra(Constant.SELECTED_CITY, selectedCity);
                intent.putParcelableArrayListExtra(Constant.SELECTED_PROVINCE, selectedProvince);
                intent.putExtra(Constant.SEARCH_STRING, search_string);
                startActivityForResult(intent, SORT_CALL);
                break;
        }
    }

    public class Search implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence search, int start, int before, int count) {
//            if (search.length() > 0) {
//                presenter.filterHospitals(hospitals, search.toString());
//            } else {
//                hospitalAdapter.update(hospitals);
//            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            retrieveHosp(String.valueOf(s));
            search_string = String.valueOf(s);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
//           }
            edSearchHospitalClinic.setText(search_string);
            retrieveHosp(search_string);
        }
    }

    private void retrieveHosp(final String s) {
        System.out.println("isMedicardOnly " + isMedicardOnly);
        System.out.println("selectedProvince " + selectedProvince);
        System.out.println("sortBy " + sortBy);
        System.out.println("selectedCity " + selectedCity);
        System.out.println("hospitalAdapter " + hospitalAdapter);
        System.out.println("s " + s);
        implement.updateListForTest(isMedicardOnly, selectedProvince, sortBy, selectedCity,hospitalAdapter, hospitals, s);
        hospitalAdapter.notifyDataSetChanged();
        implement.updateListUIForTest(hospitals, rvHospitalClinic);
    }
}
