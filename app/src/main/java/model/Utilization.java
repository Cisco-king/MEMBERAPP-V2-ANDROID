package model;

/**
 * Created by mpx-pawpaw on 11/23/16.
 */

public class Utilization {



    private String controlCode;

    private String approved;

    private String memCode;

    private String erc;

    private String hospitalName;

    private String dxRem;

    private String disapproved;

    private String availFr;

    private String advances;

    private String diagDesc;

    private String hospSoa;

    private String remarks2;

    public String getControlCode ()
    {
        return controlCode;
    }

    public void setControlCode (String controlCode)
    {
        this.controlCode = controlCode;
    }

    public String getApproved ()
    {
        return approved;
    }

    public void setApproved (String approved)
    {
        this.approved = approved;
    }

    public String getMemCode ()
    {
        return memCode;
    }

    public void setMemCode (String memCode)
    {
        this.memCode = memCode;
    }

    public String getErc ()
    {
        return erc;
    }

    public void setErc (String erc)
    {
        this.erc = erc;
    }

    public String getHospitalName ()
    {
        return hospitalName;
    }

    public void setHospitalName (String hospitalName)
    {
        this.hospitalName = hospitalName;
    }

    public String getDxRem ()
    {
        return dxRem;
    }

    public void setDxRem (String dxRem)
    {
        this.dxRem = dxRem;
    }

    public String getDisapproved ()
    {
        return disapproved;
    }

    public void setDisapproved (String disapproved)
    {
        this.disapproved = disapproved;
    }

    public String getAvailFr ()
    {
        return availFr;
    }

    public void setAvailFr (String availFr)
    {
        this.availFr = availFr;
    }

    public String getAdvances ()
    {
        return advances;
    }

    public void setAdvances (String advances)
    {
        this.advances = advances;
    }

    public String getDiagDesc ()
    {
        return diagDesc;
    }

    public void setDiagDesc (String diagDesc)
    {
        this.diagDesc = diagDesc;
    }

    public String getHospSoa ()
    {
        return hospSoa;
    }

    public void setHospSoa (String hospSoa)
    {
        this.hospSoa = hospSoa;
    }

    public String getRemarks2 ()
    {
        return remarks2;
    }

    public void setRemarks2 (String remarks2)
    {
        this.remarks2 = remarks2;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [controlCode = "+controlCode+", approved = "+approved+", memCode = "+memCode+", erc = "+erc+", hospitalName = "+hospitalName+", dxRem = "+dxRem+", disapproved = "+disapproved+", availFr = "+availFr+", advances = "+advances+", diagDesc = "+diagDesc+", hospSoa = "+hospSoa+", remarks2 = "+remarks2+"]";
    }
}
