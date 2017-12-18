package apackage.thetvdb.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Actor {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageAdded")
    @Expose
    private String imageAdded;
    @SerializedName("imageAuthor")
    @Expose
    private Integer imageAuthor;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("seriesId")
    @Expose
    private Integer seriesId;
    @SerializedName("sortOrder")
    @Expose
    private Integer sortOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageAdded() {
        return imageAdded;
    }

    public void setImageAdded(String imageAdded) {
        this.imageAdded = imageAdded;
    }

    public Integer getImageAuthor() {
        return imageAuthor;
    }

    public void setImageAuthor(Integer imageAuthor) {
        this.imageAuthor = imageAuthor;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
