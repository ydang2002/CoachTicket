package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ActivityLoginBinding;
import com.example.coachticket.presenter.Presenter;
import com.example.coachticket.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding activityLoginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        activityLoginBinding  = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        activityLoginBinding.setLoginViewModel(loginViewModel);

        activityLoginBinding.setPresenter(new Presenter() {
            @Override
            public void login() {
                final String email = activityLoginBinding.loginEmail.getText().toString();
                final String password = activityLoginBinding.loginPass.getText().toString();

                loginViewModel.loginViewModel(LoginActivity.this, email, password);
            }
        });

//        activityLoginBinding.setPresenter(new Presenter(){
//            @Override
//            public void intent() {
//                loginViewModel.intent();
//            }
//        });
    }

//    public void login() {
//        final String email = activityLoginBinding.loginEmail.getText().toString();
//        final String password = activityLoginBinding.loginPass.getText().toString();
//
//        loginViewModel.loginViewModel(LoginActivity.this, email, password);
//    }

}