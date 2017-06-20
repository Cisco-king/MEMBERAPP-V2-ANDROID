package com.medicard.member.module.diagnosistest;

import java.util.List;

import modules.base.Mvp;
import services.model.Test;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public interface TestByDiagnosisMvp {

    interface View extends Mvp.View {

        void onSuccess(List<Test> tests);

        void onError(String message);

    }

    interface Presenter extends Mvp.Presenter<TestByDiagnosisMvp.View> {

        void loadTestProcedureByDiagnosisCode(String diagnosisCode);

        void loadAllTests(boolean fromTest);

    }

}
