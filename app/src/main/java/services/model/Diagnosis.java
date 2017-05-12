package services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public class Diagnosis {

    @SerializedName("diagCode")
    @Expose
    private String diagCode;
    @SerializedName("diagDesc")
    @Expose
    private String diagDesc;
    @SerializedName("diagRemarks")
    @Expose
    private String diagRemarks;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("typeDesc")
    @Expose
    private String typeDesc;
    @SerializedName("icd10Code")
    @Expose
    private String icd10Code;
    @SerializedName("icd10Desc")
    @Expose
    private String icd10Desc;
    @SerializedName("status")
    @Expose
    private String status;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
