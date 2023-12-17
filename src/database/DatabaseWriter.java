/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import findoutyourtax.User;
import findoutyourtax.UserTaxes;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static database.Database.TABLE_NAME_USERDATA;
import static database.Database.TABLE_NAME_TAXINFO;
import java.sql.ResultSet;

/**
 *
 * @author Douglas and Felipe
 */
public class DatabaseWriter extends Database {

    private Connection conn;

    public DatabaseWriter() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            conn = new DatabaseConection().connectToDB();
        } catch (SQLException ex) {
            System.out.println("Error to conect to the Database");
        }
    }

    public void registerUser(User user) {
        try {
            Statement stmt = conn.createStatement();
            // SQL query to insert data into the UserData table
            stmt.execute("USE " + DB_NAME + ";");
            String sql = String.format("INSERT INTO " + TABLE_NAME_USERDATA + " VALUES ('%d', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %b, %b);",
                    user.getUserId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword(), user.getDateOfBirth(), user.getPpsNo(), user.getEmail(), user.isMarried(), user.isAdminAccess());

            stmt.execute(sql);
        } catch (Exception e) {
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
                    TABLE_NAME_USERDATA, user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword(),
                    user.getDateOfBirth(), user.getPpsNo(), user.getEmail(), user.isMarried(), user.isAdminAccess(),
                    user.getUserId());

            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public boolean removeUser(int userId) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");

            // Check if the user exists before proceeding with deletion
            String sqlCheckUserExistence = String.format("SELECT COUNT(*) FROM %s WHERE userID = %d;", TABLE_NAME_USERDATA, userId);
            ResultSet userExistenceResult = stmt.executeQuery(sqlCheckUserExistence);

            if (userExistenceResult.next() && userExistenceResult.getInt(1) == 0) {
                // The user does not exist
                System.out.println("User does not exist. No action taken.");
                return false;
            }

            // Check if the user is the admin before proceeding with deletion
            String sqlCheckAdmin = String.format("SELECT admin FROM %s WHERE userID = %d;", TABLE_NAME_USERDATA, userId);
            ResultSet adminCheckResult = stmt.executeQuery(sqlCheckAdmin);

            if (adminCheckResult.next() && adminCheckResult.getBoolean("admin")) {
                // The user is an admin, don't allow deletion
                System.out.println("Cannot delete admin user.");
                return false;
            }

            // Delete associated records from taxinfo table
            String sqlRemoveOperations = String.format("DELETE FROM %s WHERE userID = %d;", TABLE_NAME_TAXINFO, userId);
            stmt.execute(sqlRemoveOperations);

            // Delete the user from userdata table
            String sqlRemoveUser = String.format("DELETE FROM %s WHERE userID = %d;", TABLE_NAME_USERDATA, userId);
            stmt.execute(sqlRemoveUser);

            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean saveUserOperations(UserTaxes userTaxes) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");

            // Assuming TaxInfo table structure matches UserTaxes fields.
            String sql = String.format("INSERT INTO " + TABLE_NAME_TAXINFO + " (userID, grossIncome, taxCredits, incomeAfterCredits, partnerGrossIncome, partnerTaxCredits, partnerIncomeAfterCredits, coupleTotalIncomeAfterCredits, totalTaxesDue, liquidAmount) "
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
