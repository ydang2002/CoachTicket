package com.example.coachticket.views.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabViewAdapter2 extends FragmentPagerAdapter {
    private List<Fragment> fragments2;
    private List<String> titles2;
    private int currentIndex; // Biến lưu trữ index của tab hiện tại

    public TabViewAdapter2(FragmentManager fm) {
        super(fm);
        fragments2 = new ArrayList<>();
        titles2 = new ArrayList<>();
        currentIndex = 0;
    }

    public void addFragment(Fragment fragment2, String title2) {
        fragments2.add(fragment2);
        titles2.add(title2);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments2.get(position);
    }

    @Override
    public int getCount() {
        return fragments2.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles2.get(position);
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
