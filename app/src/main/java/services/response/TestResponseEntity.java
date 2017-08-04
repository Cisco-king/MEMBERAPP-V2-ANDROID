package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by macbookpro on 7/28/17.
 */

public class TestResponseEntity {

    @SerializedName("responseDesc")
    @Expose
    private String responseDesc;
    @SerializedName("testCodes")
    @Expose
    private List<String> testCodes = null;
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public List<String> getTestCodes() {
        return testCodes;
    }

    public void setTestCodes(List<String> testCodes) {
        this.testCodes = testCodes;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
}
