package com.example.coachticket.models;

import java.util.List;

public class RoutesBookingSeat {
    private String _id;
    private ProvinceBookingSeat origin;
    private ProvinceBookingSeat destination;

    private String distance;
    private String duration;
    private int price;
    private Trip trips;
    private Carriers carriers;

    public RoutesBookingSeat() {
    }

    public RoutesBookingSeat(String _id, ProvinceBookingSeat origin, ProvinceBookingSeat destination, String distance, String duration, int price, Trip trips, Carriers carriers) {
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

    public ProvinceBookingSeat getOrigin() {
        return origin;
    }

    public void setOrigin(ProvinceBookingSeat origin) {
        this.origin = origin;
    }

    public ProvinceBookingSeat getDestination() {
        return destination;
    }

    public void setDestination(ProvinceBookingSeat destination) {
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

    public Trip getTrips() {
        return trips;
    }

    public void setTrips(Trip trips) {
        this.trips = trips;
    }

    public Carriers getCarriers() {
        return carriers;
    }

    public void setCarriers(Carriers carriers) {
        this.carriers = carriers;
    }
}
