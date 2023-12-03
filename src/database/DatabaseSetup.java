/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import findoutyourtax.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dougl
 */
public class DatabaseSetup extends Database {

    private Connection conn;

    public DatabaseSetup() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            conn = new DatabaseConection().connectToDB();
        } catch (SQLException ex) {
            System.out.println("Error to conect to the Database");
        }
    }

    public boolean setupDB() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE DATABASE IF NOT EXISTS " + DB_NAME + ";");
            stmt.execute("USE " + DB_NAME + ";");
            String sqlTable
                    = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + "userID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "firstName VARCHAR(255) NOT NULL,"
                    + "lastName VARCHAR(255) NOT NULL,"
                    + "userName VARCHAR(255) UNIQUE NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "dateOfBirth DATE,"
                    + "ppsNo VARCHAR(10) UNIQUE,"
                    + "email VARCHAR(255) UNIQUE NOT NULL,"
                    + "married BOOLEAN,"
                    + "admin BOOLEAN"
                    + ");";
            String sqlAdminUser = String.format("INSERT INTO " + TABLE_NAME + " VALUES ('%d', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %b, %b);",
                     1,"Gustavo", "Guanabara", "CCT", "Dublin", "1978-03-17", "1234567AB", "guanabara@gmail.com", true, true);
            stmt.execute(sqlTable);
            stmt.execute(sqlAdminUser);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }



}
