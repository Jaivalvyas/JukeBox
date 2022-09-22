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
    PlaylistRepository playlistRepository = new PlaylistRepository();

    public List<Playlist> displayPlaylist() {
        int choice = -1;
        do {
            System.out.println("============================================");
            System.out.println("1. Add playlist");
            System.out.println("2. View all playlist details");
            System.out.println("3. Exit");
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
                        Song song = new Song();
                        System.out.println("Add a new playlist");
                        System.out.println("Enter the name: ");
                        String name = scanner.next();

                        System.out.println("Enter song id you want to add in the playlist: ");
                        int songId = scanner.nextInt();

                        SongRepository songRepository = new SongRepository();
                        Song song1 = songRepository.getSongById(connection, songId);

                        List<Song> songList = new ArrayList<>();
                        songList.add(song1);

                        Playlist playlist = new Playlist(0, name, (List<Song>) songList.get(song1.getId()));
                        playlistRepository.add(connection, playlist);
                        break;

                    case 2:
                        System.out.println("View all playlist");
                        playlistRepository.getAll(connection).forEach(System.out::println);
                        break;

                    case 3:
                        System.out.println("Exit");
                        break;
                    default:
                        System.err.println("Invalid choice");
                }

            } catch (ClassNotFoundException | SQLException exception) {
                System.err.println("Could not connect to the database!");
                exception.printStackTrace();
                choice = 4;
            }

        } while (choice != 4);
        return new ArrayList<>();
    }


}

