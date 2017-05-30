package model;

/**
 * Created by mpx-pawpaw on 1/4/17.
 */

public class GetDoctorsToHospital
{
    private String region;

    private String streetAddress;

    private String specialRem;

    private String docFname;

    private String specDesc;

    private String hospRemarks;

    private String doctorCode;

    private String docMname;

    private String vat;

    private String wtax;

    private String remarks;

    private String city;

    private String gracePeriod;

    private String phoneNo;

    public GetDoctorsToHospital() {
    }

    public GetDoctorsToHospital(String region, String streetAddress, String specialRem, String docFname, String specDesc, String hospRemarks, String doctorCode, String docMname, String vat, String wtax, String remarks, String city, String gracePeriod, String phoneNo, String specCode, String schedule, String faxno, String province, String hospitalName, String docLname, String hospitalCode, String contactPerson, String roomBoard, String remarks2, String room) {
        this.region = region;
        this.streetAddress = streetAddress;
        this.specialRem = specialRem;
        this.docFname = docFname;

        this.specDesc = specDesc;
        this.hospRemarks = hospRemarks;
        this.doctorCode = doctorCode;
        this.docMname = docMname;
        this.vat = vat;

        this.wtax = wtax;
        this.remarks = remarks;
        this.city = city;
        this.gracePeriod = gracePeriod;
        this.phoneNo = phoneNo;

        this.specCode = specCode;
        this.schedule = schedule;
        this.faxno = faxno;
        this.province = province;
        this.hospitalName = hospitalName;

        this.docLname = docLname;
        this.hospitalCode = hospitalCode;
        this.contactPerson = contactPerson;
        this.roomBoard = roomBoard;
        this.remarks2 = remarks2;
        this.room = room;
    }

    private String specCode;

    private String schedule;

    private String faxno;

    private String province;

    private String hospitalName;

    private String docLname;

    private String hospitalCode;

    private String contactPerson;

    private String roomBoard;

    private String remarks2;

    private String room;

    public String getRegion ()
    {
        return region;
    }

    public void setRegion (String region)
    {
        this.region = region;
    }

    public String getStreetAddress ()
    {
        return streetAddress;
    }

