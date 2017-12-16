package apackage.thetvdb.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gianniazizi on 16/12/2017.
 */

public class SerieList {
    @SerializedName("data")
    private List<Serie> series;

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }
}
