package database.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import database.table.Table;

/**
 * Created by casjohnpaul on 5/5/2017.
 */

public class Doctor implements Table.Doctor, Parcelable {


    /**
     *
     * @param doctorCode
     * @param lastName
     * @param firstName
     * @param middleName
     * @param specializationDescription
     * @param specializationCode
     * @param wtax
     * @param gracePeriod
     * @param vat
     * @param contactNumber
     * @param city
     * @param province
     * @param region
     * @param prc
     * @param streetAddress
     * @param roomNo
     * @param schedule
     */
    public Doctor(
            String doctorCode,
            String lastName,
            String firstName,
            String middleName,
            String specializationDescription,
            String specializationCode,
            String wtax,
            Integer gracePeriod,
            String vat,
            String contactNumber,
            String city,
            String province,
            String region,
            String prc,
            String streetAddress,
            String roomNo,
            String schedule) {

        this.doctorCode = doctorCode;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.specializationDescription = specializationDescription;
        this.specializationCode = specializationCode;
        this.wtax = wtax;
        this.gracePeriod = gracePeriod;
        this.vat = vat;
        this.contactNumber = contactNumber;
        this.city = city;
        this.province = province;
        this.region = region;
        this.prc = prc;
        this.streetAddress = streetAddress;
        this.roomNo = roomNo;
        this.schedule = schedule;
    }

    public Doctor(Cursor cursor) {
        setDoctorCode(cursor.getString(cursor.getColumnIndex(DOCTOR_CODE)));
        setLastName(cursor.getString(cursor.getColumnIndex(LAST_NAME)));
        setFirstName(cursor.getString(cursor.getColumnIndex(FIRST_NAME)));
        setMiddleName(cursor.getString(cursor.getColumnIndex(MIDDLE_NAME)));
        setSpecializationDescription(cursor.getString(cursor.getColumnIndex(SPECIALIZATION_DESCRIPTION)));
        setSpecializationCode(cursor.getString(cursor.getColumnIndex(SPECIALIZATION_CODE)));
        setWtax(cursor.getString(cursor.getColumnIndex(W_TAX)));
        setGracePeriod(cursor.getInt(cursor.getColumnIndex(GRACE_PERIOD)));
        setVat(cursor.getString(cursor.getColumnIndex(VAT)));
        setContactNumber(cursor.getString(cursor.getColumnIndex(CONTACT_NUMBER)));
        setCity(cursor.getString(cursor.getColumnIndex(CITY)));
        setProvince(cursor.getString(cursor.getColumnIndex(PROVINCE)));
        setRegion(cursor.getString(cursor.getColumnIndex(REGION)));
        setPrc(cursor.getString(cursor.getColumnIndex(PRC)));
        setStreetAddress(cursor.getString(cursor.getColumnIndex(STREET_ADDRESS)));
        setRoomNo(cursor.getString(cursor.getColumnIndex(ROOM_NUMBER)));
        setSchedule(cursor.getString(cursor.getColumnIndex(SCHEDULE)));
    }

    protected Doctor(Parcel in) {
        doctorCode = in.readString();
        lastName = in.readString();
        firstName = in.readString();
        middleName = in.readString();
        specializationDescription = in.readString();
        specializationCode = in.readString();
        wtax = in.readString();
        vat = in.readString();
        contactNumber = in.readString();
        city = in.readString();
        province = in.readString();
        region = in.readString();
        prc = in.readString();
        streetAddress = in.readString();
        roomNo = in.readString();
        schedule = in.readString();
    }

    private String doctorCode;

    private String lastName;

    private String firstName;

    private String middleName;

    private String specializationDescription;

    private String specializationCode;

    private String wtax;

    private Integer gracePeriod;

    private String vat;

    private String contactNumber;

    private String city;

    private String province;

    private String region;

    private String prc;

    private String streetAddress;

    private String roomNo;

    private String schedule;


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(doctorCode);
        dest.writeString(lastName);
        dest.writeString(firstName);
        dest.writeString(middleName);
        dest.writeString(specializationDescription);
        dest.writeString(specializationCode);
        dest.writeString(wtax);
        dest.writeString(vat);
        dest.writeString(contactNumber);
        dest.writeString(city);
        dest.writeString(province);
        dest.writeString(region);
        dest.writeString(prc);
        dest.writeString(streetAddress);
        dest.writeString(roomNo);
        dest.writeString(schedule);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSpecializationDescription() {
        return specializationDescription;
    }

    public void setSpecializationDescription(String specializationDescription) {
        this.specializationDescription = specializationDescription;
    }

    public String getSpecializationCode() {
        return specializationCode;
    }

    public void setSpecializationCode(String specializationCode) {
        this.specializationCode = specializationCode;
    }

    public String getWtax() {
        return wtax;
    }

    public void setWtax(String wtax) {
        this.wtax = wtax;
    }

    public Integer getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(Integer gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPrc() {
        return prc;
    }

    public void setPrc(String prc) {
        this.prc = prc;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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

    public String getFullName() {
        return lastName + ", " + firstName;
    }

    public static final String getTableStructure() {

        return "CREATE TABLE " +
                TABLE_NAME + " ( " +
                DOCTOR_CODE + " TEXT, " +
                LAST_NAME + " TEXT ," +
                FIRST_NAME + " TEXT ," +
                MIDDLE_NAME + " TEXT ," +
                SPECIALIZATION_DESCRIPTION + " TEXT ," +
                SPECIALIZATION_CODE + " TEXT ," +
                W_TAX + " TEXT ," +
                GRACE_PERIOD + " INTEGER," +
                VAT + " TEXT ," +
                CONTACT_NUMBER + " TEXT ," +
                CITY + " TEXT ," +
                PROVINCE + " TEXT ," +
                REGION + " TEXT ," +
                PRC + " TEXT ," +
                STREET_ADDRESS + " TEXT ," +
                ROOM_NUMBER + " TEXT ," +
                SCHEDULE + " TEXT )";
    }

}
