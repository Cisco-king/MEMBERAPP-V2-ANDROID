package com.medicard.member.module.doctor.fragment;

import java.util.List;

import database.entity.Doctor;
import modules.base.Mvp;
import services.model.DoctorList;
import services.model.HospitalsToDoctor;

/**
 * Created by casjohnpaul on 5/7/2017.
 */

public interface DoctorMvp {

    interface View extends Mvp.View {

        void displayDoctorsByHospital(List<HospitalsToDoctor> doctors);

        void displayAllDoctors(List<HospitalsToDoctor> doctorLists);

        void displayFilteredDoctors(List<HospitalsToDoctor> doctors);

        void onErrorRequest(String error);

    }

    interface Presenter extends Mvp.Presenter<View> {

        void loadAllDoctors();

        void loadDoctorsByName(String partialDoctorName);

        void filterDoctors(List<HospitalsToDoctor> doctors, String query);

    }

}
