package com.example.coachticket.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class BookingSeat implements Parcelable {
    private String _id;
    private String customerId;
    private int totalPrice;
    private int totalSeats;
    private RoutesBookingSeat routes;
    private Info info;
    private List<BookingSeatDetails> bookingSeatDetails;

    public BookingSeat() {
    }

    public BookingSeat(String _id, String customerId, int totalPrice, int totalSeats, RoutesBookingSeat routes, Info info, List<BookingSeatDetails> bookingSeatDetails) {
        this._id =  _id;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.totalSeats = totalSeats;
        this.routes = routes;
        this.info = info;
        this.bookingSeatDetails = bookingSeatDetails;
    }

    protected BookingSeat(Parcel in) {
        _id = in.readString();
        customerId = in.readString();
        totalPrice = in.readInt();
        totalSeats = in.readInt();
        routes = in.readParcelable(RoutesBookingSeat.class.getClassLoader());
        bookingSeatDetails = in.createTypedArrayList(BookingSeatDetails.CREATOR);
        info = in.readParcelable(Info.class.getClassLoader());
    }

    public static final Creator<BookingSeat> CREATOR = new Creator<BookingSeat>() {
        @Override
        public BookingSeat createFromParcel(Parcel in) {
            return new BookingSeat(in);
        }

        @Override
        public BookingSeat[] newArray(int size) {
            return new BookingSeat[size];
        }
    };

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public RoutesBookingSeat getRoutes() {
        return routes;
    }

    public void setRoutes(RoutesBookingSeat routes) {
        this.routes = routes;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<BookingSeatDetails> getBookingSeatDetails() {
        return bookingSeatDetails;
    }

    public void setBookingSeatDetails(List<BookingSeatDetails> bookingSeatDetails) {
        this.bookingSeatDetails = bookingSeatDetails;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(customerId);
        parcel.writeInt(totalPrice);
        parcel.writeInt(totalSeats);
        parcel.writeParcelable(routes, i);
        parcel.writeTypedList(bookingSeatDetails);
        parcel.writeParcelable(info, i);
    }
}
