package org.newdawn.spaceinvaders;


import java.sql.*;


public class UserDB {

    public static Connection conn;

    static {
        String url = "jdbc:mysql://localhost:3306/space-invaders";
        String user = "user1";
        String password = "12345";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return conn;
    }
    public UserDB() {

    }
}