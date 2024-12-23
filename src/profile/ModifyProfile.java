/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package profile;

import user.User;
import database.DatabaseWriter;
import utils.IOUtils;

/**
 * ModifyProfile class implements the ModifyProfileInterface and provides
 * methods to modify User profiles.
 * @author Douglas and Felipe
 */
public class ModifyProfile implements ModifyProfileInterface {

    // DatabaseWriter instance for writing to the database
    DatabaseWriter databaseWriter;
    // Constructor initializes DatabaseWriter

    public ModifyProfile() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        databaseWriter = new DatabaseWriter();
    }
    // Implementation of the modifyProfile method from the ModifyProfileInterface
    @Override
    public void modifyProfile(User user) {
        //Initialize necessary objects
        IOUtils input = new IOUtils();
        
        // Prompt the user to choose the attribute to modify
        int optionModdify;
        optionModdify = input.getUserInt("---- Enter the number of the info you want to change ----\n"
                + "1 - First name\n"
                + "2 - Last name\n"
                + "3 - Password\n"
                + "4 - Date of birth\n"
                + "5 - PPSNO\n"
                + "6 - Email\n"
                + "7 - Marital status\n"
                + "8 - Cancell\n");
        
        // Switch statement to handle the chosen modification
        switch (optionModdify) {
            case 1:
                String newFirstName = input.getUserText("Enter your new name:");
                user.setFirstName(newFirstName);//Set the new first name
                databaseWriter.modifyUser(user);//Will write the changes made to the user in the database
                System.out.println("Name updated.");
                break;
            case 2:
                String newLastName = input.getUserText("Enter your new last name:");
                user.setLastName(newLastName);
                databaseWriter.modifyUser(user);
                System.out.println("Last name updated.");
                break;
            case 3:
                String newPassword = input.getUserPassword("Enter your new password:");
                user.setPassword(newPassword);
                databaseWriter.modifyUser(user);
                System.out.println("Password updated.");
                break;
            case 4:
                String newDateOfBirth = input.getUserDateOfBirth("Enter your new date of birth (AAAA-MM-DD):");
                user.setDateOfBirth(newDateOfBirth);
                databaseWriter.modifyUser(user);
                System.out.println("Date of birth updated.");
                break;
            case 5:
                String newPpsNo = input.getUserPPSN("Enter your new PPSNO:");
                user.setPpsNo(newPpsNo);
                databaseWriter.modifyUser(user);
                System.out.println("PPSNO updated.");
                break;
            case 6:
                String newEmail = input.getUserEmail("Enter your new email:");
                user.setEmail(newEmail);
                databaseWriter.modifyUser(user);
                System.out.println("Email updated.");
                break;
            case 7:
                boolean isMarried = input.getUserBollean("If you are married enter 1, if not enter 2:", 1, 2);
                user.setMarried(isMarried);
                databaseWriter.modifyUser(user);
                System.out.println("Marital status updated.");
                break;
                //Cancel the operation
            case 8:
                System.out.println("Cancelling modification.");
                break;
            default:
                throw new AssertionError();
        }

    }

}
