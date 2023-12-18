/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user;

import database.DatabaseReader;
import database.DatabaseWriter;
import utils.CSVFileReader;
import menus.DisplayMenu;
import profile.ModifyProfile;
import profile.ModifyProfileInterface;
import utils.IOUtils;
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
    //All constructors from the User

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

    //Call the displayMenu for the Regularuser
    @Override
    public void displayMenu(User user) {

        //Initialize necessary objects and variables
        IOUtils input = new IOUtils();
        int option;
        UserTaxes userTaxes;
        //inicialize the interfaces and objects that need to be inside the try catch
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
                    + "1 - View profile\n"
                    + "2 - Modify profile\n"
                    + "3 - Calculate taxes\n"
                    + "4 - Import CSV file\n"
                    + "5 - See previously calculation\n"
                    + "6 - Exit", 1, 6);
            switch (option) {
                case 1:
                    System.out.println(user.toString());//View the profile
                    break;
                case 2:
                    modifyProfile.modifyProfile(user);//call the modify interface so the user can modify the profile
                    break;
                case 3:
                    int saveOption;//Will store the choice of the user save/do again/exit
                    do {
                        //get the input from the user to do the calculation
                        double grossIncome = input.getUserDecimal("Enter your annual gross income:");
                        double taxCredits = input.getUserDecimal("Enter your annul tax credits:");
                        if (user.isMarried()) {//Check if the user is meried and ask for the parther income
                            double partnerGrossIncome = input.getUserDecimal("Enter your partner annual gross income:");
                            double partnerTaxCredits = input.getUserDecimal("Enter your partner annual tax credits:");
                            userTaxes = new UserTaxes(user, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits);
                        } else {
                            userTaxes = new UserTaxes(user, grossIncome, taxCredits);
                        }
                        System.out.println(userTaxes.toString());
                        //Pront the options for the user to do it
                        saveOption = input.getUserInt(" ---- MENU ----\n"
                                + "1 - Save operations\n"
                                + "2 - Calculate again\n"
                                + "3 - Cancel operation\n", 1, 3);
                        switch (saveOption) {
                            case 1:
                                databaseWriter.saveUserOperations(userTaxes);//Call the method to save the operation
                                System.out.println("Operations saved");
                                break;
                            case 2:
                                continue;//Will get the input again from the user
                            //Calcell the operation
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
                    // Create a new instance of the CSVFileReader class
                    CSVFileReader fileReader = new CSVFileReader();
                    // Call the CSVFileReaderUser method of the fileReader object, passing the 'user' object
                    // This method returns an array of UserTaxes objects based on the user's information

                    UserTaxes[] userTaxesArray = fileReader.CSVFileReaderUser(user);

                    for (UserTaxes userTax : userTaxesArray) {
                        System.out.println("=================================");
                        System.out.println(userTax.toString());
                    }
                    System.out.println("----------------------------------");

                    break;

                case 5:
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

                case 6:
                    System.out.println("Bye. See you soon.");
                    break;
                default:
                    throw new AssertionError();
            }

        } while (option != 6);

    }
}
