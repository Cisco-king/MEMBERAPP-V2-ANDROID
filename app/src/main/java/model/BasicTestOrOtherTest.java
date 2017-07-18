package model;

import java.util.ArrayList;

import services.model.DiagnosisProcedure;

/**
 * Created by macbookpro on 7/17/17.
 */

public class BasicTestOrOtherTest {
    private String primaryDiagnosisCode;

    private String doctorCode;

    private String isDisclaimerTicked;

    private String requestBy;

    private String otherDiagnosisCode;

    private String serviceSubtype;

    private String requestOrigin;

    private String hospitalCode;

    private String requestDevice;

    private String memberCode;

    private ArrayList<DiagnosisProcedure> diagnosisProcedures;

    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getIsDisclaimerTicked() {
        return isDisclaimerTicked;
    }

    public void setIsDisclaimerTicked(String isDisclaimerTicked) {
        this.isDisclaimerTicked = isDisclaimerTicked;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getOtherDiagnosisCode() {
        return otherDiagnosisCode;
    }

    public void setOtherDiagnosisCode(String otherDiagnosisCode) {
        this.otherDiagnosisCode = otherDiagnosisCode;
    }

    public String getServiceSubtype() {
        return serviceSubtype;
    }

    public void setServiceSubtype(String serviceSubtype) {
        this.serviceSubtype = serviceSubtype;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getRequestDevice() {
        return requestDevice;
    }

    public void setRequestDevice(String requestDevice) {
        this.requestDevice = requestDevice;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public ArrayList<DiagnosisProcedure> getDiagnosisProcedures() {
        return diagnosisProcedures;
    }

    public void setDiagnosisProcedures(ArrayList<DiagnosisProcedure> diagnosisProcedures) {
        this.diagnosisProcedures = diagnosisProcedures;
    }
}
