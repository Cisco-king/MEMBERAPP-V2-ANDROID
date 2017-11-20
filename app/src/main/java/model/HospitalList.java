package model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import database.table.Table;

/**
 * Created by mpx-pawpaw on 1/4/17.
 */
public class HospitalList implements Parcelable, Table.Hospital {

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


    private Boolean isSelected;

    public HospitalList( ) {

    }


    public HospitalList(String hospitalName, String hospitalCode) {
        this.hospitalName = hospitalName;
        this.hospitalCode = hospitalCode;
    }

    public HospitalList(Cursor cursor) {
        setPhoneNo(getStringCursorValue(cursor, PHONE_NUMBER));
        setRegion(getStringCursorValue(cursor, REGION));
        setStreetAddress(getStringCursorValue(cursor, STREET_ADDRESS));
        setCategory(getStringCursorValue(cursor, CATEGORY));
        setFaxno(getStringCursorValue(cursor, FAX_NUMBER));
        setAlias(getStringCursorValue(cursor, ALIEAS));
        setKeyword(getStringCursorValue(cursor, KEYWORD));
        setProvince(getStringCursorValue(cursor, PROVINCE));
        setHospitalName(getStringCursorValue(cursor, HOSPITAL_NAME));
        setHospitalCode(getStringCursorValue(cursor, HOSPITAL_CODE));
        setCoordinator(getStringCursorValue(cursor, COORDINATOR));
        setContactPerson(getStringCursorValue(cursor, CONTACT_PERSON));
        setCity(getStringCursorValue(cursor, CITY));
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

    public void init(HospitalList hospital) {
        this.phoneNo = hospital.getPhoneNo();
        this.region = hospital.getRegion();
        this.streetAddress = hospital.getStreetAddress();
        this.category = hospital.getCategory();
        this.faxno = hospital.getFaxno();
        this.alias = hospital.getAlias();
        this.keyword = hospital.getKeyword();
        this.province = hospital.getProvince();
        this.hospitalName = hospital.getHospitalName();
        this.hospitalCode = hospital.getHospitalCode();
        this.coordinator = hospital.getCoordinator();
        this.contactPerson = hospital.getContactPerson();
        this.city = hospital.getCity();
    }


    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
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

    public String getFullAddress() {
        return streetAddress + ", " + city.trim() + " ," + province.trim() + ", " + region.trim();
    }

    protected HospitalList(Parcel in) {
        phoneNo = in.readString();
        region = in.readString();
        streetAddress = in.readString();
        category = in.readString();
        faxno = in.readString();
        alias = in.readString();
        keyword = in.readString();
        province = in.readString();
        hospitalName = in.readString();
        hospitalCode = in.readString();
        coordinator = in.readString();
        contactPerson = in.readString();
        city = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phoneNo);
        dest.writeString(region);
        dest.writeString(streetAddress);
        dest.writeString(category);
        dest.writeString(faxno);
        dest.writeString(alias);
        dest.writeString(keyword);
        dest.writeString(province);
        dest.writeString(hospitalName);
        dest.writeString(hospitalCode);
        dest.writeString(coordinator);
        dest.writeString(contactPerson);
        dest.writeString(city);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<HospitalList> CREATOR = new Parcelable.Creator<HospitalList>() {
        @Override
        public HospitalList createFromParcel(Parcel in) {
            return new HospitalList(in);
        }

        @Override
        public HospitalList[] newArray(int size) {
            return new HospitalList[size];
        }
    };

    public String getStringCursorValue(Cursor cursor, String key) {
        return cursor.getString(cursor.getColumnIndex(key));
    }

}