    public void setStreetAddress (String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    public String getSpecialRem ()
    {
        return specialRem;
    }

    public void setSpecialRem (String specialRem)
    {
        this.specialRem = specialRem;
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

    public String getHospRemarks ()
    {
        return hospRemarks;
    }

    public void setHospRemarks (String hospRemarks)
    {
        this.hospRemarks = hospRemarks;
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

    public String getRemarks ()
    {
        return remarks;
    }

    public void setRemarks (String remarks)
    {
        this.remarks = remarks;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getGracePeriod ()
    {
        return gracePeriod;
    }

    public void setGracePeriod (String gracePeriod)
    {
        this.gracePeriod = gracePeriod;
    }

    public String getPhoneNo ()
    {
        return phoneNo;
    }

    public void setPhoneNo (String phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public String getSpecCode ()
    {
        return specCode;
    }

    public void setSpecCode (String specCode)
    {
        this.specCode = specCode;
    }

    public String getSchedule ()
    {
        return schedule;
    }

    public void setSchedule (String schedule)
    {
        this.schedule = schedule;
    }

    public String getFaxno ()
    {
        return faxno;
    }

    public void setFaxno (String faxno)
    {
        this.faxno = faxno;
    }

    public String getProvince ()
    {
        return province;
    }

    public void setProvince (String province)
    {
        this.province = province;
    }

    public String getHospitalName ()
    {
        return hospitalName;
    }

    public void setHospitalName (String hospitalName)
    {
        this.hospitalName = hospitalName;
    }

    public String getDocLname ()
    {
        return docLname;
    }

    public void setDocLname (String docLname)
    {
        this.docLname = docLname;
    }

    public String getHospitalCode ()
    {
        return hospitalCode;
    }

    public void setHospitalCode (String hospitalCode)
    {
        this.hospitalCode = hospitalCode;
    }

    public String getContactPerson ()
    {
        return contactPerson;
    }

    public void setContactPerson (String contactPerson)
    {
        this.contactPerson = contactPerson;
    }

    public String getRoomBoard ()
    {
        return roomBoard;
    }

    public void setRoomBoard (String roomBoard)
    {
        this.roomBoard = roomBoard;
    }

    public String getRemarks2 ()
    {
        return remarks2;
    }

    public void setRemarks2 (String remarks2)
    {
        this.remarks2 = remarks2;
    }

    public String getRoom ()
    {
        return room;
    }

    public void setRoom (String room)
    {
        this.room = room;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetDoctorsToHospital)) return false;

        GetDoctorsToHospital that = (GetDoctorsToHospital) o;

        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (streetAddress != null ? !streetAddress.equals(that.streetAddress) : that.streetAddress != null)
            return false;
        if (specialRem != null ? !specialRem.equals(that.specialRem) : that.specialRem != null)
            return false;
        if (docFname != null ? !docFname.equals(that.docFname) : that.docFname != null)
            return false;
        if (specDesc != null ? !specDesc.equals(that.specDesc) : that.specDesc != null)
            return false;
        if (hospRemarks != null ? !hospRemarks.equals(that.hospRemarks) : that.hospRemarks != null)
            return false;
        if (doctorCode != null ? !doctorCode.equals(that.doctorCode) : that.doctorCode != null)
            return false;
        if (docMname != null ? !docMname.equals(that.docMname) : that.docMname != null)
            return false;
        if (vat != null ? !vat.equals(that.vat) : that.vat != null) return false;
        if (wtax != null ? !wtax.equals(that.wtax) : that.wtax != null) return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (gracePeriod != null ? !gracePeriod.equals(that.gracePeriod) : that.gracePeriod != null)
            return false;
        if (phoneNo != null ? !phoneNo.equals(that.phoneNo) : that.phoneNo != null) return false;
        if (specCode != null ? !specCode.equals(that.specCode) : that.specCode != null)
            return false;
        if (schedule != null ? !schedule.equals(that.schedule) : that.schedule != null)
            return false;
        if (faxno != null ? !faxno.equals(that.faxno) : that.faxno != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null)
            return false;
        if (hospitalName != null ? !hospitalName.equals(that.hospitalName) : that.hospitalName != null)
            return false;
        if (docLname != null ? !docLname.equals(that.docLname) : that.docLname != null)
            return false;
        if (hospitalCode != null ? !hospitalCode.equals(that.hospitalCode) : that.hospitalCode != null)
            return false;
        if (contactPerson != null ? !contactPerson.equals(that.contactPerson) : that.contactPerson != null)
            return false;
        if (roomBoard != null ? !roomBoard.equals(that.roomBoard) : that.roomBoard != null)
            return false;
        if (remarks2 != null ? !remarks2.equals(that.remarks2) : that.remarks2 != null)
            return false;
        return room != null ? room.equals(that.room) : that.room == null;

    }

    @Override
    public int hashCode() {
        int result = region != null ? region.hashCode() : 0;
        result = 31 * result + (streetAddress != null ? streetAddress.hashCode() : 0);
        result = 31 * result + (specialRem != null ? specialRem.hashCode() : 0);
        result = 31 * result + (docFname != null ? docFname.hashCode() : 0);
        result = 31 * result + (specDesc != null ? specDesc.hashCode() : 0);
        result = 31 * result + (hospRemarks != null ? hospRemarks.hashCode() : 0);
        result = 31 * result + (doctorCode != null ? doctorCode.hashCode() : 0);
        result = 31 * result + (docMname != null ? docMname.hashCode() : 0);
        result = 31 * result + (vat != null ? vat.hashCode() : 0);
        result = 31 * result + (wtax != null ? wtax.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (gracePeriod != null ? gracePeriod.hashCode() : 0);
        result = 31 * result + (phoneNo != null ? phoneNo.hashCode() : 0);
        result = 31 * result + (specCode != null ? specCode.hashCode() : 0);
        result = 31 * result + (schedule != null ? schedule.hashCode() : 0);
        result = 31 * result + (faxno != null ? faxno.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (hospitalName != null ? hospitalName.hashCode() : 0);
        result = 31 * result + (docLname != null ? docLname.hashCode() : 0);
        result = 31 * result + (hospitalCode != null ? hospitalCode.hashCode() : 0);
        result = 31 * result + (contactPerson != null ? contactPerson.hashCode() : 0);
        result = 31 * result + (roomBoard != null ? roomBoard.hashCode() : 0);
        result = 31 * result + (remarks2 != null ? remarks2.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [region = "+region+", streetAddress = "+streetAddress+", specialRem = "+specialRem+", docFname = "+docFname+", specDesc = "+specDesc+", hospRemarks = "+hospRemarks+", doctorCode = "+doctorCode+", docMname = "+docMname+", vat = "+vat+", wtax = "+wtax+", remarks = "+remarks+", city = "+city+", gracePeriod = "+gracePeriod+", phoneNo = "+phoneNo+", specCode = "+specCode+", schedule = "+schedule+", faxno = "+faxno+", province = "+province+", hospitalName = "+hospitalName+", docLname = "+docLname+", hospitalCode = "+hospitalCode+", contactPerson = "+contactPerson+", roomBoard = "+roomBoard+", remarks2 = "+remarks2+", room = "+room+"]";
    }
}
