package com.example.coachticket.models;

import java.util.List;

public class ProvinceBookingSeat {
    private String id;
    private String nameProvinces;
    private String locations;

    public ProvinceBookingSeat() {
    }

    public ProvinceBookingSeat(String id, String nameProvinces, String locations) {
        this.id = id;
        this.nameProvinces = nameProvinces;
        this.locations = locations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameProvinces() {
        return nameProvinces;
    }

    public void setNameProvinces(String nameProvinces) {
        this.nameProvinces = nameProvinces;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }
}
