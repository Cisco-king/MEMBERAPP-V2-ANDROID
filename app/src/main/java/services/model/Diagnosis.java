package services.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public class Diagnosis implements  Serializable {

    @SerializedName("diagCode")
    @Expose
    private String diagCode;
    @SerializedName("diagDesc")
    @Expose
    private String diagDesc;
    @SerializedName("diagRemarks")
    @Expose
    private String diagRemarks;
    @SerializedName("groupDesc")
    @Expose
    private String groupDesc;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("typeOld")
    @Expose
    private String typeOld;
    @SerializedName("typeDesc")
    @Expose
    private String typeDesc;
    @SerializedName("icd10Code")
    @Expose
    private String icd10Code;
    @SerializedName("icd10Desc")
    @Expose
    private String icd10Desc;
    @SerializedName("icd104c")
    @Expose
    private String icd104c;
    @SerializedName("status")
    @Expose
    private String status;

    public Diagnosis() {
    }

    public Diagnosis(String diagCode, String diagDesc) {
        this.diagCode = diagCode;
        this.diagDesc = diagDesc;
    }



    public void init(Diagnosis diagnosis) {
        this.diagCode = diagnosis.getDiagCode();
        this.diagDesc = diagnosis.getDiagDesc();
        this.diagRemarks = diagnosis.getDiagRemarks();
        this.groupDesc = diagnosis.getGroupDesc();
        this.type = diagnosis.getType();
        this.typeOld = diagnosis.getTypeOld();
        this.typeDesc = diagnosis.getTypeDesc();
        this.icd10Code = diagnosis.getIcd10Code();
        this.icd10Desc = diagnosis.getIcd10Desc();
        this.icd104c = diagnosis.getIcd104c();
        this.status = diagnosis.getStatus();
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

    public String getDiagRemarks() {
        return diagRemarks;
    }

    public void setDiagRemarks(String diagRemarks) {
        this.diagRemarks = diagRemarks;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeOld() {
        return typeOld;
    }

    public void setTypeOld(String typeOld) {
        this.typeOld = typeOld;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getIcd10Desc() {
        return icd10Desc;
    }

    public void setIcd10Desc(String icd10Desc) {
        this.icd10Desc = icd10Desc;
    }

    public String getIcd104c() {
        return icd104c;
    }

    public void setIcd104c(String icd104c) {
        this.icd104c = icd104c;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
