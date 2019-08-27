package com.deviget.reddit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.deviget.reddit.adapter.PostAdapter;
import com.deviget.reddit.api.APIService;
import com.deviget.reddit.entities.Child;
import com.deviget.reddit.entities.Data_;
import com.deviget.reddit.entities.Post;
import com.deviget.reddit.utils.ApiUtils;
import com.squareup.picasso.Picasso;

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

    @BindView(R.id.author)
    TextView author;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.btnsaveImage)
    Button btnsaveImage;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //butterknife
        ButterKnife.bind(this);

        service = ApiUtils.getAPIService();

        getTop50Reddit();

        swipeRefreshLayout.setOnRefreshListener(this::getTop50Reddit);

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
                    list.setAdapter(new PostAdapter(getApplicationContext(), childList, item -> {
                        updateUI(item);
                    }));
                    if (swipeRefreshLayout.isRefreshing()) {
                        //hide swipe circle
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("ERROR", getString(R.string.error));
            }

        });
    }

    private void updateUI(Data_ data) {
        author.setText(data.getAuthor());
        description.setText(data.title);
        if (!data.getThumbnail().isEmpty()) {
            image.setVisibility(View.VISIBLE);
            Picasso.get().load(data.getThumbnail()).into(image);
            btnsaveImage.setVisibility(View.VISIBLE);
        }
    }
}
