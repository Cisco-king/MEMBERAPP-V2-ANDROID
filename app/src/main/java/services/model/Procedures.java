package services.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import database.table.Table;

/**
 * Created by casjohnpaul on 6/14/2017.
 */
public class Procedures implements Parcelable, Table.Procedure2 {

    @SerializedName("diagCode")
    @Expose
    private String diagnosisCode;
    @SerializedName("diagDesc")
    @Expose
    private String diagnosisDescription;
    @SerializedName("icd10Code")
    @Expose
    private String icd10Code;
    @SerializedName("procID")
    @Expose
    private String procedureId;
    @SerializedName("procCode")
    @Expose
    private String procedureCode;
    @SerializedName("procedureName")
    @Expose
    private String procedureName;
    @SerializedName("approvalId")
    @Expose
    private Integer approvalId;
    @SerializedName("approvalType")
    @Expose
    private String approvalType;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("diagType")
    @Expose
    private String diagType;
    @SerializedName("diagTypeDesc")
    @Expose
    private String diagTypeDesc;
    @SerializedName("proClassCode")
    @Expose
    private String proClassCode;

    protected Procedures(Parcel in) {
        diagnosisCode = in.readString();
        diagnosisDescription = in.readString();
        icd10Code = in.readString();
        procedureId = in.readString();
        procedureCode = in.readString();
        procedureName = in.readString();
        approvalType = in.readString();
        diagType = in.readString();
        diagTypeDesc = in.readString();
        proClassCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(diagnosisCode);
        dest.writeString(diagnosisDescription);
        dest.writeString(icd10Code);
        dest.writeString(procedureId);
        dest.writeString(procedureCode);
        dest.writeString(procedureName);
        dest.writeString(approvalType);
        dest.writeString(diagType);
        dest.writeString(diagTypeDesc);
        dest.writeString(proClassCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Procedures> CREATOR = new Creator<Procedures>() {
        @Override
        public Procedures createFromParcel(Parcel in) {
            return new Procedures(in);
        }

        @Override
        public Procedures[] newArray(int size) {
            return new Procedures[size];
        }
    };

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getDiagnosisDescription() {
        return diagnosisDescription;
    }

    public void setDiagnosisDescription(String diagnosisDescription) {
        this.diagnosisDescription = diagnosisDescription;
    }

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDiagType() {
        return diagType;
    }

    public void setDiagType(String diagType) {
        this.diagType = diagType;
    }

    public String getDiagTypeDesc() {
        return diagTypeDesc;
    }

    public void setDiagTypeDesc(String diagTypeDesc) {
        this.diagTypeDesc = diagTypeDesc;
    }

    public String getProClassCode() {
        return proClassCode;
    }

    public void setProClassCode(String proClassCode) {
        this.proClassCode = proClassCode;
    }

}
