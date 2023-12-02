/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dougl
 */
public class DatabaseSetup extends Database {

    private Connection conn;
    private ResultSet rs;

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
            String sqlTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + "name VARCHAR(255),"
                    + "birthdate DATE,"
                    + "bloodType VARCHAR(2),"
                    + "id INT(10)"
                    + ");";
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
        
    }

}
