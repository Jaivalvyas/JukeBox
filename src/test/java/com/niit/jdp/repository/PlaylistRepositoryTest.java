package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class PlaylistRepositoryTest {

    PlaylistRepository playlistRepository = null;
    Playlist playlist;
    DatabaseService databaseService = new DatabaseService();

    @BeforeEach
    void setUp() {
        playlistRepository = new PlaylistRepository();
        playlist = new Playlist();
        databaseService = new DatabaseService();
    }

    @AfterEach
    void tearDown() {
        playlistRepository = null;
        playlist = null;
    }

    @Test
    void addSongs() throws SQLException, ClassNotFoundException {
        databaseService.connect();
        Connection connection = databaseService.getConnection();
        Song song = new Song(0, "test-playlist", "test", "test", "test-playlist");
        List<Song> songList = new ArrayList<>();
        songList.add(song);
        Playlist playlist1 = new Playlist(0, "test", songList);
        Assertions.assertTrue(playlistRepository.addSongs(connection, playlist1));
    }

    @Test
    void getAll() throws SQLException, ClassNotFoundException {
        databaseService.connect();
        Connection connection = databaseService.getConnection();
        List<Playlist> output = playlistRepository.getAll(connection);
        List<Playlist> expectedOutput = playlistRepository.getAll(connection);
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    void getSongById() throws SQLException, ClassNotFoundException {
        databaseService.connect();
        Connection connection1 = databaseService.getConnection();
        Playlist actualOutput = playlistRepository.getSongById(connection1, playlist.getPlaylistId());
        Playlist expectedOutput = playlistRepository.getSongById(connection1, playlist.getPlaylistId());
        Assertions.assertEquals(expectedOutput, actualOutput);
    }


    @Test
    void deleteById() throws SQLException, ClassNotFoundException {
        databaseService.connect();
        Connection connection = databaseService.getConnection();
        boolean output = playlistRepository.deleteById(connection, 10);
        Assertions.assertTrue(output);
    }
}