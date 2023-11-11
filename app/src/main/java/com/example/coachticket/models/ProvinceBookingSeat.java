package com.example.coachticket.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class ProvinceBookingSeat implements Parcelable {
    private String id;
    private String nameProvinces;
    private String locations;

    public ProvinceBookingSeat() {
    }

    public ProvinceBookingSeat(String id, String nameProvinces, String locations) {
        this.id = id;
        this.nameProvinces = nameProvinces;
        this.locations = locations;
    }

    protected ProvinceBookingSeat(Parcel in) {
        id = in.readString();
        nameProvinces = in.readString();
        locations = in.readString();
    }

    public static final Creator<ProvinceBookingSeat> CREATOR = new Creator<ProvinceBookingSeat>() {
        @Override
        public ProvinceBookingSeat createFromParcel(Parcel in) {
            return new ProvinceBookingSeat(in);
        }

        @Override
        public ProvinceBookingSeat[] newArray(int size) {
            return new ProvinceBookingSeat[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameProvinces() {
        return nameProvinces;
    }

    public void setNameProvinces(String nameProvinces) {
        this.nameProvinces = nameProvinces;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nameProvinces);
        parcel.writeString(locations);
    }
}
