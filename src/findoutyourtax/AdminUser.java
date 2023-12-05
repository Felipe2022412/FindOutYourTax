/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

import database.DatabaseReader;
import database.DatabaseSetup;
import database.DatabaseWriter;
import ioutils.IOUtils;
import java.util.ArrayList;

/**
 * Class for the Admin User with its methods - extends the class User and
 * implements its specifics interfaces to use the methods modify and view.
 *
 * @author dougl
 */
public class AdminUser extends User implements DisplayMenu {

    DatabaseWriter databaseWriter;
    DatabaseReader databaseReader;
    ModifyProfileInterface modifyProfile;

    public AdminUser() {
    }
    

    public AdminUser(String firstName, String lastName, String userName, String password, String dateOfBirth, String ppsNo, String email, boolean married) {
        super(firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);
        this.setAdminAccess(true);
    }

    public AdminUser(int userId, String firstName, String lastName) {
        super(userId, firstName, lastName);
        this.setAdminAccess(true);
    }

    public AdminUser(int userId, String firstName, String lastName, String userName, String password, String dateOfBirth, String ppsNo, String email, boolean married) {
        super(userId, firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);
        this.setAdminAccess(true);
    }

    public AdminUser(int userId, String firstName, String lastName, String userName, String dateOfBirth, String ppsNo, String email, boolean married) {
        super(userId, firstName, lastName, userName, dateOfBirth, ppsNo, email, married);
        this.setAdminAccess(true);
    }

    @Override
    public void displayMenu(User user) {
        //Initialize necessary objects and variables
        IOUtils input = new IOUtils();
        int option;

        try {
            modifyProfile = new ModifyProfile();
            databaseWriter = new DatabaseWriter();
            databaseReader = new DatabaseReader();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("Error to display the menu.");
        }

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
    }

}
