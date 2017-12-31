package apackage.thetvdb.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gianniazizi on 31/12/2017.
 */

public class Favorite {
    @SerializedName("favorites")
    @Expose
    private List<String> favorites = null;

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }
}
