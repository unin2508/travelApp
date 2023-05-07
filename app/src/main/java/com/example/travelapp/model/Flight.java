package com.example.travelapp.model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Flight implements Serializable {
    private  String id;
    private String departureAirport;
    private String arrivalAirport;
    private Long departureTime;
    private Long arrivalTime;
    private String airlineName;
    private Integer seat;
    private Integer minPrice;
    private List<FlightTicket> ticketList;

    public Flight(){

    }


    public Flight(String id,String departureAirport, String arrivalAirport, Long departureTime, Long arrivalTime, String airlineName, Integer seat,Integer minPrice) {
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airlineName = airlineName;
        this.seat = seat;
        this.minPrice = minPrice;
        List<FlightTicket> ticketList = new ArrayList<>();
        for (int i = 0;i<240;i++){
            int i1 = i % 6 + (int) 'A';
            char column = (char)i1;
            String row = (int)i/6+ "" ;
            String seatTicket = column+row;
            FlightTicket ticket = null;
            if (i<60){
                ticket = new FlightTicket(UUID.randomUUID().toString(),id,"business",seatTicket,"A5","T2",minPrice+500,false,null);
            } else {
                ticket = new FlightTicket(UUID.randomUUID().toString(),id,"eco",seatTicket,"A5","T2",minPrice,false,null);
            }
            ticketList.add(ticket);
        }
        this.ticketList = ticketList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Long departureTime) {
        this.departureTime = departureTime;
    }

    public Long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public List<FlightTicket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<FlightTicket> ticketList) {
        this.ticketList = ticketList;
    }
}
