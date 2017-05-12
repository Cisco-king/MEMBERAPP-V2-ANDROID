package services.client;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;
import services.response.ProcedureResponse;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public interface ProcedureClient {

    @GET("listing/getProceduresList/")
    Call<ProcedureResponse> getAllProcedures();

    @GET("listing/getProceduresList/")
    Observable<ProcedureResponse> getAllProceduresRx();

}
