package apackage.thetvdb.service;

import java.util.List;
import java.util.Map;

import apackage.thetvdb.entity.Serie;
import apackage.thetvdb.entity.ServiceResponse;

/**
 * Created by gianniazizi on 16/12/2017.
 */

public interface ISerieService {
    void list(Map<String, String> map, String search, ResponseListener<List<Serie>> responseListener);
}