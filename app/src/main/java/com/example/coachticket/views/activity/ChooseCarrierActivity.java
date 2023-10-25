package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ActivityChooseCarrierBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.viewmodels.ChooseCarrierViewModel;
import com.example.coachticket.views.adapter.ChooseCarrierAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChooseCarrierActivity extends AppCompatActivity {

    private ActivityChooseCarrierBinding activityChooseCarrierBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChooseCarrierBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose_carrier);
        activityChooseCarrierBinding.setCarrierViewModel(new ChooseCarrierViewModel(this, activityChooseCarrierBinding));
        activityChooseCarrierBinding.executePendingBindings();
    }

    public void goBack(View view) {
        finish();
    }
}