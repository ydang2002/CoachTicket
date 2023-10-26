package com.example.coachticket.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class Carriers implements Parcelable {
    private String id;
    private String name;
    private List<String> info;

    public Carriers() {
    }

    public Carriers(String id, String name, List<String> info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    protected Carriers(Parcel in) {
        id = in.readString();
        name = in.readString();
        info = in.createStringArrayList();
    }

    public static final Creator<Carriers> CREATOR = new Creator<Carriers>() {
        @Override
        public Carriers createFromParcel(Parcel in) {
            return new Carriers(in);
        }

        @Override
        public Carriers[] newArray(int size) {
            return new Carriers[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeStringList(info);
    }
}
