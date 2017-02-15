package model;

/**
 * Created by aljohnalbuera on 11/25/16.
 */

public class RequestNewPassword {

    private String email;

    private String deviceId;

    private String memberCode;

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getDeviceId ()
    {
        return deviceId;
    }

    public void setDeviceId (String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getMemberCode ()
    {
        return memberCode;
    }

    public void setMemberCode (String memberCode)
    {
        this.memberCode = memberCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [email = "+email+", deviceId = "+deviceId+", memberCode = "+memberCode+"]";
    }

}
