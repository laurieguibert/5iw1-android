package apackage.thetvdb.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gianniazizi on 19/12/2017.
 */

public class SerieDetails {
    @SerializedName("added")
    @Expose
    private String added;
    @SerializedName("airsDayOfWeek")
    @Expose
    private String airsDayOfWeek;
    @SerializedName("airsTime")
    @Expose
    private String airsTime;
    @SerializedName("aliases")
    @Expose
    private List<String> aliases = null;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("firstAired")
    @Expose
    private String firstAired;
    @SerializedName("genre")
    @Expose
    private List<String> genre = null;
    @SerializedName("imdbId")
    @Expose
    private String imdbId;
    @Expose
    private String networkId;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("runtime")
    @Expose
    private String runtime;
    @SerializedName("seriesName")
    @Expose
    private String seriesName;
    @SerializedName("siteRating")
    @Expose
    private String siteRating;
    @SerializedName("siteRatingCount")
    @Expose
    private Integer siteRatingCount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("zap2itId")
    @Expose
    private String zap2itId;
    @SerializedName("id")
    @Expose
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getAirsDayOfWeek() {
        return airsDayOfWeek;
    }

    public void setAirsDayOfWeek(String airsDayOfWeek) {
        this.airsDayOfWeek = airsDayOfWeek;
    }

    public String getAirsTime() {
        return airsTime;
    }

    public void setAirsTime(String airsTime) {
        this.airsTime = airsTime;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSiteRating() {
        return siteRating;
    }

    public void setSiteRating(String siteRating) {
        this.siteRating = siteRating;
    }

    public Integer getSiteRatingCount() {
        return siteRatingCount;
    }

    public void setSiteRatingCount(Integer siteRatingCount) {
        this.siteRatingCount = siteRatingCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getZap2itId() {
        return zap2itId;
    }

    public void setZap2itId(String zap2itId) {
        this.zap2itId = zap2itId;
    }
}
