package com.example.eslamfaisal.musicalstructure;

import java.util.ArrayList;

public class SongsListe {
    public static ArrayList<SongDetailes> getAmrDiabAlbum() {
        ArrayList<SongDetailes> amrdiab = new ArrayList<>();
        amrdiab.add(new SongDetailes(R.drawable.amrdiab, "Maak Alby", "Amntk",R.raw.maaka));
        amrdiab.add(new SongDetailes(R.drawable.asala, "Yamneen Alla", "ehlf",R.raw.sabny));
        amrdiab.add(new SongDetailes(R.drawable.tamerhosny, "Maak Alby", "La La",R.raw.maa));
        amrdiab.add(new SongDetailes(R.drawable.folder, "Quraan", "menshawy",R.raw.menshawy));
        amrdiab.add(new SongDetailes(R.drawable.amrdiab, "Maak Alby", "Maak Alby",R.raw.maaka));

        return amrdiab;
    }

    public static ArrayList<SongDetailes> getAsalaAlbum() {
        ArrayList<SongDetailes> asala = new ArrayList<>();
        asala.add(new SongDetailes(R.drawable.asala, "Yamneen Alla", "ehlf",R.raw.sabny));
        asala.add(new SongDetailes(R.drawable.folder, "Maak Alby", "Amntk",R.raw.menshawy));
         return asala;
    }

    public static ArrayList<SongDetailes> getaTamerAlbum() {
        ArrayList<SongDetailes> tamer = new ArrayList<>();
         tamer.add(new SongDetailes(R.drawable.folder, "Quraan", "menshawy",R.raw.menshawy));
        tamer.add(new SongDetailes(R.drawable.tamerhosny, "Maak Alby", "Maak Alby",R.raw.maaka));
        return tamer;
    }

    public static ArrayList<SongDetailes> getAll() {
        ArrayList<SongDetailes> all = new ArrayList<>();
        all.addAll(getAmrDiabAlbum());
        all.addAll(getAsalaAlbum());
        all.addAll(getaTamerAlbum());
        return all;
    }
}
