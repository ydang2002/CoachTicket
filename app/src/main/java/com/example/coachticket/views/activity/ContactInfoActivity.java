package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.coachticket.R;
import com.example.coachticket.SharedPreferences.SharedPrefUser;
import com.example.coachticket.databinding.ActivityContactInfoBinding;
import com.example.coachticket.viewmodels.ContactInfoViewModel;

public class ContactInfoActivity extends AppCompatActivity {

    private ActivityContactInfoBinding binding;
    private ContactInfoViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_info);
        viewModel = new ViewModelProvider(this).get(ContactInfoViewModel.class);
        binding.setViewmodel(viewModel);

        String name = SharedPrefUser.getName(this);
        String email = SharedPrefUser.getEmail(this);
        String phone = SharedPrefUser.getPhone(this);
        viewModel.setName(name);
        viewModel.setEmail(email);
        viewModel.setPhone(phone);
    }
}