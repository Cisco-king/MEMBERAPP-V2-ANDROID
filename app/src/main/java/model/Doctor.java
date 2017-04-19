package model;

/**
 * Created by mpx-pawpaw on 2/16/17.
 */


public class Doctor
{
    private String streetAddress;

    private String region;

    private String docFname;

    private String specDesc;

    private String contactNumber;

    private String doctorCode;

    private String docMname;

    private String vat;

    private String wtax;

    private String gracePeriod;

    private String city;

    private String specCode;

    private String prc;

    private String province;

    private String docLname;

    private String schedule;

    private String roomNo;

    public String getStreetAddress ()
    {
        return streetAddress;
    }

    public void setStreetAddress (String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    public String getRegion ()
    {
        return region;
    }

    public void setRegion (String region)
    {
        this.region = region;
    }

    public String getDocFname ()
    {
        return docFname;
    }

    public void setDocFname (String docFname)
    {
        this.docFname = docFname;
    }

    public String getSpecDesc ()
    {
        return specDesc;
    }

    public void setSpecDesc (String specDesc)
    {
        this.specDesc = specDesc;
    }

    public String getContactNumber ()
    {
        return contactNumber;
    }

    public void setContactNumber (String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public String getDoctorCode ()
    {
        return doctorCode;
    }

    public void setDoctorCode (String doctorCode)
    {
        this.doctorCode = doctorCode;
    }

    public String getDocMname ()
    {
        return docMname;
    }

    public void setDocMname (String docMname)
    {
        this.docMname = docMname;
    }

    public String getVat ()
    {
        return vat;
    }

    public void setVat (String vat)
    {
        this.vat = vat;
    }

    public String getWtax ()
    {
        return wtax;
    }

    public void setWtax (String wtax)
    {
        this.wtax = wtax;
    }

    public String getGracePeriod ()
    {
        return gracePeriod;
    }

    public void setGracePeriod (String gracePeriod)
    {
        this.gracePeriod = gracePeriod;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getSpecCode ()
    {
        return specCode;
    }

    public void setSpecCode (String specCode)
    {
        this.specCode = specCode;
    }

    public String getPrc ()
{
    return prc;
}

    public void setPrc (String prc)
    {
        this.prc = prc;
    }

    public String getProvince ()
    {
        return province;
    }

    public void setProvince (String province)
    {
        this.province = province;
    }

    public String getDocLname ()
    {
        return docLname;
    }

    public void setDocLname (String docLname)
    {
        this.docLname = docLname;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [streetAddress = "+streetAddress+", region = "+region+", docFname = "+docFname+", specDesc = "+specDesc+", contactNumber = "+contactNumber+", doctorCode = "+doctorCode+", docMname = "+docMname+", vat = "+vat+", wtax = "+wtax+", gracePeriod = "+gracePeriod+", city = "+city+", specCode = "+specCode+", prc = "+prc+", province = "+province+", docLname = "+docLname+"]";
    }

}

