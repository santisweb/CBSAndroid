package com.cristalbusinessservices.Retrofit;

import com.google.gson.JsonObject;
import com.cristalbusinessservices.Model.Appointments.APIAppointments;
import com.cristalbusinessservices.Model.Document.APIDocument;
import com.cristalbusinessservices.Model.Document_Type.APITypeDocument;
import com.cristalbusinessservices.Model.My_Taxes.APIMyTaxes;
import com.cristalbusinessservices.Model.Our_Office.APIOur;
import com.cristalbusinessservices.Model.Slider_Image.APISliderImage;
import com.cristalbusinessservices.Model.Tax_Dropdowns.APITaxYears;
import com.cristalbusinessservices.Model.UnPaid.APIUnPaid;
import com.cristalbusinessservices.Model.User_Info.APIUserInfo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("v1/account/register")
    Call<JsonObject> registerUser(
            @Header("Authorization") String authorization,
            @Field("businessId") int businessId,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone
    );

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("v1/account/forgotpassword")
    Call<JsonObject> resetPassword(
            @Header("Authorization") String authorization,
            @Field("email") String email
    );

    @Headers({"Content-Type: application/json"})
    @FormUrlEncoded
    @POST("token")
    Call<JsonObject> login(
            @Field("username") String email,
            @Field("password") String password,
            @Field("grant_type") String grant_type
    );

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("v1/taxes")
    Call<JsonObject> addNewTax(
            @Header("Authorization") String authorization,
            @Field("title") String title,
            @Field("year") int year,
            @Field("taxtype") int taxtype
    );

    @Headers({"Accept: application/json"})
    @Multipart
    @POST("v1/documents/1/upload")
    Call<JsonObject> postUploadDocument(
            @Header("Authorization") String Authorization,
            @Part("taxid") RequestBody taxid,
            @Part("documenttypeid") RequestBody documenttypeid,
            @Part("year") RequestBody year,
            @Part MultipartBody.Part file);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("v1/messages/contactus")
    Call<JsonObject> sendMessageContact(
            @Header("Authorization") String authorization,
            @Field("messageContent") String messageContent
    );

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST()
    Call<JsonObject> pay(
            @Url String url,
            @Header("Authorization") String authorization,
            @Field("invoiceid") int invoiceid,
            @Field("cardholdername") String cardholdername,
            @Field("cardnumber") String cardnumber,
            @Field("expirationmonth") int expirationmonth,
            @Field("expirationyear") int expirationyear,
            @Field("cvc") int cvc
    );

    @Headers({"Content-Type: application/json"})
    @GET()
    Call<JsonObject> getOffice(
            @Url String url,
            @Header("Authorization") String authorization
    );

    @Headers({"Content-Type: application/json"})
    @GET("v1/account/loginuserinfo")
    Call<APIUserInfo> userInfo(
            @Header("Authorization") String authorization
    );

    @Headers({"Content-Type: application/json"})
    @GET("v1/account/logout")
    Call<JsonObject> logout(
            @Header("Authorization") String authorization
    );

    @Headers({"Content-Type: application/json"})
    @GET()
    Call<APISliderImage> sliderImage(
            @Url String url,
            @Header("Authorization") String authorization
    );

    @Headers({"Content-Type: application/json"})
    @GET("v1/taxes/taxformdata")
    Call<APITaxYears> getTaxYears(
            @Header("Authorization") String authorization
    );

    @Headers({"Content-Type: application/json"})
    @GET("v1/taxes")
    Call<APIMyTaxes> getMyTaxes(
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize,
            @Header("Authorization") String authorization
    );

    @Headers({"Content-Type: application/json"})
    @GET("v1/taxes")
    Call<APIMyTaxes> getMyTaxesDocument(
            @Header("Authorization") String authorization
    );

    @Headers({"Content-Type: application/json"})
    @GET("v1/documents/types")
    Call<APITypeDocument> getTypeDocument(
            @Header("Authorization") String authorization
    );

    @Headers({"Content-Type: application/json"})
    @GET("v1/documents")
    Call<APIDocument> getDocument(
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize,
            @Header("Authorization") String authorization
    );

    @Headers({"Content-Type: application/json"})
    @GET()
    Call<JsonObject> downloadDocument(
            @Url String url,
            @Header("Authorization") String authorization
    );

    @Headers({"Content-Type: application/json"})
    @GET()
    Call<APIAppointments> getAppointments(
            @Header("Authorization") String authorization,
            @Url String url,
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize
    );

    @Headers({"Content-Type: application/json"})
    @GET()
    Call<APIAppointments> getAppointments2(
            @Header("Authorization") String authorization,
            @Url String url
    );

    @Headers({"Content-Type: application/json"})
    @GET()
    Call<APIUnPaid> getPaid(
            @Url String url,
            @Header("Authorization") String authorization
    );

    @Headers({"Content-Type: application/json"})
    @GET()
    Call<APIOur> getOurOffice(
            @Url String url,
            @Header("Authorization") String authorization
    );

    @Headers({"Content-Type: application/json"})
    @DELETE()
    Call<JsonObject> deleteDocument(
            @Url String url,
            @Header("Authorization") String authorization
    );

}