package com.example.travelapp.model;

import java.io.Serializable;

public class Geoloc implements Serializable {
    private double lat;
    private double lng;

    public Geoloc(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

}
