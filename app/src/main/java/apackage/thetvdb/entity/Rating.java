package apackage.thetvdb.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("ratingItemId")
    @Expose
    private Integer ratingItemId;
    @SerializedName("ratingType")
    @Expose
    private String ratingType;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRatingItemId() {
        return ratingItemId;
    }

    public void setRatingItemId(Integer ratingItemId) {
        this.ratingItemId = ratingItemId;
    }

    public String getRatingType() {
        return ratingType;
    }

    public void setRatingType(String ratingType) {
        this.ratingType = ratingType;
    }
}
