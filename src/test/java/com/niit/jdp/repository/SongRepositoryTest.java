package com.niit.jdp.repository;

import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class SongRepositoryTest {

    SongRepository songRepository = null;
    Song song;
    DatabaseService databaseService = new DatabaseService();

    @BeforeEach
    void setUp() {
        songRepository = new SongRepository();
        song = new Song();
        databaseService = new DatabaseService();

    }

    @AfterEach
    void tearDown() {
        songRepository = null;
        song = null;
    }

    @Test
    void addSongs() throws SQLException, ClassNotFoundException {
        databaseService.connect();
        Connection connection = databaseService.getConnection();
        Song song1 = new Song(0, "test", "test-album", "test-genre", "test-path");
        Assertions.assertTrue(songRepository.addSongs(connection, song1));
    }

    @Test
    void getAll() throws SQLException, ClassNotFoundException {
        databaseService.connect();
        Connection connection = databaseService.getConnection();
        List<Song> output = songRepository.getAll(connection);
        List<Song> expectedOutput = songRepository.getAll(connection);
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    void getSongById() throws SQLException, ClassNotFoundException {
        databaseService.connect();
        Connection connection = databaseService.getConnection();
        Song output = songRepository.getSongById(connection, song.getId());
        Song expectedOutput = songRepository.getSongById(connection, output.getId());
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    void deleteById() throws SQLException, ClassNotFoundException {
        databaseService.connect();
        Connection connection = databaseService.getConnection();
        boolean output = songRepository.deleteById(connection, 13);
        Assertions.assertTrue(output);
    }
}