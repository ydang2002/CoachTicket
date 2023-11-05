package com.example.coachticket.views.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import com.example.coachticket.R;
import com.example.coachticket.databinding.TabViewBinding;
import com.example.coachticket.viewmodels.SelectPickUpPointViewModel;

import java.util.ArrayList;
import java.util.List;

public class TabViewAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> titles;
    private int currentIndex; // Biến lưu trữ index của tab hiện tại

    public TabViewAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        currentIndex = 0;
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    // Phương thức để lấy index của tab hiện tại
    public int getCurrentIndex() {
        return currentIndex;
    }

    // Phương thức để cập nhật index khi người dùng chuyển tab
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        currentIndex = position;
    }
}

//public class TabViewFragment extends Fragment {
//
//    private TabViewBinding binding;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Khởi tạo databinding
//        binding = DataBindingUtil.inflate(inflater, R.layout.tab_view, container, false);
//        // Lấy ViewModel từ activity
//        SelectPickUpPointViewModel viewModel = new ViewModelProvider(requireActivity()).get(SelectPickUpPointViewModel.class);
//        // Gán ViewModel cho databinding
//        binding.setViewModel(viewModel);
//        // Trả về view từ databinding
//        return binding.getRoot();
//    }
//}
