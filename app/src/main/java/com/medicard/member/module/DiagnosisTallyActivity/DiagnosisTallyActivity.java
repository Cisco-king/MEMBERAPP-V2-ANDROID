package com.medicard.member.module.DiagnosisTallyActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Visibility;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.medicard.member.R;
import com.medicard.member.core.model.DiagnosisTests;
import com.medicard.member.core.session.DiagnosisTestSession;
import com.medicard.member.module.DiagnosisTallyActivity.adapter.DiagnosisTallyAdapter;
import com.medicard.member.module.DiagnosisTallyActivity.fragment.DiagnosisTallyFragment;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.diagnosis.DiagnosisActivity;
import com.medicard.member.module.diagnosis.fragment.DiagnosisMvp;
import com.medicard.member.module.diagnosistest.TestByDiagnosisActivity;
import com.tapadoo.alerter.Alerter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.newtest.DiagnosisDetails;
import modules.prescriptionattachment.PrescriptionAttachmentActivity;
import modules.requestnewapproval.RequestNewMvp;
import modules.requestnewapproval.adapter.DiagnosisDetailsAdapter;
import services.model.Diagnosis;
import services.model.DiagnosisTest;
import services.model.Test;

/**
 * Created by macbookpro on 7/21/17.
 */

public class  DiagnosisTallyActivity extends BaseActivity  {


    public static final String diagnosisTestsTally = "Diagnosistally";
    public static final String diagnosisTestsBundle = "bundle";


    @BindView(R.id.fbAddMoreDiagnosis)
    FancyButton fbAddMoreDiagnosis;


    @BindView(R.id.fbDone)
    FancyButton fbDone;


    @Override
    protected String activityTitle() {
        //Setting the title to Test
        return "Tests";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_diagnosistally_layout;
    }


    @BindView(R.id.rvDiagnosisTally)
    RecyclerView rvDiagnosisTally;

    @BindView(R.id.flContainer)
    FrameLayout scrollView3;

    List<DiagnosisTests> diagnosisTests = new ArrayList<>();
    List<Test> tests = new ArrayList<>();




    @Override
    protected void initComponents(Bundle savedInstanceState) {
        super.initComponents(savedInstanceState);
        setupWindowAnimations();


        try {
            diagnosisTests = DiagnosisTestSession.getAllDiagnosisTests();
            System.out.println("==================== SIZE IN TALLY FOR REAL " + diagnosisTests.size());
        }catch (Exception e){
            e.printStackTrace();
        }

        //This will go to DiagnosisTallyFragment for the display of Diagnosis
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContainer, DiagnosisTallyFragment.newInstance(diagnosisTests))
                .commit();
    }
    @OnClick(R.id.fbAddMoreDiagnosis)
    public void onAddMoreDiagnosis() {

        Intent intent = new Intent(this, DiagnosisActivity.class);
        transitionTo(intent);
    }

    @OnClick(R.id.fbDone)
    public void onDoneClick() {

        try {
            if (diagnosisTests.isEmpty() && diagnosisTests.size() == 0) {
                Alerter.create(this)
                        .setText("Please Add a Diagnosis To Proceed")
                        .setBackgroundColor(R.color.orange_a200)
                        .show();
            } else {
                Bundle bundle = new Bundle();
                bundle.putSerializable(diagnosisTestsTally, (Serializable) diagnosisTests);
                Intent intent = new Intent(this, PrescriptionAttachmentActivity.class);
                intent.putExtra(diagnosisTestsBundle, bundle);
                startActivity(intent);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    protected void setupWindowAnimations() {
        super.setupWindowAnimations();
    }

}
