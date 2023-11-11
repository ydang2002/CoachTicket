package com.example.coachticket.response;

import java.util.List;

public class BookingSeatResponse<T> {

    private String message;
    private List<T> data;

    public BookingSeatResponse(String message, List<T> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
