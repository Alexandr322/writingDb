import dao.Sqlite;
import daoImplements.MySqlImplements;
import daoImplements.MySqliteImplements;
import entity.Artist;
import entity.Song;

import java.util.List;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/music?characterEncoding=utf-8";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "sudeikin1996";


    public static void main(String[] args) {
        MySqlImplements mySql = new MySqlImplements(DB_CONNECTION, DB_USER, DB_PASSWORD);
        Sqlite sqlLite = new MySqliteImplements("jdbc:sqlite:C:\\finaly\\src\\chords.db");

        //записываем имена артистов
        System.out.println("1.Для записи артистов. 2 . Для песен");
        Scanner sc=new Scanner(System.in);
        int artist=sc.nextInt();
        switch (artist) {
            case 1:
            List<Artist> artists = sqlLite.getAllArtist();
            mySql.fillArtists(artists);
            break;
            case 2:
            //записываем имена песен и текст
            List<Song> songs = sqlLite.getAll();
            mySql.fillSongs(songs);
            break;
        }


    }
}

