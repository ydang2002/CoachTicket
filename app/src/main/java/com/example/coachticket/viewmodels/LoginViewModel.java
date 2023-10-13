package com.example.coachticket.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.coachticket.SharedPreferences.SharedPreferencesUtil;
import com.example.coachticket.api.ILoginService;
import com.example.coachticket.api.LoginCallback;
import com.example.coachticket.models.BaseResponse;
import com.example.coachticket.models.LoginBody;
import com.example.coachticket.models.User;
import com.example.coachticket.views.activity.LoginActivity;
import com.example.coachticket.views.activity.MainActivity;
import com.example.coachticket.views.activity.SignUpActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginViewModel extends BaseObservable {

    private Context context;
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
        progressBar.set(View.VISIBLE);
        LoginBody loginBody = new LoginBody(email, password);
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
                        // Lưu token vào SharedPreferences
                        SharedPreferencesUtil.saveToken(context, token);
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
                    System.out.println("ExceptionLoginreponse " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                progressBar.set(View.GONE);
//                callback.onLoginFailure("Lỗi kết nối"+t.getMessage());
                showToast("Lỗi kết nối" + t.getMessage());
                Log.e("loginreponse3", "Lỗi kết nối"+t.getMessage());
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
