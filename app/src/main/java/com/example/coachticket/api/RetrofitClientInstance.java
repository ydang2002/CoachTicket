package com.example.coachticket.api;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.coachticket.SharedPreferences.SharedPreferencesUtil;
import com.example.coachticket.models.User;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    static User loginResponse;

    private Context context;
//    String token = SharedPreferencesUtil.getToken(context);
    public static Retrofit getInstance(String token) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + token)
                            .build();
                    return chain.proceed(newRequest);
                }
        }).build();


        //https://splendid-trunks-tick.cyclic.app/users/login

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://splendid-trunks-tick.cyclic.app/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
