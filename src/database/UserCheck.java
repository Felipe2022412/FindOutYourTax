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
public class UserCheck extends Database {

    private Connection conn;

    public UserCheck() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            conn = new DatabaseConection().connectToDB();
        } catch (SQLException ex) {
            System.out.println("Error to conect to the Database");
        }
    }

// Verificar se o usuário é um administrador
    public boolean checkAdminUser(String userName, String password) {
        boolean isAdmin = false;

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            String query = String.format("SELECT admin FROM %s WHERE userName = '%s' AND password = '%s';", TABLE_NAME, userName, password);

            ResultSet results = stmt.executeQuery(query);

            if (results.next() && results.getBoolean("admin")) {
                isAdmin = true;
            }
        } catch (SQLException e) {
            System.out.println("Error identifying the user: " + e.getMessage());
        }

        return isAdmin;
    }

}
