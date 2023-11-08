package com.example.coachticket.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coachticket.models.Routes;
import com.example.coachticket.models.Seat;

import java.util.ArrayList;
import java.util.List;

public class InfoBookingSeatViewModel extends ViewModel {

    private MutableLiveData<Routes> routesLiveData = new MutableLiveData<>();
    private MutableLiveData<String> seatIds = new MutableLiveData<>("");
    private String locationOrigin = new MutableLiveData<>().toString();
    private String locationDestination = new MutableLiveData<>().toString();
    private String name = new MutableLiveData<>().toString();
    private String email = new MutableLiveData<>().toString();
    private String phone = new MutableLiveData<>().toString();
    private int price;
    private int size;

    public LiveData<Routes> getRoutesLiveData() {
        return routesLiveData;
    }

    public void setRoutes(Routes routes) {
        routesLiveData.setValue(routes);
    }

    public LiveData<String> getSeatIds() {
        return seatIds;
    }

    public String getLocationOrigin() {
        return locationOrigin;
    }

    public void setLocationOrigin(String locationOrigin) {
        this.locationOrigin = locationOrigin;
    }

    public String getLocationDestination() {
        return locationDestination;
    }

    public void setLocationDestination(String locationDestination) {
        this.locationDestination = locationDestination;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void updateSeatIds(ArrayList<Seat> selectedSeats) {
        StringBuilder builder = new StringBuilder();
        for (Seat seat : selectedSeats) {
            builder.append(seat.getId()).append(", ");
        }
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 2); // Xóa dấu phẩy cuối cùng
        }
        seatIds.setValue(builder.toString());
    }

}
