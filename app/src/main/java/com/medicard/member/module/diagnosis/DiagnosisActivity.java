package com.medicard.member.module.diagnosis;

import android.content.Intent;
import android.os.Bundle;

import com.medicard.member.R;
import com.medicard.member.core.model.DiagnosisTests;
import com.medicard.member.core.session.ConsultSession;
import com.medicard.member.core.session.DiagnosisSession;
import com.medicard.member.core.session.DiagnosisTestSession;
import com.medicard.member.core.session.DoctorSession;
import com.medicard.member.core.session.HospitalSession;
import com.medicard.member.module.DiagnosisTallyActivity.DiagnosisTallyActivity;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.diagnosis.fragment.DiagnosisFragment;
import com.medicard.member.module.diagnosistest.TestByDiagnosisActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import modules.prescriptionattachment.PrescriptionAttachmentActivity;
import services.model.Diagnosis;
import timber.log.Timber;
import utilities.ViewUtilities;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class DiagnosisActivity extends BaseActivity
        implements DiagnosisNavigator {


    @BindView(R.id.toolbarSkip) FancyButton toolbarSkip;



    public static final int TEST_PROCEDURE = 101;
    public static final String DiagnosisActivity = "DIAGNOSIS";
    public static final String diagnosisBundle = "diagnosisBundle";

    @Override
    protected String activityTitle() {
        return getString(R.string.test);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_base_layout;
    }

    @Override
    protected void initComponents(Bundle savedInstanceState) {
        super.initComponents(savedInstanceState);

        ViewUtilities.showView(toolbarSkip);



        setupWindowAnimations();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContainer, DiagnosisFragment.newInstance())
                .commit();
    }

    @Override
    public void onDiagnosisSelected(Diagnosis diagnosis) {
        Timber.d("doctor %s and hospital %s", DoctorSession.getDoctor().getFullName(), DoctorSession.getDoctor().getHospitalName());
        Timber.d("reason for consult %s", ConsultSession.getReasonForConsult());
        Timber.d("reason for hospital selected %s", HospitalSession.getHospital().getHospitalName());
        Timber.d("diagnosis %s = %s", DiagnosisSession.getDiagnosis().getDiagCode(), diagnosis.getDiagCode());
        //startActivityForResult(new Intent(this, TestByDiagnosisActivity.class), TEST_PROCEDURE);
        Intent goToTestDiagnosisActivity = new Intent(this, TestByDiagnosisActivity.class);
        System.out.println("=======================Diagnosis to PASS" + diagnosis.getDiagDesc());
        Bundle bundle = new Bundle();
        bundle.putSerializable(DiagnosisActivity, diagnosis);
        goToTestDiagnosisActivity.putExtra(diagnosisBundle, bundle);
        startActivity(goToTestDiagnosisActivity);
    }

    @OnClick(R.id.toolbarSkip)
    public void onClickSkip() {
        try {
            Intent intent = new Intent(this, TestByDiagnosisActivity.class);
            intent.putExtra(TestByDiagnosisActivity.KEY_DISPLAY_ALL, true);
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
//        startActivityForResult(intent, TEST_PROCEDURE);;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == TEST_PROCEDURE) {
            boolean displayAll =
                    data.getBooleanExtra(TestByDiagnosisActivity.KEY_DISPLAY_ALL, false);
            boolean isDoneClick =
                    data.getBooleanExtra(TestByDiagnosisActivity.KEY_DONE, false);
            if (isDoneClick) {
                transitionTo(new Intent(this, DiagnosisTallyActivity.class));
            }
        }
    }
}
