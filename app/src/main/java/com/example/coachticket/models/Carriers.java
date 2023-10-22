package com.example.coachticket.models;

import java.util.List;

public class Carriers {
    private String id;
    private String name;
    private List<String> info;

    public Carriers() {
    }

    public Carriers(String id, String name, List<String> info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }
}
