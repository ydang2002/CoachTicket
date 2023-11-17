package com.example.coachticket.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountViewModel extends ViewModel {
    private String name = new MutableLiveData<>().toString();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
