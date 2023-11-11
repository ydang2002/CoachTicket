package com.example.coachticket.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.coachticket.SharedPreferences.SharedPrefUser;
import com.example.coachticket.SharedPreferences.SharedPreferencesUtil;
import com.example.coachticket.api.ApiUtils;
import com.example.coachticket.api.ILoginService;
import com.example.coachticket.databinding.FragmentHistoryBinding;
import com.example.coachticket.models.BookingSeat;
import com.example.coachticket.response.BookingSeatResponse;
import com.example.coachticket.views.adapter.HistoryAdapter;
import com.example.coachticket.views.fragment.HistoryFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryViewModel extends ViewModel {
    private ArrayList<BookingSeat> mListBookingSeats;
    private ILoginService iLoginService;
    private HistoryAdapter adapter;
    private BookingSeatResponse bookingSeatResponse;

    public HistoryViewModel(Context context, FragmentHistoryBinding binding) {
        mListBookingSeats = new ArrayList<>();
        adapter = new HistoryAdapter(context, mListBookingSeats);
        binding.rcvCarrierHistory.setAdapter(adapter);
        String token = SharedPreferencesUtil.getToken(context);
//        String id = "65294bbf2fb2ab3bb6423181";
        String customerId = SharedPrefUser.getId(context);
        iLoginService = ApiUtils.getApiService(token);
        iLoginService.getBookingSeat(customerId).enqueue(new Callback<BookingSeatResponse<BookingSeat>>() {
            @Override
            public void onResponse(Call<BookingSeatResponse<BookingSeat>> call, Response<BookingSeatResponse<BookingSeat>> response) {
                if (response.isSuccessful()) {
                    bookingSeatResponse = response.body();
                    mListBookingSeats = (ArrayList<BookingSeat>) bookingSeatResponse.getData();
                    adapter.notifyAdapter(mListBookingSeats);
                } else {
                    Log.d("BookingSeatResponse12", "Lỗi khi lấy dữ liệu từ API");
                }
            }

            @Override
            public void onFailure(Call<BookingSeatResponse<BookingSeat>> call, Throwable t) {
                try {
                    Log.d("BookingSeatResponse13", "Lỗi kết nối API");
                    Log.d("ErrorConnectAPIBookingSeatResponse", t.getMessage());
                } catch (Exception e) {
                    Log.d("ExceptionOnFailureBookingSeatResponse", e.getMessage());
                    System.out.println("Message ExceptionOnFailure: " + e.getMessage());
                }
            }
        });
    }

}
