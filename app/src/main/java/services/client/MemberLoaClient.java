package services.client;

import model.newtest.NewTestRequest;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

    @POST("/coordinator/v2/requestBasicOrOtherTest")
    Call<MaceRequestResponse> requestBasicOrOtherTest(@Body NewTestRequest requestTest);

    @GET("memberloa/requestLOAForSelectedTests/")
    Observable<MaceRequestResponse> submitLoaForSelectedTestRx(@Body String sample);

    @POST("memberloa/addAttachmentByApprovalNo/")
    Call<ResponseBody> uploadLoaAttachment(@Part("file\"; filename=\"image.png\"")RequestBody file, @Query("requestCode") String requestCode);

    @POST("memberloa/addAttachmentByApprovalNo/")
    Observable<Void> uploadLoaAttachmentRx(@Part("file\"; filename=\"image.png\"")RequestBody file, @Query("approvalNo") String approvalNo);

}
