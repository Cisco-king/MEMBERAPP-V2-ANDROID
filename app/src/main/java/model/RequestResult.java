package model;

/**
 * Created by mpx-pawpaw on 1/5/17.
 */

public class RequestResult {


    private String responseCode;

    private String responseDesc;

    private String approvalNo;

    private String remarks;

    private String withProvider;

    private String batchCode ;

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getWithProvider() {
        return withProvider;
    }

    public void setWithProvider(String withProvider) {
        this.withProvider = withProvider;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "ClassPojo [responseCode = " + responseCode + ", responseDesc = " + responseDesc + ", approvalNo = " + approvalNo + ", remarks = " + remarks + "]";
    }
}
