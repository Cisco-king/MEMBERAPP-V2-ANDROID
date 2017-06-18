package services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 6/9/2017.
 */

public class MaceRequestOpDiag {

    @SerializedName("reqDiagId")
    @Expose
    private Integer reqDiagId;
    @SerializedName("transactionId")
    @Expose
    private Integer transactionId;
    @SerializedName("maceRequestId")
    @Expose
    private Integer maceRequestId;
    @SerializedName("maceDiagType")
    @Expose
    private Integer maceDiagType;
    @SerializedName("diagCode")
    @Expose
    private String diagCode;
    @SerializedName("diagDesc")
    @Expose
    private String diagDesc;
    @SerializedName("diagType")
    @Expose
    private String diagType;
    @SerializedName("diagTypeOld")
    @Expose
    private Object diagTypeOld;
    @SerializedName("diagClass")
    @Expose
    private String diagClass;
    @SerializedName("diagRemarks")
    @Expose
    private Object diagRemarks;
    @SerializedName("icd10Code")
    @Expose
    private String icd10Code;
    @SerializedName("icd10Class")
    @Expose
    private String icd10Class;
    @SerializedName("icd104C")
    @Expose
    private String icd104C;
    @SerializedName("typeDesc")
    @Expose
    private String typeDesc;
    @SerializedName("diseaseType")
    @Expose
    private String diseaseType;
    @SerializedName("groupDesc")
    @Expose
    private String groupDesc;

    public Integer getReqDiagId() {
        return reqDiagId;
    }

    public void setReqDiagId(Integer reqDiagId) {
        this.reqDiagId = reqDiagId;
    }

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

    public Integer getMaceDiagType() {
        return maceDiagType;
    }

    public void setMaceDiagType(Integer maceDiagType) {
        this.maceDiagType = maceDiagType;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    public String getDiagType() {
        return diagType;
    }

    public void setDiagType(String diagType) {
        this.diagType = diagType;
    }

    public Object getDiagTypeOld() {
        return diagTypeOld;
    }

    public void setDiagTypeOld(Object diagTypeOld) {
        this.diagTypeOld = diagTypeOld;
    }

    public String getDiagClass() {
        return diagClass;
    }

    public void setDiagClass(String diagClass) {
        this.diagClass = diagClass;
    }

    public Object getDiagRemarks() {
        return diagRemarks;
    }

    public void setDiagRemarks(Object diagRemarks) {
        this.diagRemarks = diagRemarks;
    }

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getIcd10Class() {
        return icd10Class;
    }

    public void setIcd10Class(String icd10Class) {
        this.icd10Class = icd10Class;
    }

    public String getIcd104C() {
        return icd104C;
    }

    public void setIcd104C(String icd104C) {
        this.icd104C = icd104C;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

}
