package com.medicard.member.module.diagnosistest;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.core.session.DiagnosisSession;
import com.medicard.member.module.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mehdi.sakout.fancybuttons.FancyButton;
import model.newtest.DiagnosisProcedure;
import modules.procedure.adapter.ProcedureAdapter;
import services.model.Diagnosis;
import services.model.Test;
import utilities.Loader;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class TestByDiagnosisActivity extends BaseActivity implements TestByDiagnosisMvp.View {

    @BindView(R.id.rvProcedures)
    RecyclerView rvProcedures;
    @BindView(R.id.edSearchProcedures)
    EditText edSearchProcedures;

    @BindView(R.id.fbDone)
    FancyButton fbDone;
    @BindView(R.id.fbAddMoreDiagnosis) FancyButton fbAddMoreDiagnosis;

    @BindView(R.id.tvNoProcedures)
    TextView tvNoProcedures;

    private TestByDiagnosisMvp.Presenter presenter;

    private Diagnosis diagnosis;

    private List<Test> tests;
    private List<DiagnosisProcedure> diagnosisProcedures;

    private ProcedureAdapter procedureAdapter;

    private Loader loader;

    @Override
    protected String activityTitle() {
        return getString(R.string.test);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_test_by_diagnosis;
    }

    @Override
    protected void initComponents(Bundle bundle) {
        super.initComponents(bundle);
        presenter = new TestByDiagnosisPresenter(context);
        presenter.attachView(this);

        loader = new Loader(context);
        loader.startLad();
        loader.setMessage("Loading resource");

        diagnosis = DiagnosisSession.getDiagnosis();

        tests = new ArrayList<>();

        presenter.loadTestProcedureByDiagnosisCode(diagnosis.getDiagCode());
    }



}
