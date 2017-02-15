package model;

/**
 * Created by mpx-pawpaw on 11/24/16.
 */

public class VerifyMemberData {

    private String responseCode;

    private String responseDesc;

    private MemberInfo MemberInfo;

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

    public MemberInfo getMemberInfo ()
    {
        return MemberInfo;
    }

    public void setMemberInfo (MemberInfo MemberInfo)
    {
        this.MemberInfo = MemberInfo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [responseCode = "+responseCode+", responseDesc = "+responseDesc+", MemberInfo = "+MemberInfo+"]";
    }

}
