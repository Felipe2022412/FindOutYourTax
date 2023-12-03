/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package findoutyourtax;

/**
 *Interface for the view methods for the Regular User - for each User's attribute.
 * @author felip
 */
public interface ViewRegularUser {
    
    //View methods that will belong to the regular user - the method will return the ability to see just the personal details from the database
    String viewUserNameRegularUser();
    String viewFirstNameRegularUser();
    String viewLastNameRegularUser();
    String viewPasswordRegularUser();
    String viewDateOfBirthRegularUser();
    String viewPPSNORegularUser();
    String viewEmailRegularUser();
    
}
