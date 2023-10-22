package com.example.coachticket.models;

import java.util.List;

public class Province {
    private String id;
    private String nameProvinces;
    private List<String> locations;

    public Province() {
    }

    public Province(String id, String nameProvinces, List<String> locations) {
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

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
