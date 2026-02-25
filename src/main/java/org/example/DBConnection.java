package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String URL =
            "jdbc:postgresql://localhost:5432/ceni";
    private final String USER = "postgres";
    private final String PASSWORD = "sarobidy";

    public DBConnection() {
    }

    public String getURL() {
        return URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion PostgreSQL", e);
        }
    }


}
