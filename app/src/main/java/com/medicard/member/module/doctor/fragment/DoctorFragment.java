package com.medicard.member.module.doctor.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.medicard.member.R;
import com.medicard.member.core.session.DoctorSession;
import com.medicard.member.module.base.BaseFragment;
import com.medicard.member.module.doctor.DoctorNavigator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import InterfaceService.DoctorRetrieve;
import Sqlite.DatabaseHandler;
import butterknife.BindView;
import model.SpecsAdapter;
import modules.doctor.adapter.AllDoctorsAdapter;
import modules.doctor.adapter.DoctorsAdapter;
import services.model.Doctor;
import services.model.DoctorList;
import services.model.HospitalsToDoctor;
import timber.log.Timber;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;
import utilities.Loader;

/**
 * Created by casjohnpaul on 6/18/2017.
 */

public class DoctorFragment extends BaseFragment
        implements DoctorMvp.View, DoctorsAdapter.OnItemClickListener, AllDoctorsAdapter.OnItemClickListener {

    public static final String FROM_NEW_REQUEST = "newRequest";

    @BindView(R.id.etSearchDoctor)
    EditText etSearchDoctor;
    @BindView(R.id.rvDoctors)
    RecyclerView rvDoctors;


    private DoctorMvp.Presenter presenter;
    private DoctorNavigator doctorNavigator;

    private AlertDialogCustom notificationDialog;

    private DoctorsAdapter doctorAdapter;

    private AllDoctorsAdapter allDoctorsAdapter;



    private List<HospitalsToDoctor> doctors;

    private List<HospitalsToDoctor> doctorLists;

    private Loader loader;

    private boolean isFromNewRequest = false;

    public static DoctorFragment newInstance(boolean isFromDoctor) {

        Bundle args = new Bundle();
        args.putBoolean(FROM_NEW_REQUEST, isFromDoctor);

        DoctorFragment fragment = new DoctorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DoctorFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DoctorNavigator) {
            doctorNavigator = (DoctorNavigator) context;
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_doctor;
    }

    @Override
    protected void initComponents(View view, Bundle bundle) {
        super.initComponents(view, bundle);

        isFromNewRequest = getArguments().getBoolean(FROM_NEW_REQUEST, false);

        presenter = new DoctorPresenter(getActivity());
        presenter.attachView(this);

        notificationDialog = new AlertDialogCustom();

        rvDoctors.setLayoutManager(new LinearLayoutManager(context));

        loader = new Loader(context);
        loader.startLad();
        loader.setMessage("Loading ...");

        presenter.loadAllDoctors();

//        presenter.loadAllDoctors();

        loader.stopLoad();
        etSearchDoctor.addTextChangedListener(new DoctorFilter());
    }

    @Override
    public void displayDoctorsByHospital(List<HospitalsToDoctor> doctors) {
        loader.stopLoad();

        this.doctors = doctors;

        doctorAdapter = new DoctorsAdapter(context, doctors,this);
        rvDoctors.setAdapter(doctorAdapter);
    }

    @Override
    public void displayAllDoctors(List<HospitalsToDoctor> doctorLists) {
        loader.stopLoad();

        this.doctorLists = doctorLists;
        allDoctorsAdapter = new AllDoctorsAdapter(context,doctorLists,this);
        rvDoctors.setAdapter(allDoctorsAdapter);


    }

    @Override
    public void displayFilteredDoctors(List<HospitalsToDoctor> doctors) {
        if (doctors != null && doctors.size() > 0) {
            doctorAdapter.update(doctors);
        } else {
            doctorAdapter.update(this.doctors);
        }
    }

    @Override
    public void onErrorRequest(String error) {
        loader.stopLoad();
        notificationDialog.showMe(
                context, notificationDialog.HOLD_ON_title, ErrorMessage.setErrorMessage(error), 1);
    }

    @Override
    public void onItemClick(int position) {
        try{
            HospitalsToDoctor doctor = allDoctorsAdapter.getDoctor(position);
            Timber.d("doctor %s", doctor.getFullName());
            DoctorSession.setDoctor(doctor);

        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            HospitalsToDoctor doctor = doctorAdapter.getDoctor(position);
            Timber.d("doctor %s", doctor.getFullName());
            DoctorSession.setDoctor(doctor);
        }catch (Exception e){

        }

        if (isFromNewRequest) {
            doctorNavigator.fromNewRequestReselected();
        } else {
            doctorNavigator.onDoctorSelected();
        }
    }



    public class DoctorFilter implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence search, int start, int before, int count) {
            if (search.length() > 0) {
                    presenter.loadDoctorsByName(search.toString());
//                presenter.filterDoctors(doctors, search.toString());
            } else {

                //doctorAdapter.update(doctors);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}
