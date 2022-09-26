/*
 * Author Name: Jaival
 * Date: 20-09-2022
 * Createdd with IntelliJ IDEA Community Editiion
 */
package com.niit.jdp.service;

import com.niit.jdp.model.Song;
import com.niit.jdp.repository.SongRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatalogService {
    public List<Song> displayCatalog() throws SQLException, ClassNotFoundException {
        int choice = -1;
        do {
            System.out.println("============================================");
            System.out.println("1. Add a new song to database");
            System.out.println("2. View all song details");
            System.out.println("3. Play Song");
            System.out.println("4. Display song details by id");
            System.out.println("5. Display song details by alphabetical order of song name");
            System.out.println("6. Delete song");
            System.out.println("============================================");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            DatabaseService databaseService = new DatabaseService();
            databaseService.connect();
            databaseService.getConnection();
            databaseService.printConnectionStatus();
            try {
                databaseService.connect();
                SongRepository songRepository = new SongRepository();
                Connection connection = databaseService.getConnection();

                switch (choice) {
                    case 1:
                        System.out.println("Add a new song");
                        System.out.print("Enter the song name: ");
                        String name = scanner.next();

                        System.out.print("Enter song album artist: ");
                        String albumArtist = scanner.next();

                        System.out.print("Enter song genre: ");
                        String genre = scanner.next();

                        System.out.print("Enter song path: ");
                        String songPath = scanner.next();

                        Song song = new Song(0, name, albumArtist, genre, songPath);
                        songRepository.addSongs(connection, song);
                        break;

                    case 2:
                        System.out.println("View all songs");
                        songRepository.getAll(connection).forEach(System.out::println);
                        break;

                    case 3:
                        System.out.println("Enter choice of song you want to play");
                        int ids = scanner.nextInt();
                        String s = String.valueOf(songRepository.getSongPath(connection, ids));

                        PlaySongService playSongService = new PlaySongService();
                        playSongService.play(s);
                        break;

                    case 4:
                        System.out.println("View song by id");
                        System.out.println("Enter id: ");
                        int id = scanner.nextInt();

                        Song song1 = songRepository.getSongById(connection, id);

                        if (song1.getId() == 0) {
                            System.err.println("No song found");
                        } else {
                            System.out.println(song1);
                        }

                        break;

                    case 5:
                        System.out.println("View all songs");
                        songRepository.getListByOrder(connection).forEach(System.out::println);
                        break;

                    case 6:
                        System.out.println("Enter song id you want to delete: ");
                        int deleteId = scanner.nextInt();

                        boolean songDelete = songRepository.deleteById(connection, deleteId);
                        if (songDelete) {
                            System.out.println("Song deleted succesfully");
                        } else {
                            System.out.println("Invalid id");
                        }
                        break;

                    default:
                        System.err.println("Invalid option");
                }

            } catch (ClassNotFoundException | SQLException exception) {
                System.err.println("Could not connect to the database!");
                exception.printStackTrace();
                choice = 7;
            }
        } while (choice != 7);
        return new ArrayList<>();
    }
}

