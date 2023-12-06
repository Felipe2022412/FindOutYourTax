/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package findoutyourtax;

import database.DatabaseSetup;
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
        User user;

        //Interfaces
        DisplayMenu displayMenu ;
        int option;

        //Starts the database
        DatabaseSetup database = new DatabaseSetup();
        database.setupDB();
        //Object that is use to read from database
        DatabaseReader databaseReader = new DatabaseReader();
        
        //Start themenu for the user
        System.out.println("===== Welcome to Find out Your tax =====");
        //Will loop until the user chose to leave
        do {
            System.out.println(" 1 - Log In \n 2 - Sing Up \n 3 - Exit");
            option = input.getUserInt("Enter one of the options above:", 1, 3);
            boolean userExists = true;//Use to check if the user exists on the database in the loop
            //This is the main Switch tha will be responsable for interactin between the classes and interfaces
            switch (option) {

                case 1:
                    //User log in
                    String userNameLogin;
                    String passwordLogin;

                    do {

                        userNameLogin = input.getUserText("Enter your user name:");
                        passwordLogin = input.basicInput("Enter your password:");
                        
                        try {
                            System.out.println("-----------------------------------");
                            user = databaseReader.getUser(userNameLogin, passwordLogin);//Get the user from the database using the username and login
                            //Display the menu for regular usser or admin calling the interfaces
                            if (user.isAdminAccess()) {//Admin user
                                userExists = false;//will stop the loop
                                
                                displayMenu=new AdminUser();
                                displayMenu.displayMenu(user);
                                
                                
                            } else {//regular user
                                userExists = false;//will stop the loop
                                
                                displayMenu=new RegularUser();
                                displayMenu.displayMenu(user);
 
                            }
                        } catch (Exception e) {//in the case teh user not exists
                            System.out.println("Username or password incorect.");
                        }
                    } while (userExists);//loop until the user log in

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
