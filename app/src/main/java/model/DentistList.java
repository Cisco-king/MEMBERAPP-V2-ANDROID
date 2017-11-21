package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IPC on 11/17/2017.
 */

public class DentistList {
    private String dentistCode;
    private String lastName;
    private String firstName;
    private String middleName;
    private String dentistAddress;
    private String contactNo;
    private String schedule;
    private String clinic;
    private String provinceCode;
    private String regionCode;
    private String cityCode;
    private String faxNo;
    private String oldCode;
    private String gracePeriod;
    private String effDate;
    private String isAccredited;
    private String effDateRa;
    private String effDateNap;
    private String vat;
    private String tinNo;
    private String taxable;
    private String wTax;
    private String specialRem;
    private String email;
    private String otherSpecialty;
    private String remarks;
    private String createdDate;
    private String createdBy;
    private String updatedDate;
    private String updatedBy;
    private String withPRC;
    private String withDiploma;
    private String withPermit;
    private String oldDentistCode;




    public DentistList(String dentistCode, String lastName, String firstName, String middleName, String dentistAddress, String contactNo, String schedule, String clinic, String provinceCode, String regionCode, String cityCode, String faxNo, String oldCode, String gracePeriod, String effDate, String isAccredited, String effDateRa, String effDateNap, String vat, String tinNo, String taxable, String wTax, String specialRem, String email, String otherSpecialty, String remarks, String createdDate, String createdBy, String updatedDate, String updatedBy, String withPRC, String withDiploma, String withPermit, String oldDentistCode) {
        this.dentistCode = dentistCode;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.dentistAddress = dentistAddress;
        this.contactNo = contactNo;
        this.schedule = schedule;
        this.clinic = clinic;
        this.provinceCode = provinceCode;
        this.regionCode = regionCode;
        this.cityCode = cityCode;
        this.faxNo = faxNo;
        this.oldCode = oldCode;
        this.gracePeriod = gracePeriod;
        this.effDate = effDate;
        this.isAccredited = isAccredited;
        this.effDateRa = effDateRa;
        this.effDateNap = effDateNap;
        this.vat = vat;
        this.tinNo = tinNo;
        this.taxable = taxable;
        this.wTax = wTax;
        this.specialRem = specialRem;
        this.email = email;
        this.otherSpecialty = otherSpecialty;
        this.remarks = remarks;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.withPRC = withPRC;
        this.withDiploma = withDiploma;
        this.withPermit = withPermit;
        this.oldDentistCode = oldDentistCode;


    }

    public DentistList() {

    }



    public String getDentistCode() {
        return dentistCode;
    }

    public void setDentistCode(String dentistCode) {
        this.dentistCode = dentistCode;
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

    public String getDentistAddress() {
        return dentistAddress;
    }

    public void setDentistAddress(String dentistAddress) {
        this.dentistAddress = dentistAddress;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getOldCode() {
        return oldCode;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }

    public String getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(String gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    public String getIsAccredited() {
        return isAccredited;
    }

    public void setIsAccredited(String isAccredited) {
        this.isAccredited = isAccredited;
    }

    public String getEffDateRa() {
        return effDateRa;
    }

    public void setEffDateRa(String effDateRa) {
        this.effDateRa = effDateRa;
    }

    public String getEffDateNap() {
        return effDateNap;
    }

    public void setEffDateNap(String effDateNap) {
        this.effDateNap = effDateNap;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

    public String getTaxable() {
        return taxable;
    }

    public void setTaxable(String taxable) {
        this.taxable = taxable;
    }

    public String getwTax() {
        return wTax;
    }

    public void setwTax(String wTax) {
        this.wTax = wTax;
    }

    public String getSpecialRem() {
        return specialRem;
    }

    public void setSpecialRem(String specialRem) {
        this.specialRem = specialRem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtherSpecialty() {
        return otherSpecialty;
    }

    public void setOtherSpecialty(String otherSpecialty) {
        this.otherSpecialty = otherSpecialty;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getWithPRC() {
        return withPRC;
    }

    public void setWithPRC(String withPRC) {
        this.withPRC = withPRC;
    }

    public String getWithDiploma() {
        return withDiploma;
    }

    public void setWithDiploma(String withDiploma) {
        this.withDiploma = withDiploma;
    }

    public String getWithPermit() {
        return withPermit;
    }

    public void setWithPermit(String withPermit) {
        this.withPermit = withPermit;
    }

    public String getOldDentistCode() {
        return oldDentistCode;
    }

    public void setOldDentistCode(String oldDentistCode) {
        this.oldDentistCode = oldDentistCode;
    }

    @Override
    public String toString() {
        String print = "";
        print += "ClassPojo";
        print += " [";
        print += "[lastName = " + lastName + ",";
        print += " firstName = " + firstName + ",";
        print += " middleName = " + middleName + ",";
        print += " dentistAddress = " + dentistAddress + ",";
        print += " contactNo = " + contactNo + ",";
        print += " schedule = " + schedule + ",";
        print += " clinic = " + clinic + ",";
        print += " provinceCode = " + provinceCode + ",";
        print += " regionCode = " + regionCode + ",";
        print += " cityCode = " + cityCode + ",";
        print += " faxNo = " + faxNo + ",";
        print += " oldCode = " + oldCode + ",";
        print += " gracePeriod = " + gracePeriod + ",";
        print += " effDate = " + effDate + ",";
        print += " isAccredited = " + isAccredited + ",";
        print += " effDateRa = " + effDateRa + ",";
        print += " effDateNap = " + effDateNap + ",";
        print += " vat = " + vat + ",";
        print += " tinNo = " + tinNo + ",";
        print += " taxable = " + taxable + ",";
        print += " wTax = " + wTax + ",";
        print += " specialRem = " + specialRem + ",";
        print += " email = " + email + ",";
        print += " otherSpecialty = " + otherSpecialty + ",";
        print += " remarks = " + remarks + ",";
        print += " createdDate = " + createdDate + ",";
        print += " createdBy = " + createdBy + ",";
        print += " updatedDate = " + updatedDate + ",";
        print += " updatedBy = " + updatedBy + ",";
        print += " withPRC = " + withPRC + ",";
        print += " withDiploma = " + withDiploma + ",";
        print += " withPermit = " + withPermit + ",";
        print += " oldDentistCode = " + oldDentistCode + ",";
        print += "]";
        return print;
    }

}
