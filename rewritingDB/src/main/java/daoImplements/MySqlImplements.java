package daoImplements;

import dao.MySql;
import entity.Artist;
import entity.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlImplements implements MySql {
    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PASSWORD;
    private Connection connection;

    public MySqlImplements(String DB_URL, String DB_USER, String DB_PASSWORD) {
        this.DB_URL = DB_URL;
        this.DB_USER = DB_USER;
        this.DB_PASSWORD = DB_PASSWORD;
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // запрос записи  имени артистов
    public void addArtist(Artist artist) {
        try (PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO artist (artist_name) VALUES (?);")) {
            ps.setString(1, artist.getArtistName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // цикл артистов в колекцию
    public void fillArtists(List<Artist> artists) {
        for (Artist artist : artists) {
            addArtist(artist);
        }
    }

    // запись текста и песен
    public void addSongsAndText(Song song) {
        try (PreparedStatement ps = connection.prepareStatement
                ("INSERT INTO music.songs (text, song_name) VALUES (?,?);")) {
            ps.setString(1, song.getText());
            ps.setString(2, song.getSongName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillSongs(List<Song> songs) {
        for (Song song : songs) {
            addSongsAndText(song);
        addSong(song);
        }

    }

    //получаем список артистов и id с моей базы
    public ArrayList<Artist> getArtistNameId() {
        // записываем их в колекцию
        ArrayList<Artist> artistNameId = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement
                ("SELECT * FROM artist")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("artist_name");
                artistNameId.add(new Artist(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artistNameId;
    }
    //получить id артиста
    public int getArtistIdByName(String artistName) {
            ArrayList<Artist> artists =getArtistNameId();
        try (PreparedStatement ps = connection.prepareStatement
                ("SELECT * FROM artist WHERE artist_name=?")) {
            ps.setString(1, artistName);
            ResultSet rs = ps.executeQuery();
            for (Artist artist: artists) {
                if (artist.getArtistName().equals(artistName)) {
                    return artist.getArtistId();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
// поиск и писание id
    public void addSong(Song song) {
        try (PreparedStatement ps = connection.prepareStatement
                ("UPDATE music.songs SET artist_id=? WHERE song_name=?")) {
            // Получаем ид артиста в твоей базе, используя его имя
            int artistId = this.getArtistIdByName(song.getArtist());
            ps.setInt(1, artistId);
            ps.setString(2,song.getSongName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
