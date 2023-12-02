/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 *
 * @author dougl
 */
public class AdminUser extends User{
    
    private boolean adminAccess;
    
    public AdminUser(String firstName, String lastName, String userName, String password, String dateOfBirthday, String ppsNo, String email, boolean married, boolean adminAccess) {
        super(firstName, lastName, userName, password, dateOfBirthday, ppsNo, email, married);
        this.adminAccess = true;
    }
    
    
    
}