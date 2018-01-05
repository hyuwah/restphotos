package io.github.hyuwah.restphoto.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.hyuwah.restphoto.R;
import io.github.hyuwah.restphoto.models.Photo;

/**
 * Created by hyuwah on 03/01/18.
 * Adapter for Photo
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private List<Photo> photoList;
    private Context context;

    public PhotosAdapter(Context context, List<Photo> photoList) {
        this.photoList = photoList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo currentPhoto = photoList.get(position);
        holder.tvAlbumId.setText("Album Id : " + Integer.toString(currentPhoto.get_albumId()));
        holder.tvId.setText("Id : " + Integer.toString(currentPhoto.get_id()));
        holder.tvTitle.setText(currentPhoto.get_title());

        // Rendering with Picasso
        if (!TextUtils.isEmpty(currentPhoto.get_thumbnailUrl())) {
            Picasso.with(context)
                    .load(currentPhoto.get_thumbnailUrl())
                    .error(R.drawable.img_error)
                    .placeholder(R.drawable.img_placeholder)
                    .fit()
                    .into(holder.ivThumbnail);
        }
    }

    @Override
    public int getItemCount() {

        return (null != photoList ? photoList.size() : 0);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        TextView tvAlbumId, tvId, tvTitle;

        public ViewHolder(View view) {
            super(view);
            this.tvAlbumId = view.findViewById(R.id.item_photo_txt_album_id);
            this.tvId = view.findViewById(R.id.item_photo_txt_id);
            this.tvTitle = view.findViewById(R.id.item_photo_txt_title);
            this.ivThumbnail = view.findViewById(R.id.item_photo_image_thumbnail);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(itemView);
    }


}
