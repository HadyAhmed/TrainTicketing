package com.hadi.trainticketing.passenger.home.model.pojo.enquire;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrayResult implements Parcelable {

    public static final Parcelable.Creator<ArrayResult> CREATOR = new Parcelable.Creator<ArrayResult>() {
        @Override
        public ArrayResult createFromParcel(Parcel source) {
            return new ArrayResult(source);
        }

        @Override
        public ArrayResult[] newArray(int size) {
            return new ArrayResult[size];
        }
    };
    @SerializedName("arrived")
    @Expose
    private Boolean arrived;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("trip")
    @Expose
    private Trip trip;
    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("to")
    @Expose
    private To to;
    @SerializedName("arrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("sort")
    @Expose
    private Integer sort;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public ArrayResult() {
    }

    protected ArrayResult(Parcel in) {
        this.arrived = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.id = in.readString();
        this.trip = in.readParcelable(Trip.class.getClassLoader());
        this.from = in.readParcelable(From.class.getClassLoader());
        this.to = in.readParcelable(To.class.getClassLoader());
        this.arrivalTime = in.readString();
        this.sort = (Integer) in.readValue(Integer.class.getClassLoader());
        this.v = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public Boolean getArrived() {
        return arrived;
    }

    public String getId() {
        return id;
    }

    public Trip getTrip() {
        return trip;
    }

    public From getFrom() {
        return from;
    }

    public To getTo() {
        return to;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public Integer getSort() {
        return sort;
    }

    public Integer getV() {
        return v;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.arrived);
        dest.writeString(this.id);
        dest.writeParcelable(this.trip, flags);
        dest.writeParcelable(this.from, flags);
        dest.writeParcelable(this.to, flags);
        dest.writeString(this.arrivalTime);
        dest.writeValue(this.sort);
        dest.writeValue(this.v);
    }
}

