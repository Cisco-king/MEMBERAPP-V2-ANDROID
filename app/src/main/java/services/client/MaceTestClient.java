package services.client;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import services.model.DiagnosisTestRequest;
import services.response.FileResponse;
import services.response.MaceRequestResponse2;

/**
 * Created by casjohnpaul on 6/21/2017.
 */

public interface MaceTestClient {

    @POST("/mace/request/createTestRequest/")
    Call<MaceRequestResponse2> createTestRequest(@Body DiagnosisTestRequest maceRequest);

    @Multipart
    @POST("/memberloa/addAttachmentByApprovalNo/")
    Call<FileResponse> uploadImageFileByApprovalNumber(@Part MultipartBody.Part image, @Part("approvalNo")RequestBody approvalNo);

}
