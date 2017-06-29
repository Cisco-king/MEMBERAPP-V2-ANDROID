package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 6/28/2017.
 */

public class FileResponse {

    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("responseDesc")
    @Expose
    private String responseDesc;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

}
