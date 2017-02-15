package model;

/**
 * Created by mpx-pawpaw on 11/24/16.
 */

public class AddDepenceResponse {

    private String responseCode;

    private String responseDesc;

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

    @Override
    public String toString()
    {
        return "ClassPojo [responseCode = "+responseCode+", responseDesc = "+responseDesc+"]";
    }

}
