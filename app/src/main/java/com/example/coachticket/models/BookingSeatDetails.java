package com.example.coachticket.models;

public class BookingSeatDetails {

    private String seatNumber;

    public BookingSeatDetails() {
    }

    public BookingSeatDetails(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
