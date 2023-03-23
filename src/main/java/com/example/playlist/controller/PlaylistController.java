package com.example.playlist.controller;

import com.example.playlist.service.PlaylistService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.playlist.api.PlaylistApi;
import com.example.playlist.model.Song;

@RestController
@RequiredArgsConstructor
public class PlaylistController implements PlaylistApi {
  private final PlaylistService playlistService;

  @Override
  public ResponseEntity<List<Song>> createPlaylist(List<Song> songs) {
    List<Song> playlist = playlistService.createPlaylist(songs);
    return new ResponseEntity<>(
        playlist,
        HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<List<Song>> getPlaylist(Long id) {
    List<Song> playlist = playlistService.getPlaylistById(id);

    return new ResponseEntity<>(
        playlist,
        HttpStatus.OK);
  }

}
