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
        
        
        //test database
        //DatabaseSetup database = new DatabaseSetup();
        //database.setupDB();
        
        
        System.out.println("===== Welcome to Find out Your tax =====");
        
        System.out.println(" 1 - Log In \n 2 - Sing In");
        input.getUserInt("Enter one of the options above:", 1, 2);
        
        
    }
    
}
