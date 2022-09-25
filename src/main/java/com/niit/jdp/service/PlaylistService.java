/*
 * Author Name: Jaival
 * Date: 20-09-2022
 * Createdd with IntelliJ IDEA Community Editiion
 */
package com.niit.jdp.service;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlaylistRepository;
import com.niit.jdp.repository.SongRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistService {
    public List<Playlist> displayPlaylist() {
        int choice = -1;
        do {
            System.out.println("============================================");
            System.out.println("1. Add playlist");
            System.out.println("2. View all playlist details");
            System.out.println("3. View playlist by id");
            System.out.println("4. Play song");
            System.out.println("============================================");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            DatabaseService databaseService = new DatabaseService();

            try {
                databaseService.connect();
                PlaylistRepository playlistRepository = new PlaylistRepository();
                Connection connection = databaseService.getConnection();

                switch (choice) {
                    case 1:

                        System.out.println("Add a new playlist");
                        System.out.println("Enter the name: ");
                        String name = scanner.next();

                        System.out.println("Enter song id you want to add in the playlist: ");
                        String songId = scanner.next();

                        songId = songId.replaceAll("[\\[\\]]", "");
                        String[] catalogId = songId.split(",");
                        List<Song> playlistArrayList = new ArrayList<>();
                        for (String songName : catalogId) {
                            int index = Integer.parseInt(songName);
                            SongRepository songRepository = new SongRepository();
                            Song song = songRepository.getSongById(connection, index);
                            playlistArrayList.add(song);
                        }
                        Playlist playlist1 = new Playlist(0, name, playlistArrayList);
                        break;

                    case 2:
                        System.out.println("View all playlist");
                        playlistRepository.getAll(connection).forEach(System.out::println);
                        break;

                    case 3:
                        System.out.println("View playlist by id");
                        System.out.println("Enter id: ");
                        int id = scanner.nextInt();
                        playlist1 = playlistRepository.getSongById(connection, id);

                        if (playlist1.getPlaylistId() == 0) {
                            System.err.println("No playlist found");
                        } else {
                            System.out.println(playlist1);
                        }

                        break;
                    case 4:
                        System.out.println("Enter song to be played");
                        int inputId = scanner.nextInt();
                        String s = String.valueOf(playlistRepository.getSongPath(connection, inputId));
                        PlaySongService playSongService = new PlaySongService();
                        playSongService.play(s);
                        break;

                    default:
                        System.err.println("Invalid choice");
                }

            } catch (ClassNotFoundException | SQLException exception) {
                System.err.println("Could not connect to the database!");
                exception.printStackTrace();
                choice = 5;
            }

        } while (choice != 5);
        return new ArrayList<>();
    }
}

