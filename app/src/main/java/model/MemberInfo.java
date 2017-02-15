package model;

import java.io.Serializable;

/**
 * Created by mpx-pawpaw on 10/27/16.
 */

public class MemberInfo
{
    private String MEM_NAME;

    private String MEM_TYPE;

    private String MEM_SEX;

    private String ID_REM3;

    private String ID_REM2;

    private String ID_REM5;

    private String ID_REM4;

    private String MEM_LNAME;

    private String ID_REM;

    private String Plan_Desc;

    private String AGE;

    private String ID_REM6;

    private String ACCOUNT_NAME;

    private String BDAY;

    private String ID_REM7;

    private String PRIN_CODE;

    private String ERC_Limits;

    private String EFF_DATE;

    private String ACCOUNT_CODE;

    private String ACCT_TYPE;

    private String VAL_DATE;

    private String DD_Reg_Limits;

    private String OTHER_REM;

    private String Mem_OStat_Code;

    private String CIVIL_STATUS;

    private String MEM_FNAME;

    public String getMEM_NAME ()
    {
        return MEM_NAME;
    }

    public void setMEM_NAME (String MEM_NAME)
    {
        this.MEM_NAME = MEM_NAME;
    }

    public String getMEM_TYPE ()
    {
        return MEM_TYPE;
    }

    public void setMEM_TYPE (String MEM_TYPE)
    {
        this.MEM_TYPE = MEM_TYPE;
    }

    public String getMEM_SEX ()
    {
        return MEM_SEX;
    }

    public void setMEM_SEX (String MEM_SEX)
    {
        this.MEM_SEX = MEM_SEX;
    }

    public String getID_REM3 ()
    {
        return ID_REM3;
    }

    public void setID_REM3 (String ID_REM3)
    {
        this.ID_REM3 = ID_REM3;
    }

    public String getID_REM2 ()
    {
        return ID_REM2;
    }

    public void setID_REM2 (String ID_REM2)
    {
        this.ID_REM2 = ID_REM2;
    }

    public String getID_REM5 ()
    {
        return ID_REM5;
    }

    public void setID_REM5 (String ID_REM5)
    {
        this.ID_REM5 = ID_REM5;
    }

    public String getID_REM4 ()
    {
        return ID_REM4;
    }

    public void setID_REM4 (String ID_REM4)
    {
        this.ID_REM4 = ID_REM4;
    }

    public String getMEM_LNAME ()
    {
        return MEM_LNAME;
    }

    public void setMEM_LNAME (String MEM_LNAME)
    {
        this.MEM_LNAME = MEM_LNAME;
    }

    public String getID_REM ()
    {
        return ID_REM;
    }

    public void setID_REM (String ID_REM)
    {
        this.ID_REM = ID_REM;
    }

    public String getPlan_Desc ()
    {
        return Plan_Desc;
    }

    public void setPlan_Desc (String Plan_Desc)
    {
        this.Plan_Desc = Plan_Desc;
    }

    public String getAGE ()
    {
        return AGE;
    }

    public void setAGE (String AGE)
    {
        this.AGE = AGE;
    }

    public String getID_REM6 ()
    {
        return ID_REM6;
    }

    public void setID_REM6 (String ID_REM6)
    {
        this.ID_REM6 = ID_REM6;
    }

    public String getACCOUNT_NAME ()
    {
        return ACCOUNT_NAME;
    }

    public void setACCOUNT_NAME (String ACCOUNT_NAME)
    {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
    }

    public String getBDAY ()
    {
        return BDAY;
    }

    public void setBDAY (String BDAY)
    {
        this.BDAY = BDAY;
    }

    public String getID_REM7 ()
    {
        return ID_REM7;
    }

    public void setID_REM7 (String ID_REM7)
    {
        this.ID_REM7 = ID_REM7;
    }

    public String getPRIN_CODE ()
    {
        return PRIN_CODE;
    }

    public void setPRIN_CODE (String PRIN_CODE)
    {
        this.PRIN_CODE = PRIN_CODE;
    }

    public String getERC_Limits ()
    {
        return ERC_Limits;
    }

    public void setERC_Limits (String ERC_Limits)
    {
        this.ERC_Limits = ERC_Limits;
    }

    public String getEFF_DATE ()
    {
        return EFF_DATE;
    }

    public void setEFF_DATE (String EFF_DATE)
    {
        this.EFF_DATE = EFF_DATE;
    }

    public String getACCOUNT_CODE ()
    {
        return ACCOUNT_CODE;
    }

    public void setACCOUNT_CODE (String ACCOUNT_CODE)
    {
        this.ACCOUNT_CODE = ACCOUNT_CODE;
    }

    public String getACCT_TYPE ()
    {
        return ACCT_TYPE;
    }

    public void setACCT_TYPE (String ACCT_TYPE)
    {
        this.ACCT_TYPE = ACCT_TYPE;
    }

    public String getVAL_DATE ()
    {
        return VAL_DATE;
    }

    public void setVAL_DATE (String VAL_DATE)
    {
        this.VAL_DATE = VAL_DATE;
    }

    public String getDD_Reg_Limits ()
    {
        return DD_Reg_Limits;
    }

    public void setDD_Reg_Limits (String DD_Reg_Limits)
    {
        this.DD_Reg_Limits = DD_Reg_Limits;
    }

    public String getOTHER_REM ()
    {
        return OTHER_REM;
    }

    public void setOTHER_REM (String OTHER_REM)
    {
        this.OTHER_REM = OTHER_REM;
    }

    public String getMem_OStat_Code ()
    {
        return Mem_OStat_Code;
    }

    public void setMem_OStat_Code (String Mem_OStat_Code)
    {
        this.Mem_OStat_Code = Mem_OStat_Code;
    }

    public String getCIVIL_STATUS ()
    {
        return CIVIL_STATUS;
    }

    public void setCIVIL_STATUS (String CIVIL_STATUS)
    {
        this.CIVIL_STATUS = CIVIL_STATUS;
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
        return "ClassPojo [MEM_NAME = "+MEM_NAME+", MEM_TYPE = "+MEM_TYPE+", MEM_SEX = "+MEM_SEX+", ID_REM3 = "+ID_REM3+", ID_REM2 = "+ID_REM2+", ID_REM5 = "+ID_REM5+", ID_REM4 = "+ID_REM4+", MEM_LNAME = "+MEM_LNAME+", ID_REM = "+ID_REM+", Plan_Desc = "+Plan_Desc+", AGE = "+AGE+", ID_REM6 = "+ID_REM6+", ACCOUNT_NAME = "+ACCOUNT_NAME+", BDAY = "+BDAY+", ID_REM7 = "+ID_REM7+", PRIN_CODE = "+PRIN_CODE+", ERC_Limits = "+ERC_Limits+", EFF_DATE = "+EFF_DATE+", ACCOUNT_CODE = "+ACCOUNT_CODE+", ACCT_TYPE = "+ACCT_TYPE+", VAL_DATE = "+VAL_DATE+", DD_Reg_Limits = "+DD_Reg_Limits+", OTHER_REM = "+OTHER_REM+", Mem_OStat_Code = "+Mem_OStat_Code+", CIVIL_STATUS = "+CIVIL_STATUS+", MEM_FNAME = "+MEM_FNAME+"]";
    }
}

