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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Attachment;
import model.HospitalList;
import modules.requestnewapproval.RequestNewActivity;
import timber.log.Timber;
import utilities.Constant;
import v2.RequestButtonsActivity;

/**
 * Created by casjohnpaul on 6/18/2017.
 */

public class HospitalActivity extends BaseActivity implements HospitalNavigator {


    ArrayList<Attachment> attachments = new ArrayList<>();
    String memberStatus ="";
    @BindView(R.id.toolbarBack)
    FancyButton toolbarBack;

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
        memberStatus = getIntent().getExtras().getString(Constant.MEM_STATUS);


        attachments = getIntent().getExtras().getParcelableArrayList(RequestNewActivity.ATTACHMENT);
        setupWindowAnimations();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContainer, HospitalFragment.newInstance(fromHospital))
                .commit();
    }

    @Override
    public void onHospitalSelected() {
        try {
            Timber.d("doctor %s and hospital %s", DoctorSession.getDoctor().getFullName(), DoctorSession.getDoctor().getHospitalName());
            Timber.d("reason for consult %s", ConsultSession.getReasonForConsult());
            Timber.d("reason for hospital selected %s", HospitalSession.getHospital().getHospitalName());
            Intent goToRequestNewActivity = new Intent(this, RequestNewActivity.class);
            goToRequestNewActivity.putExtra(RequestNewActivity.ATTACHMENT, attachments);
            goToRequestNewActivity.putExtra(Constant.MEMBER_ID, getIntent().getExtras().getString(Constant.MEMBER_ID));
            goToRequestNewActivity.putExtra(Constant.GENDER, getIntent().getExtras().getString(Constant.GENDER));
            goToRequestNewActivity.putExtra(Constant.BIRTHDAY, getIntent().getExtras().getString(Constant.BIRTHDAY));
            goToRequestNewActivity.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.NAME));
            goToRequestNewActivity.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
            goToRequestNewActivity.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
            goToRequestNewActivity.putExtra(Constant.AGE, getIntent().getExtras().getString(Constant.AGE));
            goToRequestNewActivity.putExtra(Constant.MEM_STATUS, memberStatus);
            transitionTo(goToRequestNewActivity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onHospitalReselected() {
        setResult(RESULT_OK);
        finish();
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack() {
        /*Visibility returnTransition = buildReturnTransition();
        getWindow().setReturnTransition(returnTransition);*/
        finishAfterTransition();
    }
}
