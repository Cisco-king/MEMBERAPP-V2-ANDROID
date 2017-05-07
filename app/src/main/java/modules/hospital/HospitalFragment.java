package modules.hospital;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.medicard.member.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import database.entity.Doctor;
import model.HospitalList;
import modules.newtest.NewTestMvp;


public class HospitalFragment extends Fragment implements HospitalMvp.View {

    public static final String KEY_DOCTOR = "doctor";

    @BindView(R.id.edSearchHospitalClinic) EditText edSearchHospitalClinic;
    @BindView(R.id.rvHospitalClinic) RecyclerView rvHospitalClinic;

    private NewTestMvp.View newTestNavigator;

    private Doctor doctor;

    private HospitalMvp.Presenter presenter;

    public HospitalFragment() {

    }

    public static HospitalFragment newInstance(Doctor doctor) {

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
        presenter = new HospitalPresenter(getContext());
        presenter.attachView(this);

        rvHospitalClinic.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayHospitalClinic(List<HospitalList> hospitals) {

    }

}
