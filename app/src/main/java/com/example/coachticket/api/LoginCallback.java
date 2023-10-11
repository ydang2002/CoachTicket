package com.example.coachticket.api;

public interface LoginCallback {
    void onLoginSuccess(String success);
    void onLoginFailure(String error);
}
