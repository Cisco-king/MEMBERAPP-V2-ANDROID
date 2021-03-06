package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mpx-pawpaw on 2/17/17.
 */
public class LoaFetch implements Parcelable {
    private String procedureCode;

    private String primaryComplaint;

    private String batchCode;

    private String reason;

    private String memCompany;

    private String diagnosis;

    private String remarks;

    private String diagnosisCode;

    private String type;

    private String updatedBy;

    private String callTypeId;

    private String id;

    private String runningBill;

    private String memMi;

    private String memberCode;

    private String memFname;

    private String memLname;

    private String doctorCode;

    private String actionTaken;

    private String status;

    private String updatedDate;

    private String terminalNo;

    private String approvalNo;

    private String procedureAmount;

    private String callDate;

    private String companyCode;

    private String category;

    private String callerId;

    private String approvalDate;

    private String hospitalCode;

    private String hospitalName;

    private String procedureDesc;

    private String notes;

    private String dateAdmitted;

    private String room;

    private String doctorName;

    private String doctorSpec;

    private String doctorSpecCode;

    private String schedule;

    private int withProvider;


    public LoaFetch(String primaryComplaint, String procedureCode, String batchCode, String reason, String memCompany, String diagnosis, String remarks, String diagnosisCode, String type, String updatedBy, String callTypeId, String id, String runningBill, String memMi, String memberCode, String memFname, String memLname, String doctorCode, String actionTaken, String status, String updatedDate, String terminalNo, String approvalNo, String procedureAmount, String callDate, String companyCode, String category, String callerId, String approvalDate, String hospitalCode, String procedureDesc, String notes, String dateAdmitted, String room, String doctorName, String doctorSpec, String doctorSpecCode, String hospitalName, String schedule) {
        this.primaryComplaint = primaryComplaint;
        this.procedureCode = procedureCode;
        this.batchCode = batchCode;
        this.reason = reason;
        this.memCompany = memCompany;
        this.diagnosis = diagnosis;
        this.remarks = remarks;
        this.diagnosisCode = diagnosisCode;
        this.type = type;
        this.updatedBy = updatedBy;
        this.callTypeId = callTypeId;
        this.id = id;
        this.runningBill = runningBill;
        this.memMi = memMi;
        this.memberCode = memberCode;
        this.memFname = memFname;
        this.memLname = memLname;
        this.doctorCode = doctorCode;
        this.actionTaken = actionTaken;
        this.status = status;
        this.updatedDate = updatedDate;
        this.terminalNo = terminalNo;
        this.approvalNo = approvalNo;
        this.procedureAmount = procedureAmount;
        this.callDate = callDate;
        this.companyCode = companyCode;
        this.category = category;
        this.callerId = callerId;
        this.approvalDate = approvalDate;
        this.hospitalCode = hospitalCode;
        this.procedureDesc = procedureDesc;
        this.notes = notes;
        this.dateAdmitted = dateAdmitted;
        this.room = room;
        this.doctorName = doctorName;
        this.doctorSpec = doctorSpec;
        this.doctorSpecCode = doctorSpecCode;
        this.hospitalName = hospitalName;
        this.schedule = schedule;
    }

    public LoaFetch(String primaryComplaint, String procedureCode, String batchCode, String reason, String memCompany, String diagnosis, String remarks, String diagnosisCode, String type, String updatedBy, String callTypeId, String id, String runningBill, String memMi, String memberCode, String memFname, String memLname, String doctorCode, String actionTaken, String status, String updatedDate, String terminalNo, String approvalNo, String procedureAmount, String callDate, String companyCode, String category, String callerId, String approvalDate, String hospitalCode, String procedureDesc, String notes, String dateAdmitted, String room, String doctorName, String doctorSpec, String doctorSpecCode, String hospitalName, String schedule, int withProvider) {
        this.primaryComplaint = primaryComplaint;
        this.procedureCode = procedureCode;
        this.batchCode = batchCode;
        this.reason = reason;
        this.memCompany = memCompany;
        this.diagnosis = diagnosis;
        this.remarks = remarks;
        this.diagnosisCode = diagnosisCode;
        this.type = type;
        this.updatedBy = updatedBy;
        this.callTypeId = callTypeId;
        this.id = id;
        this.runningBill = runningBill;
        this.memMi = memMi;
        this.memberCode = memberCode;
        this.memFname = memFname;
        this.memLname = memLname;
        this.doctorCode = doctorCode;
        this.actionTaken = actionTaken;
        this.status = status;
        this.updatedDate = updatedDate;
        this.terminalNo = terminalNo;
        this.approvalNo = approvalNo;
        this.procedureAmount = procedureAmount;
        this.callDate = callDate;
        this.companyCode = companyCode;
        this.category = category;
        this.callerId = callerId;
        this.approvalDate = approvalDate;
        this.hospitalCode = hospitalCode;
        this.procedureDesc = procedureDesc;
        this.notes = notes;
        this.dateAdmitted = dateAdmitted;
        this.room = room;
        this.doctorName = doctorName;
        this.doctorSpec = doctorSpec;
        this.doctorSpecCode = doctorSpecCode;
        this.hospitalName = hospitalName;
        this.schedule = schedule;
        this.withProvider = withProvider;
    }



