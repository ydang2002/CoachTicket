package com.example.coachticket.viewmodels;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticket.SharedPreferences.SharedPrefOriginDestination;
import com.example.coachticket.SharedPreferences.SharedPreferencesUtil;
import com.example.coachticket.api.ApiUtils;
import com.example.coachticket.api.ILoginService;
import com.example.coachticket.databinding.ActivityChooseCarrierBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.response.RoutesResponse;
import com.example.coachticket.views.activity.ChooseCarrierActivity;
import com.example.coachticket.views.adapter.ChooseCarrierAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseCarrierViewModel extends ViewModel/*BaseObservable*/ {
    private ChooseCarrierAdapter adapter;
    private ActivityChooseCarrierBinding activityChooseCarrierBinding;
    private Activity context;
    private ArrayList<Routes> mListRoutes;
    private ArrayList<Routes> allRoutes;
    private ILoginService iLoginService;
    private RoutesResponse routesResponse;

    public ObservableField<String> searchText = new ObservableField<>("");

    public ChooseCarrierViewModel(Context context, ActivityChooseCarrierBinding activityChooseCarrierBinding) {
        mListRoutes = new ArrayList<>();
        adapter = new ChooseCarrierAdapter(context, mListRoutes);
        activityChooseCarrierBinding.rcvCarrier.setAdapter(adapter);
        String origin = SharedPrefOriginDestination.getOrigin(context);
        String destination = SharedPrefOriginDestination.getDestination(context);
        String TVDate = SharedPrefOriginDestination.getDate(context);
        Log.d("selectedOrigin", "Selected destination value 2:" + origin);
        Log.d("selectedOrigin", "Selected destination value 3:" + destination);
        Log.d("selectedOrigin", "Selected destination value 4:" + TVDate);
        String token = SharedPreferencesUtil.getToken(context);
        Log.d("TOKENLOGINVIEWMODEL", "SharedPreferencesUtilTokenViewModel:" + token);
        iLoginService = ApiUtils.getApiService(token);
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
                                mListRoutes = (ArrayList<Routes>) routesResponse.getData();
                                adapter.notifyAdapter(mListRoutes);
                                if (mListRoutes.size() > 0) {
                                    activityChooseCarrierBinding.rcvCarrier.setVisibility(View.VISIBLE);
                                    activityChooseCarrierBinding.noCarrier.setVisibility(View.GONE);
                                } else {
                                    activityChooseCarrierBinding.rcvCarrier.setVisibility(View.GONE);
                                    activityChooseCarrierBinding.noCarrier.setVisibility(View.VISIBLE);
                                    adapter.notifyDataSetChanged();
                                }
                                Log.d("selectedOrigin", "routesResponse.getData:" + mListRoutes.toString());
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
                            Log.d("ErrorConnectAPI", t.getMessage());
                        } catch (Exception e) {
                            Log.d("ExceptionOnFailure", e.getMessage());
                            System.out.println("Message ExceptionOnFailure: " + e.getMessage());
                        }
                    }
                });

        searchFunc(activityChooseCarrierBinding.nameInput, activityChooseCarrierBinding.rcvCarrier,
                activityChooseCarrierBinding.noCarrier);

        SharedPrefOriginDestination.clearOrigin(context);
        SharedPrefOriginDestination.clearDestination(context);
        SharedPrefOriginDestination.clearDate(context);
    }

    public void searchFunc(EditText nameInput, RecyclerView recyclerView, TextView noText) {
        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    if (mListRoutes.size() != 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        noText.setVisibility(View.GONE);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        noText.setVisibility(View.VISIBLE);
                    }

                    adapter = new ChooseCarrierAdapter(context, mListRoutes);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    ArrayList<Routes> clone = new ArrayList<>();
                    for (Routes route : mListRoutes) {
                        for (int i = 0; i < route.getCarriers().size(); i++) {
                            if (route.getCarriers().get(i).getName().toLowerCase().contains(s.toString().toLowerCase())) {
                                // Nếu tìm thấy tên nhà xe phù hợp, thêm routes vào danh sách kết quả
                                clone.add(route);
                            }
                        }
                    }
                    if (clone.size() != 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        noText.setVisibility(View.GONE);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        noText.setVisibility(View.VISIBLE);
                    }

                    adapter = new ChooseCarrierAdapter(context, clone);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void goBack(View view) {
        ((Activity) context).finish();
    }
}
