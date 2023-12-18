/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package findoutyourtax;

import menus.SingUpInterface;
import menus.DisplayMenu;
import menus.SignUpMenu;
import user.AdminUser;
import user.RegularUser;
import user.User;
import database.DatabaseSetup;
import database.DatabaseReader;
import utils.IOUtils;
import java.sql.SQLException;

/**
 * GitHub Link: https://github.com/Felipe2022412/FindOutYourTax.git
 * Video Demo is avaliable on youtube with the private link: https://youtu.be/dno25clBJK0
 * @author Douglas and Felipe
 */
public class FindOutYourTax {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        // TODO code application logic here

        //Initialize necessary objects and variables
        IOUtils input = new IOUtils();
        User user;

        //Interfaces
        DisplayMenu displayMenu;
        int option;

        //Starts the database
        DatabaseSetup database = new DatabaseSetup();
        database.setupDB();
        //Object that is use to read from database
        DatabaseReader databaseReader = new DatabaseReader();

        //Start themenu for the user
        System.out.println("===== Welcome to Find out Your tax =====\n");
        //Will loop until the user chose to leave
        do {
            System.out.println("-----MENU-----\n 1 - Log In \n 2 - Sign Up \n 3 - Exit");
            option = input.getUserInt("Enter one of the options above:", 1, 3);
            boolean userExists = true;//Use to check if the user exists on the database in the loop
            //This is the main Switch tha will be responsable for interactin between the classes and interfaces
            switch (option) {

                case 1:
                    // User log in
                    String userNameLogin;
                    String passwordLogin;

                    do {
                        System.out.println("Enter 'exit' to leave the login process in the user name field.");
                        
                        
                        userNameLogin = input.getUserText("Enter your user name:");
                        //check if the user want to exit the log in process
                        if (userNameLogin.equalsIgnoreCase("exit")) {
                            break; // Exit the login process
                        }

                        passwordLogin = input.basicInput("Enter your password:");

                        try {
                            System.out.println("-----------------------------------");
                            user = databaseReader.getUser(userNameLogin, passwordLogin);

                            if (user.isAdminAccess()) {
                                userExists = false;
                                displayMenu = new AdminUser();
                                displayMenu.displayMenu(user);
                            } else {
                                userExists = false;
                                displayMenu = new RegularUser();
                                displayMenu.displayMenu(user);
                            }
                        } catch (Exception e) {
                            System.out.println("Username or password incorrect.");
                        }
                    } while (userExists);

                    break;

                //Display the sing up menu
                case 2:
                    SingUpInterface singInMenu = new SignUpMenu();
                    singInMenu.signUp();
                    break;
                //Stop the program
                case 3:
                    System.out.println("Bye. See you soon!");
                    break;
                default:
                    throw new AssertionError();
            }
            
        } while (option != 3);//loop until the user enter chose to leave
    }

}
