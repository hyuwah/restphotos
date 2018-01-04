package io.github.hyuwah.restphoto.network;

import io.github.hyuwah.restphoto.interfaces.PhotoService;

/**
 * Created by hyuwah on 03/01/18.
 *
 */

public class ApiUtils {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static PhotoService getPhotos() {
        return RetrofitClient.getClient(BASE_URL).create(PhotoService.class);
    }

    public static PhotoService getPhotos(int id) {
        return RetrofitClient.getClient(BASE_URL).create(PhotoService.class);
    }

    // TODO : Add another endpoint
}
