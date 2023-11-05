package com.example.coachticket.views.adapter;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;

public class setCheckedButton {
    @BindingAdapter("android:checkedButton")
    public static void setCheckedButton(RadioGroup radioGroup, LiveData<String> value) {
        // Lấy giá trị của LiveData<String>
        String text = value.getValue();
        // Duyệt qua các radiobutton trong RadioGroup
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View view = radioGroup.getChildAt(i);
            // Kiểm tra xem view có phải là RadioButton hay không
            if (view instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) view;
                // So sánh giá trị của RadioButton với giá trị của LiveData<String>
                if (radioButton.getText().equals(text)) {
                    // Nếu giống nhau, đặt RadioButton đó là được chọn
                    radioGroup.check(radioButton.getId());
                    break;
                }
            }
        }
    }

}
