package model;

/**
 * Created by mpx-pawpaw on 11/8/16.
 */

public class Dependents
{

    private String memFname;

    private String id;

    private String userAcctId;

    private String memLname;

    private String memBday;

    private String memCode;

    private String memMname;

    private String memSex;

    private String memType;

    public String getMemFname ()
    {
        return memFname;
    }

    public void setMemFname (String memFname)
    {
        this.memFname = memFname;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getUserAcctId ()
    {
        return userAcctId;
    }

    public void setUserAcctId (String userAcctId)
    {
        this.userAcctId = userAcctId;
    }

    public String getMemLname ()
    {
        return memLname;
    }

    public void setMemLname (String memLname)
    {
        this.memLname = memLname;
    }

    public String getMemBday ()
    {
        return memBday;
    }

    public void setMemBday (String memBday)
    {
        this.memBday = memBday;
    }

    public String getMemCode ()
    {
        return memCode;
    }

    public void setMemCode (String memCode)
    {
        this.memCode = memCode;
    }

    public String getMemMname ()
    {
        return memMname;
    }

    public void setMemMname (String memMname)
    {
        this.memMname = memMname;
    }

    public String getMemSex ()
    {
        return memSex;
    }

    public void setMemSex (String memSex)
    {
        this.memSex = memSex;
    }

    public String getMemType ()
    {
        return memType;
    }

    public void setMemType (String memType)
    {
        this.memType = memType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [memFname = "+memFname+", id = "+id+", userAcctId = "+userAcctId+", memLname = "+memLname+", memBday = "+memBday+", memCode = "+memCode+", memMname = "+memMname+", memSex = "+memSex+", memType = "+memType+"]";
    }
}

