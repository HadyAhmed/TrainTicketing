package com.hadi.trainticketing.passenger.home.model.pojo.reservation.response.seats;

import android.os.Parcel;
import android.os.Parcelable;

public class SeatRequest implements Parcelable {
    public static final Parcelable.Creator<SeatRequest> CREATOR = new Parcelable.Creator<SeatRequest>() {
        @Override
        public SeatRequest createFromParcel(Parcel source) {
            return new SeatRequest(source);
        }

        @Override
        public SeatRequest[] newArray(int size) {
            return new SeatRequest[size];
        }
    };
    private int classType;
    private String trainId;
    private String ticketId;
    private String[] reservationId;

    public SeatRequest(int classType, String trainId, String ticketId, String[] reservationId) {
        this.classType = classType;
        this.trainId = trainId;
        this.ticketId = ticketId;
        this.reservationId = reservationId;
    }

    protected SeatRequest(Parcel in) {
        this.classType = in.readInt();
        this.trainId = in.readString();
        this.ticketId = in.readString();
        this.reservationId = in.createStringArray();
    }

    public int getClassType() {
        return classType;
    }

    public void setClassType(int classType) {
        this.classType = classType;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String[] getReservationId() {
        return reservationId;
    }

    public void setReservationId(String[] reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.classType);
        dest.writeString(this.trainId);
        dest.writeString(this.ticketId);
        dest.writeStringArray(this.reservationId);
    }
}
