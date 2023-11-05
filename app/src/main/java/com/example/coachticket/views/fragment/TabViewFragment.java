package com.example.coachticket.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.coachticket.R;
import com.example.coachticket.databinding.TabViewBinding;
import com.example.coachticket.viewmodels.SelectPickUpPointViewModel;

public class TabViewFragment extends Fragment {

    private TabViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Khởi tạo databinding
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_view, container, false);
        // Lấy ViewModel từ activity
        SelectPickUpPointViewModel viewModel = new ViewModelProvider(requireActivity()).get(SelectPickUpPointViewModel.class);
        // Gán ViewModel cho databinding
        binding.setViewModel(viewModel);
        // Trả về view từ databinding
        return binding.getRoot();
    }
}