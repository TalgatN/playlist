package com.example.playlist.service;

import static com.example.playlist.TestUtil.getPlaylist;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.playlist.model.Song;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaylistServiceTest {
  private PlaylistService service;

  @BeforeEach
  public void setup() {
    service = new PlaylistService();
  }

  @Test
  void createPlaylistTest() {
    List<Song> playlist = getPlaylist();

    List<Song> actual = service.createPlaylist(playlist);

    assertEquals(playlist, actual);
  }

  @Test
  void getPlaylistTest() {
    PlaylistService playlistService = mock(PlaylistService.class);
    long id = 1L;
    List<Song> expectedPlaylist = List.of(new Song().artist("artist_1").title("title_1"));

    when(playlistService.getPlaylistById(id)).thenReturn(expectedPlaylist);

    List<Song> result = playlistService.getPlaylistById(id);

    assertEquals(expectedPlaylist, result);
  }
}