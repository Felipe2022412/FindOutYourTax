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
        //RegularUser = regularUser = new RegularUser
        int option;

        //test database
        DatabaseSetup database = new DatabaseSetup();
        database.setupDB();
        DatabaseReader databaseReader = new DatabaseReader();
        System.out.println("===== Welcome to Find out Your tax =====");
        do {
            System.out.println(" 1 - Log In \n 2 - Sing Up \n 3 - Exit");
            option = input.getUserInt("Enter one of the options above:", 1, 3);
            boolean userExists = true;
            switch (option) {

                case 1:
                    //User log in
                    String userNameLogin;
                    String passwordLogin;

                    do {

                        userNameLogin = input.getUserText("Enter your user name:");
                        passwordLogin = input.basicInput("Enter your password:");
                        
                        try {
                            user = databaseReader.getUser(userNameLogin, passwordLogin);
                            //Display the menu
                            if (user.isAdminAccess()) {
                                userExists = false;
                                
                                displayMenu=new AdminUser();
                                displayMenu.displayMenu(user);
                                
                                
                            } else {
                                userExists = false;
                                
                                displayMenu=new RegularUser();
                                displayMenu.displayMenu(user);
 
                            }
                        } catch (Exception e) {
                            System.out.println("Username or password incorect.");
                        }
                    } while (userExists);

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
