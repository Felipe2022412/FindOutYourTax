/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
            String sqlUserTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USERDATA + " ("
                    + "userID INT PRIMARY KEY,"
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
            String sqlAdminUser = String.format("INSERT INTO " + TABLE_NAME_USERDATA + " VALUES ('%d', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %b, %b);",
                    1, "Gustavo", "Guanabara", "CCT", "Dublin", "1978-03-17", "1234567AB", "guanabara@gmail.com", true, true);

            stmt.execute(sqlUserTable);
            stmt.execute(sqlTableTaxInfo);
            stmt.execute(sqlAdminUser);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
