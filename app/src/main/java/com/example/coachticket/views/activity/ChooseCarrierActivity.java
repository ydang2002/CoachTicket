package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ActivityChooseCarrierBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.viewmodels.ChooseCarrierViewModel;
import com.example.coachticket.views.adapter.ChooseCarrierAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChooseCarrierActivity extends AppCompatActivity {

    private ActivityChooseCarrierBinding activityChooseCarrierBinding;
    private ChooseCarrierAdapter adapter;
    private ArrayList<Routes> routes;
    private ChooseCarrierViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChooseCarrierBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose_carrier);
        activityChooseCarrierBinding.setCarrierViewModel(new ChooseCarrierViewModel(this, activityChooseCarrierBinding));
//        viewModel = new ChooseCarrierViewModel(this, activityChooseCarrierBinding); // Khởi tạo ViewModel? 2
//        activityChooseCarrierBinding.setCarrierViewModel(viewModel);//2

//        adapter = new ChooseCarrierAdapter(this, viewModel.getMListRoutes()); // Sử dụng danh sách từ ViewModel 2
//        activityChooseCarrierBinding.rcvCarrier.setAdapter(adapter);//2
        activityChooseCarrierBinding.executePendingBindings();

//        activityChooseCarrierBinding.nameInput.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // Nothing to do here
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                viewModel.performSearch();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // Nothing to do here
//            }
//        });
//        ChooseCarrierViewModel viewModel = new ChooseCarrierViewModel(this, activityChooseCarrierBinding);//1
//        activityChooseCarrierBinding.setCarrierViewModel(viewModel);//1
//        adapter = new ChooseCarrierAdapter(this, new ArrayList<Routes>()); // Khởi tạo adapter với một danh sách rỗng//1
//        activityChooseCarrierBinding.rcvCarrier.setAdapter(adapter);//1
        /*activityChooseCarrierBinding.executePendingBindings();

//        viewModel.performSearch();
        activityChooseCarrierBinding.getCarrierViewModel().getFilteredRoutes().observe(this, new Observer<List<Routes>>() {
            @Override
            public void onChanged(List<Routes> filteredRoutes) {
                adapter.updateData((ArrayList<Routes>) filteredRoutes);
            }
        });*///1
    }
}