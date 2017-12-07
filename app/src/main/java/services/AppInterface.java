package services;

import model.AddDepenceResponse;
import model.AddDependence;
import model.ChangePassword;
import model.City;
import model.Confirm;
import model.DentistModel;
import model.Disclaimer;
import model.DoctorNORoom;
import model.Doctors;
import model.Exclusions;
import model.FAQsModel;
import model.GetUSER;
import model.Hospital;
import model.LogIn;
import model.Pin;
import model.Pinned;
import model.Province;
import model.RequestAccount;
import model.RequestNewPassword;
import model.RequestResult;
import model.ReturnChangePassword;
import model.ReturnRequestPassword;
import model.SendLoa;
import model.SignInDetails;
import model.SpecializationList;
import model.TheDoctor;
import model.UpdatePin;
import model.VerifyMemberData;
import model.DentistList;
import model.newtest.NewTestRequest;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import services.response.AttachmentResponse;
import services.response.DentalBenefitsResponse;
import services.response.LoaListResponse;
import services.response.MaceRequestResponse;
import services.response.TestModelResponse;

/**
 * Created by window on 10/5/2016.
 */

public interface AppInterface {

//    String ENDPOINT = "http://mace-public01.medicardphils.com:8080/";
//    String PHOTOLINK = "http://mace-public01.medicardphils.com:8080/downloadpicture/";



//    String ENDPOINT = "http://192.168.1.5:8080";
//    String PHOTOLINK = "http://192.168.1.5:8080/downloadpicture/";


//    String ENDPOINT = "http://10.10.24.195:8080/";
//    String PHOTOLINK = "http://10.10.24.195:8080/downloadpicture/";

    String ENDPOINT = "http://macetestsvr01.medicardphils.com:8080/";
    String PHOTOLINK = "http://macetestsvr01.medicardphils.com:8080/downloadpicture/";

//    String ENDPOINT = "http://macetestsvr01.medicardphils.com:8081/";
//    String PHOTOLINK = "http://macetestsvr01.medicardphils.com:8081/downloadpicture/";

//    String ENDPOINT = "http://10.10.24.195:8080/";
//    String PHOTOLINK = "http://10.10.24.195:8080/downloadpicture/";


/*
    String PHOTOLINK = "http://macetestsvr01.medicardphils.com:8080/downloadpicture/";
    String ENDPOINT = "http://macetestsvr01.medicardphils.com:8080/";
*/

//    String ENDPOINT = "http://125.5.100.202:8080/";
//    String PHOTOLINK = "http://125.5.100.202:8080/downloadpicture/";

//    String ENDPOINT = "http://125.5.100.202:8080/";
//    String PHOTOLINK = "http://125.5.100.202:8080/downloadpicture/";


//    String ENDPOINT = "http://10.10.26.12:8080/";
//    String PHOTOLINK = "http://10.10.26.12:8080/downloadpicture/";





    @POST("v2/registerAccount/")
    Observable<ResponseBody> requestUser(@Body RequestAccount requestAccount);

    @GET("v2/verifyMember/?")
    Observable<VerifyMemberData> verifyMember(@Query("memberCode") String id, @Query("dob") String dob);


    @POST("v2/loginMember/")
    Observable<SignInDetails> logInUser(@Body LogIn logIn);


    @Multipart
    @POST("uploadpicture")
    Observable<ResponseBody> upload(@Part("file\"; filename=\"image.png\"") RequestBody file,
                                    @Part("memCode") RequestBody memCode,
                                    @Part("appUsername") RequestBody appUsername,
                                    @Part("userType") RequestBody userType);


    @GET("v2/viewAccountInfo/{id}")
    Observable<GetUSER> getMemberInfo(@Path("id") String id);

    @GET("v2/getMemberInfo/{id}")
    Observable<VerifyMemberData> getDependentInfo(@Path("id") String id);

    @DELETE("deletepicture/{id}")
    Observable<ResponseBody> deletePhoto(@Path("id") String id);

    @POST("/v2/addOtherAccount/")
    Observable<AddDepenceResponse> addDependence(@Body AddDependence addDependence);

