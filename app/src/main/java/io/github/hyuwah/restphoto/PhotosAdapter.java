package io.github.hyuwah.restphoto;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



import java.util.List;

/**
 * Created by hyuwah on 03/01/18.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private List<Photo> mPhoto;
    private Context mContext;



    public class ViewHolder extends RecyclerView.ViewHolder {
         protected ImageView imgThumbnail;
         TextView albumId, id, title;

        public ViewHolder(View view) {
            super(view);
            this.albumId = (TextView) view.findViewById(R.id.tvAlbumId);
            this.id = (TextView) view.findViewById(R.id.tvId);
            this.title = (TextView) view.findViewById(R.id.tvTitle);
            this.imgThumbnail = (ImageView) view.findViewById(R.id.imgThumb);

        }
    }

    public PhotosAdapter(Context context, List<Photo> photoList) {
        this.mPhoto = photoList;
        this.mContext = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_item, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo photo = mPhoto.get(position);
        holder.albumId.setText("Album Id : " + Integer.toString(photo.get_albumId()));
        holder.id.setText("Id : " + Integer.toString(photo.get_id()));
        holder.title.setText("Title : " + photo.get_title());

        // Rendering with Picasso
        if(!TextUtils.isEmpty(photo.get_thumbnailUrl())){
            Picasso.with(mContext).load(photo.get_thumbnailUrl())
                    .error(R.drawable.img_error)
                    .placeholder(R.drawable.img_placeholder)
                    .fit()
                    .into(holder.imgThumbnail);

        }

    }

    @Override
    public int getItemCount() {
        return (null != mPhoto ? mPhoto.size() : 0);
    }
}
