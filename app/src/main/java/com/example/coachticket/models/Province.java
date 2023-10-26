package com.example.coachticket.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class Province implements Parcelable {
    private String id;
    private String nameProvinces;
    private List<String> locations;

    public Province() {
    }

    public Province(String id, String nameProvinces, List<String> locations) {
        this.id = id;
        this.nameProvinces = nameProvinces;
        this.locations = locations;
    }

    protected Province(Parcel in) {
        id = in.readString();
        nameProvinces = in.readString();
        locations = in.createStringArrayList();
    }

    public static final Creator<Province> CREATOR = new Creator<Province>() {
        @Override
        public Province createFromParcel(Parcel in) {
            return new Province(in);
        }

        @Override
        public Province[] newArray(int size) {
            return new Province[size];
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

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
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
        parcel.writeStringList(locations);
    }
}
