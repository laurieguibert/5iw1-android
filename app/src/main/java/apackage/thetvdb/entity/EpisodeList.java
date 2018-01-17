package apackage.thetvdb.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gianniazizi on 15/01/2018.
 */

public class EpisodeList {
    @SerializedName("data")
    private List<Episode> episodeList;

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }
}
