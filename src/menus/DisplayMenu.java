/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package menus;

import user.User;

/**
 * Interface for the view methods for the admin - for each User's attribute.
 *
 * @author Douglas and Felipe
 */
public interface DisplayMenu {

    //View methods that will belong to the admin - the method will return the ability to see everything from the database 
    void displayMenu(User user);

}
