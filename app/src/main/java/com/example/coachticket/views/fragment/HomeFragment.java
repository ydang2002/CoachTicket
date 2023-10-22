package com.example.coachticket.views.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderGetKt;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachticket.R;
import com.example.coachticket.SharedPreferences.SharedPreferencesUtil;
import com.example.coachticket.databinding.FragmentHomeBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.presenter.Presenter;
import com.example.coachticket.viewmodels.RoutesViewModel;

import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;
    private RoutesViewModel routesViewModel;
    private TextView dateTextView;
    private String date, origin, destination;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Tạo đối tượng DataBinding và liên kết với layout XML
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
//        routesViewModel = new ViewModelProvider(this).get(RoutesViewModel.class);
        routesViewModel = new ViewModelProvider(this).get(RoutesViewModel.class);
        // Gán ViewModel cho DataBinding
        fragmentHomeBinding.setRoutesViewModel(routesViewModel);
        // Đảm bảo rằng DataBinding có thể truy cập ViewModel
        fragmentHomeBinding.setLifecycleOwner(this);
        fragmentHomeBinding.setFragmentHome(this);

        fragmentHomeBinding.setPresenter(new Presenter() {
            @Override
            public void getRoutesProvinces() {
                String token = SharedPreferencesUtil.getToken(requireContext());
//                Log.d("TOKENLOGIN", "SharedPreferencesUtilToken22:" + token);
//                routesViewModel.getRoutes(token);
                routesViewModel.getRoutesProvinces(token);

            }
        });

        routesViewModel.getDataList().observe(getViewLifecycleOwner(), new Observer<List<Routes>>() {
            @Override
            public void onChanged(List<Routes> routesList) {
                if (routesList != null) {
                    // Log giá trị của routesList khi có thay đổi
                    Log.d("RoutesLiveData", "Dữ liệu thay đổi: " + routesList.toString());
                }
            }
        });

        return fragmentHomeBinding.getRoot();
    }

    public void onDisplayDatePickerDialogClick() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Format the date as dd/MM/yyyy
                        date = dayOfMonth + "/" + (month + 1) + "/" + year;
//                        fragmentHomeBinding.originDate.setText(date);
                        routesViewModel.setDate(dayOfMonth + "-" + (month + 1) + "-" + year);
                        Log.e("HienThiNgay: ", date);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }
}