package com.example.coachticket.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.example.coachticket.SharedPreferences.SharedPrefUser;
import com.example.coachticket.SharedPreferences.SharedPreferencesUtil;
import com.example.coachticket.api.ILoginService;
import com.example.coachticket.response.BaseResponse;
import com.example.coachticket.response.LoginBodyResponse;
import com.example.coachticket.models.User;
import com.example.coachticket.views.activity.MainActivity;
import com.example.coachticket.views.activity.SignUpActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginViewModel extends ViewModel {

    private Context context;
    private String emailPattern = "[a-zA-Z0-9._-]+@gmail+\\.+com+";
    public ObservableInt progressBar;
    public final ObservableField<String > login_email = new ObservableField<>("");
    public final ObservableField<String > login_pass = new ObservableField<>("");

    public LoginViewModel(Context context) {
        this.context = context;
        progressBar = new ObservableInt(View.GONE);
    }

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://splendid-trunks-tick.cyclic.app/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();
    ILoginService iLoginService = retrofit.create(ILoginService.class);

    public void loginViewModel(Context context, String email, String password) {
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
        progressBar.set(View.VISIBLE);
        LoginBodyResponse loginBody = new LoginBodyResponse(email, password);
        Call<BaseResponse<User>> call = iLoginService.login(loginBody);
        call.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                progressBar.set(View.GONE);
                Log.e("loginreponse", "đã vào hàm");
                try {
                    if (response.isSuccessful()) {
                        Log.e("loginreponse1", "đã vào hàm");
                        String token = response.body().getData().getToken();
                        String id = response.body().getData().get_id();
                        String name = response.body().getData().getName();
                        String email = response.body().getData().getEmail();
                        String phone = response.body().getData().getPhoneNumber();
                        // Lưu token vào SharedPreferences
                        SharedPreferencesUtil.saveToken(context, token);
                        SharedPrefUser.savePhone(context, phone);
                        SharedPrefUser.SaveName(context, name);
                        SharedPrefUser.SaveEmail(context, email);
                        SharedPrefUser.SaveId(context, id);

//                    callback.onLoginSuccess("Đăng nhập thành công");
                        Intent intent = new Intent(context, MainActivity.class);
                        showToast("Đăng nhập thành công");
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    } else {
                        Log.e("loginreponse2", "Đăng nhập thất bại");
                        // Xử lý lỗi khi đăng nhập thất bại
//                    callback.onLoginFailure("Đăng nhập thất bại");
                        showToast("Đăng nhập thất bại");
                    }
                }
                catch(Exception e) {
                    Log.e("ExceptionLoginreponse", "error"+ e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                progressBar.set(View.GONE);
                showToast("Lỗi kết nối" + t.getMessage());
            }
        });
    }

    public void intent() {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
