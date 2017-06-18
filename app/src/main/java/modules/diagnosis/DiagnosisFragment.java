package modules.diagnosis;


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
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.module.diagnosis.fragment.DiagnosisMvp;
import com.medicard.member.module.diagnosis.fragment.DiagnosisPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.callback.RecyclerViewOnClickListener;
import model.HospitalList;
import modules.diagnosis.adapter.DiagnosisAdapter;
import modules.newtest.NewTestMvp;
import services.model.Diagnosis;
import timber.log.Timber;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;
import utilities.Loader;
import utilities.ViewUtilities;

public class DiagnosisFragment extends Fragment
        implements DiagnosisMvp.View, RecyclerViewOnClickListener {
    @BindView(R.id.edSearchDiagnosis) TextView edSearchDiagnosis;

    @BindView(R.id.rvHospitalDiagnosis) RecyclerView rvHospitalDiagnosis;

    @BindView(R.id.tvMessage) TextView tvMessage;

    NewTestMvp.View newTestNavigator;

    DiagnosisAdapter diagnosisAdapter;

    private DiagnosisMvp.Presenter presenter;

    private Loader loader;

    private AlertDialogCustom notificationMessage;

    List<Diagnosis> diagnosisList;


    public DiagnosisFragment() {

    }

    public static DiagnosisFragment newInstance(HospitalList hospital) {
        
        Bundle args = new Bundle();
        
        DiagnosisFragment fragment = new DiagnosisFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diagnosis, container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        diagnosisList = new ArrayList<>();

        rvHospitalDiagnosis.setLayoutManager(new LinearLayoutManager(getContext()));

        notificationMessage = new AlertDialogCustom();
        loader = new Loader(getContext());

        initComponents(view);
    }

    private void initComponents(View view) {
        presenter = new DiagnosisPresenter(getContext());
        presenter.attachView(this);

        loader.startLad();
        loader.setMessage("Loading resources...");
        presenter.loadAllDiagnosis();

        edSearchDiagnosis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                if (query.length() > 0) {
                    presenter.filterDianosis(diagnosisList, query.toString());
                } else {
                    diagnosisAdapter.update(diagnosisList);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @Override
    public void onItemClick(int position) {
//        Diagnosis diagnosis = diagnosisList.get(position);
        Diagnosis diagnosis = diagnosisAdapter.getDiagnosis(position);
        newTestNavigator.onStartDiagnosisProcedure(diagnosis);
    }

    @Override
    public void onDisplayErrorDialog(String message) {
        loader.stopLoad();
        notificationMessage.showMe(
                getContext(),
                notificationMessage.HOLD_ON_title,
                ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onDisplayDiagnosis(List<Diagnosis> diagnosisList) {
        loader.stopLoad();

        Timber.d("diagnosisByCodeId %s", diagnosisList.get(0).getDiagCode());

        this.diagnosisList = diagnosisList;

        diagnosisAdapter = new DiagnosisAdapter(getContext(), diagnosisList, this);
        rvHospitalDiagnosis.setAdapter(diagnosisAdapter);
    }

    @Override
    public void displayFilteredDiagnosis(List<Diagnosis> diagnosisList) {
        if (diagnosisList != null && diagnosisList.size() > 0) {
            displayDiagnosisRecyclerView(true);
            diagnosisAdapter.update(diagnosisList);
        } else {
//            diagnosisAdapter.update(this.diagnosisList);
            displayDiagnosisRecyclerView(false);
            tvMessage.setText("No Diagnosis found.");
        }
    }

    private void displayDiagnosisRecyclerView(boolean display) {
        if (display) {
            ViewUtilities.showView(rvHospitalDiagnosis);
            ViewUtilities.hideView(tvMessage);
        } else {
            ViewUtilities.hideView(rvHospitalDiagnosis);
            ViewUtilities.showView(tvMessage);
        }
    }

}
