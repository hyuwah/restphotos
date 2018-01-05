package io.github.hyuwah.restphoto.network;

import io.github.hyuwah.restphoto.interfaces.IPhotoService;

/**
 * Created by hyuwah on 03/01/18.
 *
 */

public class ApiUtils {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static IPhotoService getPhotos() {
        return RetrofitClient.getClient(BASE_URL).create(IPhotoService.class);
    }

    public static IPhotoService getPhotos(int id) {
        return RetrofitClient.getClient(BASE_URL).create(IPhotoService.class);
    }

    // TODO : Add another endpoint
}
