package com.medicard.member.module.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.doctor.DoctorActivity;
import com.medicard.member.module.newprocedure.NewTestProcedureActivity;

import butterknife.BindView;
import butterknife.OnClick;
import modules.prescriptionattachment.PrescriptionAttachmentActivity;
import okhttp3.MultipartBody;
import utilities.Constant;


public class TestActivity extends BaseActivity implements TestMvp.View {


    @BindView(R.id.btn_request_approval)
    Button btnRequestNewApproval;
    @BindView(R.id.rvAvailedConsultations)
    RecyclerView rvAvailedConsultations;
    @BindView(R.id.empty_text)
    TextView tv_emptyText;
    String memberStatus = "";

    private TestMvp.Presenter presenter;

    @Override
    protected String activityTitle() {
        return getString(R.string.test);
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
        memberStatus = getIntent().getExtras().getString(Constant.MEM_STATUS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick(R.id.btn_request_approval)
    public void onRequestNewApproval() {
        try {
            Intent goToPrescriptionActivity = new Intent(this, PrescriptionAttachmentActivity.class);
            goToPrescriptionActivity.putExtra(Constant.MEMBER_ID, getIntent().getExtras().getString(Constant.MEMBER_ID));
            goToPrescriptionActivity.putExtra(Constant.GENDER, getIntent().getExtras().getString(Constant.GENDER));
            goToPrescriptionActivity.putExtra(Constant.BIRTHDAY, getIntent().getExtras().getString(Constant.BIRTHDAY));
            goToPrescriptionActivity.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.NAME));
            goToPrescriptionActivity.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
            goToPrescriptionActivity.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
            goToPrescriptionActivity.putExtra(Constant.AGE, getIntent().getExtras().getString(Constant.AGE));
            goToPrescriptionActivity.putExtra(Constant.MEM_STATUS, memberStatus);
            startActivity(goToPrescriptionActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
