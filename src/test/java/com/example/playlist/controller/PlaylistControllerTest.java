package com.example.playlist.controller;


import static com.example.playlist.TestUtil.getPlaylist;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.playlist.model.Song;
import com.example.playlist.service.PlaylistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PlaylistController.class)
class PlaylistControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private PlaylistService playlistService;

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void createPlaylistTest() throws Exception {
    List<Song> playlist = getPlaylist();

    when(playlistService.createPlaylist(playlist)).thenReturn(playlist);

    mockMvc.perform(post("/playlist")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(playlist)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].artist", is("Artist")))
        .andExpect(jsonPath("$[0].title", is("Title")));


    verify(playlistService).createPlaylist(playlist);
  }

  @Test
  void createPlaylistBadRequestTest() throws Exception {
    mockMvc.perform(post("/playlist")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString("")))
        .andExpect(status().isBadRequest());

    verifyNoInteractions(playlistService);
  }

  @Test
  void getPlaylistByIdTest() throws Exception {
    List<Song> playlist = getPlaylist();
    long playlistId = 1L;

    when(playlistService.getPlaylistById(playlistId)).thenReturn(playlist);

    mockMvc.perform(get("/playlist/" + playlistId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(playlist)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].artist", is("Artist")))
        .andExpect(jsonPath("$[0].title", is("Title")));


    verify(playlistService).getPlaylistById(playlistId);
  }

  @Test
  void getPlaylistByIdBadRequestTest() throws Exception {
    mockMvc.perform(get("/playlist/id")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());

    verifyNoInteractions(playlistService);
  }

}