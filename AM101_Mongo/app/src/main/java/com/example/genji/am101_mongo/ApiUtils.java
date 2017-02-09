package com.example.genji.am101_mongo;

/**
 * Created by genji on 2/9/17.
 */

public class ApiUtils {
    public static final String BASE_URL = "http://192.168.1.2:8080";

    public static EmbeddedService getEmbeddedService() {
        return RetrofitClient.getClient(BASE_URL).create(EmbeddedService.class);
    }
}
