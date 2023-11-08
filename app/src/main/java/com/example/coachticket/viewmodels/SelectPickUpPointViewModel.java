package com.example.coachticket.viewmodels;

import android.view.View;
import android.widget.RadioButton;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SelectPickUpPointViewModel extends ViewModel {
    private MutableLiveData<List<String>> locations1;
    private MutableLiveData<List<String>> locations2;
    // Biến LiveData để lưu trữ giá trị của radio_group_1
    private MutableLiveData<String> radioGroup1Value;
//    private String radioGroup1Value;
    // Biến LiveData để lưu trữ giá trị của radio_group_2
    private MutableLiveData<String> radioGroup2Value;

    public SelectPickUpPointViewModel() {
        locations1 = new MutableLiveData<>();
        locations2 = new MutableLiveData<>();
        radioGroup1Value = new MutableLiveData<>();
//        radioGroup1Value = new MutableLiveData<>().toString();
        radioGroup2Value = new MutableLiveData<>();
    }

    public LiveData<List<String>> getLocations1() {
        return locations1;
    }

    public LiveData<List<String>> getLocations2() {
        return locations2;
    }

    public void setLocations1(List<String> list) {
        locations1.setValue(list);
    }

    public void setLocations2(List<String> list) {
        locations2.setValue(list);
    }
    // Getters và Setters cho radioGroup1Value và radioGroup2Value
    public LiveData<String> getRadioGroup1Value() {
        return radioGroup1Value;
    }

    public void setRadioGroup1Value(String value) {
        radioGroup1Value.setValue(value);
    }


//    public String getRadioGroup1Value() {
//        return radioGroup1Value;
//    }
//
//    public void setRadioGroup1Value(String radioGroup1Value) {
//        this.radioGroup1Value = radioGroup1Value;
//    }

    public LiveData<String> getRadioGroup2Value() {
        return radioGroup2Value;
    }

    public void setRadioGroup2Value(String value) {
        radioGroup2Value.setValue(value);
    }

    // Tạo một phương thức để cập nhật giá trị của selectedValue khi người dùng chọn radiobutton
    public void onRadioButtonClicked(View view) {
        // Lấy đối tượng RadioButton từ view
        RadioButton radioButton = (RadioButton) view;
        // Lấy giá trị của RadioButton đó
        String value = radioButton.getText().toString();
        // Cập nhật giá trị của selectedValue
        radioGroup1Value.setValue(value);
//        setRadioGroup1Value(value);
    }
}
