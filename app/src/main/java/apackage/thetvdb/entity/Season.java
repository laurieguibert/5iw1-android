package apackage.thetvdb.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gianniazizi on 15/01/2018.
 */

public class Season {

    @SerializedName("airedEpisodes")
    @Expose
    private String airedEpisodes;
    @SerializedName("airedSeasons")
    @Expose
    private List<String> airedSeasons = null;
    @SerializedName("dvdEpisodes")
    @Expose
    private String dvdEpisodes;
    @SerializedName("dvdSeasons")
    @Expose
    private List<String> dvdSeasons = null;

    public String getAiredEpisodes() {
        return airedEpisodes;
    }

    public void setAiredEpisodes(String airedEpisodes) {
        this.airedEpisodes = airedEpisodes;
    }

    public List<String> getAiredSeasons() {
        return airedSeasons;
    }

    public void setAiredSeasons(List<String> airedSeasons) {
        this.airedSeasons = airedSeasons;
    }

    public String getDvdEpisodes() {
        return dvdEpisodes;
    }

    public void setDvdEpisodes(String dvdEpisodes) {
        this.dvdEpisodes = dvdEpisodes;
    }

    public List<String> getDvdSeasons() {
        return dvdSeasons;
    }

    public void setDvdSeasons(List<String> dvdSeasons) {
        this.dvdSeasons = dvdSeasons;
    }

}
