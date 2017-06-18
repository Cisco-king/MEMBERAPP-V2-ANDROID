package com.medicard.member.module.hospital.fragment;

import java.util.List;

import database.entity.Doctor;
import model.HospitalList;
import modules.base.Mvp;

/**
 * Created by casjohnpaul on 5/7/2017.
 */

public interface HospitalMvp {

    interface View extends Mvp.View {

        void displayHospitalClinic(List<HospitalList> hospitals);

        void displayFilterHospitalClinics(List<HospitalList> hospitalLists);

    }

    interface Presenter extends Mvp.Presenter<View> {

        void getHospitalListByDoctor(Doctor doctor);

        void filterHospitals(List<HospitalList> hospitals, String query);

        void loadHospitalClinic();

    }

}