    @POST("/v2/changePassword/")
    Observable<ReturnChangePassword> changePassword(@Body ChangePassword changePassword);

    @POST("/v2/requestChangePassword/")
    Observable<ReturnRequestPassword> requestPassword(@Body RequestNewPassword requestNewPassword);

    @GET("/v2/listing/getHospitals/")
    Call<Hospital> getHospital(@Query("last_update_date") String last_update_date);

    @GET("/v2/listing/getDoctorsToHospital/")
    Call<Doctors> getDoctors(@Query("hospitalCode") String hospitalCode,
                             @Query("last_update_date") String last_update_date);

    @GET("/v2/listing/getDentistList")
    Call<DentistModel> getDentist();

    @GET("/v2/listing/getMaceFaqs")
    Call<FAQsModel> getMaceFAQs();

    @POST("memberloa/requestLOAConsult/")
    Observable<RequestResult> sendConsultation(@Body SendLoa sendLoa);

    @POST("memberloa/requestLOAMaternity/")
    Observable<RequestResult> sendMaternity(@Body SendLoa sendLoa);


    @GET("listing/getInpatientHospitalExclusionList/?")
    Observable<Exclusions> getInpatientExclusions(@Query("memberCode") String memberCode);

    @GET("listing/getOutpatientHospitalExclusionList/")
    Observable<Exclusions> getOutpatientExclusions(@Query("memberCode") String memberCode);

    @GET("/listing/getProvinces/")
    Observable<Province> getProvinces();

    @GET("/listing/getCities/")
    Observable<City> getCity();

    @GET("/listing/getSpecializations/")
    Observable<SpecializationList> getSpecialization();

    @POST("/v2/registerPin/")
    Observable<Pinned> regPin(@Body Pin pin);

    @POST("/v2/updatePin/")
    Observable<Pinned> updatePin(@Body UpdatePin updatePin);

    @GET("/v2/getLoaByMemberCode/?")
    Call<LoaListResponse> getLoaList(@Query("memberCode") String memberCode);

// @GET("/v2/getLoaByMemberCode/?")
//    Observable<LoaListResponse> getLoaList(@Query("memberCode") String memberCode);

    @GET("/listing/getDoctorByCode/?")
    Observable<DoctorNORoom> getDoctorData(@Query("doctorCode") String doctorCode);

    @GET("/listing/getDoctorHospitalByCode/?")
    Observable<TheDoctor> getDoctorDataWithRoom(@Query("doctorCode") String doctorCode);

    @POST("/memberloa/cancelLOA/")
    Observable<ResponseBody> setRequestCancel(@Query("requestCode") String requestCode);

    @POST("/coordinator/v2/approveLOA")
    Observable<Confirm> confirmLoaConsult(@Query("batchCode") String batchCode);

    @POST("/coordinator/v2/updateHasDisclaimerByMemberCode")
    Observable<ResponseBody> setDisclaimer(@Query("memberCode") String memberCode,
                                           @Query("hasDisclaimer") String hasDisclaimer);

    @GET("/coordinator/v2/getHasDisclaimerByMemberCode/?")
    Observable<Disclaimer> getDisclaimer(@Query("memberCode") String memberCode);

    @POST("/coordinator/v2/requestBasicOrOtherTest")
    Call<MaceRequestResponse> getBasicTestResult(@Body NewTestRequest newTestRequest);

    @POST("/memberloa/requestLoaForTests/")
    Call<TestModelResponse> requestLoaForTests(@Query("memberCode") String memberCode,
                                               @Query("hospitalCode") String hospitalCode);
    @Multipart
    @POST("/memberloa/addAttachmentByRequestCode/")
    Call<AttachmentResponse> addAttachmentByRequestCode(@Part MultipartBody.Part filePart,
                                                        @Query("requestCode") String requestCode);


    @GET("/membership/getDentalBenefits/")
    Call<DentalBenefitsResponse> getDentalBenefitByMemberCode(@Query("memberCode") String memberCode);



}
