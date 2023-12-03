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

        //RegularUser = regularUser = new RegularUser
        int option;

        //test database
        DatabaseSetup database = new DatabaseSetup();
        database.setupDB();
        DatabaseReader userCheck = new DatabaseReader();
        System.out.println("===== Welcome to Find out Your tax =====");

        System.out.println(" 1 - Log In \n 2 - Sing In");
        option = input.getUserInt("Enter one of the options above:", 1, 2);
        switch (option) {
            case 1:
                //User log in
                String userNameLogin;
                String passwordLogin;

                userNameLogin = input.getUserText("Enter your user name:");
                passwordLogin = input.basicInput("Enter your password:");

                user = userCheck.getUser(userNameLogin, passwordLogin);
                if (user.isAdminAccess() == true) {
                    System.out.println(">>>>> Welcome " + user.getFirstName() + " <<<<<");
                    System.out.println("You have admin access.");
                } else {
                    System.out.println(">>>>> Welcome " + user.getFirstName() + " <<<<<");
                    System.out.println("You are a normal user.\n");
                    System.out.println("---- MENU ----");
                    option = input.getUserInt("Enter the number of the respective task you want to do:\n"
                            + "1 - view profile\n"
                            + "2 - change profile\n"
                            + "3 - Calculate taxes\n"
                            + "4 - see previously calculation\n"
                            + "5 - exit", 1, 5);
                    switch (option) {
                        case 1:
                            System.out.println(user.toString());
                            break;
                        case 2:

                            break;
                        case 3:

                            break;
                        case 4:

                            break;
                        case 5:
                            System.out.println("Bye. See you soon.");
                            break;
                        default:
                            throw new AssertionError();
                    }

                }
                break;

            case 2:
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
                break;
            default:
                throw new AssertionError();
        }

    }

}
