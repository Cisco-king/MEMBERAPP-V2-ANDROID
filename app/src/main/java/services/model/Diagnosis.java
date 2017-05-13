package services.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public class Diagnosis implements Parcelable {

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

    protected Diagnosis(Parcel in) {
        diagCode = in.readString();
        diagDesc = in.readString();
        diagRemarks = in.readString();
        type = in.readString();
        typeDesc = in.readString();
        icd10Code = in.readString();
        icd10Desc = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(diagCode);
        dest.writeString(diagDesc);
        dest.writeString(diagRemarks);
        dest.writeString(type);
        dest.writeString(typeDesc);
        dest.writeString(icd10Code);
        dest.writeString(icd10Desc);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Diagnosis> CREATOR = new Creator<Diagnosis>() {
        @Override
        public Diagnosis createFromParcel(Parcel in) {
            return new Diagnosis(in);
        }

        @Override
        public Diagnosis[] newArray(int size) {
            return new Diagnosis[size];
        }
    };

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
