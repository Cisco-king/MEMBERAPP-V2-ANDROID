package services.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import services.response.ProcedureByDiagnosisCodeResponse;
import services.response.ProcedureResponse;
import services.response.TestResponseEntity;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public interface ProcedureClient {

    @GET("listing/getProceduresList/")
    Call<ProcedureResponse> getAllProcedures();

    @GET("listing/getProceduresList/")
    Observable<ProcedureResponse> getAllProceduresRx();

    @GET("/v2/listing/getTestsByDiagnosisCode/")
    Call<TestResponseEntity> getTestsByDiagnosisCode(@Query("diagCode") String diagCode);

    @GET("listing/getProceduresByDiagnosisCode/?")
    Call<ProcedureByDiagnosisCodeResponse> getProceduresByDiagnosisCode(@Query("diagCode") String diagnosisCode);

    @GET("listing/getProceduresByDiagnosisCode/?")
    Observable<ProcedureByDiagnosisCodeResponse> getProceduresByDiagnosisCodeRx(@Query("diagCode") String diagnosisCode);

}
