package com.example.mytunes.model;

import com.example.mytunes.controller.HelloController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class SongDAOImpl implements SongDAO
{

    private Connection con; // forbindelsen til databasen
    public SongDAOImpl(){
        try
        {
            // Opretter forbindelse til vores database

            con = DriverManager.getConnection("jdbc:sqlserver://MSI-MAGNUS;database=MyTunesDB;userName=sa;password=12345;encrypt=true;trustServerCertificate=true");

        } catch (SQLException e){
            System.err.println("can not create connection" + e.getMessage());
        }

        System.out.println("  ");
    }


    // Metode der tilføjer en sang. Informationerne omkring sangen der bliver
    // indskrevet kommer an på brugerens indput.
    public void tilføjSang(Songs song)
    {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO Songs VALUES(?,?,?,?,?,?);");
            //ps.setInt(1, HelloController.sanglisteLængde());
            ps.setString(2, song.getTitle());
            ps.setString(3,song.getArtist());
            ps.setString(4,song.getCategory());
            ps.setInt(5, song.getYear());
            ps.setFloat(6, song.getDuration());
            ps.executeUpdate();
      } catch (SQLException e)
        {
            System.err.println("Kunne ikke tilføje sang" + e.getMessage());
        }
    }

    public List<Songs> getAlleSange(){
            List<Songs> fåAlleSange= new ArrayList();
            try{
                PreparedStatement ps = con.prepareStatement("SELECT * FROM Songs");
                ResultSet rs = ps.executeQuery();

                Songs song;
                while(rs.next()){
                   //int id = rs.getInt(1);
                    String title = rs.getString(2);
                    String artist = rs.getString(3);
                    String category = rs.getString(4);
                    int year = rs.getInt(5);
                    Float duration = rs.getFloat(6);

                    song = new Songs(title,artist,category,year,duration);
                    fåAlleSange.add(song);
                }

            } catch (SQLException e){
                System.err.println("can not access records" + e.getMessage());
            }
            return fåAlleSange;
        }


    // Metode der søger efter sange i listen over sange.
    // Man kan søge efter sange, artister, kategorier, år og afspilningstiden.
    public List<Songs> getSøgSange(String query){
        List<Songs> søgteSange= new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Songs WHERE title LIKE ? OR artist LIKE ? OR category LIKE ? OR year LIKE ? OR duration LIKE;");
            ps.setString(2, query);
            ps.setString(3, query);
            ps.setString(4, query);
            ps.setString(5, query);
            ps.setString(6, query);
            ResultSet rs = ps.executeQuery();

            Songs song;
            while(rs.next())
            {
                //int id
                String title = rs.getString(2);
                String artist = rs.getString(3);
                String category = rs.getString(4);
                int year = rs.getInt(5);
                float duration = rs.getFloat(6);

                song = new Songs(title, artist, category, year, duration);
                søgteSange.add(song);
            }
        }catch (SQLException e){
            System.err.println("Kunne ikke finde sangen, " + e.getMessage());
        }
        return søgteSange;
    }

}