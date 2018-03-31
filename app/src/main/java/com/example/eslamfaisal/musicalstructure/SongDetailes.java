package com.example.eslamfaisal.musicalstructure;

import java.io.Serializable;

public class SongDetailes implements Serializable {
    private int imageID;
    private String songName;
    private String albumName;
    private int songId;

    public SongDetailes(int mImageId, String mSongName, String nAlbumName , int songId) {
        this.imageID = mImageId;
        this.songName = mSongName;
        this.albumName = nAlbumName;
        this.songId = songId;
    }

    public int getImageID() {
        return imageID;
    }

    public String getName() {
        return songName;
    }

    public String getAlbumName() {
        return albumName;
    }
    public int getSongId(){
        return songId;
    }

    @Override
    public String toString() {
        return "SongDetailes{" +
                "imageID=" + imageID +
                ", songName='" + songName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", songId=" + songId +
                '}';
    }
}
