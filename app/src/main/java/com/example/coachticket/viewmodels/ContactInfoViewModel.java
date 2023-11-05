package com.example.coachticket.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactInfoViewModel extends ViewModel {

    private String name = new MutableLiveData<>().toString();
    private String email = new MutableLiveData<>().toString();
    private String phone = new MutableLiveData<>().toString();
    public ContactInfoViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
