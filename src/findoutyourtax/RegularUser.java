/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 *Class for the Regular User with its methods - extends the class User and implements its specifics interfaces to use the methods modify and view.
 * @author dougl
 */
public class RegularUser extends User implements Modify, ViewRegularUser {

    public RegularUser(String firstName, String lastName, String userName, String password, String dateOfBirth, String ppsNo, String email, boolean married) {
        super(firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);
    }
    
        public RegularUser(int userId, String firstName, String lastName, String userName, String password, String dateOfBirth, String ppsNo, String email, boolean married) {
        super(userId,firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);

    }

    
    //Overriding the methods from the interfaces to return the new settings if any variable is modified and, to return the variables the regular User can see.
    
    @Override
    public String modifyFirstName(String newFirstName) {
        setFirstName(newFirstName);
        return getFirstName();
    }

    @Override
    public String modifyLastName(String newLastName) {
        setLastName(newLastName);
        return getLastName();
    }

    @Override
    public String modifyPassword(String newPassword) {
        setPassword(newPassword);
        return getPassword();
    }

    @Override
    public String modifyDateOfBirth(String newDateOfBirth) {
        setDateOfBirth(newDateOfBirth);
        return getDateOfBirth();
    }

    @Override
    public String modifyPPSNO(String newPPSNO) {
        setPpsNo(newPPSNO);
        return getPpsNo();
    }

    @Override
    public String modifyEmail(String newEmail) {
        setEmail(newEmail);
        return getEmail();
    }

    @Override
    public String viewUserNameRegularUser() {
        return getUserName();

    }

    @Override
    public String viewFirstNameRegularUser() {
        return getFirstName();

    }

    @Override
    public String viewLastNameRegularUser() {
        return getLastName();
    }

    @Override
    public String viewPasswordRegularUser() {
        return getPassword();
    }

    @Override
    public String viewDateOfBirthRegularUser() {
        return getDateOfBirth();
    }

    @Override
    public String viewPPSNORegularUser() {
        return getPpsNo();
    }

    @Override
    public String viewEmailRegularUser() {
        return getEmail();
    }

}
