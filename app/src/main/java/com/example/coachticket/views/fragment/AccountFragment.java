package com.example.coachticket.views.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coachticket.R;
import com.example.coachticket.SharedPreferences.SharedPrefUser;
import com.example.coachticket.databinding.FragmentAccountBinding;
import com.example.coachticket.presenter.Presenter;
import com.example.coachticket.viewmodels.AccountViewModel;
import com.example.coachticket.views.activity.ChooseCarrierActivity;
import com.example.coachticket.views.activity.LoginActivity;

public class AccountFragment extends Fragment {
    private FragmentAccountBinding binding;
    private AccountViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container,false);
        viewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        String name = SharedPrefUser.getName(getContext());
        viewModel.setName(name);
        binding.setPresenter(new Presenter() {
            @Override
            public void logout() {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                ((Activity) getActivity()).finish();
            }
        });

        return binding.getRoot();
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_account, container, false);
    }
}