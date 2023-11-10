package com.example.coachticket.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Trip implements Parcelable {
    private String id;
    private String originTime;
    private String  destinationTime;
    private String originDate;
    private String destinationDate;
    private int availableSeats;
    private List<Seat> seats;

    public Trip() {
    }

    public Trip(String id, String originTime, String destinationTime, String originDate, String destinationDate, int availableSeats, List<Seat> seats) {
        this.id = id;
        this.originTime = originTime;
        this.destinationTime = destinationTime;
        this.originDate = originDate;
        this.destinationDate = destinationDate;
        this.availableSeats = availableSeats;
        this.seats = seats;
    }

    protected Trip(Parcel in) {
        id = in.readString();
        originTime = in.readString();
        destinationTime = in.readString();
        originDate = in.readString();
        destinationDate = in.readString();
        availableSeats = in.readInt();
        seats = in.createTypedArrayList(Seat.CREATOR);
    }

    public static final Parcelable.Creator<Trip> CREATOR = new Parcelable.Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginTime() {
        return originTime;
    }

    public void setOriginTime(String originTime) {
        this.originTime = originTime;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public String getOriginDate() {
        return originDate;
    }

    public void setOriginDate(String originDate) {
        this.originDate = originDate;
    }

    public String getDestinationDate() {
        return destinationDate;
    }

    public void setDestinationDate(String destinationDate) {
        this.destinationDate = destinationDate;
    }

    public int getAvailableSeats() {
        return this.availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(originTime);
        parcel.writeString(destinationTime);
        parcel.writeString(originDate);
        parcel.writeString(destinationDate);
        parcel.writeInt(availableSeats);
        parcel.writeTypedList(seats);
    }
}