    public String getPrimaryComplaint() {
        return primaryComplaint;
    }

    public void setPrimaryComplaint(String primaryComplaint) {
        this.primaryComplaint = primaryComplaint;
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


    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMemCompany() {
        return memCompany;
    }

    public void setMemCompany(String memCompany) {
        this.memCompany = memCompany;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCallTypeId() {
        return callTypeId;
    }

    public void setCallTypeId(String callTypeId) {
        this.callTypeId = callTypeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRunningBill() {
        return runningBill;
    }

    public void setRunningBill(String runningBill) {
        this.runningBill = runningBill;
    }

    public String getMemMi() {
        return memMi;
    }

    public void setMemMi(String memMi) {
        this.memMi = memMi;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemFname() {
        return memFname;
    }

    public void setMemFname(String memFname) {
        this.memFname = memFname;
    }

    public String getMemLname() {
        return memLname;
    }

    public void setMemLname(String memLname) {
        this.memLname = memLname;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getProcedureAmount() {
        return procedureAmount;
    }

    public void setProcedureAmount(String procedureAmount) {
        this.procedureAmount = procedureAmount;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getProcedureDesc() {
        return procedureDesc;
    }

    public void setProcedureDesc(String procedureDesc) {
        this.procedureDesc = procedureDesc;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(String dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpec() {
        return doctorSpec;
    }

    public void setDoctorSpec(String doctorSpec) {
        this.doctorSpec = doctorSpec;
    }

    public String getDoctorSpecCode() {
        return doctorSpecCode;
    }

    public void setDoctorSpecCode(String doctorSpecCode) {
        this.doctorSpecCode = doctorSpecCode;
    }

    public int getWithProvider() {
        return withProvider;
    }

    public void setWithProvider(int withProvider) {
        this.withProvider = withProvider;
    }

    public boolean getBooleanWithProvider() {
        return withProvider == 1 ? true : false;
    }

    protected LoaFetch(Parcel in) {
        primaryComplaint = in.readString();
        procedureCode = in.readString();
        batchCode = in.readString();
        reason = in.readString();
        memCompany = in.readString();
        diagnosis = in.readString();
        remarks = in.readString();
        diagnosisCode = in.readString();
        type = in.readString();
        updatedBy = in.readString();
        callTypeId = in.readString();
        id = in.readString();
        runningBill = in.readString();
        memMi = in.readString();
        memberCode = in.readString();
        memFname = in.readString();
        memLname = in.readString();
        doctorCode = in.readString();
        actionTaken = in.readString();
        status = in.readString();
        updatedDate = in.readString();
        terminalNo = in.readString();
        approvalNo = in.readString();
        procedureAmount = in.readString();
        callDate = in.readString();
        companyCode = in.readString();
        category = in.readString();
        callerId = in.readString();
        approvalDate = in.readString();
        hospitalCode = in.readString();
        procedureDesc = in.readString();
        notes = in.readString();
        dateAdmitted = in.readString();
        room = in.readString();
        doctorName = in.readString();
        doctorSpec = in.readString();
        doctorSpecCode = in.readString();
        hospitalName = in.readString();
        schedule = in.readString();
        withProvider = in.readInt();


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(primaryComplaint);
        dest.writeString(procedureCode);
        dest.writeString(batchCode);
        dest.writeString(reason);
        dest.writeString(memCompany);
        dest.writeString(diagnosis);
        dest.writeString(remarks);
        dest.writeString(diagnosisCode);
        dest.writeString(type);
        dest.writeString(updatedBy);
        dest.writeString(callTypeId);
        dest.writeString(id);
        dest.writeString(runningBill);
        dest.writeString(memMi);
        dest.writeString(memberCode);
        dest.writeString(memFname);
        dest.writeString(memLname);
        dest.writeString(doctorCode);
        dest.writeString(actionTaken);
        dest.writeString(status);
        dest.writeString(updatedDate);
        dest.writeString(terminalNo);
        dest.writeString(approvalNo);
        dest.writeString(procedureAmount);
        dest.writeString(callDate);
        dest.writeString(companyCode);
        dest.writeString(category);
        dest.writeString(callerId);
        dest.writeString(approvalDate);
        dest.writeString(hospitalCode);
        dest.writeString(procedureDesc);
        dest.writeString(notes);
        dest.writeString(dateAdmitted);
        dest.writeString(room);
        dest.writeString(doctorName);
        dest.writeString(doctorSpec);
        dest.writeString(doctorSpecCode);
        dest.writeString(hospitalName);
        dest.writeString(schedule);
        dest.writeInt(withProvider);


    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LoaFetch> CREATOR = new Parcelable.Creator<LoaFetch>() {
        @Override
        public LoaFetch createFromParcel(Parcel in) {
            return new LoaFetch(in);
        }

        @Override
        public LoaFetch[] newArray(int size) {
            return new LoaFetch[size];
        }
    };

    @Override
    public String toString() {
        return new StringBuilder("Doctor Details :\n")
                .append(doctorName).append("\n")
                .append(doctorSpec).append("\n")
                .append(hospitalName).toString();
    }
}