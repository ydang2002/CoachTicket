package com.example.coachticket.viewmodels;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.coachticket.SharedPreferences.SharedPreferencesUtil;
import com.example.coachticket.api.ApiUtils;
import com.example.coachticket.api.ILoginService;
import com.example.coachticket.databinding.ActivityChooseCarrierBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.response.RoutesResponse;
import com.example.coachticket.views.activity.ChooseCarrierActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseCarrierViewModel extends BaseObservable {
    private ILoginService iLoginService;
    private RoutesResponse routesResponse;
    private MutableLiveData<String> selectedOrigin = new MutableLiveData<>();
    private MutableLiveData<String> selectedDestination = new MutableLiveData<>();
    public ObservableField<String> textViewDate = new ObservableField<>();
    private MutableLiveData<String> date = new MutableLiveData<>();

    // Tạo một phương thức để lấy giá trị được chọn từ LiveData
    public LiveData<String> getSelectedOrigin() {
        return selectedOrigin;
    }

    public LiveData<String> getSelectedDestination() {
        return selectedDestination;
    }

    public void setSelectedOrigin(String value) {
        selectedOrigin.setValue(value);
    }

    public void setSelectedDestination(String value) {
        selectedDestination.setValue(value);
    }

    public LiveData<String> getDate() {
        return date;
    }

    public void setDate(String date) {
        textViewDate.set(date);
    }

    public ChooseCarrierViewModel() {
        date = new MutableLiveData<>();
    }

    public ChooseCarrierViewModel(Context context, ActivityChooseCarrierBinding activityChooseCarrierBinding) {
        String origin = selectedOrigin.getValue();
        String destination = selectedDestination.getValue();
        String TVDate = textViewDate.get();
//        Log.d("selectedOrigin", "Selected destination value 2:" + origin);
//        Log.d("selectedOrigin", "Selected destination value 3:" + destination);
//        Log.d("selectedOrigin", "Selected destination value 4:" + TVDate);
        String token = SharedPreferencesUtil.getToken(context);
        Log.d("TOKENLOGIN", "SharedPreferencesUtilToken22:" + token);
        iLoginService = ApiUtils.getApiService(token);
//        iLoginService.getRoutesProvinces("AnGiang", "BacGiang", "25-10-2023")
        iLoginService.getRoutesProvinces(origin, destination, TVDate)
                .enqueue(new Callback<RoutesResponse<Routes>>() {
                    @Override
                    public void onResponse(Call<RoutesResponse<Routes>> call, Response<RoutesResponse<Routes>> response) {
                        int statusCode = response.code();
                        System.out.println("Status code: " + statusCode);
                        Log.d("dataModelLiveData11", "Đã vào hàm");
//                        progressBar.set(View.GONE);
                        try {
                            if (response.isSuccessful()) {
                                Log.d("dataModelLiveData12", "get api thành công");
                                routesResponse = response.body();
                                List<Routes> mRoutes3 = new ArrayList<>();
                                mRoutes3 = routesResponse.getData();
                            } else {
//                            showToast("Lỗi khi lấy dữ liệu từ API");
                            }
                        } catch (Exception e) {
                            Log.d("ExceptionOnResponse", e.getMessage());
                            System.out.println("Message ExceptionOnResponse: " + e.getMessage());
                        }

                    }

                    @Override
                    public void onFailure(Call<RoutesResponse<Routes>> call, Throwable t) {
                        try {
                            Log.d("dataModelLiveData13", "Lỗi kết nối API");

//                        progressBar.set(View.GONE);
//                        showToast("Lỗi kết nối đến API: " + t.getMessage());
                            Log.d("ErrorConnectAPI", t.getMessage());
                        } catch (Exception e) {
                            Log.d("ExceptionOnFailure", e.getMessage());
                            System.out.println("Message ExceptionOnFailure: " + e.getMessage());
                        }
                    }
                });
    }
}
