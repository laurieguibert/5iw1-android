package apackage.thetvdb.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gianniazizi on 19/12/2017.
 */

public class SerieDetailsList {
    @SerializedName("data")
    private SerieDetails serie;

    public SerieDetails getSerie() {
        return serie;
    }

    public void setSerie(SerieDetails serie) {
        this.serie = serie;
    }
}
