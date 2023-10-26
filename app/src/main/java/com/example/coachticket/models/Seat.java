package com.example.coachticket.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Seat implements Parcelable {
    private String id;
    private Boolean statusSeat;

    public Seat() {
    }

    public Seat(String id, Boolean statusSeat) {
        this.id = id;
        this.statusSeat = statusSeat;
    }

    //Constructor to read from Parcel
    public Seat(Parcel in) {
        // Read the fields from the Parcel
        id = in.readString();
        statusSeat = in.readByte() != 0;
    }

    // Creator to create Seat object from Parcel
    public static final Creator<Seat> CREATOR = new Creator<Seat>() {
        @Override
        public Seat createFromParcel(Parcel in) {
            return new Seat(in);
        }

        @Override
        public Seat[] newArray(int size) {
            return new Seat[size];
        }
    };

    // Method to describe the contents of the Parcel
    @Override
    public int describeContents() {
        return 0;
    }

    // Method to write to Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Write the fields to the Parcel
        dest.writeString(id);
        dest.writeByte((byte) (statusSeat ? 1 : 0));
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStatusSeat() {
        return statusSeat;
    }

    public void setStatusSeat(Boolean statusSeat) {
        this.statusSeat = statusSeat;
    }
}
