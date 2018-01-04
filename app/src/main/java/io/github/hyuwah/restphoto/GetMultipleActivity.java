package io.github.hyuwah.restphoto;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMultipleActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_get_multiple);

        spinner = (ProgressBar) findViewById(R.id.agmSpinner);
        spinner.setVisibility(View.VISIBLE);

        // RecyclerView
        rvPhoto = (RecyclerView) findViewById(R.id.rvGetMultiple);
        photosAdapter = new PhotosAdapter(this,photoList);

        // Linear 1 column
        RecyclerView.LayoutManager photoLM = new LinearLayoutManager(getApplicationContext());

        // 2 Column
        //RecyclerView.LayoutManager photoLM = new GridLayoutManager(this, 2);
        rvPhoto.setLayoutManager(photoLM);
        rvPhoto.setItemAnimator(new DefaultItemAnimator());
        //rvPhoto.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        rvPhoto.setAdapter(photosAdapter);

        photoList.clear();
        // Retrofit
        mApiService = ApiUtils.getPhotos();

        mApiService.getPhotos().enqueue(new Callback<List<Photo>>() {

            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                spinner.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), "Received "+ response.body().size() +" Data", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    List<Photo> lp = response.body();


                    for (Photo photoItem : lp
                            ) {
//                        Log.i("Debug", "onResponse: "+photoItem.toString());
                        photoList.add(photoItem);
                    }

                    photosAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                spinner.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
