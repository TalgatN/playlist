package com.example.playlist.service;

import com.example.playlist.model.Song;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlaylistService {

  public List<Song> createPlaylist(List<Song> songs) {
    log.info("Creating a playlist");
    return songs;
  }

  public List<Song> getPlaylistById(Long id) {
    log.info("Getting a playlist by id - {}", id);
    return List.of(new Song().artist("artist_1").title("title_1"));
  }

}
