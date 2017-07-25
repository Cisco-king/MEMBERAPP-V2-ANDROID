package services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 6/9/2017.
 */

public class MaceRequest {

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
    private Integer memAge;
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

    public Integer getMemAge() {
        return memAge;
    }

    public void setMemAge(Integer memAge) {
        this.memAge = memAge;
    }

    public String getMemType() {
        return memType;
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
