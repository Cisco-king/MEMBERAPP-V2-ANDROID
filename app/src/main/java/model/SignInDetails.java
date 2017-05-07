package model;

import java.util.ArrayList;

/**
 * Created by mpx-pawpaw on 10/28/16.
 */

public class SignInDetails {
    private ArrayList<MemberAccounts> MemberAccounts;

    private String responseCode;

    private String responseDesc;

    private UserAccount UserAccount;

    private Boolean hasMaternity;

    public ArrayList<MemberAccounts> getMemberAccounts ()
    {
        return MemberAccounts;
    }

    public void setMemberAccounts (ArrayList<MemberAccounts> MemberAccounts)
    {
        this.MemberAccounts = MemberAccounts;
    }

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

    public UserAccount getUserAccount ()
    {
        return UserAccount;
    }

    public void setUserAccount (UserAccount UserAccount)
    {
        this.UserAccount = UserAccount;
    }

    public Boolean getHasMaternity() {
        return hasMaternity;
    }

    public void setHasMaternity(Boolean hasMaternity) {
        this.hasMaternity = hasMaternity;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MemberAccounts = "+MemberAccounts+", responseCode = "+responseCode+", responseDesc = "+responseDesc+", UserAccount = "+UserAccount+"]";
    }}
