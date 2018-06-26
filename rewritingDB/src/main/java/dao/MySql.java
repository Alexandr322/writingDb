package dao;

import entity.Artist;

import java.util.List;

public interface MySql {
    void addArtist(Artist artist);
    void fillArtists(List<Artist> artists);
 //   void fillSongs(List<Song> songs);
}
