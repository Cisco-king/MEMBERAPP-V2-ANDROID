package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mpx-pawpaw on 11/23/16.
 */

public class UserAccount implements Parcelable {
    private String STATUS;

    private String PHONE;

    private String MEM_CODE;

    private String REG_DEVICE;

    private String FORCE_CHANGE_PASSWORD;

    private String MEM_SEX;

    private String ID;

    private String INVLOGINATT;

    private String USERNAME;

    private String EMAIL;

    private String PIN;

    private String DATE_REGISTERED;

    private String MEM_MI;

    private String PASSWORD;

    private String MEM_LNAME;

    private String MEM_FNAME;

    private String HAS_DISCLAIMER ;

    public String getHAS_DISCLAIMER() {
        return HAS_DISCLAIMER;
    }

    public void setHAS_DISCLAIMER(String HAS_DISCLAIMER) {
        this.HAS_DISCLAIMER = HAS_DISCLAIMER;
    }

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

    public String getFORCE_CHANGE_PASSWORD ()
    {
        return FORCE_CHANGE_PASSWORD;
    }

    public void setFORCE_CHANGE_PASSWORD (String FORCE_CHANGE_PASSWORD)
    {
        this.FORCE_CHANGE_PASSWORD = FORCE_CHANGE_PASSWORD;
    }

    public String getMEM_SEX ()
    {
        return MEM_SEX;
    }

    public void setMEM_SEX (String MEM_SEX)
    {
        this.MEM_SEX = MEM_SEX;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getINVLOGINATT ()
    {
        return INVLOGINATT;
    }

    public void setINVLOGINATT (String INVLOGINATT)
    {
        this.INVLOGINATT = INVLOGINATT;
    }

    public String getUSERNAME ()
    {
        return USERNAME;
    }

    public void setUSERNAME (String USERNAME)
    {
        this.USERNAME = USERNAME;
    }

    public String getEMAIL ()
    {
        return EMAIL;
    }

    public void setEMAIL (String EMAIL)
    {
        this.EMAIL = EMAIL;
    }

    public String getPIN ()
    {
        return PIN;
    }

    public void setPIN (String PIN)
    {
        this.PIN = PIN;
    }

    public String getDATE_REGISTERED ()
    {
        return DATE_REGISTERED;
    }

    public void setDATE_REGISTERED (String DATE_REGISTERED)
    {
        this.DATE_REGISTERED = DATE_REGISTERED;
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

    @Override
    public String toString()
    {
        return "ClassPojo [STATUS = "+STATUS+", PHONE = "+PHONE+", MEM_CODE = "+MEM_CODE+", REG_DEVICE = "+REG_DEVICE+", FORCE_CHANGE_PASSWORD = "+FORCE_CHANGE_PASSWORD+", MEM_SEX = "+MEM_SEX+", ID = "+ID+", INVLOGINATT = "+INVLOGINATT+", USERNAME = "+USERNAME+", EMAIL = "+EMAIL+", PIN = "+PIN+", DATE_REGISTERED = "+DATE_REGISTERED+", MEM_MI = "+MEM_MI+", PASSWORD = "+PASSWORD+", MEM_LNAME = "+MEM_LNAME+", MEM_FNAME = "+MEM_FNAME+"]";
    }

    protected UserAccount(Parcel in) {
        STATUS = in.readString();
        HAS_DISCLAIMER = in.readString();
        PHONE = in.readString();
        MEM_CODE = in.readString();
        REG_DEVICE = in.readString();
        FORCE_CHANGE_PASSWORD = in.readString();
        MEM_SEX = in.readString();
        ID = in.readString();
        INVLOGINATT = in.readString();
        USERNAME = in.readString();
        EMAIL = in.readString();
        PIN = in.readString();
        DATE_REGISTERED = in.readString();
        MEM_MI = in.readString();
        PASSWORD = in.readString();
        MEM_LNAME = in.readString();
        MEM_FNAME = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(STATUS);
        dest.writeString(HAS_DISCLAIMER);
        dest.writeString(PHONE);
        dest.writeString(MEM_CODE);
        dest.writeString(REG_DEVICE);
        dest.writeString(FORCE_CHANGE_PASSWORD);
        dest.writeString(MEM_SEX);
        dest.writeString(ID);
        dest.writeString(INVLOGINATT);
        dest.writeString(USERNAME);
        dest.writeString(EMAIL);
        dest.writeString(PIN);
        dest.writeString(DATE_REGISTERED);
        dest.writeString(MEM_MI);
        dest.writeString(PASSWORD);
        dest.writeString(MEM_LNAME);
        dest.writeString(MEM_FNAME);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserAccount> CREATOR = new Parcelable.Creator<UserAccount>() {
        @Override
        public UserAccount createFromParcel(Parcel in) {
            return new UserAccount(in);
        }

        @Override
        public UserAccount[] newArray(int size) {
            return new UserAccount[size];
        }
    };
}