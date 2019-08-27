package com.deviget.reddit;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deviget.reddit.adapter.PostAdapter;
import com.deviget.reddit.api.APIService;
import com.deviget.reddit.entities.Child;
import com.deviget.reddit.entities.Post;
import com.deviget.reddit.utils.ApiUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APIService service;

    @BindView(R.id.list)
    RecyclerView list;

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
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    list.setHasFixedSize(true);
                    list.setLayoutManager(layoutManager);
                    List<Child> childList = response.body().data.children;

                    list.setAdapter(new PostAdapter(getApplicationContext(), childList));

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("ERROR", getString(R.string.error));
            }

        });
    }
}
