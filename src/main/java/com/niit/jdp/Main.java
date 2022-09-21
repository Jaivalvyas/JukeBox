package com.niit.jdp;

import com.niit.jdp.service.DatabaseService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Hello world!");
        DatabaseService databaseService = new DatabaseService();
        databaseService.connect();
        databaseService.getConnection();
        databaseService.printConnectionStatus();
    }
}