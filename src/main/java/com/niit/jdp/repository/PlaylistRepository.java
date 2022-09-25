/*
 * Author Name: Jaival
 * Date: 20-09-2022
 * Createdd with IntelliJ IDEA Community Editiion
 */
package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository implements Repository<Playlist> {

    @Override
    public boolean addSongs(Connection connection, Playlist playlist) throws SQLException {
        String insertCarQuery = "INSERT INTO `jukebox`.`playlist` (`name`, `catalog_id`) VALUES" + " (?, ?);";
        Song song = new Song();
        int numberOfRowsAffected;

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCarQuery)) {
            preparedStatement.setString(1, playlist.getPlaylistName());
            preparedStatement.setString(2, String.valueOf(playlist.getSongList().get(song.getId()).getId()));
            numberOfRowsAffected = preparedStatement.executeUpdate();
        }
        return numberOfRowsAffected > 0;
    }

    @Override
    public List<Playlist> getAll(Connection connection) throws SQLException {

        List<Playlist> songInPlaylist = new ArrayList<>();
        Playlist playlist = new Playlist();
        // create a SQL query to retrieve all the rows from the catalog table
        String readQuery = "SELECT * FROM `jukebox`.`playlist`;";

        // create a statement object
        try (PreparedStatement preparedStatement = connection.prepareStatement(readQuery, Statement.RETURN_GENERATED_KEYS)) {

            // execute the query
            ResultSet songResultSet = preparedStatement.executeQuery();

            // iterate over the result set and create a list of song objects
            while (songResultSet.next()) {
                // fetch the values of the current row from the result set
                int id = songResultSet.getInt("id");
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                String name = songResultSet.getString("name");
                String catalog = songResultSet.getString("catalog_id");
                playlist.setPlaylistId(id);
                playlist.setPlaylistName(name);

                catalog = catalog.replaceAll("[\\[\\]]", "");
                String[] catalogId = catalog.split(",");
                List<Song> playlistArrayList = new ArrayList<>();
                for (String songName : catalogId) {
                    int index = Integer.parseInt(songName);
                    SongRepository songRepository = new SongRepository();
                    Song song = songRepository.getSongById(connection, index);
                    playlistArrayList.add(song);
                }
                Playlist playlist1 = new Playlist(id, name, playlistArrayList);
                songInPlaylist.add(playlist1);
            }
        }
        //return the list of songs
        return songInPlaylist;
    }

    @Override
    public Playlist getSongById(Connection connection, int id) throws SQLException {
        Playlist playlist = new Playlist();
        List<Playlist> songInPlaylist = new ArrayList<>();
        String searchQuery = "SELECT * FROM `jukebox`.`playlist` WHERE(`id` = ?);";

        Playlist playlist1 = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // fetch the values of the current row from the result set
                int playlistId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String songsId = resultSet.getString("catalog_id");
                playlist.setPlaylistId(id);
                playlist.setPlaylistName(name);

                songsId = songsId.replaceAll("[\\[\\]]", "");
                String[] catalogId = songsId.split(",");
                List<Song> playlistArrayList = new ArrayList<>();
                for (String songName : catalogId) {
                    int index = Integer.parseInt(songName);
                    SongRepository songRepository = new SongRepository();
                    Song song = songRepository.getSongById(connection, index);
                    playlistArrayList.add(song);
                }
                playlist1 = new Playlist(playlistId, name, playlistArrayList);
                // songInPlaylist.add(playlist1);
            }
        }
        //return the list of songs
        return playlist1;

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

    @Override
    public boolean deleteById(Connection connection, int id) throws SQLException {
        String deleteQuery = "DELETE FROM `jukebox`.`playlist` WHERE (`id` = ?);";
        int numberOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            numberOfRowsAffected = preparedStatement.executeUpdate();
        }
        return numberOfRowsAffected > 0;
    }
}
