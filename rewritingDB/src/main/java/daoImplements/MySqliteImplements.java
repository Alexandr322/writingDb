package daoImplements;

import dao.Sqlite;
import entity.Artist;
import entity.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqliteImplements implements Sqlite{
    private Connection conn;

    public MySqliteImplements(String url) {
        try {
            this.conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //получаем имена артистов
    public List<Artist> getAllArtist() {
        List<Artist> artists = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement
                    ("SELECT * FROM songs GROUP BY artist");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("artist");
                Artist artist = new Artist();
                artist.setArtistName(name);
                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }
    // получаем все записи
    public List<Song> getAll() {
        List<Song> all = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement
                    ("SELECT * FROM main.songs");
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    String artist = rs.getString("artist");
                    String songName = rs.getString("song");
                    String text = rs.getString("text");
                    Song song=new Song();

                    song.setArtist(artist);
                    song.setSongName(songName);
                    song.setText(text);
                    all.add(song);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }
}
