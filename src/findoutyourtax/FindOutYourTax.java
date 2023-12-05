/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package findoutyourtax;

import database.DatabaseSetup;
import database.DatabaseWriter;
import database.DatabaseReader;
import ioutils.IOUtils;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author felip
 */
public class FindOutYourTax {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        // TODO code application logic here

        //Initialize necessary objects and variables
        IOUtils input = new IOUtils();
        DatabaseWriter databaseWriter = new DatabaseWriter();
        User user;
        UserTaxes userTaxes;

        //Interfaces
        ModifyProfileInterface modifyProfile = new ModifyProfile();
        //RegularUser = regularUser = new RegularUser
        int option;

        //test database
        DatabaseSetup database = new DatabaseSetup();
        database.setupDB();
        DatabaseReader databaseReader = new DatabaseReader();
        System.out.println("===== Welcome to Find out Your tax =====");
        do {
            System.out.println(" 1 - Log In \n 2 - Sing Up \n 3 - Exit");
            option = input.getUserInt("Enter one of the options above:", 1, 3);
            boolean userExists = true;
            switch (option) {

                case 1:
                    //User log in
                    String userNameLogin;
                    String passwordLogin;

                    do {

                        userNameLogin = input.getUserText("Enter your user name:");
                        passwordLogin = input.basicInput("Enter your password:");

                        try {
                            user = databaseReader.getUser(userNameLogin, passwordLogin);

                            if (user.isAdminAccess()) {
                                userExists = false;
                                System.out.println(">>>>> Welcome " + user.getFirstName() + " <<<<<");
                                System.out.println("You have admin access.");

                                do {
                                    System.out.println("---- MENU ----");
                                    option = input.getUserInt("Enter the number of the respective task you want to do:\n"
                                            + "1 - view profile\n"
                                            + "2 - Modify profile\n"
                                            + "3 - Access a list of all other users in the system\n"
                                            + "4 - Remove other users from the system\n"
                                            + "5 - Review the operations performed by other users\n"
                                            + "6 - Exit", 1, 6);

                                    switch (option) {
                                        case 1:
                                            System.out.println(user.toString());
                                            break;
                                        case 2:
                                            modifyProfile.modifyProfile(user);
                                            break;
                                        case 3:
                                            ArrayList<User> usersList = databaseReader.getAllUsers();
                                            if (usersList.isEmpty()) {
                                                System.out.println("No users are registered.");
                                            } else {
                                                System.out.println("List of Users:");
                                                for (User userInDB : usersList) {
                                                    System.out.println(userInDB.toString());
                                                    System.out.println();
                                                }
                                            }
                                            break;
                                        case 4:
                                            ArrayList<User> users = databaseReader.getAllUsers();

                                            if (users.size() == 1) {
                                                System.out.println("No users registered to be removed.");
                                            } else {

                                                int count = 1;
                                                for (User userInDB : users) {
                                                    if (!userInDB.isAdminAccess()) {//this prevents the user to remove and Admin user
                                                        System.out.println("List of Users:");
                                                        System.out.println(count + " - " + userInDB.toString());
                                                        System.out.println();
                                                        count++;
                                                    }
                                                }
                                                int userToBeRemove = input.getUserInt("Enter the user ID to be revoved:");

                                                if (databaseWriter.removeUser(userToBeRemove)) {
                                                    System.out.println("User removed.");
                                                } else {
                                                    System.out.println("Error to remove the user.");
                                                }
                                            }
                                            break;
                                        case 5:
                                            ArrayList<UserTaxes> usersOperations = databaseReader.getAllTaxesAllUsers();
                                            int count = 1;
                                            if (usersOperations.isEmpty()) {
                                                System.out.println("No tax information available.");
                                            } else {
                                                System.out.println("List of operations:");
                                                for (UserTaxes userInDB : usersOperations) {
                                                    System.out.println(userInDB.getUser().getFirstName());
                                                    System.out.println(count + " - " + userInDB.toString());
                                                    System.out.println();
                                                    count++;
                                                }
                                            }
                                            break;
                                        case 6:
                                            System.out.println("Bye. See you soon.");
                                            break;
                                        default:
                                            throw new AssertionError();
                                    }
                                } while (option != 6);

                            } else if (!user.isAdminAccess()) {
                                userExists = false;
                                System.out.println(">>>>> Welcome " + user.getFirstName() + " <<<<<");
                                System.out.println("You are a regular user.\n");
                                do {
                                    System.out.println("---- MENU ----");
                                    option = input.getUserInt("Enter the number of the respective task you want to do:\n"
                                            + "1 - view profile\n"
                                            + "2 - Modify profile\n"
                                            + "3 - Calculate taxes\n"
                                            + "4 - see previously calculation\n"
                                            + "5 - exit", 1, 5);
                                    switch (option) {
                                        case 1:
                                            System.out.println(user.toString());
                                            break;
                                        case 2:
                                            modifyProfile.modifyProfile(user);
                                            break;
                                        case 3:
                                            int saveOption;
                                            do {
                                                double grossIncome = input.getUserDecimal("Enter your gross income:");
                                                double taxCredits = input.getUserDecimal("Enter your tax credits:");
                                                if (user.isMarried()) {
                                                    double partnerGrossIncome = input.getUserDecimal("Enter your partner gross income:");
                                                    double partnerTaxCredits = input.getUserDecimal("Enter your partner tax credits:");
                                                    userTaxes = new UserTaxes(user, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits);
                                                } else {
                                                    userTaxes = new UserTaxes(user, grossIncome, taxCredits);
                                                }
                                                System.out.println(userTaxes.toString());

                                                saveOption = input.getUserInt(" ---- MENU ----\n"
                                                        + "1 - Save operations\n"
                                                        + "2 - Calculate again\n"
                                                        + "3 - Cancel operation\n", 1, 3);
                                                switch (saveOption) {
                                                    case 1:
                                                        databaseWriter.saveUserOperations(userTaxes);
                                                        System.out.println("Operations saved");
                                                        break;
                                                    case 2:
                                                        continue;

                                                    case 3:
                                                        System.out.println("Operation canceled.");
                                                        break;
                                                    default:
                                                        throw new AssertionError();
                                                }
                                                // Break out of the loop when the user chooses to save operations
                                                if (saveOption == 1) {
                                                    break;
                                                }
                                            } while (saveOption != 3);

                                            
                                            break;
                                        case 4:
                                            ArrayList<UserTaxes> usersOperation = databaseReader.getAllTaxes(user);
                                            int count = 1;

                                            if (usersOperation.isEmpty()) {
                                                System.out.println("No tax information available.");
                                            } else {
                                                System.out.println("List of operations:");

                                                for (UserTaxes allUserTaxes : usersOperation) {
                                                    System.out.println(allUserTaxes.getUser().getFirstName());
                                                    System.out.println(count + " - " + allUserTaxes.toString());
                                                    System.out.println();
                                                    count++;
                                                }
                                            }
                                            break;

                                        case 5:
                                            System.out.println("Bye. See you soon.");
                                            break;
                                        default:
                                            throw new AssertionError();
                                    }

                                } while (option != 5);
                            }
                        } catch (Exception e) {
                            System.out.println("Username or password incorect.");
                        }
                    } while (userExists);

                    break;
                case 2:
                    SingUpInterface singInMenu = new SignUpMenu();
                    singInMenu.signUp();
                    break;
                case 3:
                    System.out.println("Bye. See you soon!");
                    break;
                default:
                    throw new AssertionError();
            }

        } while (option != 3);
    }

}
