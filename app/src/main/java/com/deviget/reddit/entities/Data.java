package com.deviget.reddit.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("modhash")
    @Expose
    public String modhash;
    @SerializedName("dist")
    @Expose
    public Integer dist;
    @SerializedName("children")
    @Expose
    public List<Child> children = null;
}

