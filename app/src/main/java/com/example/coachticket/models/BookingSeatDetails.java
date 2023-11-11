package com.example.coachticket.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class BookingSeatDetails implements Parcelable {

    private String seatNumber;

    public BookingSeatDetails() {
    }

    public BookingSeatDetails(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    protected BookingSeatDetails(Parcel in) {
        seatNumber = in.readString();
    }

    public static final Creator<BookingSeatDetails> CREATOR = new Creator<BookingSeatDetails>() {
        @Override
        public BookingSeatDetails createFromParcel(Parcel in) {
            return new BookingSeatDetails(in);
        }

        @Override
        public BookingSeatDetails[] newArray(int size) {
            return new BookingSeatDetails[size];
        }
    };

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(seatNumber);
    }
}
