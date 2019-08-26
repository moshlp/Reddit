package com.deviget.reddit.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Data_ implements Serializable {


    @SerializedName("author_fullname")
    @Expose
    public String authorFullname;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;

    @SerializedName("created")
    @Expose
    public long created;

    @SerializedName("view_count")
    @Expose
    public Object viewCount;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("author")
    @Expose
    public String author;

    @SerializedName("num_comments")
    @Expose
    public Integer numComments;

    @SerializedName("permalink")
    @Expose
    public String permalink;

    @SerializedName("url")
    @Expose
    public String url;


}
