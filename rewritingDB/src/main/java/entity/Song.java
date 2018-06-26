package entity;


public class Song {
    String songName;
    String text;
    String artist;


 public Song(){}
    public Song(String songName,String text,String artist){
        this.songName=songName;
        this.text=text;
    }

    public String getSongName() {
        return songName;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
}
