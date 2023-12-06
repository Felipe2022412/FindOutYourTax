/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DatabaseSetup class handles the setup of database tables and initial data.
 *
 * @author Douglas and Felipe
 */
public class DatabaseSetup extends Database {

    private Connection conn;

    // Constructor initializes the DatabaseSetup and establishes a connection to the database
    public DatabaseSetup() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            conn = new DatabaseConection().connectToDB();
        } catch (SQLException ex) {
            System.out.println("Error to conect to the Database");
        }
    }

    // Method to set up the database with necessary tables and initial data (user admin that was provaided)
    public boolean setupDB() {
        try {
            // Create a statement to execute SQL commands
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE DATABASE IF NOT EXISTS " + DB_NAME + ";");//Create the database
            stmt.execute("USE " + DB_NAME + ";");//Select the database to be used
            String sqlUserTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USERDATA + " ("
                    + "userID INT AUTO_INCREMENT PRIMARY KEY,"
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

            String sqlTableTaxInfo = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TAXINFO + " ("
                    + "    operationID INT AUTO_INCREMENT," // Unique identifier for each operation
                    + "    userID INT,"
                    + "    grossIncome DOUBLE,"
                    + "    taxCredits DOUBLE,"
                    + "    incomeAfterCredits DOUBLE,"
                    + "    partnerGrossIncome DOUBLE,"
                    + "    partnerTaxCredits DOUBLE,"
                    + "    partnerIncomeAfterCredits DOUBLE,"
                    + "    coupleTotalIncomeAfterCredits DOUBLE,"
                    + "    totalTaxesDue DOUBLE,"
                    + "    liquidAmount DOUBLE,"
                    + "    PRIMARY KEY (operationID),"
                    + "    FOREIGN KEY (userID) REFERENCES " + TABLE_NAME_USERDATA + " (userID)"
                    + ");";
            
            //Execute SQL commands
            stmt.execute(sqlUserTable);
            stmt.execute(sqlTableTaxInfo);
            
            // Insert an admin user into USERDATA table
            // Check if the admin user already exists
            String sqlCheckAdminUser = "SELECT * FROM " + TABLE_NAME_USERDATA + " WHERE userName = 'CCT';";
            if (!stmt.executeQuery(sqlCheckAdminUser).next()) {
                // Create the admin user
                String sqlAdminUser = String.format("INSERT INTO " + TABLE_NAME_USERDATA + " (firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married, admin) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', %b, %b);",
                        "Gustavo", "Guanabara", "CCT", "Dublin", "1978-03-17", "1234567AB", "guanabara@gmail.com", true, true);
                stmt.execute(sqlAdminUser);
            }

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
