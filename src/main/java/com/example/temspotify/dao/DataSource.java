package com.example.temspotify.dao;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private String hostname;
    private String username;
    private String password;
    private String database;
    private Connection connection;
    private String port;
    private String databaseVendor;

    public DataSource(){
        connect();
    }

    private void connect() {
        try {
            connectOnDataSource();
        } catch (SQLException ex) {
            printException(ex);
        }
    }

    private void connectOnDataSource() throws SQLException {
        username = "temspotify";
        password = "T3m@ul@";
        DriverManager.registerDriver(new Driver());
        connection = DriverManager.getConnection(getDatabaseURL(), username, password);
        System.out.println("DataSource - Connected");
    }

    private String getDatabaseURL() {
        databaseVendor = "mysql";
        hostname = "localhost";
        database = "temspotify";
        port = "3307";
        String url = "JDBC:"
                .concat(databaseVendor)
                .concat("://")
                .concat(hostname)
                .concat(":")
                .concat(port)
                .concat("/")
                .concat(database);
        return url;
    }

    private static void printException(SQLException ex) {
        System.out.println("Erro ao conectar: " + ex.getMessage());
        ex.printStackTrace();
    }

    public Connection getConnection() {
        return connection;
    }
}
