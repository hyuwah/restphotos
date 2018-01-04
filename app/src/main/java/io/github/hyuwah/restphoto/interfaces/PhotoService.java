package io.github.hyuwah.restphoto.interfaces;

import java.util.List;

import io.github.hyuwah.restphoto.models.Photo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hyuwah on 03/01/18.
 * API interface for retrofit 2
 * see https://jsonplaceholder.typicode.com/ for Documentation
 */

public interface PhotoService {
    @GET("photos")
    Call<List<Photo>> getPhotos();

    @GET("photos/{id}")
    Call<Photo> getPhoto(@Path("id") int id);

    // TODO: Add another endpoint
}
