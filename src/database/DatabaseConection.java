/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection class for handling database connections. Extends the
 * Database class. Authors: Douglas and Felipe
 */
public class DatabaseConection extends Database {

    public Connection connectToDB() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = null;
        try {
            // Attempt to establish a connection to the database using provided credentials
            conn = DriverManager.getConnection(DB_BASE_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException error) {
        // Handle the SQLException appropriately
            throw error;
        }
        //Return a conection to interact with the database 
        return conn;
    }

}
