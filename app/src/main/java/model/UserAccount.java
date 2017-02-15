package model;

/**
 * Created by mpx-pawpaw on 11/23/16.
 */

public class UserAccount {

    private String STATUS;

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    private String PIN;

    private String PHONE;

    private String MEM_CODE;

    private String REG_DEVICE;

    private String MEM_SEX;

    private String INVLOGINATT;

    private String ID;

    private String EMAIL;

    private String DATE_REGISTERED;

    private String USERNAME;

    private String MEM_MI;

    private String PASSWORD;

    private String MEM_LNAME;

    private String MEM_FNAME;

    private String FORCE_CHANGE_PASSWORD;

    public String getSTATUS ()
    {
        return STATUS;
    }

    public void setSTATUS (String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getPHONE ()
    {
        return PHONE;
    }

    public void setPHONE (String PHONE)
    {
        this.PHONE = PHONE;
    }

    public String getMEM_CODE ()
    {
        return MEM_CODE;
    }

    public void setMEM_CODE (String MEM_CODE)
    {
        this.MEM_CODE = MEM_CODE;
    }

    public String getREG_DEVICE ()
    {
        return REG_DEVICE;
    }

    public void setREG_DEVICE (String REG_DEVICE)
    {
        this.REG_DEVICE = REG_DEVICE;
    }

    public String getMEM_SEX ()
    {
        return MEM_SEX;
    }

    public void setMEM_SEX (String MEM_SEX)
    {
        this.MEM_SEX = MEM_SEX;
    }

    public String getINVLOGINATT ()
    {
        return INVLOGINATT;
    }

    public void setINVLOGINATT (String INVLOGINATT)
    {
        this.INVLOGINATT = INVLOGINATT;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getEMAIL ()
    {
        return EMAIL;
    }

    public void setEMAIL (String EMAIL)
    {
        this.EMAIL = EMAIL;
    }

    public String getDATE_REGISTERED ()
    {
        return DATE_REGISTERED;
    }

    public void setDATE_REGISTERED (String DATE_REGISTERED)
    {
        this.DATE_REGISTERED = DATE_REGISTERED;
    }

    public String getUSERNAME ()
    {
        return USERNAME;
    }

    public void setUSERNAME (String USERNAME)
    {
        this.USERNAME = USERNAME;
    }

    public String getMEM_MI ()
    {
        return MEM_MI;
    }

    public void setMEM_MI (String MEM_MI)
    {
        this.MEM_MI = MEM_MI;
    }

    public String getPASSWORD ()
    {
        return PASSWORD;
    }

    public void setPASSWORD (String PASSWORD)
    {
        this.PASSWORD = PASSWORD;
    }

    public String getMEM_LNAME ()
    {
        return MEM_LNAME;
    }

    public void setMEM_LNAME (String MEM_LNAME)
    {
        this.MEM_LNAME = MEM_LNAME;
    }

    public String getMEM_FNAME ()
    {
        return MEM_FNAME;
    }

    public void setMEM_FNAME (String MEM_FNAME)
    {
        this.MEM_FNAME = MEM_FNAME;
    }

    public String getFORCE_CHANGE_PASSWORD() {
        return FORCE_CHANGE_PASSWORD;
    }

    public void setFORCE_CHANGE_PASSWORD(String FORCE_CHANGE_PASSWORD) {
        this.FORCE_CHANGE_PASSWORD = FORCE_CHANGE_PASSWORD;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [STATUS = "+STATUS+", PHONE = "+PHONE+", MEM_CODE = "+MEM_CODE+", REG_DEVICE = "+REG_DEVICE+", MEM_SEX = "+MEM_SEX+", INVLOGINATT = "+INVLOGINATT+", ID = "+ID+", EMAIL = "+EMAIL+", DATE_REGISTERED = "+DATE_REGISTERED+", USERNAME = "+USERNAME+", MEM_MI = "+MEM_MI+", PASSWORD = "+PASSWORD+", MEM_LNAME = "+MEM_LNAME+", MEM_FNAME = "+MEM_FNAME+"]";
    }


}
