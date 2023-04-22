package com.example.travelapp.model;



public class Airport{
    private String name;
    private String city;
    private String country;
    private String iata_code;
    private Geoloc _geoloc;
    private int links_count;
    private String objectID;

    public Airport(String name, String city, String country, String iata_code, Geoloc _geoloc, int links_count, String objectID) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.iata_code = iata_code;
        this._geoloc = _geoloc;
        this.links_count = links_count;
        this.objectID = objectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIata_code() {
        return iata_code;
    }

    public void setIata_code(String iata_code) {
        this.iata_code = iata_code;
    }

    public Geoloc get_geoloc() {
        return _geoloc;
    }

    public void set_geoloc(Geoloc _geoloc) {
        this._geoloc = _geoloc;
    }

    public int getLinks_count() {
        return links_count;
    }

    public void setLinks_count(int links_count) {
        this.links_count = links_count;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }
}
