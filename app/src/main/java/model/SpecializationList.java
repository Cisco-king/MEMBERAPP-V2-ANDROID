package model;

import java.util.ArrayList;

/**
 * Created by mpx-pawpaw on 1/30/17.
 */

public class SpecializationList {

    private ArrayList<Specializations> specializations;

    public ArrayList<Specializations> getSpecializations ()
    {
        return specializations;
    }

    public void setSpecializations (ArrayList<Specializations> specializations)
    {
        this.specializations = specializations;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [specializations = "+specializations+"]";
    }
}
