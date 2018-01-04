package io.github.hyuwah.restphoto.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hyuwah on 03/01/18.
 * Photo model for photo object
 */

public class Photo {
    @SerializedName("albumId")
    private int _albumId;

    @SerializedName("id")
    private int _id;

    @SerializedName("title")
    private String _title;

    @SerializedName("url")
    private String _url;

    @SerializedName("thumbnailUrl")
    private String _thumbnailUrl;

    public int get_albumId() {
        return _albumId;
    }

    public int get_id() {
        return _id;
    }

    public String get_title() {
        return _title;
    }

    public String get_url() {
        return _url;
    }

    public String get_thumbnailUrl() {
        return _thumbnailUrl;
    }

    @Override
    public String toString(){
        return
                "Photo{"+
                        "albumId = " + _albumId + '\'' +
                        ", id = " + _id + '\'' +
                        ", title = " + _title + '\'' +
                        ", url = " + _url + '\'' +
                        ", thumbnailUrl = " + _thumbnailUrl + '\'' +
                        "}";


    }
}
