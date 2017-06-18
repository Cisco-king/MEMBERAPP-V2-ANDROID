package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import services.model.MaceRequestData;

/**
 * Created by casjohnpaul on 6/9/2017.
 */

public class MaceRequestResponse {

    @SerializedName("responseDesc")
    @Expose
    private String responseDesc;
    @SerializedName("data")
    @Expose
    private MaceRequestData data;
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;

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
