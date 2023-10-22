package com.example.coachticket.models;

public class Seat {
    private String id;
    private Boolean statusSeat;

    public Seat() {
    }

    public Seat(String id, Boolean statusSeat) {
        this.id = id;
        this.statusSeat = statusSeat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStatusSeat() {
        return statusSeat;
    }

    public void setStatusSeat(Boolean statusSeat) {
        this.statusSeat = statusSeat;
    }
}
