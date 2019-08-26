package com.deviget.reddit;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.deviget.reddit.api.APIService;
import com.deviget.reddit.entities.Post;
import com.deviget.reddit.utils.ApiUtils;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APIService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //butterknife
        ButterKnife.bind(this);

        service = ApiUtils.getAPIService();

        getTop50Reddit();

    }

    private void getTop50Reddit() {
        service.getTop50(ApiUtils.limit).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("ERROR", getString(R.string.error));
            }

        });
    }
}
