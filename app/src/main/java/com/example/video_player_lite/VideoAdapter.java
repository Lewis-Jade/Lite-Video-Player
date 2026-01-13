package com.example.video_player_lite;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private  final List<Uri> videoUris;
    private  String videoName;
    private Context context;
    public  VideoAdapter( List<Uri> videoUris, Context context){
        this.videoUris = videoUris;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);


        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Uri videoUri = videoUris.get(position);
        String videoName = videoUri.getLastPathSegment();
        holder.textName.setText(videoName);

        // --- MediaMetadataRetriever for real duration and thumbnail ---
        try {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(context, videoUri);

            // Duration
            String dur = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            long durMs = Long.parseLong(dur);
            long min = durMs / 1000 / 60;
            long sec = (durMs / 1000) % 60;
            holder.txtDuration.setText(String.format("%02d:%02d", min, sec));

            // Thumbnail
            Bitmap thumb = retriever.getFrameAtTime();
            holder.imgThumbnail.setImageBitmap(thumb);

            retriever.release();
        } catch (Exception e) {
            holder.txtDuration.setText("--:--");
            holder.imgThumbnail.setImageResource(R.drawable.media); // fallback image
            e.printStackTrace();
        }

        // Click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoPlayBackActivity.class);
            intent.putExtra("videoUri", videoUri);
            context.startActivity(intent);
        });


    }


    @Override
    public int getItemCount() {
        return videoUris.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView textName, txtDuration;
        ImageView imgThumbnail;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.txtTitle);
            txtDuration = itemView.findViewById(R.id.txtDuration);
            imgThumbnail = itemView.findViewById(R.id.imgThumb);
        }
    }
}
