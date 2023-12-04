/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

import database.DatabaseWriter;
import ioutils.IOUtils;

/**
 *
 * @author dougl
 */
public class SignUpMenu implements SingUpInterface {

    IOUtils input = new IOUtils();
    DatabaseWriter databaseWriter;

    public SignUpMenu() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.databaseWriter = new DatabaseWriter();
    }

    @Override
    public void signUp() {

        //Initialize necessary objects and variables
        //Take all the informatio of the user and create a object and store to the database
        String firstName;
        String lastName;
        String userName;
        String password;
        String dateOfBirth;
        String ppsNo;
        String email;
        boolean married;

        System.out.println(">>> Register form <<<");

        firstName = input.getUserText("Enter your first name:");
        lastName = input.getUserText("Enter your last name:");
        userName = input.getUserText("Enter a user name that will be used for login:");
        email = input.getUserEmail("Enter your email:");
        password = input.getUserPassword("Enter a password:");
        dateOfBirth = input.getUserDateOfBirth("Enter your date of birth in the follow format = AAAA-MM-DD:");
        ppsNo = input.getUserPPSN("Enter your PPSNO:");
        married = input.getUserBollean("If you are married enter 1 if not enter 2:", 1, 2);

        RegularUser regularUser = new RegularUser(firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);
        databaseWriter.registerUser(regularUser);
        System.out.println("You have been registered successfully.");
    }

}
