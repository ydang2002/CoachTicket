package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

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
       int price = (int) getIntent().getSerializableExtra("price");

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
        // Thêm 2 tab với tên Điểm đón và Điểm trả
        adapter.addFragment(new TabViewFragment(), "Điểm đón");
        adapter.addFragment(new TabViewFragment2(), "Điểm trả");
        // Gán adapter cho viewPager
        viewPager.setAdapter(adapter);
        viewPager.setAdapter(adapter);
        // Đồng bộ tabLayout với viewPager
        tabLayout.setupWithViewPager(viewPager);
//        MutableLiveData<String> text = (MutableLiveData<String>) viewModel.getRadioGroup1Value();
//        Log.d("getRadioGroup1Value", String.valueOf(text));

        binding.setPresenter(new Presenter() {
            @Override
            public void intentContactInfo() {
                MutableLiveData<String> radioButton1 = (MutableLiveData<String>) viewModel.getRadioGroup1Value();
                MutableLiveData<String> radioButton2 = (MutableLiveData<String>) viewModel.getRadioGroup2Value();

                if (radioButton1.getValue() == null) {
                    Toast.makeText(SelectPickUpPointActivity.this, "Vui lòng chọn điểm đón", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (radioButton2.getValue() == null) {
                    Toast.makeText(SelectPickUpPointActivity.this, "Vui lòng chọn điểm trả", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(SelectPickUpPointActivity.this, ContactInfoActivity.class);
                intent.putParcelableArrayListExtra("selectedSeats", new ArrayList<>(selectedSeats));
                intent.putExtra("routes", Parcels.wrap(routes));
                intent.putExtra("radioButton1", (CharSequence) radioButton1.getValue());
                intent.putExtra("radioButton2", (CharSequence) radioButton2.getValue());
                intent.putExtra("price",price);
                startActivity(intent);
            }
        });

//        binding.setPresenter(new Presenter() {
//            @Override
//            public void goBackSelectPickUpPoint() {
//                finish();
//            }
//        });
    }

    public void goBack(View view) {
        finish();
    }
}