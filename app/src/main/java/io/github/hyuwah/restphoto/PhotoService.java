package io.github.hyuwah.restphoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hyuwah on 03/01/18.
 */

public interface PhotoService {
    @GET("photos")
    Call<List<Photo>> getPhotos();

    @GET("photos/{id}")
    Call<Photo> getPhoto(@Path("id") int id);

    // TODO: Add another endpoint
}
