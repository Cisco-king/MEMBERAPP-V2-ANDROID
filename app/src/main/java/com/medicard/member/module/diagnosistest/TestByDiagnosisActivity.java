package com.medicard.member.module.diagnosistest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.medicard.member.R;
import com.medicard.member.core.model.DiagnosisTests;
import com.medicard.member.core.session.DiagnosisSession;
import com.medicard.member.core.session.DiagnosisTestSession;
import com.medicard.member.module.DiagnosisTallyActivity.DiagnosisTallyActivity;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.diagnosis.DiagnosisActivity;
import com.medicard.member.module.diagnosis.fragment.DiagnosisFragment;
import com.medicard.member.module.diagnosistest.adapter.TestProcedureAdapter;
import com.tapadoo.alerter.Alerter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.medicard.member.*;

import butterknife.BindView;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.newtest.DiagnosisProcedure;
import modules.procedure.adapter.ProcedureAdapter;
import modules.requestnewapproval.RequestNewActivity;
import modules.tests.Tests;
import services.model.Diagnosis;
import services.model.DiagnosisTest;
import services.model.Test;
import timber.log.Timber;
import utilities.DialogUtils;
import utilities.Loader;
import utilities.ViewUtilities;

import static com.medicard.member.module.diagnosis.DiagnosisActivity.diagnosisBundle;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class TestByDiagnosisActivity extends BaseActivity implements TestByDiagnosisMvp.View {


    public static final String KEY_DONE = "keyDone";
    public static final String KEY_DISPLAY_ALL = "displayAll";
    public static final String TESTSFORACTIVITY = "testsforActivity";
    public static final String DIAGNOSISFORACTIVITY = "DiagnosisForActivity";
    public static final String BUNDLEFORACTIVITY = "bundle";

    @BindView(R.id.rvProcedures)
    RecyclerView rvProcedures;
    @BindView(R.id.edSearchProcedures)
    EditText edSearchProcedures;

    @BindView(R.id.fbDone)
    FancyButton fbDone;
    DiagnosisTests diagtests;
    /* @BindView(R.id.fbAddMoreDiagnosis)
     FancyButton fbAddMoreDiagnosis;
 */
    @BindView(R.id.tvNoProcedures)
    TextView tvNoProcedures;

    private TestByDiagnosisMvp.Presenter presenter;

    private Diagnosis diagnosis;

    private List<Test> tests;
    Diagnosis diag;
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
        edSearchProcedures.addTextChangedListener(new Search());

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra(diagnosisBundle);
        diag = (Diagnosis) args.getSerializable(DiagnosisActivity.DiagnosisActivity);



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
            //ViewUtilities.hideView(fbAddMoreDiagnosis);
            presenter.loadAllTests(fromTest);
        } else {
          //  ViewUtilities.showView(fbAddMoreDiagnosis);
            presenter.loadTestProcedureByDiagnosisCode(diagnosis.getDiagCode());
        }
    }

    @OnClick(R.id.fbDone)
    public void onDoneClick() {
        // make sure that the content is release to get only zero index cause
        // as of now only skip and all test was display
        DiagnosisTestSession.releaseContent();
        if (getSelectedTests() != null && getSelectedTests().size() > 0) {
            List<Test> testToPass = getSelectedTests();
            diagtests= new DiagnosisTests();
            diagtests.setTests(testToPass);
            diagtests.setDiagnosis(diag);
            List<DiagnosisTests> diagALL = DiagnosisTestSession.getAllDiagnosisTests();
            if(diagALL.size() == 0){
                DiagnosisTestSession.setDiagnosisTests(diagtests);
            }else
                DiagnosisTestSession.setDiagnosisTests(diagtests);

            Intent intent = new Intent(this, DiagnosisTallyActivity.class);
            startActivity(intent);

        } else {
            Alerter.create(this)
                    .setText("Please select Test(s) to proceed.")
                    .setBackgroundColor(R.color.orange_a200)
                    .show();
        }
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

    public class Search implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence search, int start, int before, int count) {
            if (search.length() > 0) {
                presenter.filterTest(tests, search.toString());
            } else {

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
