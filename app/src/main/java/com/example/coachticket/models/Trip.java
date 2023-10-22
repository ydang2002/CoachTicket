package com.example.coachticket.models;

import java.util.List;

public class Trip {
    private String id;
    private String originTime;
    private String  destinationTime;
    private String originDate;
    private String destinationDate;
    private int availableSeats;
    private List<Seat> seats;

    public Trip() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginTime() {
        return originTime;
    }

    public void setOriginTime(String originTime) {
        this.originTime = originTime;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public String getOriginDate() {
        return originDate;
    }

    public void setOriginDate(String originDate) {
        this.originDate = originDate;
    }

    public String getDestinationDate() {
        return destinationDate;
    }

    public void setDestinationDate(String destinationDate) {
        this.destinationDate = destinationDate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
