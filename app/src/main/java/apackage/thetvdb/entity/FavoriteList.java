package apackage.thetvdb.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gianniazizi on 31/12/2017.
 */

public class FavoriteList {

    @SerializedName("data")
    @Expose
    private Favorite data;

    public Favorite getData() {
        return data;
    }

    public void setData(Favorite data) {
        this.data = data;
    }
}
