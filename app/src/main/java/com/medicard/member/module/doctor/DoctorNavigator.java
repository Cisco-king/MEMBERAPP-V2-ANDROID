package com.medicard.member.module.doctor;

/**
 * Created by casjohnpaul on 6/18/2017.
 */

public interface DoctorNavigator {

    /**
     * This will serve as listener if the doctor in the list is selected
     */
    void onDoctorSelected();

    void fromNewRequestReselected();

}
