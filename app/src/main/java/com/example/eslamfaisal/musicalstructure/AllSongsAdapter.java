package com.example.eslamfaisal.musicalstructure;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AllSongsAdapter extends ArrayAdapter<SongDetailes> {
    public AllSongsAdapter(Context context, ArrayList<SongDetailes> songs) {
        super(context, 0, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_all_songs, parent, false);
        }
        SongDetailes currentSong = getItem(position);
        ImageView songImage = (ImageView) listItemView.findViewById(R.id.list_all_songs_image);
        songImage.setImageResource(currentSong.getImageID());
        TextView songName = (TextView) listItemView.findViewById(R.id.list_all_songs_name);
        songName.setText(currentSong.getName());
        TextView songAlbum = (TextView) listItemView.findViewById(R.id.list_all_songs_album_name);
        songAlbum.setText(currentSong.getAlbumName());
        return listItemView;
    }
}
