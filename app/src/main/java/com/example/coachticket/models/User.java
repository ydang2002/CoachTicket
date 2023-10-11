package com.example.coachticket.models;

public class User {
    private String _id;
    private String email;
    private String name;
    private String phoneNumber;
    private String token;

    public User(String _id, String email, String name, String phoneNumber, String token) {
        this._id = _id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.token = token;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
