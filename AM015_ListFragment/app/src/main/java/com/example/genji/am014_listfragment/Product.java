package com.example.genji.am014_listfragment;

/**
 * Created by genji on 2/7/16.
 */
public class Product {

    private String name;
    private String description;

    public Product(String name, String description){
        this.name = name;
        this.description = description;
    }

    String getName(){
        return name;
    }

    String getDescription(){
        return description;
    }
}
