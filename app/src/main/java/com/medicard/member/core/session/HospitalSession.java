package com.medicard.member.core.session;

import model.HospitalList;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class HospitalSession {

    private static final HospitalList hospital = new HospitalList();

    public static void setHospital(HospitalList content) {
        hospital.init(content);
    }

    public static HospitalList getHospital() {
        return hospital;
    }

}
