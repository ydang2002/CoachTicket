package com.example.coachticket.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Info implements Parcelable {
    private String name;
    private String email;
    private String phone;

    public Info() {
    }

    public Info(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Phương thức để đọc dữ liệu từ Parcel
    protected Info(Parcel in) {
        name = in.readString();
        email = in.readString();
        phone = in.readString();
    }

    // Creator để tạo đối tượng từ Parcel
    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };

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

    // Phương thức này trả về một bitmask mô tả các đặc điểm của đối tượng Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    // Phương thức để viết dữ liệu vào Parcel
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(phone);
    }
}
