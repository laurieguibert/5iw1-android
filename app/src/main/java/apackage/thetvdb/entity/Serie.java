package apackage.thetvdb.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gianniazizi on 16/12/2017.
 */

public class Serie implements Parcelable {
    @SerializedName("aliases")
    @Expose
    private List<String> aliases = null;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("firstAired")
    @Expose
    private String firstAired;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("network")
    @Expose
    private String network;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("seriesName")
    @Expose
    private String seriesName;
    @SerializedName("status")
    @Expose
    private String status;

    public Serie(int id, String overview, String status, String seriesName, String banner, String firstAired) {
        this.id = id;
        this.overview = overview;
        this.status = status;
        this.seriesName = seriesName;
        this.banner = banner;
        this.firstAired = firstAired;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(banner);
        parcel.writeString(firstAired);
        parcel.writeInt(id);
        parcel.writeString(overview);
        parcel.writeString(seriesName);
        parcel.writeString(status);
    }

    public Serie(Parcel parcel) {
        this.banner = parcel.readString();
        this.firstAired = parcel.readString();
        this.id = parcel.readInt();
        this.overview = parcel.readString();
        this.seriesName = parcel.readString();
        this.status = parcel.readString();
    }

    public static final Parcelable.Creator<Serie> CREATOR = new Parcelable.Creator<Serie>() {

        public Serie createFromParcel(Parcel in) {
            return new Serie(in);
        }

        public Serie[] newArray(int size) {
            return new Serie[size];
        }
    };
}
