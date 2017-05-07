package services.client;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;
import services.response.DoctorListResponse;

/**
 * Created by casjohnpaul on 5/5/2017.
 */

public interface DoctorClient {

    @GET("listing/getDoctors/")
    Call<DoctorListResponse> getAllDoctors();

    @GET("listing/getDoctors/")
    Observable<DoctorListResponse> getAllDoctorsRx();

}
