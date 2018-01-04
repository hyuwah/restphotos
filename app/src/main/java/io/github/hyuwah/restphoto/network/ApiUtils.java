package io.github.hyuwah.restphoto.network;

import android.content.res.Resources;

import io.github.hyuwah.restphoto.R;
import io.github.hyuwah.restphoto.interfaces.PhotoService;

/**
 * Created by hyuwah on 03/01/18.
 *
 */

public class ApiUtils {

    public static final String BASE_URL = Resources.getSystem().getString(R.string.API_BASE_URL);

    public static PhotoService getPhotos() {
        return RetrofitClient.getClient(BASE_URL).create(PhotoService.class);
    }

    public static PhotoService getPhotos(int id) {
        return RetrofitClient.getClient(BASE_URL).create(PhotoService.class);
    }

    // TODO : Add another endpoint
}
