package services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import utilities.DateConverter;

/**
 * Created by casjohnpaul on 6/9/2017.
 */

public class MaceRequest implements Serializable {


    @SerializedName("requestType")
    @Expose
    private String requestType;


    @SerializedName("requestTypeDetail01")
    @Expose
    private String requestTypeDetail01;


    @SerializedName("requestTypeDetail02")
    @Expose
    private String requestTypeDetail02;


    @SerializedName("requestTypeDetail03")
    @Expose
    private String requestTypeDetail03;


    @SerializedName("doctorName")
    @Expose
    private String doctorName;


    @SerializedName("doctorSpec")
    @Expose
    private String doctorSpec;


    @SerializedName("primaryDiag")
    @Expose
    private String primaryDiag;


    @SerializedName("reasonForConsult")
    @Expose
    private String reasonForConsult;


    @SerializedName("approvalNo")
    @Expose
    private String approvalNo;


    @SerializedName("requestId")
    @Expose
    private Integer requestId;


    @SerializedName("statusAssignee")
    @Expose
    private Object statusAssignee;


    @SerializedName("statusRemarks")
    @Expose
    private Object statusRemarks;


    @SerializedName("serviceTypeId")
    @Expose
    private Integer serviceTypeId;


    @SerializedName("memCode")
    @Expose
    private String memCode;

    @Expose
    @SerializedName("hospitalName")
    private String hospitalName;

    @Expose
    @SerializedName("hospitalAddress")
    private String hospitalAddress;

    @Expose
    @SerializedName("hospitalContact")
    private String hospitalContact;


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


    @SerializedName("memAcct")
    @Expose
    private String memAcct;

    @SerializedName("memStat")
    @Expose
    private String memStat;

    @SerializedName("memBdate")
    @Expose
    private String memBdate;

    @SerializedName("memGender")
    @Expose
    private String memGender;

    @SerializedName("memAge")
    @Expose
    private String memAge;

    @SerializedName("memType")
    @Expose
    private String memType;

    @SerializedName("idremarks")
    @Expose
    private String idremarks;

    @SerializedName("acctValidity")
    @Expose
    private String acctValidity;

    @SerializedName("acctEffectivity")
    @Expose
    private String acctEffectivity;

    @SerializedName("requestOrigin")
    @Expose
    private String requestOrigin;

    @SerializedName("requestFromhosp")
    @Expose
    private Object requestFromhosp;

    @SerializedName("requestFrommem")
    @Expose
    private Object requestFrommem;

    @SerializedName("requestBy")
    @Expose
    private Object requestBy;

    @SerializedName("requestDevice")
    @Expose
    private String requestDevice;

    @SerializedName("requestDatetime")
    @Expose
    private String requestDatetime;

    @SerializedName("disclaimerTicked")
    @Expose
    private Object disclaimerTicked;

    @SerializedName("lastupdateOn")
    @Expose
    private String lastupdateOn;

    @SerializedName("lastupdateBy")
    @Expose
    private Object lastupdateBy;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("override")
    @Expose
    private Boolean override;

    @SerializedName("parRequestId")
    @Expose
    private Integer parRequestId;

    @SerializedName("mbasCode")
    @Expose
    private Object mbasCode;

    @SerializedName("mbasApprover")
    @Expose
    private Object mbasApprover;

    @SerializedName("mbasupdateOn")
    @Expose
    private Object mbasupdateOn;

    @SerializedName("requestCode")
    @Expose
    private String requestCode;


    @SerializedName("serviceType")
    @Expose
    private String serviceType;


    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestTypeDetail01() {
        return requestTypeDetail01;
    }

    public void setRequestTypeDetail01(String requestTypeDetail01) {
        this.requestTypeDetail01 = requestTypeDetail01;
    }

    public String getRequestTypeDetail02() {
        return requestTypeDetail02;
    }

    public void setRequestTypeDetail02(String requestTypeDetail02) {
        this.requestTypeDetail02 = requestTypeDetail02;
    }

    public String getRequestTypeDetail03() {
        return requestTypeDetail03;
    }

