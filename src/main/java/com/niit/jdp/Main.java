package com.niit.jdp;

import com.niit.jdp.service.CatalogService;
import com.niit.jdp.service.DatabaseService;
import com.niit.jdp.service.PlaylistService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        DatabaseService databaseService = new DatabaseService();
        databaseService.connect();
        databaseService.getConnection();
        databaseService.printConnectionStatus();

//        try(Connection connection = databaseService.getConnection()) {
//            PlaylistRepository playlistRepository = new PlaylistRepository();
//            playlistRepository.createTable(connection);
//        }

        // PlaySongService playSongService = new PlaySongService();
        //playSongService.play("src/main/resources/songs/akatsuki-theme.wav");


        System.out.println("--------------------------------------");
        System.out.println("1. Select to see catalog");
        System.out.println("2. Select to check playlist options");
        System.out.println("--------------------------------------");
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                CatalogService catalogService = new CatalogService();
                catalogService.displayCatalog();
            case 2:
                PlaylistService playlistService = new PlaylistService();
                playlistService.displayPlaylist();
        }
    }
}