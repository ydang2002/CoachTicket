package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ActivityChooseCarrierBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.viewmodels.ChooseCarrierViewModel;
import com.example.coachticket.views.adapter.ChooseCarrierAdapter;

import java.util.ArrayList;

public class ChooseCarrierActivity extends AppCompatActivity {

    private ActivityChooseCarrierBinding activityChooseCarrierBinding;
    private ChooseCarrierAdapter adapter;
    private ArrayList<Routes> routes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChooseCarrierBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose_carrier);
        activityChooseCarrierBinding.setCarrierViewModel(new ChooseCarrierViewModel(this, activityChooseCarrierBinding));
        activityChooseCarrierBinding.executePendingBindings();
//        setContentView(activityChooseCarrierBinding.getRoot());
    }

    private void disPlayListCarrier() {
        adapter = new ChooseCarrierAdapter(routes);
        RecyclerView rcvRoutes = activityChooseCarrierBinding.rcvCarrier;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvRoutes.setLayoutManager(linearLayoutManager);
        rcvRoutes.setAdapter(adapter);

    }
}