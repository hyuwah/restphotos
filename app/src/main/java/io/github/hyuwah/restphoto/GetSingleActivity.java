package io.github.hyuwah.restphoto;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSingleActivity extends AppCompatActivity {

    private static Context context;
    private PhotoService mApiService;

    private ProgressBar spinner;

    // RecyclerView
    public static List<Photo> photoList = new ArrayList<>();
    public RecyclerView rvPhoto;
    public static PhotosAdapter photosAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_single);

        spinner = (ProgressBar) findViewById(R.id.agsSpinner);
        spinner.setVisibility(View.VISIBLE);

        // RecyclerView
        rvPhoto = (RecyclerView) findViewById(R.id.rvGetSingle);
        photosAdapter = new PhotosAdapter(this,photoList);
        RecyclerView.LayoutManager photoLM = new LinearLayoutManager(getApplicationContext());
        rvPhoto.setLayoutManager(photoLM);
        rvPhoto.setItemAnimator(new DefaultItemAnimator());
        //rvPhoto.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        rvPhoto.setAdapter(photosAdapter);
        photoList.clear();

        // Retrofit
        int randId =  (int)(Math.random()*5000);
        mApiService = ApiUtils.getPhotos(randId);
        mApiService.getPhoto(randId).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                spinner.setVisibility(View.GONE);

                Toast.makeText(getBaseContext(),"Received 1 Data", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){
                    Photo photo = response.body();

                    photoList.add(photo);

                    photosAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                spinner.setVisibility(View.GONE);

                Toast.makeText(getBaseContext(),"Failed to fetch data", Toast.LENGTH_SHORT).show();

            }
        });



    }


}
