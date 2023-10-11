package com.example.coachticket.api;

public class ApiUtils {

    public static ILoginService getApiService(String token) {
        return RetrofitClientInstance.getInstance(token).create(ILoginService.class);
    }
}
