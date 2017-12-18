package apackage.thetvdb.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gianniazizi on 18/12/2017.
 */

public class ActorList {
    @SerializedName("data")
    private List<Actor> actors;

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
