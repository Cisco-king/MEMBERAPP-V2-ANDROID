package model;

import java.util.ArrayList;

/**
 * Created by IPC on 11/20/2017.
 */

public class  DentistModel {

    ArrayList<DentistList> dentistList;

    public ArrayList<DentistList> getDentistList() {
        return dentistList;
    }

    public void setDentistList(ArrayList<DentistList> dentistList) {
        this.dentistList = dentistList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dentistList = "+dentistList+"]";
    }
}