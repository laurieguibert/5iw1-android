package apackage.thetvdb.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by gianniazizi on 22/12/2017.
 */

public class Token extends RealmObject {
    @SerializedName("token")
    @Expose
    private String token;
    private Date date;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Token() {}
}
