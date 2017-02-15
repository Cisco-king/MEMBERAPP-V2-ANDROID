package model;

import java.util.ArrayList;

/**
 * Created by mpx-pawpaw on 1/4/17.
 */

public class Hospital {


    private ArrayList<HospitalList> hospitalList;

    public ArrayList<HospitalList> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(ArrayList<HospitalList> hospitalList) {
        this.hospitalList = hospitalList;
    }

    @Override
    public String toString() {
        return "ClassPojo [hospitalList = " + hospitalList + "]";
    }
}
