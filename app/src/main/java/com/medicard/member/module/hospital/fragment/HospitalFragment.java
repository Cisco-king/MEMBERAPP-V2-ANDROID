package com.medicard.member.module.hospital.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.medicard.member.R;
import com.medicard.member.core.session.HospitalSession;
import com.medicard.member.module.base.BaseFragment;
import com.medicard.member.module.hospital.HospitalNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import core.callback.RecyclerViewOnClickListener;
import model.HospitalList;
import modules.hospital.adapter.HospitalDoctorAdapter;

/**
 * Created by casjohnpaul on 6/18/2017.
 */

public class HospitalFragment extends BaseFragment
        implements HospitalMvp.View, RecyclerViewOnClickListener {

    public static final String FROM_NEW_REQUEST = "fromNewRequest";

    @BindView(R.id.edSearchHospitalClinic) EditText edSearchHospitalClinic;
    @BindView(R.id.rvHospitalClinic) RecyclerView rvHospitalClinic;

    private HospitalMvp.Presenter presenter;
    private HospitalNavigator navigator;

    private HospitalDoctorAdapter hospitalAdapter;
    private List<HospitalList> hospitals;

    private boolean fromNewRequest = false;

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

        presenter = new HospitalPresenter(context);
        presenter.attachView(this);

        hospitals = new ArrayList<>();

        rvHospitalClinic.setLayoutManager(new LinearLayoutManager(context));

        presenter.loadHospitalClinic();

        edSearchHospitalClinic.addTextChangedListener(new Search());
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

    public class Search implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence search, int start, int before, int count) {
            if (search.length() > 0) {
                presenter.filterHospitals(hospitals, search.toString());
            } else {
                hospitalAdapter.update(hospitals);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
