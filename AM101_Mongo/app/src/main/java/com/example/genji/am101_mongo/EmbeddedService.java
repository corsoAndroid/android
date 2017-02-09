package com.example.genji.am101_mongo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by genji on 2/9/17.
 */

public interface EmbeddedService {
    @GET("/testdb/cars")
    Call<Embedded> getAnswers();
}
