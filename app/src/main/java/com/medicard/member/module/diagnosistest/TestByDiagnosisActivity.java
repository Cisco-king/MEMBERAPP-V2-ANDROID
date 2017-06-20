package com.medicard.member.module.diagnosistest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.core.model.DiagnosisTests;
import com.medicard.member.core.session.DiagnosisSession;
import com.medicard.member.core.session.DiagnosisTestSession;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.diagnosistest.adapter.TestProcedureAdapter;
import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.newtest.DiagnosisProcedure;
import modules.procedure.adapter.ProcedureAdapter;
import modules.requestnewapproval.RequestNewActivity;
import modules.tests.Tests;
import services.model.Diagnosis;
import services.model.Test;
import timber.log.Timber;
import utilities.DialogUtils;
import utilities.Loader;
import utilities.ViewUtilities;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class TestByDiagnosisActivity extends BaseActivity implements TestByDiagnosisMvp.View {


    public static final String KEY_DONE = "keyDone";
    public static final String KEY_DISPLAY_ALL = "displayAll";

    @BindView(R.id.rvProcedures)
    RecyclerView rvProcedures;
    @BindView(R.id.edSearchProcedures)
    EditText edSearchProcedures;

    @BindView(R.id.fbDone)
    FancyButton fbDone;
    @BindView(R.id.fbAddMoreDiagnosis)
    FancyButton fbAddMoreDiagnosis;

    @BindView(R.id.tvNoProcedures)
    TextView tvNoProcedures;

    private TestByDiagnosisMvp.Presenter presenter;

    private Diagnosis diagnosis;

    private List<Test> tests;
    private List<DiagnosisProcedure> diagnosisProcedures;

    private ProcedureAdapter procedureAdapter;
    private TestProcedureAdapter testAdapter;

    private Loader loader;

    private boolean displayAll;
    private boolean fromTest = false;

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

        fromTest = getIntent().getBooleanExtra(RequestNewActivity.FROM_TEST, false);

        if (fromTest) {
            Timber.d("from new request ");
            displayAll = DiagnosisTestSession.isDisplayAll();
        } else {
            Timber.d("from skip is click");
            displayAll = getIntent().getBooleanExtra(KEY_DISPLAY_ALL, false);
        }

        tests = new ArrayList<>();

        loader = new Loader(context);
        loader.startLad();
        loader.setMessage("Loading resource");

        diagnosis = DiagnosisSession.getDiagnosis();

        rvProcedures.setLayoutManager(new LinearLayoutManager(context));

        Timber.d("this is the diagnosis %s", diagnosis.getDiagCode());

        if (displayAll) {
            ViewUtilities.hideView(fbAddMoreDiagnosis);
            presenter.loadAllTests(fromTest);
        } else {
            ViewUtilities.showView(fbAddMoreDiagnosis);
            presenter.loadTestProcedureByDiagnosisCode(diagnosis.getDiagCode());
        }
    }

    @OnClick(R.id.fbDone)
    public void onDoneClick() {
        // make sure that the content is release to get only zero index cause
        // as of now only skip and all test was display
        DiagnosisTestSession.releaseContent();
        if (getSelectedTests() != null && getSelectedTests().size() > 0) {
            Intent intent = new Intent();
            intent.putExtra(KEY_DONE, true);
            intent.putExtra(KEY_DISPLAY_ALL, displayAll);
            setResult(RESULT_OK, intent);

            // todo sprint6 currently working if test is from SKIP (this is need of required scenario)
            if (displayAll) {
                Timber.d("from display all request");

                DiagnosisTests diagnosisTest = new DiagnosisTests();
                diagnosisTest.setTests(getSelectedTests());

                DiagnosisTestSession.setDiagnosisTests(diagnosisTest);
                DiagnosisTestSession.setDisplayAll(displayAll);
            }
            finish();
        } else {
            Alerter.create(this)
                    .setText("Please select Test(s) to proceed.")
                    .setBackgroundColor(R.color.orange_a200)
                    .show();
        }
    }

    @OnClick(R.id.fbAddMoreDiagnosis)
    public void onAddMoreDiagnosis() {

        DiagnosisTests diagnosisTests =
            new DiagnosisTests(diagnosis, getSelectedTests());
        DiagnosisTestSession.setDiagnosisTests(diagnosisTests);

        Intent intent = new Intent();
        intent.putExtra(KEY_DISPLAY_ALL, displayAll);
        intent.putExtra(KEY_DONE, false);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onSuccess(List<Test> tests) {
        loader.stopLoad();
        if (tests != null && tests.size() > 0) {
            this.tests = tests;

            ViewUtilities.showView(rvProcedures);
            ViewUtilities.hideView(tvNoProcedures);

            testAdapter = new TestProcedureAdapter(this.tests);
            rvProcedures.setAdapter(testAdapter);
        } else {
            ViewUtilities.hideView(rvProcedures);
            ViewUtilities.showView(tvNoProcedures);

            tvNoProcedures.setText("No Test(s) find on ");
            tvNoProcedures.append(diagnosis.getDiagDesc());
            tvNoProcedures.append(" Diagnosis");
        }
    }

    @Override
    public void onError(String message) {
        loader.stopLoad();
    }

    @Override
    public void onBackPressed() {
        if (displayAll) {
            DialogUtils.showDialog(this, R.string.alert, R.string.alert_cancel_procedure,
                    new DialogUtils.OnDiaglogClickListener() {

                @Override
                public void onOk() {
                    DiagnosisTestSession.releaseContent();
                    finish();
                }

                @Override
                public void onCancel() {

                }
            });
        }
    }

    public List<Test> getSelectedTests() {
        List<Test> selected = new ArrayList<>();
        for (Test test : tests) {
            if (test.isSelected()) {
                selected.add(test);
            }
        }

        Timber.d("number of selected test %s", selected.size());
        return selected;
    }

}
