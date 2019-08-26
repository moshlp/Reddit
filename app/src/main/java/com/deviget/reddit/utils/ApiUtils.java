package com.deviget.reddit.utils;


import com.deviget.reddit.api.APIService;
import com.deviget.reddit.api.RetrofitClient;

public class ApiUtils {
    private ApiUtils() {}

    public static  final int limit = 50;

    public static final String BASE_URL = "https://www.reddit.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
