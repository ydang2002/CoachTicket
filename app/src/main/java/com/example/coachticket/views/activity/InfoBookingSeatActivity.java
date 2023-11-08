package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coachticket.R;
import com.example.coachticket.SharedPreferences.SharedPrefOriginDestination;
import com.example.coachticket.SharedPreferences.SharedPrefUser;
import com.example.coachticket.databinding.ActivityInfoBookingSeatBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.models.Seat;
import com.example.coachticket.viewmodels.InfoBookingSeatViewModel;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Route;

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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_info_booking_seat);
        viewModel = new ViewModelProvider(this).get(InfoBookingSeatViewModel.class);
        viewModel.setRoutes(routes);
        viewModel.updateSeatIds(selectedSeats);
        viewModel.setLocationOrigin(radioButton1);
        viewModel.setLocationDestination(radioButton2);

        String name = SharedPrefUser.getName(this);
        String email = SharedPrefUser.getEmail(this);
        String phone = SharedPrefUser.getPhone(this);
        viewModel.setName(name);
        viewModel.setEmail(email);
        viewModel.setPhone(phone);
        viewModel.setPrice(price);
        viewModel.setSize(selectedSeats.size());

        // Liên kết ViewModel với DataBinding
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    public void goBack(View view) {
        finish();
    }
}