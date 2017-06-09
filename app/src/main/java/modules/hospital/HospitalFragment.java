package modules.hospital;


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

import com.google.gson.Gson;
import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.callback.RecyclerViewOnClickListener;
import database.dao.DoctorDao;
import model.HospitalList;
import modules.hospital.adapter.HospitalDoctorAdapter;
import modules.newtest.NewTestMvp;
import services.model.HospitalsToDoctor;
import timber.log.Timber;
import utilities.SharedPref;


public class HospitalFragment extends Fragment
        implements HospitalMvp.View, RecyclerViewOnClickListener {

    public static final String KEY_DOCTOR = "doctor";

    @BindView(R.id.edSearchHospitalClinic) EditText edSearchHospitalClinic;
    @BindView(R.id.rvHospitalClinic) RecyclerView rvHospitalClinic;

    private NewTestMvp.View newTestNavigator;

    private HospitalsToDoctor doctor;
    private List<HospitalList> hospitals;

    private HospitalDoctorAdapter hospitalAdapter;

    private Gson gson;
    private HospitalMvp.Presenter presenter;

    public HospitalFragment() {

    }

    public static HospitalFragment newInstance(HospitalsToDoctor doctor) {

        Bundle args = new Bundle();
        args.putParcelable(KEY_DOCTOR, doctor);

        HospitalFragment fragment = new HospitalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewTestMvp.View) {
            newTestNavigator = (NewTestMvp.View) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            doctor = bundle.getParcelable(KEY_DOCTOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hospital, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initComponents(view);
    }

    private void initComponents(View view) {
        hospitals = new ArrayList<>();
        gson = new Gson();

        presenter = new HospitalPresenter(getContext());
        presenter.attachView(this);

        rvHospitalClinic.setLayoutManager(new LinearLayoutManager(getContext()));
        Timber.d("doctorCode %s", doctor.getDoctorCode());

        presenter.loadHospitalClinic();

        edSearchHospitalClinic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence query, int start, int count, int after) {
                if (query.length() > 0) {
                    presenter.filterHospitals(hospitals, query.toString());
                } else {
                    hospitalAdapter.update(hospitals);
                }
            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                if (query.length() > 0) {
                    presenter.filterHospitals(hospitals, query.toString());
                } else {
                    hospitalAdapter.update(hospitals);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void displayHospitalClinic(List<HospitalList> hospitals) {
        this.hospitals = hospitals;
        hospitalAdapter = new HospitalDoctorAdapter(getContext(), hospitals, this);

        rvHospitalClinic.setAdapter(hospitalAdapter);
    }

    @Override
    public void displayFilterHospitalClinics(List<HospitalList> hospitalLists) {
        hospitalAdapter.update(hospitalLists);
    }

    @Override
    public void onItemClick(int position) {
        Timber.d("item click position %s", position);
        SharedPref.setAppPreference(getContext(), SharedPref.KEY_HOSPITAL, gson.toJson(hospitals.get(position)));
        newTestNavigator.displayDiagnosis(hospitals.get(position));
    }

}
