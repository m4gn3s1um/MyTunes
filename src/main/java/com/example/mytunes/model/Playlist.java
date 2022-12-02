package com.example.mytunes.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist
{
    private String listName;
    private List<Songs> sange = new ArrayList<Songs>();

    public Playlist(String listName)
    {
        this.listName = listName;
    }


    // Metode der tilføjer sang til en liste
    public void tilføjSang(Songs s)
    {
        sange.add(s);
    }



    public String toString()
    {
        return listName;
    }



    public String getListName() {
        return listName;
    }
}