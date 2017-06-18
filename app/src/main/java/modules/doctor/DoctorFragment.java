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

import com.google.gson.Gson;
import com.medicard.member.R;
import com.medicard.member.module.doctor.fragment.DoctorMvp;
import com.medicard.member.module.doctor.fragment.DoctorPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import modules.doctor.adapter.DoctorsAdapter;
import modules.newtest.NewTestMvp;
import services.model.HospitalsToDoctor;
import timber.log.Timber;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;
import utilities.Loader;
import utilities.SharedPref;


public class DoctorFragment extends Fragment
        implements DoctorMvp.View, DoctorsAdapter.OnItemClickListener {


    @BindView(R.id.etSearchDoctor)
    EditText etSearchDoctor;

    @BindView(R.id.rvDoctors)
    RecyclerView rvDoctors;

    private NewTestMvp.View newTestNovigator;
    private DoctorMvp.Presenter presenter;

    private Gson gson;

    private List<HospitalsToDoctor> doctors;

    private DoctorsAdapter doctorAdapter;

    private Loader loader;

    private AlertDialogCustom notificationMessage;


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

        gson = new Gson();

        notificationMessage = new AlertDialogCustom();
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

        loader = new Loader(getContext());

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

        loader.startLad();
        loader.setMessage("Loading resource...");
        presenter.loadAllDoctors();

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
    public void displayDoctorsByHospital(List<HospitalsToDoctor> doctors) {
        Timber.d("doctors total : %s", doctors.size());
        loader.stopLoad();

        this.doctors = doctors;

        doctorAdapter = new DoctorsAdapter(getContext(), doctors, this);
        rvDoctors.setAdapter(doctorAdapter);
        rvDoctors.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
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
    public void onErrorRequest(String message) {
        loader.stopLoad();
        notificationMessage.showMe(
                getContext(),
                notificationMessage.HOLD_ON_title,
                ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onItemClick(int position) {
        HospitalsToDoctor doctor = doctors.get(position);
        SharedPref.setAppPreference(getActivity(), SharedPref.KEY_DOCTOR, gson.toJson(doctor));
        newTestNovigator.displayHospitalView(doctor);
    }

}
