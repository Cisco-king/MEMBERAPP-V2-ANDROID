package services.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import services.response.ProcedureByDiagnosisCodeResponse;
import services.response.ProcedureResponse;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public interface ProcedureClient {

    @GET("listing/getProceduresList/")
    Call<ProcedureResponse> getAllProcedures();

    @GET("listing/getProceduresList/")
    Observable<ProcedureResponse> getAllProceduresRx();

    @GET("/listing/getTestsByDiagnosisCode/")
    Call<String> getTestsByDiagnosisCode(@Query("diagCode") String diagCode);

    @GET("listing/getProceduresByDiagnosisCode/?")
    Call<ProcedureByDiagnosisCodeResponse> getProceduresByDiagnosisCode(@Query("diagCode") String diagnosisCode);

    @GET("listing/getProceduresByDiagnosisCode/?")
    Observable<ProcedureByDiagnosisCodeResponse> getProceduresByDiagnosisCodeRx(@Query("diagCode") String diagnosisCode);

}
