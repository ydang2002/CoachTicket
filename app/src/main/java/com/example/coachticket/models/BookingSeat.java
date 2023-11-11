package com.example.coachticket.models;

import java.util.List;

public class BookingSeat {
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
}
