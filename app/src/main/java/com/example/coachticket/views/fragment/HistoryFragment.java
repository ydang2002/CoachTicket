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
        // Tạo đối tượng DataBinding và liên kết với layout XML
        // Đối tượng binding này chứa các tham chiếu đến các thành phần giao diện người dùng trong layout.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        //lớp ViewModel được sử dụng để cung cấp dữ liệu cho fragment.
        binding.setHistoryViewModel(new HistoryViewModel(requireContext(), binding));
        // phương thức executePendingBindings được gọi để đảm bảo rằng các giá trị được gán cho các biến trong layout được cập nhật đúng cách.
        binding.executePendingBindings();
        //Phương thức getRoot được gọi để trả về View chứa các thành phần giao diện người dùng được hiển thị trên màn hình.
        return binding.getRoot();
    }
}