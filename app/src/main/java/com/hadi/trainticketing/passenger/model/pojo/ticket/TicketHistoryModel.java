package com.hadi.trainticketing.passenger.model.pojo.ticket;

public class TicketHistoryModel {
    private boolean ticketValidation;
    private String launchTime;
    private String arrivalTime;
    private Ticket reservationTicket;
    private String reservationId;
    private String seatNumber;

    public TicketHistoryModel(boolean ticketValidation, String launchTime, String arrivalTime, Ticket reservationTicket, String reservationId, String seatNumber) {
        this.ticketValidation = ticketValidation;
        this.launchTime = launchTime;
        this.arrivalTime = arrivalTime;
        this.reservationTicket = reservationTicket;
        this.reservationId = reservationId;
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String isTicketValidation() {
        return ticketValidation ? "Valid" : "Not Valid";
    }

    public boolean isValidated() {
        return this.ticketValidation;
    }

    public void setTicketValidation(boolean ticketValidation) {
        this.ticketValidation = ticketValidation;
    }

    public String getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(String launchTime) {
        this.launchTime = launchTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Ticket getReservationTicket() {
        return reservationTicket;
    }

    public void setReservationTicket(Ticket reservationTicket) {
        this.reservationTicket = reservationTicket;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
}
