package com.medicard.member.module.DiagnosisTallyActivity.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.medicard.member.core.model.DiagnosisTests;
import com.medicard.member.module.DiagnosisTallyActivity.*;

import java.util.List;

import services.model.Diagnosis;

/**
 * Created by macbookpro on 7/21/17.
 */

public class DiagnosisPresenteForTally implements DiagnosisMVP.Presenter {


    private DiagnosisMVP.View diagnosisView;

    public DiagnosisPresenteForTally(){

    }
    @Override
    public void loadDiagnosisTest(List<DiagnosisTests> diagnosisTestsList) {

        List<DiagnosisTests> diagTestList = diagnosisTestsList;
        diagnosisView.displayDiagnosisTests(diagTestList);
;

    }

    @Override
    public void attachView(DiagnosisMVP.View view) {
        diagnosisView = view;
    }

    @Override
    public void detachView() {
        diagnosisView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }
}
