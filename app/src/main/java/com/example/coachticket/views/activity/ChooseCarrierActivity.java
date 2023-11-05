package com.example.coachticket.views.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ActivityChooseCarrierBinding;
import com.example.coachticket.presenter.Presenter;
import com.example.coachticket.viewmodels.ChooseCarrierViewModel;
import com.example.coachticket.views.fragment.HomeFragment;

public class ChooseCarrierActivity extends AppCompatActivity {
    private ChooseCarrierViewModel viewModel;
    private ActivityChooseCarrierBinding activityChooseCarrierBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChooseCarrierBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose_carrier);
        activityChooseCarrierBinding.setCarrierViewModel(new ChooseCarrierViewModel(this, activityChooseCarrierBinding));
        viewModel = new ChooseCarrierViewModel(this);
        activityChooseCarrierBinding.setCarrierViewModel(viewModel);
        activityChooseCarrierBinding.setPresenter(new Presenter() {
            @Override
            public void goBack() {
                Toast.makeText(ChooseCarrierActivity.this, "go back", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ChooseCarrierActivity.this, LoginActivity.class);
                ChooseCarrierActivity.this.startActivity(intent);
                ((Activity) ChooseCarrierActivity.this).finish();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                HomeFragment homeFragment = new HomeFragment();
//                fragmentTransaction.replace(R.id.action_home, homeFragment);
//                fragmentTransaction.addToBackStack(null); // Thêm vào stack để có thể quay lại
//                fragmentTransaction.commit();
            }
        });
        activityChooseCarrierBinding.executePendingBindings();
//        setContentView(activityChooseCarrierBinding.getRoot());
    }
}