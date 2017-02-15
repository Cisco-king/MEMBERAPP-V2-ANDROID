package model;

/**
 * Created by mpx-pawpaw on 1/4/17.
 */

public class HospitalList {

    private String phoneNo;

    private String region;

    private String streetAddress;

    private String category;

    private String faxno;

    private String alias;

    private String keyword;

    private String province;

    private String hospitalName;

    private String hospitalCode;

    private String coordinator;

    private String contactPerson;

    private String city;

    public HospitalList( ) {

    }

    public HospitalList(String phoneNo, String region, String streetAddress, String category, String faxno, String alias, String keyword, String province, String hospitalName, String hospitalCode, String coordinator, String contactPerson, String city) {
        this.phoneNo = phoneNo;
        this.region = region;
        this.streetAddress = streetAddress;
        this.category = category;
        this.faxno = faxno;
        this.alias = alias;
        this.keyword = keyword;
        this.province = province;
        this.hospitalName = hospitalName;
        this.hospitalCode = hospitalCode;
        this.coordinator = coordinator;
        this.contactPerson = contactPerson;
        this.city = city;
    }

    public String getPhoneNo ()
    {
        return phoneNo;
    }

    public void setPhoneNo (String phoneNo)
    {
        this.phoneNo = phoneNo;
    }

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

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getFaxno ()
    {
        return faxno;
    }

    public void setFaxno (String faxno)
    {
        this.faxno = faxno;
    }

    public String getAlias ()
    {
        return alias;
    }

    public void setAlias (String alias)
    {
        this.alias = alias;
    }

    public String getKeyword ()
    {
        return keyword;
    }

    public void setKeyword (String keyword)
    {
        this.keyword = keyword;
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

    public String getHospitalCode ()
    {
        return hospitalCode;
    }

    public void setHospitalCode (String hospitalCode)
    {
        this.hospitalCode = hospitalCode;
    }

    public String getCoordinator ()
    {
        return coordinator;
    }

    public void setCoordinator (String coordinator)
    {
        this.coordinator = coordinator;
    }

    public String getContactPerson ()
    {
        return contactPerson;
    }

    public void setContactPerson (String contactPerson)
    {
        this.contactPerson = contactPerson;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [phoneNo = "+phoneNo+", region = "+region+", streetAddress = "+streetAddress+", category = "+category+", faxno = "+faxno+", alias = "+alias+", keyword = "+keyword+", province = "+province+", hospitalName = "+hospitalName+", hospitalCode = "+hospitalCode+", coordinator = "+coordinator+", contactPerson = "+contactPerson+", city = "+city+"]";
    }
}