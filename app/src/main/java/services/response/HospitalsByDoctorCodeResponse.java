package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import services.model.HospitalsByDoctorCode;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public class HospitalsByDoctorCodeResponse {

    @SerializedName("responseDesc")
    @Expose
    private String responseDescription;
    @SerializedName("hospitalsByDoctorCode")
    @Expose
    private List<HospitalsByDoctorCode> hospitalsByDoctorCode = null;
    @SerializedName("responseCode")
    @Expose
    private String responseCode;

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public List<HospitalsByDoctorCode> getHospitalsByDoctorCode() {
        return hospitalsByDoctorCode;
    }

    public void setHospitalsByDoctorCode(List<HospitalsByDoctorCode> hospitalsByDoctorCode) {
        this.hospitalsByDoctorCode = hospitalsByDoctorCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

}
