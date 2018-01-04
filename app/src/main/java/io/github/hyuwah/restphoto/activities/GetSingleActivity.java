package io.github.hyuwah.restphoto.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.hyuwah.restphoto.BuildConfig;
import io.github.hyuwah.restphoto.network.ApiUtils;
import io.github.hyuwah.restphoto.interfaces.PhotoService;
import io.github.hyuwah.restphoto.adapters.PhotosAdapter;
import io.github.hyuwah.restphoto.R;
import io.github.hyuwah.restphoto.models.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSingleActivity extends AppCompatActivity {

    private static final String TAG = GetSingleActivity.class.getSimpleName();

    private PhotoService apiService;
    ProgressBar pbSpinner;

    // RecyclerView
    private static List<Photo> photoList = new ArrayList<>();
    RecyclerView rvPhoto;
    private static PhotosAdapter photosAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_single);

        setupView();

        fetchPhoto();

    }

    /**
     * Setup pbSpinner and RecyclerView for Photos
     */
    private void setupView() {
        pbSpinner = findViewById(R.id.activity_get_single_spinner);
        pbSpinner.setVisibility(View.VISIBLE);

        // RecyclerView
        rvPhoto = findViewById(R.id.activity_get_single_recyclerview);
        photosAdapter = new PhotosAdapter(this, photoList);
        RecyclerView.LayoutManager photoLM = new LinearLayoutManager(getApplicationContext());
        rvPhoto.setLayoutManager(photoLM);
        rvPhoto.setItemAnimator(new DefaultItemAnimator());
        rvPhoto.setAdapter(photosAdapter);
    }

    /**
     * Fetch 1 photo with random id between 0 - 5000
     * from https://jsonplaceholder.typicode.com/photos/{id}
     */
    private void fetchPhoto() {
        photoList.clear();
        // Retrofit
        int randId = (int) (Math.random() * 5000);
        apiService = ApiUtils.getPhotos(randId);
        apiService.getPhoto(randId).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                pbSpinner.setVisibility(View.GONE);

                Toast.makeText(getBaseContext(), "Received 1 Data", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    Photo photo = response.body();

                    if (BuildConfig.DEBUG) Log.d(TAG, "onResponse: " + photo.toString());

                    photoList.add(photo);

                    photosAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                pbSpinner.setVisibility(View.GONE);

                Toast.makeText(getBaseContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