    public void setRequestTypeDetail03(String requestTypeDetail03) {
        this.requestTypeDetail03 = requestTypeDetail03;
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

    public String getPrimaryDiag() {
        return primaryDiag;
    }

    public void setPrimaryDiag(String primaryDiag) {
        this.primaryDiag = primaryDiag;
    }

    public String getReasonForConsult() {
        return reasonForConsult;
    }

    public void setReasonForConsult(String reasonForConsult) {
        this.reasonForConsult = reasonForConsult;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Object getStatusAssignee() {
        return statusAssignee;
    }

    public void setStatusAssignee(Object statusAssignee) {
        this.statusAssignee = statusAssignee;
    }

    public Object getStatusRemarks() {
        return statusRemarks;
    }

    public void setStatusRemarks(Object statusRemarks) {
        this.statusRemarks = statusRemarks;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public String getMemLname() {
        return memLname;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
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

    public String getMemAcct() {
        return memAcct;
    }

    public void setMemAcct(String memAcct) {
        this.memAcct = memAcct;
    }

    public String getMemStat() {
        return memStat;
    }

    public void setMemStat(String memStat) {
        this.memStat = memStat;
    }


    public String getMemBdate() {
        return memBdate;
    }

    public void setMemBdate(String memBdate) {
        this.memBdate = memBdate;
    }

    public String getMemGender() {
        return memGender;
    }

    public void setMemGender(String memGender) {
        this.memGender = memGender;
    }

    public String getMemAge() {
        return memAge;
    }

    public void setMemAge(String memAge) {
        this.memAge = memAge;
    }

    public String getMemType() {
        return memType;
    }

    public String getFullName() {
        return memLname + " " + memFname;
    }

    public void setMemType(String memType) {
        this.memType = memType;
    }

    public String getIdremarks() {
        return idremarks;
    }

    public void setIdremarks(String idremarks) {
        this.idremarks = idremarks;
    }


    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public Object getRequestFromhosp() {
        return requestFromhosp;
    }

    public void setRequestFromhosp(Object requestFromhosp) {
        this.requestFromhosp = requestFromhosp;
    }

    public Object getRequestFrommem() {
        return requestFrommem;
    }

    public void setRequestFrommem(Object requestFrommem) {
        this.requestFrommem = requestFrommem;
    }

    public Object getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(Object requestBy) {
        this.requestBy = requestBy;
    }

    public String getRequestDevice() {
        return requestDevice;
    }

    public void setRequestDevice(String requestDevice) {
        this.requestDevice = requestDevice;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalContact() {
        return hospitalContact;
    }

    public void setHospitalContact(String hospitalContact) {
        this.hospitalContact = hospitalContact;
    }

    public Object getDisclaimerTicked() {
        return disclaimerTicked;
    }

    public void setDisclaimerTicked(Object disclaimerTicked) {
        this.disclaimerTicked = disclaimerTicked;
    }

    public String getAcctValidity() {
        return acctValidity;
    }

    public void setAcctValidity(String acctValidity) {
        this.acctValidity = acctValidity;
    }

    public String getAcctEffectivity() {
        return acctEffectivity;
    }

    public void setAcctEffectivity(String acctEffectivity) {
        this.acctEffectivity = acctEffectivity;
    }

    public String getRequestDatetime() {
        return requestDatetime;
    }

    public void setRequestDatetime(String requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public String getLastupdateOn() {
        return lastupdateOn;
    }

    public void setLastupdateOn(String lastupdateOn) {
        this.lastupdateOn = lastupdateOn;
    }

    public Object getLastupdateBy() {
        return lastupdateBy;
    }

    public void setLastupdateBy(Object lastupdateBy) {
        this.lastupdateBy = lastupdateBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getOverride() {
        return override;
    }

    public void setOverride(Boolean override) {
        this.override = override;
    }

    public Integer getParRequestId() {
        return parRequestId;
    }

    public void setParRequestId(Integer parRequestId) {
        this.parRequestId = parRequestId;
    }

    public Object getMbasCode() {
        return mbasCode;
    }

    public void setMbasCode(Object mbasCode) {
        this.mbasCode = mbasCode;
    }

    public Object getMbasApprover() {
        return mbasApprover;
    }

    public void setMbasApprover(Object mbasApprover) {
        this.mbasApprover = mbasApprover;
    }

    public Object getMbasupdateOn() {
        return mbasupdateOn;
    }

    public void setMbasupdateOn(Object mbasupdateOn) {
        this.mbasupdateOn = mbasupdateOn;
    }

}
