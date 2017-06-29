package services.model.mace;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 6/27/2017.
 */

public class Details {

    @SerializedName("transactionId")
    @Expose
    private Integer transactionId;
    @SerializedName("transCode")
    @Expose
    private String transCode;
    @SerializedName("maceRequestId")
    @Expose
    private Integer maceRequestId;
    @SerializedName("consultSubtype")
    @Expose
    private Integer consultSubtype;
    @SerializedName("requestFrom")
    @Expose
    private String requestFrom;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusRemarks")
    @Expose
    private String statusRemarks;
    @SerializedName("approvedOn")
    @Expose
    private String approvedOn;
    @SerializedName("approvedBy")
    @Expose
    private String approvedBy;
    @SerializedName("approvalRemarks")
    @Expose
    private String approvalRemarks;
    @SerializedName("disapprovedOn")
    @Expose
    private String disapprovedOn;
    @SerializedName("disapprovedBy")
    @Expose
    private String disapprovedBy;
    @SerializedName("disapprovalRemarks")
    @Expose
    private String disapprovalRemarks;
    @SerializedName("disapprovalReason")
    @Expose
    private String disapprovalReason;
    @SerializedName("referenceNo")
    @Expose
    private String referenceNo;
    @SerializedName("validfrom")
    @Expose
    private String validfrom;
    @SerializedName("validto")
    @Expose
    private String validto;
    @SerializedName("availedOn")
    @Expose
    private String availedOn;
    @SerializedName("availMemberAgreed")
    @Expose
    private String availMemberAgreed;
    @SerializedName("availMemberAgreedon")
    @Expose
    private String availMemberAgreedon;
    @SerializedName("pinEntered")
    @Expose
    private String pinEntered;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("recordSubmittedOn")
    @Expose
    private String recordSubmittedOn;
    @SerializedName("recordSubmittedBy")
    @Expose
    private String recordSubmittedBy;
    @SerializedName("expiredOn")
    @Expose
    private String expiredOn;
    @SerializedName("consultationDate")
    @Expose
    private String consultationDate;
    @SerializedName("consultReason")
    @Expose
    private String consultReason;
    @SerializedName("docHospId")
    @Expose
    private Integer docHospId;
    @SerializedName("doctorCode")
    @Expose
    private String doctorCode;
    @SerializedName("hospitalCode")
    @Expose
    private String hospitalCode;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("historyOfPresentIllness")
    @Expose
    private String historyOfPresentIllness;
    @SerializedName("pastOrFamilyHistory")
    @Expose
    private String pastOrFamilyHistory;
    @SerializedName("reviewOfSystems")
    @Expose
    private String reviewOfSystems;
    @SerializedName("vitalsBp")
    @Expose
    private String vitalsBp;
    @SerializedName("vitalsHr")
    @Expose
    private String vitalsHr;
    @SerializedName("vitalsRr")
    @Expose
    private String vitalsRr;
    @SerializedName("vitalsTemp")
    @Expose
    private String vitalsTemp;
    @SerializedName("physicalExamination")
    @Expose
    private String physicalExamination;
    @SerializedName("primaryDiagnosisCode")
    @Expose
    private String primaryDiagnosisCode;
    @SerializedName("primaryDiagnosisIcd10")
    @Expose
    private String primaryDiagnosisIcd10;
    @SerializedName("primaryDiagnosisDesc")
    @Expose
    private String primaryDiagnosisDesc;
    @SerializedName("dxRemarks")
    @Expose
    private String dxRemarks;
    @SerializedName("planOfManagement")
    @Expose
    private String planOfManagement;
    @SerializedName("transAmount")
    @Expose
    private String transAmount;
    @SerializedName("maternity")
    @Expose
    private String maternity;
    @SerializedName("congenital")
    @Expose
    private String congenital;
    @SerializedName("medicolegal")
    @Expose
    private String medicolegal;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public Integer getConsultSubtype() {
        return consultSubtype;
    }

