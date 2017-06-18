package com.medicard.member.module.diagnosis;

import android.os.Bundle;

import com.medicard.member.R;
import com.medicard.member.core.session.ConsultSession;
import com.medicard.member.core.session.DiagnosisSession;
import com.medicard.member.core.session.DoctorSession;
import com.medicard.member.core.session.HospitalSession;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.diagnosis.fragment.DiagnosisFragment;

import services.model.Diagnosis;
import timber.log.Timber;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class DiagnosisActivity extends BaseActivity
        implements DiagnosisNavigator {


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
    }
}
