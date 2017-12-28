package apackage.thetvdb.service;

import java.util.List;
import java.util.Map;

import apackage.thetvdb.entity.Rating;
import apackage.thetvdb.entity.RatingList;


public interface IUserService {
    void evaluate(Map<String, String> token, String itemType, int itemId, int itemRating);
    void getRatings(Map<String, String> token, ResponseListener<List<Rating>> responseListener);
}