    public void setConsultSubtype(Integer consultSubtype) {
        this.consultSubtype = consultSubtype;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusRemarks() {
        return statusRemarks;
    }

    public void setStatusRemarks(String statusRemarks) {
        this.statusRemarks = statusRemarks;
    }

    public String getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(String approvedOn) {
        this.approvedOn = approvedOn;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovalRemarks() {
        return approvalRemarks;
    }

    public void setApprovalRemarks(String approvalRemarks) {
        this.approvalRemarks = approvalRemarks;
    }

    public String getDisapprovedOn() {
        return disapprovedOn;
    }

    public void setDisapprovedOn(String disapprovedOn) {
        this.disapprovedOn = disapprovedOn;
    }

    public String getDisapprovedBy() {
        return disapprovedBy;
    }

    public void setDisapprovedBy(String disapprovedBy) {
        this.disapprovedBy = disapprovedBy;
    }

    public String getDisapprovalRemarks() {
        return disapprovalRemarks;
    }

    public void setDisapprovalRemarks(String disapprovalRemarks) {
        this.disapprovalRemarks = disapprovalRemarks;
    }

    public String getDisapprovalReason() {
        return disapprovalReason;
    }

    public void setDisapprovalReason(String disapprovalReason) {
        this.disapprovalReason = disapprovalReason;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getValidfrom() {
        return validfrom;
    }

    public void setValidfrom(String validfrom) {
        this.validfrom = validfrom;
    }

    public String getValidto() {
        return validto;
    }

    public void setValidto(String validto) {
        this.validto = validto;
    }

    public String getAvailedOn() {
        return availedOn;
    }

    public void setAvailedOn(String availedOn) {
        this.availedOn = availedOn;
    }

    public String getAvailMemberAgreed() {
        return availMemberAgreed;
    }

    public void setAvailMemberAgreed(String availMemberAgreed) {
        this.availMemberAgreed = availMemberAgreed;
    }

    public String getAvailMemberAgreedon() {
        return availMemberAgreedon;
    }

    public void setAvailMemberAgreedon(String availMemberAgreedon) {
        this.availMemberAgreedon = availMemberAgreedon;
    }

    public String getPinEntered() {
        return pinEntered;
    }

    public void setPinEntered(String pinEntered) {
        this.pinEntered = pinEntered;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRecordSubmittedOn() {
        return recordSubmittedOn;
    }

    public void setRecordSubmittedOn(String recordSubmittedOn) {
        this.recordSubmittedOn = recordSubmittedOn;
    }

    public String getRecordSubmittedBy() {
        return recordSubmittedBy;
    }

    public void setRecordSubmittedBy(String recordSubmittedBy) {
        this.recordSubmittedBy = recordSubmittedBy;
    }

    public String getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(String expiredOn) {
        this.expiredOn = expiredOn;
    }

    public String getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getConsultReason() {
        return consultReason;
    }

    public void setConsultReason(String consultReason) {
        this.consultReason = consultReason;
    }

    public Integer getDocHospId() {
        return docHospId;
    }

    public void setDocHospId(Integer docHospId) {
        this.docHospId = docHospId;
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

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getHistoryOfPresentIllness() {
        return historyOfPresentIllness;
    }

    public void setHistoryOfPresentIllness(String historyOfPresentIllness) {
        this.historyOfPresentIllness = historyOfPresentIllness;
    }

    public String getPastOrFamilyHistory() {
        return pastOrFamilyHistory;
    }

    public void setPastOrFamilyHistory(String pastOrFamilyHistory) {
        this.pastOrFamilyHistory = pastOrFamilyHistory;
    }

    public String getReviewOfSystems() {
        return reviewOfSystems;
    }

    public void setReviewOfSystems(String reviewOfSystems) {
        this.reviewOfSystems = reviewOfSystems;
    }

    public String getVitalsBp() {
        return vitalsBp;
    }

    public void setVitalsBp(String vitalsBp) {
        this.vitalsBp = vitalsBp;
    }

    public String getVitalsHr() {
        return vitalsHr;
    }

    public void setVitalsHr(String vitalsHr) {
        this.vitalsHr = vitalsHr;
    }

    public String getVitalsRr() {
        return vitalsRr;
    }

    public void setVitalsRr(String vitalsRr) {
        this.vitalsRr = vitalsRr;
    }

    public String getVitalsTemp() {
        return vitalsTemp;
    }

    public void setVitalsTemp(String vitalsTemp) {
        this.vitalsTemp = vitalsTemp;
    }

    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    public String getPrimaryDiagnosisIcd10() {
        return primaryDiagnosisIcd10;
    }

    public void setPrimaryDiagnosisIcd10(String primaryDiagnosisIcd10) {
        this.primaryDiagnosisIcd10 = primaryDiagnosisIcd10;
    }

    public String getPrimaryDiagnosisDesc() {
        return primaryDiagnosisDesc;
    }

    public void setPrimaryDiagnosisDesc(String primaryDiagnosisDesc) {
        this.primaryDiagnosisDesc = primaryDiagnosisDesc;
    }

    public String getDxRemarks() {
        return dxRemarks;
    }

    public void setDxRemarks(String dxRemarks) {
        this.dxRemarks = dxRemarks;
    }

    public String getPlanOfManagement() {
        return planOfManagement;
    }

    public void setPlanOfManagement(String planOfManagement) {
        this.planOfManagement = planOfManagement;
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getMaternity() {
        return maternity;
    }

    public void setMaternity(String maternity) {
        this.maternity = maternity;
    }

    public String getCongenital() {
        return congenital;
    }

    public void setCongenital(String congenital) {
        this.congenital = congenital;
    }

    public String getMedicolegal() {
        return medicolegal;
    }

    public void setMedicolegal(String medicolegal) {
        this.medicolegal = medicolegal;
    }

}
