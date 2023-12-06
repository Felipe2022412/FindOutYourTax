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

// Verificar se o usuário é um administrador e se existe no banco de dados na hora do login
    public User getUser(String userName, String password) {

        //boolean isAdmin = false;
        User user = null;

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            //String query = String.format("SELECT admin FROM %s WHERE userName = '%s' AND password = '%s';", TABLE_NAME, userName, password);
            String query = String.format("SELECT userID, admin, firstName, lastName, dateOfBirth, ppsNo, email, married FROM %s WHERE userName = '%s' AND BINARY password = '%s';", TABLE_NAME_USERDATA, userName, password);//BINARY means case sensitive for the password

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
            System.out.println("Error identifying the user");
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

    public ArrayList<UserTaxes> getAllTaxes(User user) {
        ArrayList<UserTaxes> userTaxesList = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");

            String query = String.format(
                    "SELECT DISTINCT T.userID, T.grossIncome, T.taxCredits, "
                    + "P.partnerGrossIncome, P.partnerTaxCredits " // Added a space here
                    + "FROM %s T "
                    + "LEFT JOIN %s P ON T.userID = P.userID "
                    + "WHERE T.userID = %d;",
                    TABLE_NAME_TAXINFO, TABLE_NAME_PARTNER_TAXINFO, user.getUserId()
            );

            ResultSet results = stmt.executeQuery(query);

            while (results.next()) {

                if (user.isMarried()) {

                    double grossIncome = results.getDouble("grossIncome");
                    double taxCredits = results.getDouble("taxCredits");
                    double partnerGrossIncome = results.getDouble("partnerGrossIncome");
                    double partnerTaxCredits = results.getDouble("partnerTaxCredits");

                    // Create UserTaxes object
                    UserTaxes userTaxes = new UserTaxes(user, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits);
                    userTaxesList.add(userTaxes);
                    System.out.println("USER ADD casado");

                } else {
                    double grossIncome = results.getDouble("grossIncome");
                    double taxCredits = results.getDouble("taxCredits");
                    UserTaxes userTaxes = new UserTaxes(user, grossIncome, taxCredits);

                    userTaxesList.add(userTaxes);
                    System.out.println("USER ADD single");

                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all taxes: " + e.getMessage());
        }

        return userTaxesList;
    }

    public ArrayList<UserTaxes> getAllTaxesAllUsers() {
        ArrayList<UserTaxes> allUsersTaxes = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");

            String query = String.format(
                    "SELECT DISTINCT U.userID, U.firstName, U.lastName, U.userName, U.dateOfBirth, U.ppsNo, U.email, U.married, "
                    + "T.grossIncome, T.taxCredits, P.partnerGrossIncome, P.partnerTaxCredits "
                    + "FROM %s U "
                    + "LEFT JOIN %s T ON U.userID = T.userID "
                    + "LEFT JOIN %s P ON U.userID = P.userID "
                    + "WHERE U.admin = false;",
                    TABLE_NAME_USERDATA, TABLE_NAME_TAXINFO, TABLE_NAME_PARTNER_TAXINFO
            );

            ResultSet results = stmt.executeQuery(query);
            //nao esta mostrando resultados dos solteiros e dos casados mostra duas vezes
            //tem a ver com o construtor se e casado ou solteiro

            while (results.next()) {

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

                if (married) {

                    double grossIncome = results.getDouble("grossIncome");
                    double taxCredits = results.getDouble("taxCredits");
                    double partnerGrossIncome = results.getDouble("partnerGrossIncome");
                    double partnerTaxCredits = results.getDouble("partnerTaxCredits");

                    // Create UserTaxes object
                    UserTaxes userTaxes = new UserTaxes(user, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits);
                    allUsersTaxes.add(userTaxes);
                    System.out.println("USER ADD");

                } else {
                    double grossIncome = results.getDouble("grossIncome");
                    double taxCredits = results.getDouble("taxCredits");
                    UserTaxes userTaxes = new UserTaxes(user, grossIncome, taxCredits);

                    allUsersTaxes.add(userTaxes);
                    System.out.println("USER ADD");

                }

                // Add to the list
            }
        } catch (SQLException e) {
            System.out.println("Error getting all taxes from all users: " + e.getMessage());
        }

        return allUsersTaxes;
    }

    public int getCurrentIDFromDatabase() {
        int lastUsedID = 0;
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + DB_NAME + ";");
            String query = "SELECT MAX(userID) FROM " + TABLE_NAME_USERDATA + ";";
            ResultSet results = stmt.executeQuery(query);

            if (results.next()) {
                lastUsedID = results.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error getting current ID: " + e.getMessage());
        }
        return lastUsedID;
    }

    public boolean userExists(String userName, String ppsNo) {
        for (User userInDB : getAllUsers()) {
            if (userInDB.getUserName().equals(userName) || userInDB.getPpsNo().equals(ppsNo)) {
                return true;
            }
        }
        return false;
    }

}
