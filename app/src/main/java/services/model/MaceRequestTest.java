package services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 6/9/2017.
 */

public class MaceRequestTest {

    @SerializedName("transactionId")
    @Expose
    private Integer transactionId;
    @SerializedName("maceRequestId")
    @Expose
    private Integer maceRequestId;
    @SerializedName("requestFrom")
    @Expose
    private Object requestFrom;
    @SerializedName("statusRemarks")
    @Expose
    private String statusRemarks;
    @SerializedName("approvedOn")
    @Expose
    private Object approvedOn;
    @SerializedName("approvedBy")
    @Expose
    private Object approvedBy;
    @SerializedName("approvalRemarks")
    @Expose
    private Object approvalRemarks;
    @SerializedName("disapprovedOn")
    @Expose
    private Object disapprovedOn;
    @SerializedName("disapprovedBy")
    @Expose
    private Object disapprovedBy;
    @SerializedName("disapprovalRemarks")
    @Expose
    private Object disapprovalRemarks;
    @SerializedName("approvalNo")
    @Expose
    private String approvalNo;
    @SerializedName("validFrom")
    @Expose
    private Integer validFrom;
    @SerializedName("validTo")
    @Expose
    private Integer validTo;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("expiredOn")
    @Expose
    private Object expiredOn;
    @SerializedName("consultReason")
    @Expose
    private Object consultReason;
    @SerializedName("docHospId")
    @Expose
    private Integer docHospId;
    @SerializedName("doctorCode")
    @Expose
    private String doctorCode;
    @SerializedName("hospitalCode")
    @Expose
    private String hospitalCode;
    @SerializedName("primaryDiagnosisCode")
    @Expose
    private String primaryDiagnosisCode;
    @SerializedName("primaryDiagnosisICD10")
    @Expose
    private String primaryDiagnosisICD10;
    @SerializedName("primaryDiagnosisDesc")
    @Expose
    private String primaryDiagnosisDesc;
    @SerializedName("dxRemarks")
    @Expose
    private String dxRemarks;
    @SerializedName("availHospId")
    @Expose
    private String availHospId;
    @SerializedName("planOfManagement")
    @Expose
    private String planOfManagement;
    @SerializedName("transamount")
    @Expose
    private Integer transamount;
    @SerializedName("testSubtype")
    @Expose
    private Integer testSubtype;
    @SerializedName("maceTestGroup")
    @Expose
    private String maceTestGroup;
    @SerializedName("refRequestId")
    @Expose
    private Integer refRequestId;
    @SerializedName("refRefNo")
    @Expose
    private Object refRefNo;
    @SerializedName("disapprvalReason")
    @Expose
    private Object disapprvalReason;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public Object getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(Object requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getStatusRemarks() {
        return statusRemarks;
    }

    public void setStatusRemarks(String statusRemarks) {
        this.statusRemarks = statusRemarks;
    }

    public Object getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(Object approvedOn) {
        this.approvedOn = approvedOn;
    }

    public Object getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Object approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Object getApprovalRemarks() {
        return approvalRemarks;
    }

    public void setApprovalRemarks(Object approvalRemarks) {
        this.approvalRemarks = approvalRemarks;
    }

    public Object getDisapprovedOn() {
        return disapprovedOn;
    }

    public void setDisapprovedOn(Object disapprovedOn) {
        this.disapprovedOn = disapprovedOn;
    }

    public Object getDisapprovedBy() {
        return disapprovedBy;
    }

    public void setDisapprovedBy(Object disapprovedBy) {
        this.disapprovedBy = disapprovedBy;
    }

    public Object getDisapprovalRemarks() {
        return disapprovalRemarks;
    }

    public void setDisapprovalRemarks(Object disapprovalRemarks) {
        this.disapprovalRemarks = disapprovalRemarks;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public Integer getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Integer validFrom) {
        this.validFrom = validFrom;
    }

    public Integer getValidTo() {
        return validTo;
    }

    public void setValidTo(Integer validTo) {
        this.validTo = validTo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Object getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(Object expiredOn) {
        this.expiredOn = expiredOn;
    }

    public Object getConsultReason() {
        return consultReason;
    }

    public void setConsultReason(Object consultReason) {
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

    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    public String getPrimaryDiagnosisICD10() {
        return primaryDiagnosisICD10;
    }

    public void setPrimaryDiagnosisICD10(String primaryDiagnosisICD10) {
        this.primaryDiagnosisICD10 = primaryDiagnosisICD10;
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

    public String getAvailHospId() {
        return availHospId;
    }

    public void setAvailHospId(String availHospId) {
        this.availHospId = availHospId;
    }

    public String getPlanOfManagement() {
        return planOfManagement;
    }

    public void setPlanOfManagement(String planOfManagement) {
        this.planOfManagement = planOfManagement;
    }

    public Integer getTransamount() {
        return transamount;
    }

    public void setTransamount(Integer transamount) {
        this.transamount = transamount;
    }

    public Integer getTestSubtype() {
        return testSubtype;
    }

    public void setTestSubtype(Integer testSubtype) {
        this.testSubtype = testSubtype;
    }

    public String getMaceTestGroup() {
        return maceTestGroup;
    }

    public void setMaceTestGroup(String maceTestGroup) {
        this.maceTestGroup = maceTestGroup;
    }

    public Integer getRefRequestId() {
        return refRequestId;
    }

    public void setRefRequestId(Integer refRequestId) {
        this.refRequestId = refRequestId;
    }

    public Object getRefRefNo() {
        return refRefNo;
    }

    public void setRefRefNo(Object refRefNo) {
        this.refRefNo = refRefNo;
    }

    public Object getDisapprvalReason() {
        return disapprvalReason;
    }

    public void setDisapprvalReason(Object disapprvalReason) {
        this.disapprvalReason = disapprvalReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}