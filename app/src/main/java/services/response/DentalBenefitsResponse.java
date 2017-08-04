package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by macbookpro on 7/31/17.
 */

public class DentalBenefitsResponse {

    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;

    @SerializedName("responseDesc")
    @Expose
    private String responseDesc;

    @SerializedName("Val")
    @Expose
    private String Val;

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

    public String getVal() {
        return Val;
    }

    public void setVal(String val) {
        Val = val;
    }
}
