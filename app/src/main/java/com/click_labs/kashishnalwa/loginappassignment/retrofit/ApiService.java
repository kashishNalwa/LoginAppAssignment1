package com.click_labs.kashishnalwa.loginappassignment.retrofit;

import com.click_labs.kashishnalwa.loginappassignment.Model.DriverDetail;
import com.click_labs.kashishnalwa.loginappassignment.Model.LogInData;
import com.click_labs.kashishnalwa.loginappassignment.Model.RegisterDriverResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Define all server calls here
 */
public interface ApiService {

//    /**
//     *
//     * @param accessToken
//     * @param deviceToken
//     * @param regType
//     * @param callback
//     */
//    @FormUrlEncoded
//    @POST("/login_through_accesstoken")
//    public void login(@Field("access_token") String accessToken, @Field("device_token") String deviceToken, @Field("registration_type") String regType, Callback<String> callback);


    @FormUrlEncoded
    @POST("/api/admin/createSupplier")
    public void signUpClicked(@Field("name") String name, @Field("phoneNo") String phoneNo, @Field("email") String email, @Field("password") String password, Callback<LogInData> callback);

    @FormUrlEncoded
    @POST("/api/admin/supplierLogin")
    public void loginClicked(@Field("email") String email, @Field("password") String password, Callback<LogInData> callback);


    @GET("/api/admin/getAlldriver")
    void getAllDriver(@Header("authorization") String accessToken, Callback<DriverDetail> callback);


   public void deleteClicked(@Header("authorization") String accessToken, @Body String deleteData, Callback<RegisterDriverResponse> callback);


    @FormUrlEncoded
    @POST("/api/admin/registerDriver")
    public void addDriverClicked(@Header("authorization") String accessToken, @Field("name") String name, @Field("phoneNo") String phoneNo, @Field("email") String email, @Field("address") String address, Callback<RegisterDriverResponse> callback);
    /**
     *
     * @param accessToken
     * @param deviceToken
     * @param regType
     * @param callback
     */
    /*@FormUrlEncoded
    @POST("/login_through_accesstoken")
    public void login(@Field("access_token") String accessToken, @Field("device_token") String deviceToken, @Field("registration_type") String regType, Callback<UserData> callback);
*/

}