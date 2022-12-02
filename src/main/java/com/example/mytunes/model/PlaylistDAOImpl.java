package com.example.mytunes.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class PlaylistDAOImpl implements PlaylistDAO {

    private Connection con; // forbindelsen til databasen



    public PlaylistDAOImpl() {
        try {
            // Connector til vores database
            con = DriverManager.getConnection("jdbc:sqlserver://MSI-MAGNUS;database=MyTunesDB;userName=sa;password=12345;encrypt=true;trustServerCertificate=true");

        } catch (SQLException e) {
            System.err.println("Could not create connection  " + e.getMessage());
        }

        System.out.println("Connected");
    }

    // Metode der opretter en playliste med navnet en bruger har indtastet
    // i det givne felt.
    public void opretPlaylist(Playlist playlist) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Playlist VALUES(?,?);");
            ps.setString(2, playlist.getListName());

            // Ikke færdig, skal stadig lige give ID på et tidspunkt. playlistID som
            // parameterindex 1 i stedet for getListName? (fordi playlistID kommer før navnet?)

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("  ");
        }
    }

    public List<Playlist> getAllPlaylist(){

        List<Playlist> fåAllePlaylister = new ArrayList();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Playlist");
            ResultSet rs = ps.executeQuery();

            Playlist playlist;
            while(rs.next()){
                String listName = rs.getString(2);

                playlist = new Playlist(listName);
                fåAllePlaylister.add(playlist);
            }

        } catch (SQLException e){
            System.err.println("can not access records" + e.getMessage());
        }
        return fåAllePlaylister;


    }

}