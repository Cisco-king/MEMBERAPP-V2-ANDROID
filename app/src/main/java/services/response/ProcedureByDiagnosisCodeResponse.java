package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by casjohnpaul on 5/13/2017.
 */

public class ProcedureByDiagnosisCodeResponse {

    @SerializedName("responseDesc")
    @Expose
    private String responseDesc;
    @SerializedName("procedures")
    @Expose
    private List<String> procedures = null;
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public List<String> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<String> procedures) {
        this.procedures = procedures;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

}
