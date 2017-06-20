package com.medicard.member.module.newprocedure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.medicard.member.R;
import com.medicard.member.core.constant.NewTest;
import com.medicard.member.core.session.ConsultSession;
import com.medicard.member.core.utilities.TransitionHelper;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.doctor.DoctorActivity;
import com.medicard.member.module.reasonforconsult.ConsultFragment;

import timber.log.Timber;

public class NewTestProcedureActivity extends BaseActivity
        implements NewTestMvp.View {


    private NewTestMvp.Presenter presenter;


    @Override
    protected String activityTitle() {
        return getString(R.string.test);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_new_test_procedure;
    }

    @Override
    protected void initComponents(Bundle savedInstanceState) {
        super.initComponents(savedInstanceState);

        presenter = new NewTestProcedurePresenter(context);
        presenter.attachView(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flNewProcedureContainer, ConsultFragment.newInstance())
                .commit();
    }

    @Override
    public void proceedDoctorActivity() {
        Timber.d("this is a test %s", ConsultSession.getReasonForConsult());
        transitionTo(new Intent(this, DoctorActivity.class));
    }


}
