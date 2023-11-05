package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;

import com.example.coachticket.BR;
import com.example.coachticket.R;
import com.example.coachticket.databinding.ActivitySelectPickUpPointBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.models.Seat;
import com.example.coachticket.presenter.Presenter;
import com.example.coachticket.viewmodels.SelectPickUpPointViewModel;
import com.example.coachticket.views.adapter.BindingAdapters;
import com.example.coachticket.views.adapter.TabViewAdapter;
import com.example.coachticket.views.adapter.TabViewAdapter2;
import com.example.coachticket.views.fragment.TabViewFragment;
import com.example.coachticket.views.fragment.TabViewFragment2;
import com.google.android.material.tabs.TabLayout;

import org.parceler.Parcels;

import java.util.ArrayList;

public class SelectPickUpPointActivity extends AppCompatActivity {

    private ActivitySelectPickUpPointBinding binding;
    public SelectPickUpPointViewModel viewModel;
    private RadioGroup radioGroup;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Routes routes = Parcels.unwrap(intent.getParcelableExtra("routes"));
        ArrayList<Seat> selectedSeats = getIntent().getParcelableArrayListExtra("selectedSeats");
        ArrayList<String> listLocation1 =  getIntent().getStringArrayListExtra("listLocation1");
        ArrayList<String> listLocation2 =  getIntent().getStringArrayListExtra("listLocation2");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_pick_up_point);
        viewModel = new ViewModelProvider(this).get(SelectPickUpPointViewModel.class);
        binding.setVariable(BR.viewModel, viewModel);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        // Cập nhật danh sách cho ViewModel
        viewModel.setLocations1(listLocation1);
        viewModel.setLocations2(listLocation2);
        // Tạo một TabLayout và một ViewPager
        TabLayout tabLayout = binding.tabLayout;
        ViewPager viewPager = binding.viewPager;
        // Tạo một TabViewAdapter để quản lý các tab
        TabViewAdapter adapter = new TabViewAdapter(getSupportFragmentManager());
//        TabViewAdapter2 adapter2 = new TabViewAdapter2(getSupportFragmentManager());
        // Thêm 2 tab với tên listLocation1 và listLocation2
        adapter.addFragment(new TabViewFragment(), "Điểm đón");
        adapter.addFragment(new TabViewFragment2(), "Điểm trả");
        // Gán adapter cho viewPager
        viewPager.setAdapter(adapter);
        viewPager.setAdapter(adapter);
        // Đồng bộ tabLayout với viewPager
        tabLayout.setupWithViewPager(viewPager);
        MutableLiveData<String> text = (MutableLiveData<String>) viewModel.getRadioGroup1Value();
        Log.d("getRadioGroup1Value", String.valueOf(text));

        binding.setPresenter(new Presenter() {
            @Override
            public void intentContactInfo() {
                Intent intent = new Intent(SelectPickUpPointActivity.this, ContactInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}