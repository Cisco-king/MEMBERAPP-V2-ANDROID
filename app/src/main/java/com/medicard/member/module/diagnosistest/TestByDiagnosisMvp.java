package com.medicard.member.module.diagnosistest;

import modules.base.Mvp;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public interface TestByDiagnosisMvp {

    interface View extends Mvp.View {

    }

    interface Presenter extends Mvp.Presenter<TestByDiagnosisMvp.View> {

        void loadTestProcedureByDiagnosisCode(String diagnosisCode);

    }

}
