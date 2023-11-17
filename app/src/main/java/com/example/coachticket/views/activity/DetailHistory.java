package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ActivityDetailHistoryBinding;
import com.example.coachticket.models.BookingSeat;
import com.example.coachticket.models.BookingSeatDetails;
import com.example.coachticket.viewmodels.DetailHistoryViewModel;

import org.parceler.Parcels;

import java.util.ArrayList;

public class DetailHistory extends AppCompatActivity {

    private ActivityDetailHistoryBinding binding;
    private DetailHistoryViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        BookingSeat bookingSeat = Parcels.unwrap(intent.getParcelableExtra("bookingSeat"));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_history);
        viewModel = new ViewModelProvider(this).get(DetailHistoryViewModel.class);
        viewModel.setBookingSeat(bookingSeat);
        viewModel.updateSeatIds((ArrayList<BookingSeatDetails>) bookingSeat.getBookingSeatDetails());

        // Liên kết ViewModel với DataBinding
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    public void goBack(View view) {
        finish();
    }
}