package com.medicard.member.module.diagnosis.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import java.util.Collections;

import com.medicard.member.R;
import com.medicard.member.core.model.DiagnosisTests;
import com.medicard.member.core.session.DiagnosisSession;
import com.medicard.member.core.session.DiagnosisTestSession;
import com.medicard.member.module.base.BaseFragment;
import com.medicard.member.module.diagnosis.DiagnosisNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import core.callback.RecyclerViewOnClickListener;
import modules.diagnosis.adapter.DiagnosisAdapter;
import services.model.Diagnosis;
import services.model.Test;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;
import utilities.Loader;
import utilities.ViewUtilities;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class DiagnosisFragment extends BaseFragment
        implements DiagnosisMvp.View, RecyclerViewOnClickListener {


    @BindView(R.id.edSearchDiagnosis) TextView edSearchDiagnosis;
    @BindView(R.id.rvHospitalDiagnosis) RecyclerView rvHospitalDiagnosis;
    @BindView(R.id.tvMessage) TextView tvMessage;

    private DiagnosisMvp.Presenter presenter;
    private DiagnosisNavigator navigator;

    private DiagnosisAdapter diagnosisAdapter;
    private AlertDialogCustom alertDialog;
    List<DiagnosisTests> diagnosisTestsList = new ArrayList<>();

    private List<Diagnosis> diagnosisList;

    private Loader loader;


    public DiagnosisFragment() {
    }

    public static DiagnosisFragment newInstance() {

        Bundle args = new Bundle();

        DiagnosisFragment fragment = new DiagnosisFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DiagnosisNavigator) {
            navigator = (DiagnosisNavigator) context;
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_diagnosis;
    }

    @Override
    protected void initComponents(View view, Bundle bundle) {
        super.initComponents(view, bundle);
        presenter = new DiagnosisPresenter(context);
        presenter.attachView(this);

        loader = new Loader(context);

        diagnosisList = new ArrayList<>();


        rvHospitalDiagnosis.setLayoutManager(new LinearLayoutManager(context));

        alertDialog = new AlertDialogCustom();

        loader.startLad();
        loader.setMessage("Loading resources...");

        edSearchDiagnosis.addTextChangedListener(new Search());

        presenter.loadAllDiagnosis();
    }

    @Override
    public void onDisplayErrorDialog(String message) {
        loader.stopLoad();

        alertDialog.showMe(
                getContext(),
                alertDialog.HOLD_ON_title,
                ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onDisplayDiagnosis(List<Diagnosis> diagnosisList) {
        System.out.println("onDisplayDiagnosis 2");
        diagnosisTestsList = DiagnosisTestSession.getAllDiagnosisTests();
        System.out.println("BLAH BLAH BLAH " + diagnosisTestsList.size());

        loader.stopLoad();

        this.diagnosisList = diagnosisList;
        //Sorting DiagnosisList Alphabetically
        Collections.reverse(diagnosisList);

        diagnosisAdapter = new DiagnosisAdapter(context, diagnosisList, this);
        rvHospitalDiagnosis.setAdapter(diagnosisAdapter);
    }

    @Override
    public void displayFilteredDiagnosis(List<Diagnosis> diagnosisList) {
        if (diagnosisList != null && diagnosisList.size() > 0) {
            displayDiagnosisRecyclerView(true);
            diagnosisAdapter.update(diagnosisList);
        } else {
            displayDiagnosisRecyclerView(false);
            tvMessage.setText("No Diagnosis found.");
        }
    }

    @Override
    public void onItemClick(int position) {
        Diagnosis diagnosis = diagnosisAdapter.getDiagnosis(position);
        DiagnosisSession.setDiagnosis(diagnosis);
        navigator.onDiagnosisSelected(diagnosis);
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

    public class Search implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence search, int start, int before, int count) {
            if (search.length() > 0) {
                presenter.filterDianosis(diagnosisList, search.toString());
            } else {
                diagnosisAdapter.update(diagnosisList);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
