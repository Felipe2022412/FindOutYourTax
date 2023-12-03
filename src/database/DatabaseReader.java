/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import findoutyourtax.AdminUser;
import findoutyourtax.RegularUser;
import findoutyourtax.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dougl
 */
public class DatabaseReader extends Database {

    private Connection conn;

    public DatabaseReader() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            conn = new DatabaseConection().connectToDB();
        } catch (SQLException ex) {
            System.out.println("Error to conect to the Database");
        }
    }

// Verificar se o usuário é um administrador
    public User getUser(String userName, String password) {

        //boolean isAdmin = false;
        User user = null;

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            //String query = String.format("SELECT admin FROM %s WHERE userName = '%s' AND password = '%s';", TABLE_NAME, userName, password);
            String query = String.format("SELECT userID, admin, firstName, lastName, dateOfBirth, ppsNo, email, married FROM %s WHERE userName = '%s' AND password = '%s';", TABLE_NAME, userName, password);
            
            
            ResultSet results = stmt.executeQuery(query);

            if (results.next() && results.getBoolean("admin")) {
                //isAdmin = true;
                int userID = results.getInt("userID");
                String firstName = results.getString("firstName");
                String lastName = results.getString("lastName");
                String dateOfBirth = results.getString("dateOfBirth");
                String ppsNo = results.getString("ppsNo");
                String email = results.getString("email");
                boolean married = results.getBoolean("married");
                
                
                User adminUser = new AdminUser(userID,firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);
                user = adminUser;
            }else{
                int userID = results.getInt("userID");
                String firstName = results.getString("firstName");
                String lastName = results.getString("lastName");
                String dateOfBirth = results.getString("dateOfBirth");
                String ppsNo = results.getString("ppsNo");
                String email = results.getString("email");
                boolean married = results.getBoolean("married");
                
                User regulaUser = new RegularUser(userID, firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);
                 
                user = regulaUser;
                
            }
        } catch (SQLException e) {
            System.out.println("Error identifying the user: " + e.getMessage());
        }
        
        return user;
        
        
        /*try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            String query = String.format("SELECT admin FROM %s WHERE userName = '%s' AND password = '%s';", TABLE_NAME, userName, password);

            ResultSet results = stmt.executeQuery(query);

            if (results.next()) {
                String firstName = results.getString("firstName");
                String lastName = results.getString("lastName");
                String dateOfBirth = results.getString("dateOfBirth");
                String ppsNo = results.getString("ppsNo");
                String email = results.getString("email");
                boolean married = results.getBoolean("married");
                boolean admin = results.getBoolean("admin");
                
                User user = new User(firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married, adminAccess);
            }
        } catch (SQLException e) {
            System.out.println("Error identifying the user: " + e.getMessage());
        }*/


    }

}
