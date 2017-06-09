package services.client;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;
import services.response.DiagnosisResponse;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public interface DiagnosisClient {

    @GET("listing/getFilteredDiagnosisList/")
    Call<DiagnosisResponse> getFilteredDiagnosisList();

    @GET("listing/getFilteredDiagnosisList/")
    Observable<DiagnosisResponse> getFilteredDiagnosisListRx();

    @GET("listing/getDiagnosisList/")
    Call<DiagnosisResponse> getDiagnosisList();

    @GET("listing/getDiagnosisList/")
    Observable<DiagnosisResponse> getDiagnosisListRx();



}
