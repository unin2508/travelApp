package com.example.travelapp.model;

import java.io.Serializable;

public class FlightTicket implements Serializable {
    private String id;
    private Flight flight;
    private String type;
    private String seat;
    private String gate;
    private String terminal;
    private Integer price;
    private Boolean isSold;
    private String userId;

    public FlightTicket(String id, Flight flight, String type, String seat, String gate, String terminal, Integer price, Boolean isSold, String userId) {
        this.id = id;
        this.flight = flight;
        this.type = type;
        this.seat = seat;
        this.gate = gate;
        this.terminal = terminal;
        this.price = price;
        this.isSold = isSold;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
