package services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 5/5/2017.
 */

public class DoctorList {

    @SerializedName("doctorCode")
    @Expose
    private String doctorCode;
    @SerializedName("docLname")
    @Expose
    private String lastName;
    @SerializedName("docFname")
    @Expose
    private String docFname;
    @SerializedName("docMname")
    @Expose
    private String docMname;
    @SerializedName("hospitalName")
    @Expose
    private String hospitalName;
    @SerializedName("specDesc")
    @Expose

    private String specDesc;
    @SerializedName("specCode")
    @Expose
    private String specCode;
    @SerializedName("wtax")
    @Expose
    private String wtax;
    @SerializedName("gracePeriod")
    @Expose
    private Integer gracePeriod;
    @SerializedName("vat")
    @Expose
    private String vat;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("prc")
    @Expose
    private String prc;
    @SerializedName("streetAddress")
    @Expose
    private String streetAddress;
    @SerializedName("roomNo")
    @Expose
    private String roomNo;
    @SerializedName("schedule")
    @Expose
    private String schedule;

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

    public String getDocFname() {
        return docFname;
    }

    public void setDocFname(String docFname) {
        this.docFname = docFname;
    }

    public String getDocMname() {
        return docMname;
    }

    public void setDocMname(String docMname) {
        this.docMname = docMname;
    }

    public String getSpecDesc() {
        return specDesc;
    }

    public void setSpecDesc(String specDesc) {
        this.specDesc = specDesc;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
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
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getFullName() {
        return lastName + ", " + docFname;
    }

}
