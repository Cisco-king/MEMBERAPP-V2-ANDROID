package model;

import java.util.ArrayList;

/**
 * Created by mpx-pawpaw on 1/4/17.
 */

public class Doctors {

    private ArrayList<GetDoctorsToHospital> getDoctorsToHospital;

    public ArrayList<GetDoctorsToHospital> getGetDoctorsToHospital() {
        return getDoctorsToHospital;
    }

    public void setGetDoctorsToHospital(ArrayList<GetDoctorsToHospital> getDoctorsToHospital) {
        this.getDoctorsToHospital = getDoctorsToHospital;
    }

    @Override
    public String toString() {
        return "ClassPojo [getDoctorsToHospital = " + getDoctorsToHospital + "]";
    }
}
