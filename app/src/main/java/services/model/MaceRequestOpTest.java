package services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 6/9/2017.
 */

public class MaceRequestOpTest {

    @SerializedName("diagTestId")
    @Expose
    private Integer diagTestId;
    @SerializedName("reqDiagId")
    @Expose
    private Integer reqDiagId;
    @SerializedName("maceRequestId")
    @Expose
    private Integer maceRequestId;
    @SerializedName("prescribedTestId")
    @Expose
    private Integer prescribedTestId;
    @SerializedName("transactionId")
    @Expose
    private Integer transactionId;
    @SerializedName("refDiagProcId")
    @Expose
    private Integer refDiagProcId;
    @SerializedName("diagCode")
    @Expose
    private String diagCode;
    @SerializedName("procCode")
    @Expose
    private String procCode;
    @SerializedName("procDesc")
    @Expose
    private String procDesc;
    @SerializedName("procType")
    @Expose
    private String procType;
    @SerializedName("procTypeDesc")
    @Expose
    private String procTypeDesc;
    @SerializedName("procClass")
    @Expose
    private String procClass;
    @SerializedName("procClassDesc")
    @Expose
    private String procClassDesc;
    @SerializedName("procHospAmount")
    @Expose
    private Integer procHospAmount;
    @SerializedName("procDefAmount")
    @Expose
    private Integer procDefAmount;
    @SerializedName("procActualAmount")
    @Expose
    private Integer procActualAmount;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("ruv")
    @Expose
    private String ruv;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("maceSubtype")
    @Expose
    private Integer maceSubtype;

    public Integer getDiagTestId() {
        return diagTestId;
    }

    public void setDiagTestId(Integer diagTestId) {
        this.diagTestId = diagTestId;
    }

    public Integer getReqDiagId() {
        return reqDiagId;
    }

    public void setReqDiagId(Integer reqDiagId) {
        this.reqDiagId = reqDiagId;
    }

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public Integer getPrescribedTestId() {
        return prescribedTestId;
    }

    public void setPrescribedTestId(Integer prescribedTestId) {
        this.prescribedTestId = prescribedTestId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getRefDiagProcId() {
        return refDiagProcId;
    }

    public void setRefDiagProcId(Integer refDiagProcId) {
        this.refDiagProcId = refDiagProcId;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getProcType() {
        return procType;
    }

    public void setProcType(String procType) {
        this.procType = procType;
    }

    public String getProcTypeDesc() {
        return procTypeDesc;
    }

    public void setProcTypeDesc(String procTypeDesc) {
        this.procTypeDesc = procTypeDesc;
    }

    public String getProcClass() {
        return procClass;
    }

    public void setProcClass(String procClass) {
        this.procClass = procClass;
    }

    public String getProcClassDesc() {
        return procClassDesc;
    }

    public void setProcClassDesc(String procClassDesc) {
        this.procClassDesc = procClassDesc;
    }

    public Integer getProcHospAmount() {
        return procHospAmount;
    }

    public void setProcHospAmount(Integer procHospAmount) {
        this.procHospAmount = procHospAmount;
    }

    public Integer getProcDefAmount() {
        return procDefAmount;
    }

    public void setProcDefAmount(Integer procDefAmount) {
        this.procDefAmount = procDefAmount;
    }

    public Integer getProcActualAmount() {
        return procActualAmount;
    }

    public void setProcActualAmount(Integer procActualAmount) {
        this.procActualAmount = procActualAmount;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRuv() {
        return ruv;
    }

    public void setRuv(String ruv) {
        this.ruv = ruv;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMaceSubtype() {
        return maceSubtype;
    }

    public void setMaceSubtype(Integer maceSubtype) {
        this.maceSubtype = maceSubtype;
    }

}
