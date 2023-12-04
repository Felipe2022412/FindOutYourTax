/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import findoutyourtax.AdminUser;
import findoutyourtax.RegularUser;
import findoutyourtax.User;
import findoutyourtax.UserTaxes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            String query = String.format("SELECT userID, admin, firstName, lastName, dateOfBirth, ppsNo, email, married FROM %s WHERE userName = '%s' AND password = '%s';", TABLE_NAME_USERDATA, userName, password);

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

                User adminUser = new AdminUser(userID, firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);
                user = adminUser;
            } else {
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

    }

    //public AdminUser(int userId, String firstName, String lastName)
    //considering the user is admin!! FOR ADMIN ONLY
    public User getUser(int userId) {

        User user = null;

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            //String query = String.format("SELECT admin FROM %s WHERE userName = '%s' AND password = '%s';", TABLE_NAME, userName, password);
            String query = String.format("SELECT userID, admin, firstName, lastName FROM %s WHERE userId = '%d';", TABLE_NAME_USERDATA, userId);

            ResultSet results = stmt.executeQuery(query);

            //isAdmin = true;
            if (results.next()) {
                int userID = results.getInt("userID");
                String firstName = results.getString("firstName");
                String lastName = results.getString("lastName");

                // Create an AdminUser instance
                User adminUser = new AdminUser(userID, firstName, lastName);
                user = adminUser;
            } else {
                // Handle the case where no user with the specified userId is found
                System.out.println("No user found with userId: " + userId);
            }
        } catch (SQLException e) {
            System.out.println("Error identifying the user: " + e.getMessage());
        }

        return user;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            String query = String.format("SELECT userID, admin, firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married FROM %s;", TABLE_NAME_USERDATA);

            ResultSet results = stmt.executeQuery(query);

            while (results.next()) {
                int userID = results.getInt("userID");
                String firstName = results.getString("firstName");
                String lastName = results.getString("lastName");
                String userName = results.getString("userName");
                String dateOfBirth = results.getString("dateOfBirth");
                String ppsNo = results.getString("ppsNo");
                String email = results.getString("email");
                boolean married = results.getBoolean("married");

                if (results.getBoolean("admin")) {
                    users.add(new AdminUser(userID, firstName, lastName, userName, dateOfBirth, ppsNo, email, married));
                } else {
                    users.add(new RegularUser(userID, firstName, lastName, userName, dateOfBirth, ppsNo, email, married));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all users: " + e.getMessage());
        }

        return users;
    }

    public UserTaxes getAllTaxes(User user) {

        UserTaxes userTaxes = null;
        user = this.getUser(user.getUserId());

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            String query = String.format("SELECT userID, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits, totalTaxesDue, liquidAmount FROM %s;", TABLE_NAME_TAXINFO);

            ResultSet results = stmt.executeQuery(query);

            while (results.next()) {
                int userID = results.getInt("userID");
                double grossIncome = results.getDouble("grossIncome");
                double taxCredits = results.getDouble("taxCredits");
                double partnerGrossIncome = results.getDouble("partnerGrossIncome");
                double partnerTaxCredits = results.getDouble("partnerTaxCredits");
                double totalTaxesDue = results.getDouble("totalTaxesDue");
                double liquidAmount = results.getDouble("liquidAmount");

                // Use the user object directly in the UserTaxes constructor
                userTaxes = new UserTaxes(user, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits, totalTaxesDue, liquidAmount);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all taxes: " + e.getMessage());
        }

        return userTaxes;
    }

    public ArrayList<UserTaxes> getAllTaxesAllUsers(User user) {

        ArrayList<UserTaxes> allUsersTaxes = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            String query = String.format("SELECT userID, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits, totalTaxesDue, liquidAmount FROM %s;", TABLE_NAME_TAXINFO);

            ResultSet results = stmt.executeQuery(query);

            while (results.next()) {
                int userID = results.getInt("userID");
                double grossIncome = results.getDouble("grossIncome");
                double taxCredits = results.getDouble("taxCredits");
                double partnerGrossIncome = results.getDouble("partnerGrossIncome");
                double partnerTaxCredits = results.getDouble("partnerTaxCredits");
                double totalTaxesDue = results.getDouble("totalTaxesDue");
                double liquidAmount = results.getDouble("liquidAmount");

                // Use the user object directly in the UserTaxes constructor
                allUsersTaxes.add(new UserTaxes(user, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits, totalTaxesDue, liquidAmount));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all taxes from all users: " + e.getMessage());
        }

        return allUsersTaxes;
    }

}
