package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.coachticket.R;
import com.example.coachticket.SharedPreferences.SharedPrefUser;
import com.example.coachticket.databinding.ActivityContactInfoBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.models.Seat;
import com.example.coachticket.presenter.Presenter;
import com.example.coachticket.viewmodels.ContactInfoViewModel;

import org.parceler.Parcels;

import java.util.ArrayList;

public class ContactInfoActivity extends AppCompatActivity {

    private ActivityContactInfoBinding binding;
    private ContactInfoViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Routes routes = Parcels.unwrap(intent.getParcelableExtra("routes"));
        ArrayList<Seat> selectedSeats = getIntent().getParcelableArrayListExtra("selectedSeats");
        String radioButton1 = getIntent().getStringExtra("radioButton1");
        String radioButton2 = getIntent().getStringExtra("radioButton2");
        int price = (int) getIntent().getSerializableExtra("price");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_info);
        viewModel = new ViewModelProvider(this).get(ContactInfoViewModel.class);
        binding.setViewmodel(viewModel);

        String name = SharedPrefUser.getName(this);
        String email = SharedPrefUser.getEmail(this);
        String phone = SharedPrefUser.getPhone(this);
        viewModel.setName(name);
        viewModel.setEmail(email);
        viewModel.setPhone(phone);

        binding.setPresenter(new Presenter() {
            @Override
            public void intentInfoBookingSeat() {
                Intent intent = new Intent(ContactInfoActivity.this, InfoBookingSeatActivity.class);
                intent.putParcelableArrayListExtra("selectedSeats", new ArrayList<>(selectedSeats));
                intent.putExtra("routes", Parcels.wrap(routes));
                intent.putExtra("radioButton1", radioButton1);
                intent.putExtra("radioButton2", radioButton2);
                intent.putExtra("price",price);
                startActivity(intent);
            }
        });
    }

    public void goBack(View view) {
        finish();
    }
}