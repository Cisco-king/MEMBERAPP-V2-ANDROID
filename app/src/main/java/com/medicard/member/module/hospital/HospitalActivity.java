package com.medicard.member.module.hospital;

import android.content.Intent;
import android.os.Bundle;

import com.medicard.member.R;
import com.medicard.member.core.session.ConsultSession;
import com.medicard.member.core.session.DoctorSession;
import com.medicard.member.core.session.HospitalSession;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.diagnosis.DiagnosisActivity;
import com.medicard.member.module.hospital.fragment.HospitalFragment;

import model.HospitalList;
import modules.requestnewapproval.RequestNewActivity;
import timber.log.Timber;

/**
 * Created by casjohnpaul on 6/18/2017.
 */

public class HospitalActivity extends BaseActivity implements HospitalNavigator {

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

        boolean fromHospital = getIntent().getBooleanExtra(RequestNewActivity.FROM_HOSPITAL, false);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContainer, HospitalFragment.newInstance(fromHospital))
                .commit();
    }

    @Override
    public void onHospitalSelected() {
        Timber.d("doctor %s and hospital %s", DoctorSession.getDoctor().getFullName(), DoctorSession.getDoctor().getHospitalName());
        Timber.d("reason for consult %s", ConsultSession.getReasonForConsult());
        Timber.d("reason for hospital selected %s", HospitalSession.getHospital().getHospitalName());
        startActivity(new Intent(this, DiagnosisActivity.class));
    }

    @Override
    public void onHospitalReselected() {
        setResult(RESULT_OK);
        finish();
    }
}
