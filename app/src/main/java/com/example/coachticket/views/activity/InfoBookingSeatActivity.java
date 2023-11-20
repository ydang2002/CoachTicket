package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coachticket.R;
import com.example.coachticket.SharedPreferences.SharedPrefUser;
import com.example.coachticket.SharedPreferences.SharedPreferencesUtil;
import com.example.coachticket.databinding.ActivityInfoBookingSeatBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.models.Seat;
import com.example.coachticket.models.Trip;
import com.example.coachticket.viewmodels.InfoBookingSeatViewModel;
import com.example.coachticket.views.fragment.HistoryFragment;

import org.parceler.Parcels;

import java.util.ArrayList;

public class InfoBookingSeatActivity extends AppCompatActivity {

    private ActivityInfoBookingSeatBinding binding;
    private InfoBookingSeatViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Routes routes = Parcels.unwrap(intent.getParcelableExtra("routes"));
        ArrayList<Seat> selectedSeats = getIntent().getParcelableArrayListExtra("selectedSeats");
        String radioButton1 = getIntent().getStringExtra("radioButton1");
        String radioButton2 = getIntent().getStringExtra("radioButton2");
        int price = (int) getIntent().getSerializableExtra("price");

        // Lặp qua danh sách trips trong routes
//        for (Trip trip : routes.getTrips()) {
//            // Lặp qua danh sách seats trong từng trip
//            for (Seat seat : trip.getSeats()) {
//                // Lặp qua danh sách selectedSeats để tìm seat có cùng id
//                for (Seat selectedSeat : selectedSeats) {
//                    // Nếu id trùng khớp, cập nhật statusSeat
//                    if (seat.getId().equals(selectedSeat.getId())) {
//                        seat.setStatusSeat(selectedSeat.isStatusSeat());
//                        break; // Kết thúc vòng lặp khi đã tìm thấy trùng khớp
//                    }
//                }
//            }
//        }

        // Duyệt qua từng phần tử trong selectedSeats
        for (Seat selectedSeat : selectedSeats) {
            // Duyệt qua danh sách seats trong trips của routes
            for (Seat seat : routes.getTrips().getSeats()) {
                // So sánh id của seat trong routes với id của selectedSeat
                if (seat.getId().equals(selectedSeat.getId())) {
                    // Cập nhật trạng thái của seat trong routes bằng trạng thái của selectedSeat
                    seat.setStatusSeat(true);
                    break; // Dừng vòng lặp khi tìm thấy phần tử tương ứng
                }
            }
        }

        // Lấy đối tượng Context từ Activity
        Context context = this;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_info_booking_seat);
        viewModel = new ViewModelProvider(this).get(InfoBookingSeatViewModel.class);

        viewModel.setContext(context);
        //        viewModel = new InfoBookingSeatViewModel(this);
//        binding.setViewModel(viewModel);
        viewModel.setRoutes(routes);
        viewModel.updateSeatIds(selectedSeats);
        viewModel.setLocationOrigin(radioButton1);
        viewModel.setLocationDestination(radioButton2);
//        viewModel.updateStatusSeats(selectedSeats);

        String name = SharedPrefUser.getName(this);
        String email = SharedPrefUser.getEmail(this);
        String phone = SharedPrefUser.getPhone(this);
        String token = SharedPreferencesUtil.getToken(this);
        String id = SharedPrefUser.getId(this);
        viewModel.setName(name);
        viewModel.setEmail(email);
        viewModel.setPhone(phone);
        viewModel.setPrice(price);
        viewModel.setSize(selectedSeats.size());
        viewModel.createBookingSeatDetails(selectedSeats);
        viewModel.setId(id);
        viewModel.setToken(token);

        // Liên kết ViewModel với DataBinding
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    public void goBack(View view) {
        finish();
    }

//    public void backChooseCarrier(View view) {
//            Intent intent = new Intent(this, HistoryFragment.class);
//            this.startActivity(intent);
//            ((Activity) this).finish();
//    }
}