package com.medicard.member.module.DiagnosisTallyActivity.fragment;

import android.view.View;

import com.medicard.member.core.model.DiagnosisTests;
import com.medicard.member.module.diagnosis.fragment.DiagnosisMvp;

import java.util.List;

import modules.base.Mvp;

/**
 * Created by macbookpro on 7/21/17.
 */

public interface DiagnosisMVP {

    interface View extends Mvp.View{

        void displayDiagnosisTests(List<DiagnosisTests> diagnosisTestsList);
    }



    interface Presenter extends Mvp.Presenter<View>{


        void loadDiagnosisTest(List<DiagnosisTests> diagnosisLists);
    }
}
