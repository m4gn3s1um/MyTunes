package com.example.mytunes.model;

import java.util.List;

public interface PlaylistDAO {

    public void opretPlaylist(Playlist playlist); // Create playlist

    public List<Playlist> getAllPlaylist();
}