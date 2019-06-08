package com.hadi.trainticketing.passenger.home.model.pojo.ticket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trian implements Parcelable {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("days")
    @Expose
    private List<String> days = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Boolean getStatus() {
        return status;
    }

    public List<String> getDays() {
        return days;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getV() {
        return v;
    }

    public static final Parcelable.Creator<Trian> CREATOR = new Parcelable.Creator<Trian>() {
        @Override
        public Trian createFromParcel(Parcel source) {
            return new Trian(source);
        }

        @Override
        public Trian[] newArray(int size) {
            return new Trian[size];
        }
    };

    public Trian() {
    }

    protected Trian(Parcel in) {
        this.status = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.days = in.createStringArrayList();
        this.id = in.readString();
        this.name = in.readString();
        this.number = (Integer) in.readValue(Integer.class.getClassLoader());
        this.v = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.status);
        dest.writeStringList(this.days);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.number);
        dest.writeValue(this.v);
    }
}
