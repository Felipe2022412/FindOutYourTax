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
 * SignUpMenu class implements the SignUpInterface and provides a user registration menu.
 * It collects user information, creates a RegularUser object, and stores it in the database.
 * @author Douglas and Felipe
 */
public class SignUpMenu implements SingUpInterface {

    IOUtils input = new IOUtils();
    DatabaseWriter databaseWriter;
    DatabaseReader databaseReader;
    ArrayList<User> usersList = new ArrayList<>();

    public SignUpMenu() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.databaseWriter = new DatabaseWriter();
        this.databaseReader = new DatabaseReader();
        usersList = databaseReader.getAllUsers();
    }

    @Override
    public void signUp() {

        //Take all the information of the user and create an object and store it in the database
        String firstName;
        String lastName;
        String userName;
        String password;
        String dateOfBirth;
        String ppsNo;
        String email;
        boolean married;

        do {
            System.out.println(">>> Register form <<<");
            //Get all the user information
            firstName = input.getUserText("Enter your first name:");
            lastName = input.getUserText("Enter your last name:");
            userName = input.getUserText("Enter a user name that will be used for login:");
            email = input.getUserEmail("Enter your email:");
            password = input.getUserPassword("Enter a password:");
            dateOfBirth = input.getUserDateOfBirth("Enter your date of birth in the following format = AAAA-MM-DD:");
            ppsNo = input.getUserPPSN("Enter your PPSNO:");
            married = input.getUserBollean("If you are married enter 1 if not enter 2:", 1, 2);
            //Will check if the user alrady exist in the database checkin the username and PPSN
            if (databaseReader.userExists(userName, ppsNo)) {
                System.out.println("User with the same username or PPSN already exists. Please choose different credentials.");
            } else {//If not exists will create the user
                RegularUser regularUser = new RegularUser(firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);
                databaseWriter.registerUser(regularUser);
                System.out.println("You have been registered successfully.");
                break;  // Break out of the loop if registration is successful
            }

        } while (true);
    }


}
