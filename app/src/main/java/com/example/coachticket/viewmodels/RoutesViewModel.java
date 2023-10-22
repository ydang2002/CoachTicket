package com.example.coachticket.viewmodels;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coachticket.R;
import com.example.coachticket.SharedPreferences.SharedPreferencesUtil;
import com.example.coachticket.api.ApiUtils;
import com.example.coachticket.api.ILoginService;
import com.example.coachticket.models.Routes;
import com.example.coachticket.models.User;
import com.example.coachticket.response.RoutesResponse;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoutesViewModel extends ViewModel {

    //    private WeakReference<Context> context;
    private Context context;
    public ObservableInt progressBar;
    private ILoginService iLoginService;
    private MutableLiveData<RoutesResponse> dataModelLiveData = new MutableLiveData<>();
    private RoutesResponse routesResponse;

    public ObservableField<String> textViewDate = new ObservableField<>();

    public String[] origin = {
            "Chọn điểm đi", "AnGiang", "Bà Rịa-Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu",
            "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau",
            "Cần Thơ", "Cao Bằng", "Đà Nẵng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai",
            "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội", "Hà Tĩnh", "Hải Dương",
            "Hải Phòng", "Hậu Giang", "TP. Hồ Chí Minh", "Hòa Bình", "Hưng Yên", "Khánh Hòa",
            "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An",
            "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên", "Quảng Bình",
            "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh",
            "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên - Huế", "Tiền Giang", "Trà Vinh",
            "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"
    };

    public String[] destination = {
            "Chọn điểm đến", "An Giang", "Bà Rịa-Vũng Tàu", "BacGiang", "Bắc Kạn", "Bạc Liêu",
            "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau",
            "Cần Thơ", "Cao Bằng", "Đà Nẵng", "Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai",
            "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội", "Hà Tĩnh", "Hải Dương",
            "Hải Phòng", "Hậu Giang", "TP. Hồ Chí Minh", "Hòa Bình", "Hưng Yên", "Khánh Hòa",
            "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An",
            "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên", "Quảng Bình",
            "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh",
            "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên - Huế", "Tiền Giang", "Trà Vinh",
            "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"
    };

    public RoutesViewModel(Context context) {
//        context = new WeakReference<>(ontext);
        this.context = context;
        this.progressBar = new ObservableInt(View.GONE);
    }

    private MutableLiveData<List<Routes>> mRoutes = new MutableLiveData<>();

    // Tạo phương thức để khởi tạo và trả về biến LiveData hoặc MutableLiveData
    public LiveData<List<Routes>> getDataList() {
        return mRoutes;
    }

    private MutableLiveData<String> date = new MutableLiveData<>();
    private MutableLiveData<String> selectedOrigin = new MutableLiveData<>();
    private MutableLiveData<String> selectedDestination = new MutableLiveData<>();

    public RoutesViewModel() {
        date = new MutableLiveData<>();
    }

    public LiveData<String> getDate() {
        return date;
    }

    public void setDate(String date) {
        textViewDate.set(date);
    }

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

//    public LiveData<RoutesResponse> getRoutesProvinces() {
    public void getRoutesProvinces(String token) {
//        progressBar.set(View.VISIBLE);
        String origin = selectedOrigin.getValue();
        String destination = selectedDestination.getValue();
        String TVDate = textViewDate.get();
//        Log.d("selectedOrigin", "Selected destination value 2:" + origin);
//        Log.d("selectedOrigin", "Selected destination value 3:" + destination);
//        Log.d("selectedOrigin", "Selected destination value 4:" + TVDate);
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
                                mRoutes.setValue(routesResponse.getData());
//                                mRoutes = (MutableLiveData<List<Routes>>) routesResponse.getData();
//                            dataModelLiveData.setValue(response.body());
//                            RoutesResponse<Routes> data = response.body();
                                Log.d("dataModelLiveData", mRoutes.toString());
                                Log.d("dataModelLiveData23", routesResponse.getData().toString());
                            } else {
//                            showToast("Lỗi khi lấy dữ liệu từ API");
                            }
                        }  catch (Exception e) {
                            Log.d("ExceptionOnResponse",  e.getMessage());
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
                        }  catch (Exception e) {
                            Log.d("ExceptionOnFailure",  e.getMessage());
                            System.out.println("Message ExceptionOnFailure: " + e.getMessage());
                        }
                    }
                });
    }
    public void getRoutes(String token) {

        Log.d("TOKENLOGIN", "SharedPreferencesUtilToken22:" + token);
        iLoginService = ApiUtils.getApiService(token);
//        iLoginService.getRoutesProvinces("An Giang", "Bắc Giang", "25-10-2023")
        iLoginService.getRoutes().enqueue(new Callback<RoutesResponse<Routes>>() {
                    @Override
                    public void onResponse(Call<RoutesResponse<Routes>> call, Response<RoutesResponse<Routes>> response) {
//                        progressBar.set(View.GONE);
                        if (response.isSuccessful()) {
                            routesResponse = response.body();
//                            mRoutes = routesResponse.getData();
//                            dataModelLiveData.setValue(response.body());
//                            RoutesResponse<Routes> data = response.body();
                            Log.d("dataModelLiveData", response.toString());
                            Log.d("dataModelLiveData23", routesResponse.getData().toString());
                        } else {
//                            showToast("Lỗi khi lấy dữ liệu từ API");
                        }
                    }

                    @Override
                    public void onFailure(Call<RoutesResponse<Routes>> call, Throwable t) {
//                        progressBar.set(View.GONE);
//                        showToast("Lỗi kết nối đến API: " + t.getMessage());
                        String error = t.getMessage().toString();
                        Log.d("ErrorConnectAPI", t.getMessage());
                    }
                });
    }

//    void showToast(String msg) {
//        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
//    } 2023-10-22 12:23:28.292 12528-12528 RoutesLiveData          com.example.coachticket              D  Dữ liệu thay đổi: [com.example.coachticket.models.Routes@5ad8503]

}
