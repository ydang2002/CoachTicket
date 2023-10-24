package com.example.coachticket.models;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Routes implements Serializable {
    private String _id;
    private List<Province> origin;
    private List<Province> destination;

    private String distance;
    private String duration;
    private int price;
    private List<Trip> trips;
    private List<Carriers> carriers;

    public Routes() {
    }

    public Routes(String _id, List<Province> origin, List<Province> destination, String distance,
                  String duration, int price, List<Trip> trips, List<Carriers> carriers) {
        this._id = _id;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.price = price;
        this.trips = trips;
        this.carriers = carriers;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<Province> getOrigin() {
        return origin;
    }

    public void setOrigin(List<Province> origin) {
        this.origin = origin;
    }

    public List<Province> getDestination() {
        return destination;
    }

    public void setDestination(List<Province> destination) {
        this.destination = destination;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Carriers> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Carriers> carriers) {
        this.carriers = carriers;
    }
}
