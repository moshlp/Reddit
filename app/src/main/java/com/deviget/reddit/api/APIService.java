package com.deviget.reddit.api;


import com.deviget.reddit.entities.Post;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("/r/redditdev.json")
    Call<Post> getTop50(@Query("limit") int limit);
}
