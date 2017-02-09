package com.example.genji.am101_mongo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by genji on 2/9/17.
 */

public class Embedded {
    @SerializedName("_embedded")
    @Expose
    private List<Car> embedded = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("_returned")
    @Expose
    private Integer returned;

    public List<Car> getEmbedded() {
        return embedded;
    }

    public void setEmbedded(List<Car> embedded) {
        this.embedded = embedded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getReturned() {
        return returned;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }
}
