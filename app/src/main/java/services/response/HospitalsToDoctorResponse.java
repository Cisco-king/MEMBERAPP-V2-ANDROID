package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import services.model.HospitalsToDoctor;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public class HospitalsToDoctorResponse {

    @SerializedName("getDoctorsToHospital")
    @Expose
    private List<HospitalsToDoctor> doctorsToHospital;

    public List<HospitalsToDoctor> getDoctorsToHospital() {
        return doctorsToHospital;
    }

    public void setDoctorsToHospital(List<HospitalsToDoctor> doctorsToHospital) {
        this.doctorsToHospital = doctorsToHospital;
    }

}
