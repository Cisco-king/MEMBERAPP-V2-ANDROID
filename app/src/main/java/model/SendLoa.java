package model;

/**
 * Created by mpx-pawpaw on 1/4/17.
 */

public class SendLoa {
    private String locationCode;

    private String username;

    private String procedureCode;

    private String doctorCode;

    private String deviceID;

    private String procedureAmount;

    private String procedureDesc;

    private String hospitalCode;

    private String diagnosisCode;

    private String memberCode;

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getProcedureAmount() {
        return procedureAmount;
    }

    public void setProcedureAmount(String procedureAmount) {
        this.procedureAmount = procedureAmount;
    }

    public String getProcedureDesc() {
        return procedureDesc;
    }

    public void setProcedureDesc(String procedureDesc) {
        this.procedureDesc = procedureDesc;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    @Override
    public String toString() {
        return "ClassPojo [locationCode = " + locationCode + ", username = " + username + ", procedureCode = " + procedureCode + ", doctorCode = " + doctorCode + ", deviceID = " + deviceID + ", procedureAmount = " + procedureAmount + ", procedureDesc = " + procedureDesc + ", hospitalCode = " + hospitalCode + ", diagnosisCode = " + diagnosisCode + ", memberCode = " + memberCode + "]";
    }

}
