package com.example.coachticket.views.adapter;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.example.coachticket.viewmodels.SelectPickUpPointViewModel;

public class BindingAdaptersGetValue {
    @BindingAdapter({"bind:viewModel", "bind:radioGroupValue"})
    public static void bindRadioGroup(RadioGroup group, SelectPickUpPointViewModel viewModel, MutableLiveData<String> radioGroupValue) {
        // Lắng nghe sự kiện thay đổi trạng thái của RadioGroup
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Lấy RadioButton được chọn
                RadioButton button = group.findViewById(checkedId);
                // Lấy giá trị của RadioButton
                String value = button.getText().toString();
                // Cập nhật giá trị cho ViewModel
                radioGroupValue.setValue(value);
            }
        });
    }
}
