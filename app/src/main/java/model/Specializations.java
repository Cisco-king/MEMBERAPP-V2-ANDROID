package model;

/**
 * Created by mpx-pawpaw on 1/30/17.
 */

public class Specializations {
    private String specializationDescription;

    private String specializationCode;

    public Specializations(String specializationCode, String specializationDescription) {
        this.specializationDescription = specializationDescription;
        this.specializationCode = specializationCode;
    }

    public String getSpecializationDescription ()
    {
        return specializationDescription;
    }

    public void setSpecializationDescription (String specializationDescription)
    {
        this.specializationDescription = specializationDescription;
    }

    public String getSpecializationCode ()
    {
        return specializationCode;
    }

    public void setSpecializationCode (String specializationCode)
    {
        this.specializationCode = specializationCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [specializationDescription = "+specializationDescription+", specializationCode = "+specializationCode+"]";
    }
}
