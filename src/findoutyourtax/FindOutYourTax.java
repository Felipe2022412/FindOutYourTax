/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package findoutyourtax;

import database.DatabaseSetup;
import ioutils.IOUtils;

/**
 *
 * @author felip
 */
public class FindOutYourTax {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // TODO code application logic here

        //Initialize necessary objects and variables
        IOUtils input = new IOUtils();

        //RegularUser = regularUser = new RegularUser
        int option;

        //test database
        //DatabaseSetup database = new DatabaseSetup();
        //database.setupDB();
        System.out.println("===== Welcome to Find out Your tax =====");

        System.out.println(" 1 - Log In \n 2 - Sing In");
        option = input.getUserInt("Enter one of the options above:", 1, 2);
        switch (option) {
            case 1:

                break;
            case 2:
                //Take all the informatio of the user and create a object and store to the database
                String firstName;
                String lastName;
                String userName;
                String password;
                String dateOfBirthday;
                String ppsNo;
                String email;
                boolean married;

                System.out.println(">>> Register form <<<");

                firstName = input.getUserText("Enter your first name:");
                lastName = input.getUserText("Enter your last name:");
                userName = input.getUserText("Enter a user name that will be use for login:");
                email = input.getUserEmail("Enter your email:");
                password = input.getUserPassword("Enter a password:");
                dateOfBirthday = input.getUserDateOfBirth("Enter your date of birthday in the follow format = AAAA-MM-DD:");
                ppsNo = input.getUserText("Enter your PPSNO:");
                married = input.getUserBollean("IF your married enter 1 if not 2", 1, 2);

                RegularUser regularUser = new RegularUser(firstName, lastName, userName, password, dateOfBirthday, ppsNo, email, married);

                break;
            default:
                throw new AssertionError();
        }

    }

}
