package com.medicard.member.core.session;

import services.model.HospitalsToDoctor;

/**
 * Created by casjohnpaul on 6/18/2017.
 */

public class DoctorSession {

    private static HospitalsToDoctor doctor = new HospitalsToDoctor();

    public static void setDoctor(HospitalsToDoctor content) {
        doctor.init(content);
    }

    public static HospitalsToDoctor getDoctor() {
        return doctor;
    }

}
