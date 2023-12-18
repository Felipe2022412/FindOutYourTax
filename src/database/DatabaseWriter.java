/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import user.User;
import user.UserTaxes;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import static database.Database.TABLE_NAME_USERINFO;
import static database.Database.TABLE_NAME_USERTAXINFO;

/**
 * This class is responsible for writing and updating data in the database. It
 * includes methods to register, modify, remove users and save user operations.
 *
 * @author Douglas and Felipe
 */
public class DatabaseWriter extends Database {

    private Connection conn;

    /**
     * Constructor for DatabaseWriter.Establishes a connection to the database.
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public DatabaseWriter() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            conn = new DatabaseConection().connectToDB();
        } catch (SQLException ex) {
            System.out.println("Error to conect to the Database");
        }
    }

    /**
     * Registers a new user in the database.Inserts user data into the userinfo
     * table.
     *
     * @param user
     */
    public void registerUser(User user) {
        try {
            Statement stmt = conn.createStatement();
            // SQL query to insert data into the UserData table
            stmt.execute("USE " + DB_NAME + ";");
            String sql = String.format("INSERT INTO " + TABLE_NAME_USERINFO + " VALUES ('%d', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %b, %b);",
                    user.getUserId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword(), user.getDateOfBirth(), user.getPpsNo(), user.getEmail(), user.isMarried(), user.isAdminAccess());

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void modifyUser(User user) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");

            String sql = String.format("UPDATE %s SET "
                    + "firstName = '%s', "
                    + "lastName = '%s', "
                    + "userName = '%s', "
                    + "password = '%s', "
                    + "dateOfBirth = '%s', "
                    + "ppsNo = '%s', "
                    + "email = '%s', "
                    + "married = %b, "
                    + "admin = %b "
                    + "WHERE userID = %d;",
                    TABLE_NAME_USERINFO, user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword(),
                    user.getDateOfBirth(), user.getPpsNo(), user.getEmail(), user.isMarried(), user.isAdminAccess(),
                    user.getUserId());

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Modifies the details of an existing user in the database.Updates user
     * data in the userinfo table.
     *
     * @return boolean
     * @param userId
     */
    public boolean removeUser(int userId) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");

            // Check if the user exists before proceeding with deletion
            String sqlCheckUserExistence = String.format("SELECT COUNT(*) FROM %s WHERE userID = %d;", TABLE_NAME_USERINFO, userId);
            ResultSet userExistenceResult = stmt.executeQuery(sqlCheckUserExistence);

            if (userExistenceResult.next() && userExistenceResult.getInt(1) == 0) {
                // The user does not exist
                System.out.println("User does not exist. No action taken.");
                return false;
            }

            // Check if the user is the admin before proceeding with deletion
            String sqlCheckAdmin = String.format("SELECT admin FROM %s WHERE userID = %d;", TABLE_NAME_USERINFO, userId);
            ResultSet adminCheckResult = stmt.executeQuery(sqlCheckAdmin);

            if (adminCheckResult.next() && adminCheckResult.getBoolean("admin")) {
                // The user is an admin, don't allow deletion
                System.out.println("Cannot delete admin user.");
                return false;
            }

            // Delete associated records from taxinfo table
            String sqlRemoveOperations = String.format("DELETE FROM %s WHERE userID = %d;", TABLE_NAME_USERTAXINFO, userId);
            stmt.execute(sqlRemoveOperations);

            // Delete the user from userdata table
            String sqlRemoveUser = String.format("DELETE FROM %s WHERE userID = %d;", TABLE_NAME_USERINFO, userId);
            stmt.execute(sqlRemoveUser);

            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean saveUserOperations(UserTaxes userTaxes) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");

            // Assuming TaxInfo table structure matches UserTaxes fields.
            String sql = String.format("INSERT INTO " + TABLE_NAME_USERTAXINFO + " (userID, grossIncome, taxCredits, incomeAfterCredits, partnerGrossIncome, partnerTaxCredits, partnerIncomeAfterCredits, coupleTotalIncomeAfterCredits, totalTaxesDue, liquidAmount) "
                    + "VALUES (%d, %f, %f, %f, %f, %f, %f, %f, %f, %f);",
                    userTaxes.getUser().getUserId(),
                    userTaxes.getGrossIncome(),
                    userTaxes.getTaxCredits(),
                    userTaxes.getIncomeAfterCredits(),
                    userTaxes.getPartnerGrossIncome(),
                    userTaxes.getPartnerTaxCredits(),
                    userTaxes.getPartnerIncomeAfterCredits(),
                    userTaxes.getCoupleTotalIncomeAfterCredits(),
                    userTaxes.getTotalTaxesDue(),
                    userTaxes.getLiquidAmount());

            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println("Error saving user operations: " + e.getMessage());
            return false;
        }
    }

}
