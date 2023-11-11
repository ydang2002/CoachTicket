package com.example.coachticket.views.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coachticket.R;
import com.example.coachticket.databinding.FragmentHistoryBinding;
import com.example.coachticket.viewmodels.HistoryViewModel;

public class HistoryFragment extends Fragment {
    private FragmentHistoryBinding binding;
    private HistoryViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        binding.setHistoryViewModel(new HistoryViewModel(requireContext(), binding));
        binding.executePendingBindings();
        return binding.getRoot();
    }
}