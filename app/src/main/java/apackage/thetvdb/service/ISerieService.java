package apackage.thetvdb.service;

import java.util.List;
import java.util.Map;

import apackage.thetvdb.entity.Actor;
import apackage.thetvdb.entity.Serie;
import apackage.thetvdb.entity.SerieDetails;
import apackage.thetvdb.entity.ServiceResponse;

/**
 * Created by gianniazizi on 16/12/2017.
 */

public interface ISerieService {
    void list(Map<String, String> token, String search, ResponseListener<List<Serie>> responseListener);
    void getActors(Map<String, String> token, int id, ResponseListener<List<Actor>> responseListener);
    void get(Map<String, String> token, int id, ResponseListener<SerieDetails> responseListener);
}
