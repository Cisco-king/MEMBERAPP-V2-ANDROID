package model;

/**
 * Created by mpx-pawpaw on 10/25/16.
 */

public class VerifyMember {


    private String responseCode;

    private String responseDesc;

    private MemberInfo MemberInfo;

    private String[] Dependents;

    private String[] Utilization;

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

    public String[] getDependents ()
    {
        return Dependents;
    }

    public void setDependents (String[] Dependents)
    {
        this.Dependents = Dependents;
    }

    public String[] getUtilization ()
    {
        return Utilization;
    }

    public void setUtilization (String[] Utilization)
    {
        this.Utilization = Utilization;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [responseCode = "+responseCode+", responseDesc = "+responseDesc+", MemberInfo = "+MemberInfo+", Dependents = "+Dependents+", Utilization = "+Utilization+"]";
    }
}
