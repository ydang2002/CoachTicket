package com.example.coachticket.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.coachticket.SharedPreferences.SharedPreferencesUtil;
import com.example.coachticket.api.ApiUtils;
import com.example.coachticket.api.ILoginService;
import com.example.coachticket.models.User;
import com.example.coachticket.views.activity.LoginActivity;
import com.example.coachticket.views.activity.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends BaseObservable {

    private ILoginService iLoginService;
    private Context context;
    public ObservableInt progressBar;

    public final ObservableField<String > signup_username = new ObservableField<>("");
    public final ObservableField<String > signup_email = new ObservableField<>("");
    public final ObservableField<String > signup_pass = new ObservableField<>("");
    public final ObservableField<String > signup_address = new ObservableField<>("");
    public final ObservableField<String > phone_number = new ObservableField<>("");

    public SignUpViewModel(Context context) {
        this.context = context;
        this.progressBar = new ObservableInt(View.GONE);
    }

    public void signUpViewModel(String username, String email, String address, String password, String phone) {
        progressBar.set(View.VISIBLE);
        String token = SharedPreferencesUtil.getToken(context);
        User user = new User("", email, username, phone, "", address, password);
        iLoginService = ApiUtils.getApiService(token);
        iLoginService.register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressBar.set(View.GONE);
                Intent intent = new Intent(context, LoginActivity.class);
                Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT);
                context.startActivity(intent);
                ((Activity) context).finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressBar.set(View.GONE);
                Toast.makeText(context, "Đăng ký thất bại", Toast.LENGTH_SHORT);
            }
        });
    }

    public void intent() {
        Intent intent = new Intent(context, LoginActivity.class);
        Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT);
        context.startActivity(intent);
        ((Activity) context).finish();
    }
}
