package com.hadi.trainticketing.passenger.home.model.pojo.stations;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class StationsResponse {

    @SerializedName("result")
    @Expose
    private List<Station> stationList = null;

    public List<Station> getStationList() {
        return stationList;
    }
}
