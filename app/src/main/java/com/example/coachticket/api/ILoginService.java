package com.example.coachticket.api;

import com.example.coachticket.models.BookingSeat;
import com.example.coachticket.models.Routes;
import com.example.coachticket.response.BaseResponse;
import com.example.coachticket.response.CheckUsernameAndEmailResponse;
import com.example.coachticket.response.LoginBodyResponse;
import com.example.coachticket.models.User;
import com.example.coachticket.response.RoutesResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ILoginService {

    @POST("users/login")
    Call<BaseResponse<User>> login(@Body LoginBodyResponse loginBody);

    @POST("users/register")
    Call<User> register(@Body User user);

    @POST("users/login")
    @FormUrlEncoded
    Call<CheckUsernameAndEmailResponse> checkUsernameAndEmail(
            /*@Field("username") String username,*/
            @Field("email") String email
    );

    @GET("routes/{origin}/{destination}/{originDate}")
    Call<RoutesResponse<Routes>> getRoutesProvinces(
            @Path("origin") String origin,
            @Path("destination") String destination,
            @Path("originDate") String originDate
    );

    @GET("/routes")
    Call<RoutesResponse<Routes>> getRoutes2(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("originDate") String originDate
    );

    @GET("routes/f")
    Call<RoutesResponse<Routes>> getRoutes();

    @POST("bookingSeat")
    Call<BookingSeat> insertBookingSeat(@Body BookingSeat bookingSeat);
}
