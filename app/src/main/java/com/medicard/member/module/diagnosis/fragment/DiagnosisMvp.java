package com.medicard.member.module.diagnosis.fragment;

import java.util.List;

import modules.base.Mvp;
import services.model.Diagnosis;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public interface DiagnosisMvp {

    interface View extends Mvp.View {

        void onDisplayErrorDialog(String message);

        void onDisplayDiagnosis(List<Diagnosis> diagnosisList);

        void displayFilteredDiagnosis(List<Diagnosis> diagnosisList);

    }

    interface Presenter extends Mvp.Presenter<View> {

        void loadAllDiagnosis();

        void filterDianosis(List<Diagnosis> diagnosisList, String query);

    }

}
