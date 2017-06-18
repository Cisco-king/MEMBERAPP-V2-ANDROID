package services.client;

import retrofit2.Call;
import retrofit2.http.GET;
import services.response.TestResponse;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public interface TestClient {

    @GET("listing/getAllTestsList")
    Call<TestResponse> getAllTest();

}
