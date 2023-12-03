/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 *Class for User personal details
 * @author felip
 */
public class User {
    
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int userId;
    private static int currentID = 2;
    private String dateOfBirth;
    private String ppsNo;
    private String email;
    private boolean married;
    protected boolean adminAccess;

    //Constructor to make USER objects
    public User(String firstName, String lastName, String userName, String password, String dateOfBirth, String ppsNo, String email, boolean married) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.userId = currentID; // user id will be the current id. It starts in 1 and increases as a new object is created.
        this.dateOfBirth = dateOfBirth;
        this.ppsNo = ppsNo;
        this.email = email;
        this.married = married;
        adminAccess = false;
        currentID++;// to incriment the id for each user each time a object is created.
        
    }

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

    public static int getCurrentID() {
        return currentID;
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

    

    
    
}
