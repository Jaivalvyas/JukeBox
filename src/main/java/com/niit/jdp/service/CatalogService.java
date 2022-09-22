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
    SongRepository songRepository = new SongRepository();

    public List<Song> displayCatalog() {
        int choice = -1;
        do {
            System.out.println("============================================");
            System.out.println("1. Add a new song to database");
            System.out.println("2. View all song details");
            System.out.println("3. Play Song");
            System.out.println("4. Display song details by id");
            System.out.println("4. Exit");
            System.out.println("============================================");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            DatabaseService databaseService = new DatabaseService();

            try {
                databaseService.connect();
                SongRepository songRepository = new SongRepository();
                Connection connection = databaseService.getConnection();

                switch (choice) {
                    case 1:
                        System.out.println("Add a new song");
                        System.out.println("Enter the song name: ");
                        String name = scanner.next();

                        System.out.println("Enter song album artist: ");
                        String albumArtist = scanner.next();

                        System.out.println("Enter song genre: ");
                        String genre = scanner.next();

                        Song song = new Song(0, name, albumArtist, genre);
                        songRepository.add(connection, song);
                        break;

                    case 2:
                        System.out.println("View all songs");
                        songRepository.getAll(connection).forEach(System.out::println);
                        break;

                    case 3:
                        Scanner sc = new Scanner(System.in);
                        PlaySongService playSongService = new PlaySongService();
                        System.out.println("Enter choice of song you want to play: ");
                        int songChoice = sc.nextInt();
                        switch (songChoice) {
                            case 1:
                                playSongService.play("src/main/resources/songs/akatsuki-theme.wav");

                            case 2:
                                playSongService.play("src/main/resources/songs/tinywow_Kate_Bush_Running_Up_That_Hill_5779048.wav");
                        }

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
                        System.out.println("Exit");
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

