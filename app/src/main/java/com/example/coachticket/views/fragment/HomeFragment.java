package com.example.coachticket.views.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderGetKt;

import android.text.TextUtils;
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
import com.example.coachticket.views.activity.ChooseCarrier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;
    private RoutesViewModel routesViewModel;
    private TextView dateTextView;
    private ArrayList<Routes> routes;
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
                final String originDate = fragmentHomeBinding.originDate.getText().toString();
                final String origin = (String) fragmentHomeBinding.spinnerOrigin.getSelectedItem();
                final String destination = (String) fragmentHomeBinding.spinnerDestination.getSelectedItem();

                if (origin.equals("Chọn điểm đi")) {
                    showToast("Vui lòng chọn điểm đi");
                    return;
                }

                if (destination.equals("Chọn điểm đến")) {
                    showToast("Vui lòng chọn điểm đến");
                    return;
                }

                if (TextUtils.isEmpty(originDate)) {
                    showToast("Vui lòng chọn ngày đi");
                    return;
                }

                Intent intent = new Intent(getActivity(), ChooseCarrier.class);
                getActivity().startActivity(intent);
                ((Activity) getActivity()).finish();
//                String token = SharedPreferencesUtil.getToken(requireContext());
//                Log.d("TOKENLOGIN", "SharedPreferencesUtilToken22:" + token);
//                routesViewModel.getRoutes(token);
//                routesViewModel.getRoutesProvinces(token);

//                Intent intent = new Intent(getActivity(), ChooseCarrier.class);
//                List<Routes> dataList = routesViewModel.getRoutesProvinces(token);
//                Log.d("getRoutesResult", dataList.toString());
//                routes = new ArrayList<>(dataList);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("ARRAYLIST", (Serializable)routes);
//                intent.putExtra("routes", bundle);
//                intent.putExtra("routes", bundle);
//                startActivity(intent);
            }
        });



//        routesViewModel.getDataList().observe(getViewLifecycleOwner(), new Observer<List<Routes>>() {
//            @Override
//            public void onChanged(List<Routes> routesList) {
//                if (routesList != null) {
//                    // Log giá trị của routesList khi có thay đổi
//                    Log.d("RoutesLiveData", "Dữ liệu thay đổi: " + routesList.toString());
//                }
//            }
//        });

        return fragmentHomeBinding.getRoot();
    }

    void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
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