package com.example.mytunes.model;

import java.util.List;

public interface SongDAO {

    public void tilføjSang(Songs sang); // Create song

    public List<Songs> getSøgSange(String query); // Read searched songs

    public List<Songs> getAlleSange(); // Get all songs

}
