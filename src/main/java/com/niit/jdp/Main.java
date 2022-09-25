package com.niit.jdp;

import com.niit.jdp.service.CatalogService;
import com.niit.jdp.service.DatabaseService;
import com.niit.jdp.service.PlaylistService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InvalidException {
        DatabaseService databaseService = new DatabaseService();
        databaseService.connect();
        databaseService.getConnection();
        databaseService.printConnectionStatus();
        Scanner sc = new Scanner(System.in);

        System.out.println("--------------------------------------");
        System.out.println("1. Select to see catalog");
        System.out.println("2. Select to check playlist options");
        System.out.println("--------------------------------------");
        System.out.println("Enter your choice: ");
        try {
            int choice = sc.nextInt();
            if (choice > 2) throw new InvalidException("Invalid option");
            switch (choice) {
                case 1:
                    CatalogService catalogService = new CatalogService();
                    catalogService.displayCatalog();
                    break;
                case 2:
                    PlaylistService playlistService = new PlaylistService();
                    playlistService.displayPlaylist();
                    break;
            }
        } catch (InvalidException e) {
            System.out.println(e);
            System.out.println("Please enter correct option");
        }
    }
}