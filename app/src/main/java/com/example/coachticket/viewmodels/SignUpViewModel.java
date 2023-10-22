package com.example.coachticket.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.coachticket.SharedPreferences.SharedPreferencesUtil;
import com.example.coachticket.api.ApiUtils;
import com.example.coachticket.api.ILoginService;
import com.example.coachticket.models.User;
import com.example.coachticket.response.CheckUsernameAndEmailResponse;
import com.example.coachticket.views.activity.LoginActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends BaseObservable {

    private ILoginService iLoginService;
    private final String emailPattern = "[a-zA-Z0-9._-]+@gmail+\\.+com+";
    private final String phoneNumberPattern = "0\\d{9}";
    private final Context context;
    public ObservableInt progressBar;

    public final ObservableField<String> signup_username = new ObservableField<>("");
    public final ObservableField<String> signup_email = new ObservableField<>("");
    public final ObservableField<String> signup_pass = new ObservableField<>("");
    public final ObservableField<String> signup_address = new ObservableField<>("");
    public final ObservableField<String> phone_number = new ObservableField<>("");

    public SignUpViewModel(Context context) {
        this.context = context;
        this.progressBar = new ObservableInt(View.GONE);
    }

    public boolean checkUsernameAndEmailExist(/*String username, */String email) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7Il9pZCI6IjY0YzI0YTgzMjc1ZGJmZmVhNDgwOTFmYSIsIm5hbWUiOiJ5ZmFmZiIsImVtYWlsIjoieWFmYmZAZ21haWwuY29tIiwicGFzc3dvcmQiOiIkMmIkMTAkUkxmN3RyMHdZZzNQaFJFUDJhTWd3T3pFM0xiR3JBL0d2eHVBdExsb1Zsc2NmempXeUFzWjYiLCJwaG9uZU51bWJlciI6IjAzNDMyMjM0NCIsIl9fdiI6MH0sImlhdCI6MTY5NzIwNDU0OCwiZXhwIjoxNjk4MDY4NTQ4fQ.owWJpcwxU-ms55mYRydSnET7CUQ59akLP3NBjLmZb30";
        iLoginService = ApiUtils.getApiService(token);
        try {
            Response<CheckUsernameAndEmailResponse> response = iLoginService.checkUsernameAndEmail(/*username, */email).execute();
            if (response.isSuccessful()) {
                CheckUsernameAndEmailResponse apiResponse = response.body();
                if (apiResponse != null) {
                    boolean usernameExists = apiResponse.isUsernameExists();
                    boolean emailExists = apiResponse.isEmailExists();

                    return usernameExists || emailExists;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Mặc định trả về false nếu có lỗi hoặc không thành công
    }


    public void signUpViewModel(String username, String email, String address, String password, String phone) {

        if (TextUtils.isEmpty(username)) {
            showToast("Vui lòng nhập họ tên");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            showToast("Vui lòng nhập email");
            return;
        }

        if (!email.matches(emailPattern)) {
            showToast("Vui lòng nhập đúng định dạng email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            showToast("Vui lòng nhập mật khẩu");
            return;
        }

        if (password.length() < 8) {
            showToast("Mật khẩu phải lớn hơn 7 kí tự");
            return;
        }

        if (TextUtils.isEmpty(address)) {
            showToast("Vui lòng nhập địa chỉ");
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            showToast("Vui lòng nhập số điện thoại");
            return;
        }

        if (!phone.matches(phoneNumberPattern)) {
            showToast("Vui lòng nhập đúng định dạng số điện thoại");
            return;
        }

//        if(!checkUsernameAndEmailExist(/*username, */email)) {
//            showToast("Tên hoặc email đã tồn tại");
//            return;
//        }

        progressBar.set(View.VISIBLE);
        String token = SharedPreferencesUtil.getToken(context);
        User user = new User("", email, username, phone, "", address, password);
        iLoginService = ApiUtils.getApiService(token);
        iLoginService.register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressBar.set(View.GONE);
                Intent intent = new Intent(context, LoginActivity.class);
                showToast("Đăng ký thành công");
                context.startActivity(intent);
                ((Activity) context).finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressBar.set(View.GONE);
                showToast("Đăng ký thất bại");
            }
        });
    }

    public void intent() {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
