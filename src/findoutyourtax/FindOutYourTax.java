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
        //RegularUser = regularUser = new RegularUser
        int option;

        //test database
        DatabaseSetup database = new DatabaseSetup();
        database.setupDB();
        DatabaseReader databaseReader = new DatabaseReader();
        System.out.println("===== Welcome to Find out Your tax =====");
        do {
            System.out.println(" 1 - Log In \n 2 - Sing In \n 3 - Exit");
            option = input.getUserInt("Enter one of the options above:", 1, 3);
            switch (option) {
                case 1:
                    //User log in
                    String userNameLogin;
                    String passwordLogin;

                    userNameLogin = input.getUserText("Enter your user name:");
                    passwordLogin = input.basicInput("Enter your password:");

                    user = databaseReader.getUser(userNameLogin, passwordLogin);
                    if (user.isAdminAccess() == true) {
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
                                    int optionModdify;
                                    optionModdify = input.getUserInt("---- Enter the number of the info you want to change ----\n"
                                            + "1 - First name\n"
                                            + "2 - Last name\n"
                                            + "3 - Password\n"
                                            + "4 - Date of birth\n"
                                            + "5 - PPSNO\n"
                                            + "6 - Email\n"
                                            + "7 - Marital status\n"
                                            + "8 - Cancell\n");
                                    switch (optionModdify) {
                                        case 1:
                                            String newFirstName = input.getUserText("Enter your new name:");
                                            user.setFirstName(newFirstName);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("Name updated.");
                                            break;
                                        case 2:
                                            String newLastName = input.getUserText("Enter your new last name:");
                                            user.setLastName(newLastName);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("Last name updated.");
                                            break;
                                        case 3:
                                            String newPassword = input.getUserPassword("Enter your new password:");
                                            user.setPassword(newPassword);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("Password updated.");
                                            break;
                                        case 4:
                                            String newDateOfBirth = input.getUserDateOfBirth("Enter your new date of birth (AAAA-MM-DD):");
                                            user.setDateOfBirth(newDateOfBirth);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("Date of birth updated.");
                                            break;
                                        case 5:
                                            String newPpsNo = input.getUserPPSN("Enter your new PPSNO:");
                                            user.setPpsNo(newPpsNo);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("PPSNO updated.");
                                            break;
                                        case 6:
                                            String newEmail = input.getUserEmail("Enter your new email:");
                                            user.setEmail(newEmail);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("Email updated.");
                                            break;
                                        case 7:
                                            boolean isMarried = input.getUserBollean("If you are married enter 1, if not enter 2:", 1, 2);
                                            user.setMarried(isMarried);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("Marital status updated.");
                                            break;
                                        case 8:
                                            System.out.println("Cancelling modification.");
                                            break;
                                        default:
                                            throw new AssertionError();
                                    }

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
                                    break;
                                case 6:
                                    System.out.println("Bye. See you soon.");
                                    break;
                                default:
                                    throw new AssertionError();
                            }

                        } while (option != 6);

                    } else {
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
                                    int optionModdify;

                                    optionModdify = input.getUserInt("---- Enter the number of the info you want to change ----\n"
                                            + "1 - First name\n"
                                            + "2 - Last name\n"
                                            + "3 - Password\n"
                                            + "4 - Date of birth\n"
                                            + "5 - PPSNO\n"
                                            + "6 - Email\n"
                                            + "7 - Marital status\n"
                                            + "8 - Cancel\n");
                                    switch (optionModdify) {
                                        case 1:
                                            String newFirstName = input.getUserText("Enter your new name:");
                                            user.setFirstName(newFirstName);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("Name updated.");
                                            break;
                                        case 2:
                                            String newLastName = input.getUserText("Enter your new last name:");
                                            user.setLastName(newLastName);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("Last name updated.");
                                            break;
                                        case 3:
                                            String newPassword = input.getUserPassword("Enter your new password:");
                                            user.setPassword(newPassword);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("Password updated.");
                                            break;
                                        case 4:
                                            String newDateOfBirth = input.getUserDateOfBirth("Enter your new date of birth (AAAA-MM-DD):");
                                            user.setDateOfBirth(newDateOfBirth);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("Date of birth updated.");
                                            break;
                                        case 5:
                                            String newPpsNo = input.getUserPPSN("Enter your new PPSNO:");
                                            user.setPpsNo(newPpsNo);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("PPSNO updated.");
                                            break;
                                        case 6:
                                            String newEmail = input.getUserEmail("Enter your new email:");
                                            user.setEmail(newEmail);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("Email updated.");
                                            break;
                                        case 7:
                                            boolean isMarried = input.getUserBollean("If you are married enter 1, if not enter 2:", 1, 2);
                                            user.setMarried(isMarried);
                                            databaseWriter.modifyUser(user);
                                            System.out.println("Marital status updated.");
                                            break;
                                        case 8:
                                            System.out.println("Cancelling modification.");
                                            break;
                                        default:
                                            throw new AssertionError();
                                    }

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

                                    break;
                                case 5:
                                    System.out.println("Bye. See you soon.");
                                    break;
                                default:
                                    throw new AssertionError();
                            }

                        } while (option != 5);
                    }
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
