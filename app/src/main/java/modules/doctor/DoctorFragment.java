package modules.doctor;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import adapter.DoctorAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import database.entity.Doctor;
import modules.doctor.adapter.DoctorsAdapter;
import modules.newtest.NewTestMvp;


public class DoctorFragment extends Fragment
        implements DoctorMvp.View, DoctorsAdapter.OnItemClickListener {


    @BindView(R.id.etSearchDoctor) EditText etSearchDoctor;

    @BindView(R.id.rvDoctors) RecyclerView rvDoctors;

    private NewTestMvp.View newTestNovigator;
    private DoctorMvp.Presenter presenter;

    private List<Doctor> doctors;

    private DoctorsAdapter doctorAdapter;


    public DoctorFragment() {
        // Required empty public constructor
    }

    public static DoctorFragment newInstance() {

        Bundle args = new Bundle();

        DoctorFragment fragment = new DoctorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewTestMvp.View) {
            newTestNovigator = (NewTestMvp.View) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DoctorPresenter(getContext());
        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doctor, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initComponents(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        presenter.detachCallback();
    }

    private void initComponents(View view) {
        rvDoctors.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.getAllDoctors();

        etSearchDoctor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                if (query.length() > 0) {
                    presenter.filterDoctors(doctors, query.toString());
                } else {
                    doctorAdapter.update(doctors);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void displayDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
        doctorAdapter = new DoctorsAdapter(getContext(), doctors, this);
        rvDoctors.setAdapter(doctorAdapter);
    }

    @Override
    public void displayFilteredDoctors(List<Doctor> doctors) {
        doctorAdapter.update(doctors);
    }

    @Override
    public void onItemClick(int position) {
        Doctor doctor = doctors.get(position);
        newTestNovigator.displayHospitalView(doctor);
    }

}
