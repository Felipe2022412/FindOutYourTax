/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user;

/**
 * Class for User personal details
 *
 * @author Douglas and Felipe
 */
public abstract class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int userId;
    //private static int currentID = 1;
    private String dateOfBirth;
    private String ppsNo;
    private String email;
    private boolean married;
    private boolean adminAccess;

    
    //Constructor to call the interfaces
    public User() {
    }

    //Constructor to make USER objects
    public User(String firstName, String lastName, String userName, String password, String dateOfBirth, String ppsNo, String email, boolean married) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.ppsNo = ppsNo;
        this.email = email;
        this.married = married;
        adminAccess = false;

    }

    //Contructor to get only userID, first name and last name - to get the user in the database reader using the ID
    public User(int userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    //Constructor to show all user to the admin but not the password
    public User(int userId, String firstName, String lastName, String userName, String dateOfBirth, String ppsNo, String email, boolean married) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userId = userId; // user id will be the current id. It starts in 1 and increases as a new object is created.
        this.dateOfBirth = dateOfBirth;
        this.ppsNo = ppsNo;
        this.email = email;
        this.married = married;

    }
    //To get a user from the database when the loging is sucecfull
    public User(int userId, String firstName, String lastName, String userName, String password, String dateOfBirth, String ppsNo, String email, boolean married) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.userId = userId; // user id será o currentID, que pode ser o userId ajustado
        this.dateOfBirth = dateOfBirth;
        this.ppsNo = ppsNo;
        this.email = email;
        this.married = married;
    }

    //Getters and setters for the variables
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPpsNo() {
        return ppsNo;
    }

    public String getEmail() {
        return email;
    }

    public boolean isMarried() {
        return married;
    }

    public boolean isAdminAccess() {
        return adminAccess;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPpsNo(String ppsNo) {
        this.ppsNo = ppsNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public void setAdminAccess(boolean adminAccess) {
        this.adminAccess = adminAccess;
    }

    @Override
    public String toString() {
        return "User details as follow:\n"
                + "User ID: " + userId + "\n"
                + "First Name: " + firstName + "\n"
                + "Last Name: " + lastName + "\n"
                + "User Name: " + userName + "\n"
                + "Date of Birth: " + dateOfBirth + "\n"
                + "PPS Number: " + ppsNo + "\n"
                + "Email: " + email + "\n"
                + "Married: " + married;
    }

}
