package services;

import model.AddDepenceResponse;
import model.AddDependence;
import model.ChangePassword;
import model.City;
import model.Doctors;
import model.Exclusions;
import model.Hospital;
import model.Pin;
import model.Pinned;
import model.Province;
import model.Provinces;
import model.RequestNewPassword;
import model.RequestResult;
import model.ReturnChangePassword;
import model.GetUSER;
import model.LogIn;
import model.RequestAccount;
import model.ReturnRequestPassword;
import model.SendLoa;
import model.SignInDetails;
import model.SpecializationList;
import model.UpdatePin;
import model.VerifyMemberData;
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
import utilities.SharedPref;

/**
 * Created by window on 10/5/2016.
 */

public interface AppInterface {


//    String ENDPOINT = "http://10.10.24.195:8080/";
//    String PHOTOLINK = "http://10.10.24.195:8080/downloadpicture/";

    String ENDPOINT = "http://mace-public01.medicardphils.com:8080/";
    String PHOTOLINK = "http://mace-public01.medicardphils.com:8080/downloadpicture/";


    @POST("v2/registerAccount/")
    Observable<ResponseBody> requestUser(@Body RequestAccount requestAccount);

    @GET("v2/verifyMember/?")
    Observable<VerifyMemberData> verifyMember(@Query("memberCode") String id, @Query("dob") String dob);


    @POST("v2/loginMember/")
    Observable<SignInDetails> logInUser(@Body LogIn logIn);


    @Multipart
    @POST("uploadpicture")
    Observable<ResponseBody> upload(@Part("file\"; filename=\"image.png\"") RequestBody file, @Part("memCode") RequestBody memCode);


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

    @GET("listing/getHospitals/")
    Observable<Hospital> getHospital();

    @GET("listing/getDoctorsToHospital/?")
    Observable<Doctors> getDoctors(@Query("hospitalCode") String hospitalCode);

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
}
