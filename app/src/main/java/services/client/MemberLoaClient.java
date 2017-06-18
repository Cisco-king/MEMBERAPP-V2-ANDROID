package services.client;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;
import services.response.MaceRequestResponse;

/**
 * Created by casjohnpaul on 6/3/2017.
 */

public interface MemberLoaClient {


    @GET("memberloa/requestLOAForSelectedTests/")
    Call<MaceRequestResponse> submitLoaForSelectedTest(@Body String sample);

    @GET("memberloa/requestLOAForSelectedTests/")
    Observable<MaceRequestResponse> submitLoaForSelectedTestRx(@Body String sample);

    @POST("memberloa/addAttachmentByApprovalNo/")
    Call<Void> uploadLoaAttachment(@Part("file\"; filename=\"image.png\"")RequestBody file, @Query("approvalNo") String approvalNo);

    @POST("memberloa/addAttachmentByApprovalNo/")
    Observable<Void> uploadLoaAttachmentRx(@Part("file\"; filename=\"image.png\"")RequestBody file, @Query("approvalNo") String approvalNo);

}
