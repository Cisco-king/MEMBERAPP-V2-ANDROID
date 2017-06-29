package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import services.model.mace.Details;
import services.model.mace.MaceRequest;

/**
 * Created by casjohnpaul on 6/28/2017.
 */

public class MaceRequestResponse2 {

    @SerializedName("maceRequest")
    @Expose
    private MaceRequest maceRequest;
    @SerializedName("diagnosisTests")
    @Expose
    private List<Object> diagnosisTests = null;
    @SerializedName("maceRequestCode")
    @Expose
    private String maceRequestCode;
    @SerializedName("responseDesc")
    @Expose
    private String responseDesc;
    @SerializedName("details")
    @Expose
    private Details details;
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;

    public MaceRequest getMaceRequest() {
        return maceRequest;
    }

    public void setMaceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
    }

    public List<Object> getDiagnosisTests() {
        return diagnosisTests;
    }

    public void setDiagnosisTests(List<Object> diagnosisTests) {
        this.diagnosisTests = diagnosisTests;
    }

    public String getMaceRequestCode() {
        return maceRequestCode;
    }

    public void setMaceRequestCode(String maceRequestCode) {
        this.maceRequestCode = maceRequestCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

}
