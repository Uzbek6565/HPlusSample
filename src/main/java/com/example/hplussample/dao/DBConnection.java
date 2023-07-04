package com.example.hplussample.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnectionToDatabase(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostrgreSQL JDBC Driver Registered!");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres", "1234");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find PostgreSQL Driver");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Connection failed! Check output console");
            throw new RuntimeException(e);
        }
        if (connection != null) {
            System.out.println("Connection made to DB!");
        }

        return connection;
    }
}
