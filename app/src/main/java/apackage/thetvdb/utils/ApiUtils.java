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
    private static String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTM1MTY5NDMsImlkIjoiRVNHSSIsIm9yaWdfaWF0IjoxNTEzNDMwNTQzfQ.lRyOvA2aeNHEw6v7LxRWlQSEmycfbBbeCKPv9sh8saqVz7HwSzKrrHH7Z9jwseE6BgGU3s0XYvrmScKVQPz9u4DFKqWLULb8HtP9Q2mY9AzaES-3x6QBxygke05TmzRAn98GdUO4L8qHS7ajBpy9AUoO1rkV8eW6b1pBxdDiqMQp_Zx-qlLgsHLMSS8O5AKzVF3Df16INqp-MIrEl01AhrO7R_rMGs6y8PeeNBgukL4pq8wLo1ShnlgO8wZwNZfdxualMuqL17l316X1q6Cd4lAPMmBKB59DWE04K6vkWCtpNcsGxP3_NQtebrTubGf0-eHPUok02vcw-SDaDjlmkA";

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
