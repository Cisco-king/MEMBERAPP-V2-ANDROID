package com.medicard.member.module.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.newprocedure.NewTestProcedureActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class TestActivity extends BaseActivity implements TestMvp.View {


    @BindView(R.id.btn_request_approval) Button btnRequestNewApproval;
    @BindView(R.id.rvAvailedConsultations) RecyclerView rvAvailedConsultations;
    @BindView(R.id.empty_text) TextView tv_emptyText;

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

    @OnClick(R.id.btn_request_approval)
    public void onRequestNewApproval() {
        transitionTo(new Intent(this, NewTestProcedureActivity.class));
    }

}
