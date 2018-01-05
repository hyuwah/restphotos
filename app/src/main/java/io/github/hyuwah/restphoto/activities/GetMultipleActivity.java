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
import io.github.hyuwah.restphoto.interfaces.IPhotoService;
import io.github.hyuwah.restphoto.adapters.PhotosAdapter;
import io.github.hyuwah.restphoto.R;
import io.github.hyuwah.restphoto.models.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMultipleActivity extends AppCompatActivity {

    private static final String TAG = GetMultipleActivity.class.getSimpleName();

    private IPhotoService apiService;
    ProgressBar pbSpinner;

    // RecyclerView
    private static List<Photo> photoList = new ArrayList<>();
    RecyclerView rvPhoto;
    private static PhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_multiple);
        setupView();
        fetchPhotos();
    }

    /**
     * Setup spinner and RecyclerView for Photos
     */
    private void setupView() {
        pbSpinner = findViewById(R.id.activity_get_multiple_spinner);
        pbSpinner.setVisibility(View.VISIBLE);

        // RecyclerView
        rvPhoto = findViewById(R.id.activity_get_multiple_recyclerview);
        photosAdapter = new PhotosAdapter(this, photoList);

        // Linear 1 column
        RecyclerView.LayoutManager photoLM = new LinearLayoutManager(getApplicationContext());
        // 2 Column
        //RecyclerView.LayoutManager photoLM = new GridLayoutManager(this, 2);

        rvPhoto.setLayoutManager(photoLM);
        rvPhoto.setItemAnimator(new DefaultItemAnimator());
        rvPhoto.setAdapter(photosAdapter);
    }

    /**
     * Fetch all photo (5000)
     * from https://jsonplaceholder.typicode.com/photos/
     */
    private void fetchPhotos() {
        photoList.clear();
        // Retrofit
        apiService = ApiUtils.getPhotos();
        apiService.getPhotos().enqueue(new Callback<List<Photo>>() {

            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                pbSpinner.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), "Received " + response.body().size() + " Data", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    List<Photo> lp = response.body();
                    for (Photo photoItem : lp) {
                        if (BuildConfig.DEBUG) Log.d(TAG, "onResponse: " + photoItem.toString());
                        photoList.add(photoItem);
                    }
                    photosAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                pbSpinner.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
