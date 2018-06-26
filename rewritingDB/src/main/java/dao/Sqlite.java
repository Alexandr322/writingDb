package dao;

import entity.Artist;
import entity.Song;

import java.util.List;

public interface Sqlite {
    List<Artist> getAllArtist();
    List<Song> getAll();
}
