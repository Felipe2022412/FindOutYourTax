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
    String modifyName(); 
    String modifySurname();
    String modifyPassword();
    String modifyDateOfBirth();
    String modifyPPSNO();
    String modifyEmail();
    
    
   }
