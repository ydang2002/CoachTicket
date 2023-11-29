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
        // Tạo đối tượng DataBinding và liên kết với layout XML
        activityChooseCarrierBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose_carrier);
        //Tạo một đối tượng ChooseCarrierViewModel mới và gán cho activityChooseCarrierBinding. Điều này thiết lập ViewModel cho DataBinding, cho phép layout XML truy cập và hiển thị dữ liệu từ ViewModel.
        activityChooseCarrierBinding.setCarrierViewModel(new ChooseCarrierViewModel(this, activityChooseCarrierBinding));
        viewModel = new ChooseCarrierViewModel(this);
        //Gán viewModel cho activityChooseCarrierBinding. Điều này cung cấp cho layout XML khả năng truy cập và hiển thị dữ liệu từ viewModel.
        activityChooseCarrierBinding.setCarrierViewModel(viewModel);
        activityChooseCarrierBinding.setPresenter(new Presenter() {
            @Override
            public void goBack() {
                Toast.makeText(ChooseCarrierActivity.this, "go back", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ChooseCarrierActivity.this, MainActivity.class);
                ChooseCarrierActivity.this.startActivity(intent);
                ((Activity) ChooseCarrierActivity.this).finish();
            }
        });
        //Thực hiện các lệnh liên quan đến DataBinding. Điều này đảm bảo rằng bất kỳ thay đổi dữ liệu nào trong ViewModel đã được ánh xạ chính xác lên layout XML.
        activityChooseCarrierBinding.executePendingBindings();
//        setContentView(activityChooseCarrierBinding.getRoot());
    }
}