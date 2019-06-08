package com.hadi.trainticketing.passenger.home.model.pojo.ticket;

import android.os.Parcel;
import android.os.Parcelable;

public class TicketHistoryModel implements Parcelable {
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
        return !ticketValidation ? "Valid" : "Not Valid";
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

    public static final Parcelable.Creator<TicketHistoryModel> CREATOR = new Parcelable.Creator<TicketHistoryModel>() {
        @Override
        public TicketHistoryModel createFromParcel(Parcel source) {
            return new TicketHistoryModel(source);
        }

        @Override
        public TicketHistoryModel[] newArray(int size) {
            return new TicketHistoryModel[size];
        }
    };

    protected TicketHistoryModel(Parcel in) {
        this.ticketValidation = in.readByte() != 0;
        this.launchTime = in.readString();
        this.arrivalTime = in.readString();
        this.reservationTicket = in.readParcelable(Ticket.class.getClassLoader());
        this.reservationId = in.readString();
        this.seatNumber = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.ticketValidation ? (byte) 1 : (byte) 0);
        dest.writeString(this.launchTime);
        dest.writeString(this.arrivalTime);
        dest.writeParcelable(this.reservationTicket, flags);
        dest.writeString(this.reservationId);
        dest.writeString(this.seatNumber);
    }
}
