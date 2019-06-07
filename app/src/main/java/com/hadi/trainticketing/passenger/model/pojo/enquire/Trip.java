package com.hadi.trainticketing.passenger.model.pojo.enquire;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip implements Parcelable {

    public static final Parcelable.Creator<Trip> CREATOR = new Parcelable.Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel source) {
            return new Trip(source);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };
    @SerializedName("finished")
    @Expose
    private Boolean finished;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("train")
    @Expose
    private Train train;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Trip() {
    }

    protected Trip(Parcel in) {
        this.finished = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.id = in.readString();
        this.number = (Integer) in.readValue(Integer.class.getClassLoader());
        this.endTime = in.readString();
        this.train = in.readParcelable(Train.class.getClassLoader());
        this.v = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public Boolean getFinished() {
        return finished;
    }

    public String getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getEndTime() {
        return endTime;
    }

    public Train getTrain() {
        return train;
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
        dest.writeValue(this.finished);
        dest.writeString(this.id);
        dest.writeValue(this.number);
        dest.writeString(this.endTime);
        dest.writeParcelable(this.train, flags);
        dest.writeValue(this.v);
    }
}

