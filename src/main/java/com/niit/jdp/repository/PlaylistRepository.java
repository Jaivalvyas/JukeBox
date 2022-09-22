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
    public boolean add(Connection connection, Playlist playlist) throws SQLException {
        Song song = new Song();
        Playlist playlist1 = new Playlist();
        playlist1.getSongList().get(song.getId());
        String insertQuery = "INSERT INTO `jukebox`.`playlist` (`name`, `catalog_id`) VALUES ('?', '?');";
        // create a statement object
        int numberOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            // set the values of the query parameters
            preparedStatement.setString(1, playlist.getPlaylistName());
            preparedStatement.setInt(2, playlist1.getSongList().get(song.getId()));
            // execute the query
            numberOfRowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        try (PreparedStatement preparedStatement = connection.prepareStatement
                (readQuery, Statement.RETURN_GENERATED_KEYS
                )) {

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
//                    SongRepository songRepository = new SongRepository();
//                    Song song = songRepository.getSongById(connection,id);
//                    playlist.getSongList().add(song);
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
        return null;
    }

    @Override
    public boolean deleteById(Connection connection, int id) throws SQLException {
        return false;
    }
}
