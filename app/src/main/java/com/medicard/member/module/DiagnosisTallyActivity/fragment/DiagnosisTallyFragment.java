package com.medicard.member.module.DiagnosisTallyActivity.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.core.model.DiagnosisTests;
import com.medicard.member.core.session.DiagnosisSession;
import com.medicard.member.module.DiagnosisTallyActivity.DiagnosisTallyActivity;
import com.medicard.member.module.DiagnosisTallyActivity.adapter.DiagnosisTallyAdapter;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.base.BaseFragment;
import com.medicard.member.module.diagnosis.DiagnosisNavigator;
import com.medicard.member.module.diagnosis.fragment.DiagnosisFragment;
import com.medicard.member.module.diagnosis.fragment.DiagnosisMvp;
import com.medicard.member.module.diagnosis.fragment.DiagnosisPresenter;
import com.medicard.member.module.diagnosistest.TestByDiagnosisActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
 * Created by macbookpro on 7/21/17.
 */

public class DiagnosisTallyFragment extends BaseFragment implements DiagnosisMVP.View,DiagnosisTallyAdapter.OnDiagnosisClickListener {

    public static final String DIAGNOSISTESTSLIST = "diagnosistestslist";

    DiagnosisTallyActivity listener;
    DiagnosisTallyAdapter adapter;


    @BindView(R.id.rvDiagnosisTally)
    RecyclerView rvDiagnosisTally;







    private DiagnosisMVP.Presenter presenter;




    List<DiagnosisTests> diagnosisTests = new ArrayList<>();
    List<Test> tests = new ArrayList<>();
    DiagnosisTallyAdapter diagnosisDetailsAdapter;
    Diagnosis diag;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity){
         this.listener = (DiagnosisTallyActivity) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static Fragment newInstance(List<DiagnosisTests> diagnosisTestsList) {
        DiagnosisTallyFragment fragment = new DiagnosisTallyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DIAGNOSISTESTSLIST, (Serializable) diagnosisTestsList);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutResource() {
        return R.layout.content_tally_activity;
    }

    @Override
    protected void initComponents(View view, Bundle savedInstanceState){
        //Populate RecyclerView
        super.initComponents(view,savedInstanceState);



        diagnosisTests = (List<DiagnosisTests>) getArguments().getSerializable(DIAGNOSISTESTSLIST);
        presenter = new DiagnosisPresenteForTally();
        presenter.attachView(this);

        rvDiagnosisTally.setLayoutManager(new LinearLayoutManager(context));

        presenter.loadDiagnosisTest(diagnosisTests);



    }

    @Override
    public void displayDiagnosisTests(List<DiagnosisTests> diagnosisTestsList) {
        adapter = new DiagnosisTallyAdapter(context,diagnosisTestsList,this);
        rvDiagnosisTally.setAdapter(adapter);

    }


    @Override
    public void onRemoveDiagnosis(int position) {
        this.diagnosisTests.remove(position);
        adapter.update(this.diagnosisTests);

    }
}
