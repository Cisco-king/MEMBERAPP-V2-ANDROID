package services.client;

import retrofit2.Call;
import retrofit2.http.Query;

/**
 * Created by casjohnpaul on 6/8/2017.
 */

public interface BasicTestClient {

    Call<Void> getFilteredLoaByMemberCode(@Query("memberCode") String memberCode, @Query("filterType") String filterType);

}
