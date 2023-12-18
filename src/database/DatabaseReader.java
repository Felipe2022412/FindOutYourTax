/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import user.AdminUser;
import user.RegularUser;
import user.User;
import user.UserTaxes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class is responsible for reading data from the database. It extends the
 * 'Database' class and uses SQL queries to fetch user and tax information.
 *
 * @author Douglas and Felipe
 */
public class DatabaseReader extends Database {

    private Connection conn;

    /**
     * Constructor for DatabaseReader.Establishes a connection to the database.
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public DatabaseReader() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            conn = new DatabaseConection().connectToDB();
        } catch (SQLException ex) {
            System.out.println("Error to conect to the Database");
        }
    }

    /**
     * Retrieves a user from the database based on the provided username and
     * password.Determines if the user is an admin or regular user and returns
     * the appropriate object.
     *
     * @param userName
     * @param password
     * @return user
     */
    public User getUser(String userName, String password) {

        User user = null;

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            String query = String.format("SELECT userID, admin, firstName, lastName, dateOfBirth, ppsNo, email, married FROM %s WHERE userName = '%s' AND BINARY password = '%s';", TABLE_NAME_USERINFO, userName, password);

            ResultSet results = stmt.executeQuery(query);

            if (results.next() && results.getBoolean("admin")) {
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
            System.out.println("Error identifying the user");
        }

        return user;

    }

    /**
     * Retrieves a user from the database based on the user ID. This method is
     * intended for admin users only.
     */
    /*public User getUser(int userId) {

        User user = null;

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            String query = String.format("SELECT userID, admin, firstName, lastName FROM %s WHERE userId = '%d';", TABLE_NAME_USERINFO, userId);

            ResultSet results = stmt.executeQuery(query);

            if (results.next()) {
                int userID = results.getInt("userID");
                String firstName = results.getString("firstName");
                String lastName = results.getString("lastName");

                // Create an AdminUser
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
    }*/
    /**
     * Retrieves a list of all users from the database.The list includes both
     * admin and regular users.
     *
     * @return ArrayList of users
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            String query = String.format("SELECT userID, admin, firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married FROM %s;", TABLE_NAME_USERINFO);

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

    /**
     * Retrieves tax information for a specific user.This method returns a list
     * of 'UserTaxes' objects for the given user, which are the operations he
     * made.
     *
     * @param user
     * @return ArrayList of the user taxes
     */
    public ArrayList<UserTaxes> getAllTaxes(User user) {

        ArrayList<UserTaxes> userTaxesList = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            String query = String.format("SELECT userID, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits, totalTaxesDue, liquidAmount FROM %s WHERE userID = %d;", TABLE_NAME_USERTAXINFO, user.getUserId());

            ResultSet results = stmt.executeQuery(query);

            while (results.next()) {
                int userID = results.getInt("userID");
                double grossIncome = results.getDouble("grossIncome");
                double taxCredits = results.getDouble("taxCredits");
                double partnerGrossIncome = results.getDouble("partnerGrossIncome");
                double partnerTaxCredits = results.getDouble("partnerTaxCredits");
                double totalTaxesDue = results.getDouble("totalTaxesDue");
                double liquidAmount = results.getDouble("liquidAmount");

                // Create a new UserTaxes object for each row and add it to the list
                UserTaxes userTaxes = new UserTaxes(user, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits, totalTaxesDue, liquidAmount);
                userTaxesList.add(userTaxes);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all taxes: " + e.getMessage());
        }

        return userTaxesList;
    }

    /**
     * Retrieves tax information for all users, for the admin to see. TThis
     * method is used to get a comprehensive list of tax details for all users.h
     *
     * @return ArrayList of users taxes
     */
    public ArrayList<UserTaxes> getAllTaxesAllUsers() {
        ArrayList<UserTaxes> allUsersTaxes = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            String query = String.format("SELECT T.userID, T.grossIncome, T.taxCredits, T.partnerGrossIncome, T.partnerTaxCredits, T.totalTaxesDue, T.liquidAmount, "
                    + "U.userID, U.firstName, U.lastName, U.userName, U.dateOfBirth, U.ppsNo, U.email, U.married "
                    + "FROM %s T "
                    + "JOIN %s U ON T.userID = U.userID "
                    + "WHERE U.admin = false;", TABLE_NAME_USERTAXINFO, TABLE_NAME_USERINFO);

            ResultSet results = stmt.executeQuery(query);

            while (results.next()) {
                int userID = results.getInt("userID");
                double grossIncome = results.getDouble("grossIncome");
                double taxCredits = results.getDouble("taxCredits");
                double partnerGrossIncome = results.getDouble("partnerGrossIncome");
                double partnerTaxCredits = results.getDouble("partnerTaxCredits");
                double totalTaxesDue = results.getDouble("totalTaxesDue");
                double liquidAmount = results.getDouble("liquidAmount");

                // Retrieve user information
                int userUserID = results.getInt("U.userID");
                String firstName = results.getString("U.firstName");
                String lastName = results.getString("U.lastName");
                String userName = results.getString("U.userName");
                String dateOfBirth = results.getString("U.dateOfBirth");
                String ppsNo = results.getString("U.ppsNo");
                String email = results.getString("U.email");
                boolean married = results.getBoolean("U.married");

                // Create User object
                User user = new RegularUser(userUserID, firstName, lastName, userName, dateOfBirth, ppsNo, email, married);

                // Create UserTaxes object
                UserTaxes userTaxes = new UserTaxes(user, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits, totalTaxesDue, liquidAmount);

                // Add to the list
                allUsersTaxes.add(userTaxes);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all taxes from all users: " + e.getMessage());
        }

        return allUsersTaxes;
    }

    /*public int getCurrentIDFromDatabase() {
        int lastUsedID = 0;
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            String query = "SELECT MAX(userID) FROM " + TABLE_NAME_USERINFO + ";";
            ResultSet results = stmt.executeQuery(query);

            if (results.next()) {
                lastUsedID = results.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error getting current ID: " + e.getMessage());
        }
        return lastUsedID;
    }*/
    /**
     * Checks if a user already exists in the database based on username or PPS
     * number.Returns true if the user exists, false otherwise.
     * @param userName
     * @param ppsNo
     * @return boolean
     */
    public boolean userExists(String userName, String ppsNo) {
        for (User userInDB : getAllUsers()) {
            if (userInDB.getUserName().equals(userName) || userInDB.getPpsNo().equals(ppsNo)) {
                return true;
            }
        }
        return false;
    }

}
