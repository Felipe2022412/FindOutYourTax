/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package findoutyourtax;

/**
 *Interface for the modifier methods - for each User's attribute.
 * @author felip
 */
public interface Modify {
    
    //Modifiers that will belong to the admin user and regular user. 
    String modifyFirstName(String newFirstName); 
    String modifyLastName(String newLastName);
    String modifyPassword(String newPassword);
    String modifyDateOfBirth(String newDateOfBirth);
    String modifyPPSNO(String newPPSNO);
    String modifyEmail(String newEmail);
    
    
   }
