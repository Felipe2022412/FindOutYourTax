/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 * Class for the Regular User with its methods - extends the class User and
 * implements its specifics interfaces to use the methods modify and view.
 *
 * @author dougl
 */
public class RegularUser extends User implements  ViewRegularUser {

    public RegularUser(String firstName, String lastName, String userName, String password, String dateOfBirth, String ppsNo, String email, boolean married) {
        super(firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);
    }

    public RegularUser(int userId, String firstName, String lastName, String userName, String password, String dateOfBirth, String ppsNo, String email, boolean married) {
        super(userId, firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);

    }
    
    public RegularUser(int userId, String firstName, String lastName, String userName, String dateOfBirth, String ppsNo, String email, boolean married) {
        super(userId, firstName, lastName, userName, dateOfBirth, ppsNo, email, married);
    }
    


    @Override
    public void displayMenu(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
