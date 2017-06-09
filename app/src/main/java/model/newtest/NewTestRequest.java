package model.newtest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by casjohnpaul on 6/3/2017.
 */

public class NewTestRequest {

    @SerializedName("diagnosisProcedures")
    @Expose
    private List<DiagnosisProcedure> diagnosisProcedures = null;
    @SerializedName("disclaimerTicked")
    @Expose
    private Boolean disclaimerTicked;
    @SerializedName("doctorCode")
    @Expose
    private String doctorCode;
    @SerializedName("hospitalCode")
    @Expose
    private String hospitalCode;
    @SerializedName("isDisclaimerTicked")
    @Expose
    private Boolean isDisclaimerTicked;
    @SerializedName("memberCode")
    @Expose
    private String memberCode;
    @SerializedName("otherDiagnosisCode")
    @Expose
    private String otherDiagnosisCode;
    @SerializedName("primaryComplaint")
    @Expose
    private String primaryComplaint;
    @SerializedName("primaryDiagnosisCode")
    @Expose
    private String primaryDiagnosisCode;
    @SerializedName("requestBy")
    @Expose
    private String requestBy;
    @SerializedName("requestDevice")
    @Expose
    private String requestDevice;
    @SerializedName("requestOrigin")
    @Expose
    private String requestOrigin;
    @SerializedName("serviceSubtype")
    @Expose
    private Integer serviceSubtype;

    public List<DiagnosisProcedure> getDiagnosisProcedures() {
        return diagnosisProcedures;
    }

    public void setDiagnosisProcedures(List<DiagnosisProcedure> diagnosisProcedures) {
        this.diagnosisProcedures = diagnosisProcedures;
    }

    public Boolean getDisclaimerTicked() {
        return disclaimerTicked;
    }

    public void setDisclaimerTicked(Boolean disclaimerTicked) {
        this.disclaimerTicked = disclaimerTicked;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public Boolean getIsDisclaimerTicked() {
        return isDisclaimerTicked;
    }

    public void setIsDisclaimerTicked(Boolean isDisclaimerTicked) {
        this.isDisclaimerTicked = isDisclaimerTicked;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getOtherDiagnosisCode() {
        return otherDiagnosisCode;
    }

    public void setOtherDiagnosisCode(String otherDiagnosisCode) {
        this.otherDiagnosisCode = otherDiagnosisCode;
    }

    public String getPrimaryComplaint() {
        return primaryComplaint;
    }

    public void setPrimaryComplaint(String primaryComplaint) {
        this.primaryComplaint = primaryComplaint;
    }

    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getRequestDevice() {
        return requestDevice;
    }

    public void setRequestDevice(String requestDevice) {
        this.requestDevice = requestDevice;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public Integer getServiceSubtype() {
        return serviceSubtype;
    }

    public void setServiceSubtype(Integer serviceSubtype) {
        this.serviceSubtype = serviceSubtype;
    }
}
