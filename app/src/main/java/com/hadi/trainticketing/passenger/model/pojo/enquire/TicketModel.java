package com.hadi.trainticketing.passenger.model.pojo.enquire;

import java.util.List;

public class TicketModel {
    private String launchTime;
    private String arrivalTime;
    private com.hadi.trainticketing.passenger.model.pojo.ticket.Ticket reservationTicket;
    private String reservationId;
    private Ticket ticket;
    private List<ArrayResult> arrayResults;

    public TicketModel(String launchTime, String arrivalTime, com.hadi.trainticketing.passenger.model.pojo.ticket.Ticket ticket, String reservationId) {
        this.launchTime = launchTime;
        this.arrivalTime = arrivalTime;
        this.reservationTicket = ticket;
        this.reservationId = reservationId;
    }

    public TicketModel(String launchTime, String arrivalTime, Ticket ticket, List<ArrayResult> arrayResults) {
        this.launchTime = launchTime;
        this.arrivalTime = arrivalTime;
        this.ticket = ticket;
        this.arrayResults = arrayResults;
    }

    public String getReservationId() {
        return reservationId;
    }

    public List<ArrayResult> getArrayResults() {
        return arrayResults;
    }

    public String getLaunchTime() {
        return launchTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
