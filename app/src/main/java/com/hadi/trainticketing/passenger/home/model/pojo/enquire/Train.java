package com.hadi.trainticketing.passenger.home.model.pojo.enquire;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Train implements Parcelable {

    public static final Parcelable.Creator<Train> CREATOR = new Parcelable.Creator<Train>() {
        @Override
        public Train createFromParcel(Parcel source) {
            return new Train(source);
        }

        @Override
        public Train[] newArray(int size) {
            return new Train[size];
        }
    };
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

    public Train() {
    }

    protected Train(Parcel in) {
        this.status = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.days = in.createStringArrayList();
        this.id = in.readString();
        this.name = in.readString();
        this.number = (Integer) in.readValue(Integer.class.getClassLoader());
        this.v = (Integer) in.readValue(Integer.class.getClassLoader());
    }

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

