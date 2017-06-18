package com.medicard.member.module.doctor;

import android.content.Intent;
import android.os.Bundle;

import com.medicard.member.R;
import com.medicard.member.core.session.ConsultSession;
import com.medicard.member.core.session.DoctorSession;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.doctor.fragment.DoctorFragment;
import com.medicard.member.module.hospital.HospitalActivity;

import timber.log.Timber;

/**
 * Created by casjohnpaul on 6/18/2017.
 */

public class DoctorActivity extends BaseActivity
        implements DoctorNavigator {

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
                .replace(R.id.flContainer, DoctorFragment.newInstance())
                .commit();
    }

    @Override
    public void onDoctorSelected() {
        Timber.d("doctor %s and hospital %s", DoctorSession.getDoctor().getFullName(), DoctorSession.getDoctor().getHospitalName());
        Timber.d("reason for consult %s", ConsultSession.getReasonForConsult());
        startActivity(new Intent(this, HospitalActivity.class));
    }
}
