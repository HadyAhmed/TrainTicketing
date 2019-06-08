package com.hadi.trainticketing.passenger.home.model.pojo.enquire;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class From implements Parcelable {

    public static final Parcelable.Creator<From> CREATOR = new Parcelable.Creator<From>() {
        @Override
        public From createFromParcel(Parcel source) {
            return new From(source);
        }

        @Override
        public From[] newArray(int size) {
            return new From[size];
        }
    };
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public From() {
    }

    protected From(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.v = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.v);
    }
}

