package com.example.playlist;

import com.example.playlist.model.Song;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {
  public static List<Song> getPlaylist() {
    List<Song> playlist = new ArrayList<>();
    Song song = new Song();
    song.setArtist("Artist");
    song.setTitle("Title");
    playlist.add(song);
    return playlist;
  }

}
