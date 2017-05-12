package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import services.model.Diagnosis;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public class DiagnosisResponse {

    @SerializedName("responseDesc")
    @Expose
    private String responseDesc;
    @SerializedName("diagnosisList")
    @Expose
    private List<Diagnosis> diagnosisList = null;
    @SerializedName("responseCode")
    @Expose
    private String responseCode;

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public List<Diagnosis> getDiagnosisList() {
        return diagnosisList;
    }

    public void setDiagnosisList(List<Diagnosis> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

}
