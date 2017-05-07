package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import services.model.DoctorList;

/**
 * Created by casjohnpaul on 5/5/2017.
 */

public class DoctorListResponse {

    @SerializedName("doctorList")
    @Expose
    private List<DoctorList> doctorList = null;

    public List<DoctorList> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<DoctorList> doctorList) {
        this.doctorList = doctorList;
    }

}
