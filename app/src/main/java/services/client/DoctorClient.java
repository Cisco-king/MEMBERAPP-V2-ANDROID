package services.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import services.model.HospitalsByDoctorCode;
import services.response.DoctorListResponse;
import services.response.HospitalsByDoctorCodeResponse;
import services.response.HospitalsToDoctorResponse;

/**
 * Created by casjohnpaul on 5/5/2017.
 */

public interface DoctorClient {

    @GET("listing/getDoctors/")
    Call<DoctorListResponse> getAllDoctors();

    @GET("listing/getDoctors/")
    Observable<DoctorListResponse> getAllDoctorsRx();

    @GET("listing/getAllDoctorsToHospital/")
    Call<HospitalsToDoctorResponse> getAllDoctorsToHospital();

    @GET("listing/getAllDoctorsToHospital/")
    Observable<HospitalsToDoctorResponse> getAllDoctorsToHospitalRx();

    @GET("listing/getDoctorHospitalByCode/")
    Call<HospitalsByDoctorCodeResponse> getHospitalsByDoctorCode(@Query("doctorCode") String doctorCode);

    @GET("listing/getDoctorHospitalByCode/")
    Observable<HospitalsByDoctorCodeResponse> getHospitalsByDoctorCodeRx(@Query("doctorCode") String doctorCode);

}
