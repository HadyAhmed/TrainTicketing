package com.hadi.trainticketing.passenger.home.model.pojo.ticket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket implements Parcelable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("classType")
    @Expose
    private Integer classType;
    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("to")
    @Expose
    private To to;
    @SerializedName("trian")
    @Expose
    private Trian trian;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getClassType() {
        return classType;
    }

    public From getFrom() {
        return from;
    }

    public To getTo() {
        return to;
    }

    public Trian getTrian() {
        return trian;
    }

    public Integer getV() {
        return v;
    }

    public static final Parcelable.Creator<Ticket> CREATOR = new Parcelable.Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel source) {
            return new Ticket(source);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

    public Ticket() {
    }

    protected Ticket(Parcel in) {
        this.id = in.readString();
        this.price = (Integer) in.readValue(Integer.class.getClassLoader());
        this.classType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.from = in.readParcelable(From.class.getClassLoader());
        this.to = in.readParcelable(To.class.getClassLoader());
        this.trian = in.readParcelable(Trian.class.getClassLoader());
        this.v = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeValue(this.price);
        dest.writeValue(this.classType);
        dest.writeParcelable(this.from, flags);
        dest.writeParcelable(this.to, flags);
        dest.writeParcelable(this.trian, flags);
        dest.writeValue(this.v);
    }
}
