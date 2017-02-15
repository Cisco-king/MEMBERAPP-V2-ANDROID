package model;

import java.util.ArrayList;

/**
 * Created by mpx-pawpaw on 11/8/16.
 */

public class GetUSER{

    private String responseCode;

    private String responseDesc;

   // private VerifiedMember VerifiedMember;

    private MemberInfo MemberInfo;

    private ArrayList<Dependents> Dependents;

    private ArrayList<Utilization> Utilization;

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
//
//    public VerifiedMember getVerifiedMember ()
//    {
//        return VerifiedMember;
//    }
//
//    public void setVerifiedMember (VerifiedMember VerifiedMember)
//    {
//        this.VerifiedMember = VerifiedMember;
//    }

    public MemberInfo getMemberInfo ()
    {
        return MemberInfo;
    }

    public void setMemberInfo (MemberInfo MemberInfo)
    {
        this.MemberInfo = MemberInfo;
    }

    public ArrayList<Dependents> getDependents ()
    {
        return Dependents;
    }

    public void setDependents (ArrayList<Dependents> Dependents)
    {
        this.Dependents = Dependents;
    }

    public ArrayList<Utilization> getUtilization ()
    {
        return Utilization;
    }

    public void setUtilization (ArrayList<Utilization> Utilization)
    {
        this.Utilization = Utilization;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [responseCode = "+responseCode+", responseDesc = "+responseDesc+"MemberInfo = "+MemberInfo+", Dependents = "+Dependents+", Utilization = "+Utilization+"]";
    }
}

