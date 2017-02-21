package model;

import java.util.ArrayList;

/**
 * Created by mpx-pawpaw on 2/16/17.
 */

public class TheDoctor {
    private String responseCode;

    private String responseDesc;

    private ArrayList<DoctorsToHospital> doctorsToHospital;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public ArrayList<DoctorsToHospital> getDoctorsToHospital() {
        return doctorsToHospital;
    }

    public void setDoctorsToHospital(ArrayList<DoctorsToHospital> doctorsToHospital) {
        this.doctorsToHospital = doctorsToHospital;
    }

    @Override
    public String toString() {
        return "ClassPojo [responseCode = " + responseCode + ", responseDesc = " + responseDesc + ", doctorsToHospital = " + doctorsToHospital + "]";
    }
}

