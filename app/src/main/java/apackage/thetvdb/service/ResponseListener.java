package apackage.thetvdb.service;

import apackage.thetvdb.entity.ServiceResponse;

/**
 * Created by gianniazizi on 16/12/2017.
 */

public interface ResponseListener<T> {
    public void onSuccess(ServiceResponse<T> serviceResponse);
}
