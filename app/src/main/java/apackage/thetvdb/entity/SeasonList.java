package apackage.thetvdb.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gianniazizi on 15/01/2018.
 */

public class SeasonList {
    @SerializedName("data")
    private Season season;

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
