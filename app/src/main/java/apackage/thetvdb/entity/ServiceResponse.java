package apackage.thetvdb.entity;

/**
 * Created by gianniazizi on 16/12/2017.
 */

public class ServiceResponse<T> {
    private T data;

    public ServiceResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
