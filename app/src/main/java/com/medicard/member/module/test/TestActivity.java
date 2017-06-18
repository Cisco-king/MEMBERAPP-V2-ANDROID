package com.medicard.member.module.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.medicard.member.R;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.newprocedure.NewTestProcedureActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class TestActivity extends BaseActivity implements TestMvp.View {


    @BindView(R.id.fabRequestNewApproval) FloatingActionButton fabRequestNewApproval;
    @BindView(R.id.rvAvailedConsultations) RecyclerView rvAvailedConsultations;

    private TestMvp.Presenter presenter;

    @Override
    protected String activityTitle() {
        return getString(R.string.basic_test);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_test;
    }

    @Override
    protected void initComponents(Bundle savedInstanceState) {
        super.initComponents(savedInstanceState);
        presenter = new TestPresenter(this);
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick(R.id.fabRequestNewApproval)
    public void onRequestNewApproval() {
        startActivity(new Intent(this, NewTestProcedureActivity.class));
    }

}
