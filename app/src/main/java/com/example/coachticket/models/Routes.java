package com.example.coachticket.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class Routes implements Parcelable {
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

    // Constructor to read from Parcel
    public Routes(Parcel in) {
        // Read the fields from the Parcel
        _id = in.readString();
        origin = in.createTypedArrayList(Province.CREATOR);
        destination = in.createTypedArrayList(Province.CREATOR);
        distance = in.readString();
        duration = in.readString();
        price = in.readInt();
        trips = in.createTypedArrayList(Trip.CREATOR);
        carriers = in.createTypedArrayList(Carriers.CREATOR);
    }


    // Method to describe the contents of the Parcel
    @Override
    public int describeContents() {
        return 0;
    }

    // Method to write to Parcel
    @Override
    public void writeToParcel(@NonNull Parcel dest, int i) {
        // Write the fields to the Parcel
        dest.writeString(_id);
        dest.writeTypedList(origin);
        dest.writeTypedList(destination);
        dest.writeString(distance);
        dest.writeString(duration);
        dest.writeInt(price);
        dest.writeTypedList(trips);
        dest.writeTypedList(carriers);
    }

    // Creator to create Routes object from Parcel
    public static final Parcelable.Creator<Routes> CREATOR = new Parcelable.Creator<Routes>() {
        @Override
        public Routes createFromParcel(Parcel source) {
            return new Routes(source);
        }

        @Override
        public Routes[] newArray(int size) {
            return new Routes[size];
        }
    };

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
