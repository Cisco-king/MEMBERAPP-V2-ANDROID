package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import services.model.MaceRequestData;

/**
 * Created by casjohnpaul on 6/9/2017.
 */

public class MaceRequestResponse {


    private String responseDesc;

    private MaceRequestData data;

    private Integer responseCode;

    private String batchCode;

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public MaceRequestData getData() {
        return data;
    }

    public void setData(MaceRequestData data) {
        this.data = data;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

}
