package apackage.thetvdb.utils;

import java.util.HashMap;
import java.util.Map;

import apackage.thetvdb.remote.RetrofitClient;
import apackage.thetvdb.service.IRFSerieService;

/**
 * Created by gianniazizi on 16/12/2017.
 */

public class ApiUtils {
    private static final String BASE_URL = "https://api.thetvdb.com";
    private static Map<String, String> headers = null;
    private static String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTM3NTg5ODYsImlkIjoiRVNHSSIsIm9yaWdfaWF0IjoxNTEzNjcyNTg2fQ.f9CHdJt-ah1GSAy0webDfBq6_zKHQhBCrMPMq3nZvAEwKN3cY9ThKpcLl_VqEvAEBjIDGw_-HwgZxH2Iea7vPY--HEmq2MND7HyxJJVvidLotBsZfQlWVHq-y2mvePwUocFTiN4apubVWmyT4NbZzSamfkYB-PJQSQ4ouaNeh_JfDV8F4w_ostVBSF9ndZ6YxfPddAXNASDLzqW8o7VOEg_DR5DUjriSJ7-4f06fpyYFj52_4X3uFiGS24soOn18ZS2GoYYk3UnKHRxFJxErCftAwPKPiZl8GI5G7aNqlZt3IqYgEcpz9JAVw10AVDiIUvnzsRLBa2QU7LmY7YKrjw";

    public static IRFSerieService getSerieService() {
        return RetrofitClient.getClient(BASE_URL).create(IRFSerieService.class);
    }

    public static Map<String, String> getHeaders() {
        // TODO checker si le token est toujours bon ici
        if(headers == null) {
            headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + token);
            headers.put("Content-Type", "application/json");
        }

        return headers;
    }
}
