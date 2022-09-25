/*
 * Author Name: Jaival
 * Date: 20-09-2022
 * Createdd with IntelliJ IDEA Community Editiion
 */
package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepository implements Repository<Song> {

    @Override
    public boolean addSongs(Connection connection, Song song) throws SQLException {
        String insertCarQuery = "INSERT INTO `jukebox`.`catalog` (`name`, `album_artist`, `genre`, `song_path`) VALUES" + " (?, ?, ?, ?);";

        int numberOfRowsAffected;

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCarQuery)) {
            preparedStatement.setString(1, song.getName());
            preparedStatement.setString(2, song.getAlbumArtist());
            preparedStatement.setString(3, song.getGenre());
            preparedStatement.setString(4, song.getSongPath());
            numberOfRowsAffected = preparedStatement.executeUpdate();
        }
        return numberOfRowsAffected > 0;
    }

    @Override
    public List<Song> getAll(Connection connection) throws SQLException {

        List<Song> songList = new ArrayList<>();
        String readQuery = "SELECT * FROM `jukebox`.`catalog`;";

        try (Statement statement = connection.createStatement()) {

            ResultSet songResultSet = statement.executeQuery(readQuery);

            while (songResultSet.next()) {
                int id = songResultSet.getInt("id");
                String name = songResultSet.getString("name");
                String albumArtist = songResultSet.getString("album_artist");
                String genre = songResultSet.getString("genre");
                String songPath = songResultSet.getString("song_path");

                Song song = new Song(id, name, albumArtist, genre, songPath);

                songList.add(song);
            }
        }
        return songList;
    }

    @Override
    public Song getSongById(Connection connection, int id) throws SQLException {
        String searchQuery = "SELECT * FROM `jukebox`.`catalog` WHERE(`id` = ?);";

        Song song = new Song();

        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // fetch the values of the current row from the result set

                int songId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String albumArtist = resultSet.getString("album_artist");
                String genre = resultSet.getString("genre");
                String songPath = resultSet.getString("song_path");

                // create a student object using the values fetched from the result set
                song = new Song(id, name, albumArtist, genre, songPath);
            }
        }
        return song;
    }

    public String getSongPath(Connection connection, int id) {
        String seacrhPath = "SELECT `song_path` FROM `jukebox`.`catalog` WHERE (`id`=?);";

        Song song = new Song();

        try (PreparedStatement preparedStatement = connection.prepareStatement(seacrhPath)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // fetch the values of the current row from the result set
                String songPath = resultSet.getString("song_path");
                // create a student object using the values fetched from the result set
                song = new Song(songPath);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return song.getSongPath();
    }

    public List<Song> getListByOrder(Connection connection) throws SQLException {

        List<Song> songList = new ArrayList<>();
        // create a SQL query to retrieve all the rows from the catalog table
        String readQuery = "SELECT * FROM `jukebox`.`catalog` ORDER BY `name` ASC;";

        // create a statement object
        try (Statement statement = connection.createStatement()) {

            // execute the query
            ResultSet songResultSet = statement.executeQuery(readQuery);

            // iterate over the result set and create a list of song objects
            while (songResultSet.next()) {
                //fetch the values of the current row from the result set
                int id = songResultSet.getInt("id");
                String name = songResultSet.getString("name");
                String albumArtist = songResultSet.getString("album_artist");
                String genre = songResultSet.getString("genre");
                String songPath = songResultSet.getString("song_path");

                // create a song object using the values fetched from the result set
                Song song = new Song(id, name, albumArtist, genre, songPath);

                // add the song object to the list
                songList.add(song);
            }
        }

        //return the list of songs
        return songList;
    }

    @Override
    public boolean deleteById(Connection connection, int id) throws SQLException {
        String deleteQuery = "DELETE FROM `jukebox`.`catalog` WHERE (`id` = ?);";
        int numberOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            numberOfRowsAffected = preparedStatement.executeUpdate();
        }
        return numberOfRowsAffected > 0;
    }
}
