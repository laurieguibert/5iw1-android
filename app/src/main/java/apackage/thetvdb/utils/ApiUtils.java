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
    private static String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTM2MDY1NzgsImlkIjoiRVNHSSIsIm9yaWdfaWF0IjoxNTEzNTIwMTc4fQ.UpuWO6d09CwRRzfjEHa6dB1rSrVAlqm1QEO9nXi0zpL0UzP0-m0ayOEfWQ-AmHBpKzUbsDqGuVP8gYI2D07xMcO7SS-S7CAgIFh9YbO3gStd3UTOcjSmVmn2gy3HqgRvVtD2H-EfJS6Kyynd-BUlQpJmT_dAJKCrhmIcbjYK_saAyXWDiJ_hkEEWPj3J3IWZq-IrfoaZJAdJF2HhbMtLzIA8YPBrTjnd7ekIcLS7-L2D3uL5FzCFZnFmzN3G4UnUB0DYDwWWea3m4sY_r1scd4ifZR-QR8-pzxPI5GN3GANQb7D3ogRHswBBsYaTzBy9y9QjovRrvKJUUXcDOR9yhw";

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
