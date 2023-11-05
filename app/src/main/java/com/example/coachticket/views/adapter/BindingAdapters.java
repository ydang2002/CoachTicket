package com.example.coachticket.views.adapter;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.example.coachticket.R;
import com.example.coachticket.viewmodels.SelectPickUpPointViewModel;
import com.example.coachticket.views.activity.SelectPickUpPointActivity;

import java.util.List;

public class BindingAdapters {
    @BindingAdapter("items")
    public static void setItems(RadioGroup radioGroup, List<String> items) {
        // Xóa tất cả các radiobutton hiện có trong RadioGroup
        radioGroup.removeAllViews();

        // Nếu danh sách items là null hoặc rỗng, không làm gì cả
        if (items == null || items.isEmpty()) {
            return;
        }

        // Duyệt qua danh sách items và tạo một radiobutton cho mỗi item
//        for (String item : items) {
//            RadioButton radioButton = new RadioButton(radioGroup.getContext());
//            radioButton.setText(item);
//            // Thêm radiobutton vào RadioGroup
//            radioGroup.addView(radioButton);
//        }
        for (String item : items) {
            RadioButton radioButton = createRadioButton(radioGroup.getContext(), item);
            setupRadioButton(radioButton, item, radioGroup);
        }
    }


    private static RadioButton createRadioButton(Context context, String text) {
        RadioButton radioButton = new RadioButton(context);
        radioButton.setText(text);
        return radioButton;
    }

    private static void setupRadioButton(RadioButton radioButton, String item, RadioGroup radioGroup) {
        radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                updateSelectedLocation(item, radioGroup);
            }
        });
        radioGroup.addView(radioButton);
    }

    private static void updateSelectedLocation(String item, RadioGroup radioGroup) {
        if (radioGroup.getId() == R.id.radio_group_1) {
            ((SelectPickUpPointActivity) radioGroup.getContext()).viewModel.setRadioGroup1Value(item);
        } else if (radioGroup.getId() == R.id.radio_group_2) {
            ((SelectPickUpPointActivity) radioGroup.getContext()).viewModel.setRadioGroup2Value(item);
        }
    }
//    private static String selectedLocation1;
//    private static String selectedLocation2;
//
//    @BindingAdapter("items1")
//    public static void setItems1(RadioGroup radioGroup, List<String> items) {
//        radioGroup.removeAllViews();
//
//        if (items == null || items.isEmpty()) {
//            return;
//        }
//
//        for (String item : items) {
//            RadioButton radioButton = new RadioButton(radioGroup.getContext());
//            radioButton.setText(item);
//            radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
//                if (isChecked) {
//                    selectedLocation1 = item;
//                    // Cập nhật TextView trong Activity
//                    TextView selectedLocationTextView = ((SelectPickUpPointActivity) radioGroup.getContext()).findViewById(R.id.selected_location1);
//                    selectedLocationTextView.setText(selectedLocation1);
//                }
//            });
//            radioGroup.addView(radioButton);
//        }
//    }
//
//    @BindingAdapter("items2")
//    public static void setItems2(RadioGroup radioGroup, List<String> items) {
//        radioGroup.removeAllViews();
//
//        if (items == null || items.isEmpty()) {
//            return;
//        }
//
//        for (String item : items) {
//            RadioButton radioButton = new RadioButton(radioGroup.getContext());
//            radioButton.setText(item);
//            radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
//                if (isChecked) {
//                    selectedLocation2 = item;
//                    // Cập nhật TextView trong Activity
//                    TextView selectedLocationTextView = ((SelectPickUpPointActivity) radioGroup.getContext()).findViewById(R.id.selected_location2);
//                    selectedLocationTextView.setText(selectedLocation2);
//                }
//            });
//            radioGroup.addView(radioButton);
//        }
//    }
}
