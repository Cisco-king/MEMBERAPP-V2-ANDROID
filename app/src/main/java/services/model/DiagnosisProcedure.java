package services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 6/9/2017.
 */

public class DiagnosisProcedure {

    @SerializedName("maceRequestOpDiag")
    @Expose
    private MaceRequestOpDiag maceRequestOpDiag;
    @SerializedName("maceRequestTest")
    @Expose
    private MaceRequestTest maceRequestTest;
    @SerializedName("maceRequestOpTest")
    @Expose
    private MaceRequestOpTest maceRequestOpTest;
    @SerializedName("approvalNo")
    @Expose
    private String approvalNo;
    @SerializedName("procedureCode")
    @Expose
    private String procedureCode;
    @SerializedName("diagnosisCode")
    @Expose
    private String diagnosisCode;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("remarks")
    @Expose
    private Object remarks;

    public MaceRequestOpDiag getMaceRequestOpDiag() {
        return maceRequestOpDiag;
    }

    public void setMaceRequestOpDiag(MaceRequestOpDiag maceRequestOpDiag) {
        this.maceRequestOpDiag = maceRequestOpDiag;
    }

    public MaceRequestTest getMaceRequestTest() {
        return maceRequestTest;
    }

    public void setMaceRequestTest(MaceRequestTest maceRequestTest) {
        this.maceRequestTest = maceRequestTest;
    }

    public MaceRequestOpTest getMaceRequestOpTest() {
        return maceRequestOpTest;
    }

    public void setMaceRequestOpTest(MaceRequestOpTest maceRequestOpTest) {
        this.maceRequestOpTest = maceRequestOpTest;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

}
