package services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 5/5/2017.
 */

public class LoaList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("approvalNo")
    @Expose
    private String approvalNo;
    @SerializedName("batchCode")
    @Expose
    private String batchCode;
    @SerializedName("callerId")
    @Expose
    private String callerId;
    @SerializedName("callTypeId")
    @Expose
    private Integer callTypeId;
    @SerializedName("memberCode")
    @Expose
    private String memberCode;
    @SerializedName("hospitalCode")
    @Expose
    private String hospitalCode;
    @SerializedName("companyCode")
    @Expose
    private String companyCode;
    @SerializedName("doctorCode")
    @Expose
    private String doctorCode;
    @SerializedName("doctor")
    @Expose
    private Doctor doctor;
    @SerializedName("diagnosisCode")
    @Expose
    private String diagnosisCode;
    @SerializedName("procedureCode")
    @Expose
    private String procedureCode;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("dateAdmitted")
    @Expose
    private String dateAdmitted;
    @SerializedName("diagnosis")
    @Expose
    private String diagnosis;
    @SerializedName("procedureDesc")
    @Expose
    private String procedureDesc;
    @SerializedName("procedureAmount")
    @Expose
    private String procedureAmount;
    @SerializedName("actionTaken")
    @Expose
    private Integer actionTaken;
    @SerializedName("updatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("updatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("runningBill")
    @Expose
    private String runningBill;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("memLname")
    @Expose
    private String memLname;
    @SerializedName("memFname")
    @Expose
    private String memFname;
    @SerializedName("memMi")
    @Expose
    private String memMi;
    @SerializedName("memCompany")
    @Expose
    private String memCompany;
    @SerializedName("terminalNo")
    @Expose
    private String terminalNo;
    @SerializedName("callDate")
    @Expose
    private String callDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("approvalDate")
    @Expose
    private String approvalDate;
    @SerializedName("primaryComplaint")
    @Expose
    private String primaryComplaint;
    @SerializedName("disclaimerTicked")
    @Expose
    private Integer disclaimerTicked;
    @SerializedName("requestOrigin")
    @Expose
    private String requestOrigin;

    @SerializedName("withProvider")
    @Expose
    private boolean withProvider;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public Integer getCallTypeId() {
        return callTypeId;
    }

    public void setCallTypeId(Integer callTypeId) {
        this.callTypeId = callTypeId;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(String dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getProcedureDesc() {
        return procedureDesc;
    }

    public void setProcedureDesc(String procedureDesc) {
        this.procedureDesc = procedureDesc;
    }

    public String getProcedureAmount() {
        return procedureAmount;
    }

    public void setProcedureAmount(String procedureAmount) {
        this.procedureAmount = procedureAmount;
    }

    public Integer getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(Integer actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRunningBill() {
        return runningBill;
    }

    public void setRunningBill(String runningBill) {
        this.runningBill = runningBill;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMemLname() {
        return memLname;
    }

    public void setMemLname(String memLname) {
        this.memLname = memLname;
    }

    public String getMemFname() {
        return memFname;
    }

    public void setMemFname(String memFname) {
        this.memFname = memFname;
    }

    public String getMemMi() {
        return memMi;
    }

    public void setMemMi(String memMi) {
        this.memMi = memMi;
    }

    public String getMemCompany() {
        return memCompany;
    }

    public void setMemCompany(String memCompany) {
        this.memCompany = memCompany;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getPrimaryComplaint() {
        return primaryComplaint;
    }

    public void setPrimaryComplaint(String primaryComplaint) {
        this.primaryComplaint = primaryComplaint;
    }

    public Integer getDisclaimerTicked() {
        return disclaimerTicked;
    }

    public void setDisclaimerTicked(Integer disclaimerTicked) {
        this.disclaimerTicked = disclaimerTicked;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public boolean getWithProvider() {
        return withProvider;
    }

    public void setWithProvider(boolean withProvider) {
        this.withProvider = withProvider;
    }
}