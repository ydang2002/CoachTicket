package com.example.coachticket.api;

import com.example.coachticket.models.BaseResponse;
import com.example.coachticket.models.LoginBody;
import com.example.coachticket.models.User;
import com.example.coachticket.views.activity.LoginActivity;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ILoginService {

//    @POST("users/login")
//    Call<BaseResponse<User>> login(@Query("email") String email, @Query("password") String password);

    @POST("users/login")
    Call<BaseResponse<User>> login(@Body LoginBody loginBody);

}
