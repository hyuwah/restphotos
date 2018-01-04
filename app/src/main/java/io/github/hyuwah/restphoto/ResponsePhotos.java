package io.github.hyuwah.restphoto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hyuwah on 03/01/18.
 */

public class ResponsePhotos {
    @SerializedName("Photos")
    private List<Photo> photos;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isError(){
        return error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString(){
        return
                "ResponsePhotos{"+
                        "photos = '" + photos + '\'' +
                        ", error = '"+error+ '\'' +
                        ", message = '" + message + '\'' +
                        "}";
    }
}
