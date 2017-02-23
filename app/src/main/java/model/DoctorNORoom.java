package model;

/**
 * Created by mpx-pawpaw on 2/23/17.
 */

public class DoctorNORoom {

    private String responseCode;

    private String responseDesc;

    private Doctor doctor;

    public String getResponseCode ()
    {
        return responseCode;
    }

    public void setResponseCode (String responseCode)
    {
        this.responseCode = responseCode;
    }

    public String getResponseDesc ()
    {
        return responseDesc;
    }

    public void setResponseDesc (String responseDesc)
    {
        this.responseDesc = responseDesc;
    }

    public Doctor getDoctor ()
    {
        return doctor;
    }

    public void setDoctor (Doctor doctor)
    {
        this.doctor = doctor;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [responseCode = "+responseCode+", responseDesc = "+responseDesc+", doctor = "+doctor+"]";
    }
}
