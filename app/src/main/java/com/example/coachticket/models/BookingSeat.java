package com.example.coachticket.models;

import java.util.List;

public class BookingSeat {
    private String customerId;
    private int totalPrice;
    private int totalSeats;
    private RoutesBookingSeat routes;
    private Info info;
    private List<BookingSeatDetails> bookingSeatDetails;

    public BookingSeat() {
    }

    public BookingSeat(String customerId, int totalPrice, int totalSeats, RoutesBookingSeat routes, Info info, List<BookingSeatDetails> bookingSeatDetails) {
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
}
