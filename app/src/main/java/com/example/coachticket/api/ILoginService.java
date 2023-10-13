package com.example.coachticket.api;

import com.example.coachticket.response.BaseResponse;
import com.example.coachticket.response.CheckUsernameAndEmailResponse;
import com.example.coachticket.response.LoginBodyResponse;
import com.example.coachticket.models.User;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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

}
