/*
 * Author Name: Jaival
 * Date: 20-09-2022
 * Createdd with IntelliJ IDEA Community Editiion
 */
package com.niit.jdp.service;

import com.niit.jdp.model.Playlist;
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
            System.out.println("5. Delete playlist");
            System.out.println("============================================");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            DatabaseService databaseService = new DatabaseService();
            Playlist playlist;

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
                        String ids = scanner.next();

                        playlist = new Playlist(0, name, ids);
                        playlistRepository.addSongs(connection, playlist);
                        break;

                    case 2:
                        System.out.println("View all playlist");
                        playlistRepository.getAll(connection).forEach(System.out::println);
                        break;

                    case 3:
                        System.out.println("View playlist by id");
                        System.out.println("Enter id: ");
                        int id = scanner.nextInt();
                        playlist = playlistRepository.getSongById(connection, id);

                        if (playlist.getPlaylistId() == 0) {
                            System.err.println("No playlist found");
                        } else {
                            System.out.println(playlist);
                        }
                        break;

                    case 4:
                        System.out.println("Enter song to be played");
                        int inputId = scanner.nextInt();
                        SongRepository songRepository = new SongRepository();
                        String s = String.valueOf(songRepository.getSongPath(connection, inputId));
                        PlaySongService playSongService = new PlaySongService();
                        playSongService.play(s);
                        break;

                    case 5:
                        System.out.println("Enter playlist id you want to delete");
                        int deleteId = scanner.nextInt();

                        boolean songDelete = playlistRepository.deleteById(connection, deleteId);
                        if (songDelete) {
                            System.out.println("Song deleted succesfully");
                        } else {
                            System.out.println("Invalid id");
                        }
                        break;
                    default:
                        System.err.println("Invalid choice");
                }

            } catch (ClassNotFoundException | SQLException exception) {
                System.err.println("Could not connect to the database!");
                exception.printStackTrace();
                choice = 6;
            }

        } while (choice != 6);
        return new ArrayList<>();
    }
}

