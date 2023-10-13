package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ActivitySignUpBinding;
import com.example.coachticket.presenter.Presenter;
import com.example.coachticket.viewmodels.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding activitySignUpBinding;
    private SignUpViewModel signUpViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        signUpViewModel = new SignUpViewModel(this);
        activitySignUpBinding.setSignUpViewModel(signUpViewModel);
//        setContentView(R.layout.activity_sign_up);
        activitySignUpBinding.setPresenter(new Presenter(){
            @Override
            public void signUp() {
                final String username = activitySignUpBinding.signupUsername.getText().toString();
                final String email = activitySignUpBinding.signupEmail.getText().toString();
                final String address = activitySignUpBinding.signupAddress.getText().toString();
                final String phone = activitySignUpBinding.phoneNumber.getText().toString();
                final String password = activitySignUpBinding.signupPass.getText().toString();

                signUpViewModel.signUpViewModel(username, email, address, password, phone);
            }
        });

    }
}