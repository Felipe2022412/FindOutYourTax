/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package findoutyourtax;

/**
 *Interface for the view methods for the admin - for each User's attribute.
 * @author felip
 */
public interface ViewAdmin {
    
   
    //View methods that will belong to the admin - the method will return the ability to see everything from the database 
    String viewFirstNameAdmin();
    String viewLastNameAdmin();
    String viewPasswordAdmin();
    String viewDateOfBirthAdmin();
    String viewPPSNOAdmin();
    String viewEmailAdmin();
   
}
