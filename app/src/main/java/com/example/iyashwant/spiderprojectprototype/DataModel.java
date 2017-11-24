package com.example.iyashwant.spiderprojectprototype;

/**
 * Created by rakesh on 24/11/17.
 */

public class DataModel {

    String name;
    String noofapplicants;
    int id_;
    int image;

    public DataModel(String name, String version, int id_, int image) {
        this.name = name;
        this.noofapplicants = version;
        this.id_ = id_;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return noofapplicants;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}