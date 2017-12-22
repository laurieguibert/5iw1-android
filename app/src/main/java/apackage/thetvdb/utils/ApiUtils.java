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
    private static String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTM5NTMzMTEsImlkIjoiRVNHSSIsIm9yaWdfaWF0IjoxNTEzODY2OTExfQ.N_YcLPNRQW_NjrtzCBBHTX8vFrVRrCKJfBB6Jl_vp8GSL7yf3_amGSZUXvsO5NELRvoRtGZ3Md8X1JJcQwYUi8eURkhwxoP7i6fleH4E1SB5ryRwcUXGY1KqjSIbvz1WM13kgbQNORh3ch1Szrb_BKOT_pNAmvihrBBpxEI1JV7wtIitsrR9EjPWJM7aG813JhMxVvyZpB7wjNm52ywu1z1i35YD_ZNrUd0Hmh6ZN16hQtEyrF9nejEYObvSNoY2zymlZhU2Ed56x68Az2cTg5MQ5-gMqrxrd58P6pW8L4eVVmbj0ZgfHsR44XG39IMy00sT5fZOuUe0W5A0j6DPoQ";

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
