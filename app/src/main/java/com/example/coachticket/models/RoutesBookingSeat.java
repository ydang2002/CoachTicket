package com.example.coachticket.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class RoutesBookingSeat implements Parcelable {
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

    protected RoutesBookingSeat(Parcel in) {
        _id = in.readString();
        distance = in.readString();
        duration = in.readString();
        price = in.readInt();
        trips = in.readParcelable(Trip.class.getClassLoader());
        carriers = in.readParcelable(Carriers.class.getClassLoader());
        origin = in.readParcelable(ProvinceBookingSeat.class.getClassLoader());
        destination = in.readParcelable(ProvinceBookingSeat.class.getClassLoader());
    }

    public static final Creator<RoutesBookingSeat> CREATOR = new Creator<RoutesBookingSeat>() {
        @Override
        public RoutesBookingSeat createFromParcel(Parcel in) {
            return new RoutesBookingSeat(in);
        }

        @Override
        public RoutesBookingSeat[] newArray(int size) {
            return new RoutesBookingSeat[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(distance);
        parcel.writeString(duration);
        parcel.writeInt(price);
        parcel.writeParcelable(trips, i);
        parcel.writeParcelable(carriers, i);
        parcel.writeParcelable(origin, i);
        parcel.writeParcelable(destination, i);
    }
}
