package com.medicard.member.module.DentistBenefitsActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.module.DentistBenefitsActivity.fragment.DentalBenefitsFragment;
import com.medicard.member.module.DentistBenefitsActivity.fragment.DentalBenefitsMVP;
import com.medicard.member.module.DentistBenefitsActivity.fragment.DentalBenefitsPresenter;
import com.medicard.member.module.base.BaseActivity;

import butterknife.BindView;
import utilities.Constant;
import utilities.SharedPref;

/**
 * Created by macbookpro on 7/31/17.
 */

public class DentalBenefitsActivity extends BaseActivity implements DentalBenefitsMVP.View,DentalBenefitsPresenter.DentalBenefitCallback {

    DentalBenefitsMVP.Presenter presenter;
    private String memberCode = "";
    DentalBenefitsPresenter.DentalBenefitCallback callback;


    @BindView(R.id.tv_dental_benefits_details)
    TextView tv_dental_benefits_details;

    @Override
    protected void initComponents(Bundle savedInstanceState) {
        super.initComponents(savedInstanceState);

        callback = this;
        memberCode = getIntent().getExtras().getString(Constant.MEMBER_ID);
        presenter = new DentalBenefitsPresenter(context);
        presenter.loadDentalBenefits(memberCode,callback);
    }

    @Override
    protected String activityTitle() {
        return "Dental Coverage";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_dentist_benefits;
    }

    @Override
    public void onDisplayMessage(String message) {
        System.out.println("======================== MESSAGE MO TO" + message);

    }

    @Override
    public void onSuccess(String message) {
        message = "Dental Benefits for 2621761 - DELA CRUZ, JUAN: Perm Amalgam = 2 SURFACES AT MPI CLINICS ONLY;  Lightcure = 3 TEETH AT MPI CLINICS ONLY;  Prophy = ANNUAL; others = NOTE:-LIGHT CURE CAN BE AVAILED AT MEDICARD OWNED CLINICS ONLY AND FOR PROVINCIAL EMPLOYEES & QUALIFIED DEPENDENTS RESIDING IN PROVINCES MAY AVAIL OF THE SERVICE T ANY ACCREDITED DENTAL CLINICS EMERGENCY OUT-PATIENT DENTAL TREATMENT, TMJ CONSULTATIONS, RESTORATIVE & PROSTHODONTIC CONSULTATIONS, DENTAL NUTRITION & DIETARY COUNSELING, DENTAL HEALTH EDUCATION, PRE-NATAL & POST-NATAL DENTAL CONSULTATIONS-COVERED;;";

        if(message.contains(":")){
            String parts[] = message.split(";");
            String allText = "";
            for(int i=0;i<parts.length;i++){
                if(parts[i].contains("\\,"))
                    allText += "\n";
                else
                    allText += parts[i] + "\n";
            }

            tv_dental_benefits_details.setText(allText);
        }else {
            tv_dental_benefits_details.setText(message);
        }



//        tv_dental_benefits_details.setText(message.trim());
    }
}
