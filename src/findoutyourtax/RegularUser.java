/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

import database.DatabaseReader;
import database.DatabaseWriter;
import ioutils.IOUtils;
import java.util.ArrayList;

/**
 * Class for the Regular User with its methods - extends the class User and
 * implements its specifics interfaces to use the methods modify and view.
 *
 * @author dougl
 */
public class RegularUser extends User implements DisplayMenu {

    DatabaseWriter databaseWriter;
    DatabaseReader databaseReader;
    ModifyProfileInterface modifyProfile;

    public RegularUser(String firstName, String lastName, String userName, String password, String dateOfBirth, String ppsNo, String email, boolean married) {
        super(firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);
    }

    public RegularUser(int userId, String firstName, String lastName, String userName, String password, String dateOfBirth, String ppsNo, String email, boolean married) {
        super(userId, firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);

    }

    public RegularUser(int userId, String firstName, String lastName, String userName, String dateOfBirth, String ppsNo, String email, boolean married) {
        super(userId, firstName, lastName, userName, dateOfBirth, ppsNo, email, married);
    }

    public RegularUser() {
    }

    @Override
    public void displayMenu(User user) {

        //Initialize necessary objects and variables
        IOUtils input = new IOUtils();
        int option;
        UserTaxes userTaxes;

        try {
            modifyProfile = new ModifyProfile();
            databaseWriter = new DatabaseWriter();
            databaseReader = new DatabaseReader();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("Error to display the menu.");
        }

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
                            System.out.println("-----------------------------------");
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

        }while (option != 5);

    }
}
