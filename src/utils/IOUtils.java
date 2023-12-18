/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package utils;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * A set of useful Input methods - get text -get a int (no limits) - get a
 * decimal - get an int (with a minimum value) - get a int (with a min and max
 * value)
 *
 * @author Ken Healy and modify by Douglas Vinicius Dierings
 */
public class IOUtils {

    /**
     * Get a string / text from the user by issuing a prompt if user enters
     * invalid input - output an error and ask again
     *
     * @param prompt the prompt to issue
     * @return a valid user string
     */
    public String getUserText(String prompt) {

        Scanner myKB = new Scanner(System.in);
        String userInput;
        boolean valid = false;

        do {
            System.out.println(prompt); //display prompt

            userInput = myKB.nextLine();

            //check to see if input is not just letters and spaces
            if (!userInput.matches("[a-zA-Z ]+")) {

                System.out.println("Invalid input - you must enter text only");
            } else {
                valid = true;
            }

        } while (!valid); //keep going wile input is not valid

        //i know the input is valid now
        return (userInput);

    }

    /**
     * Get an integer from the user by issuing a prompt keep asking if user does
     * not enter a integer
     *
     * @param prompt the prompt to issue
     * @return a VALID integer
     */
    public int getUserInt(String prompt) {

        Scanner myKB = new Scanner(System.in);
        int userInput = 0;
        boolean valid = false;

        do {
            System.out.println(prompt); //display prompt

            try {
                userInput = myKB.nextInt(); //get a number -- causes an exception if not an int!
                valid = true; // must be OK
            } catch (Exception e) {

                System.out.println("Invalid input - you must enter an integer only");
                myKB.nextLine(); // get rid of return char
            }

        } while (!valid);

        return userInput;
    }

    /**
     * Get a decimal value from the user by issuing a prompt
     *
     * @param prompt the prompt to be issued
     * @return a VALID decimal or -1 if not valid (THIS WILL CHANGE!!)
     */
    public double getUserDecimal(String prompt) {

        Scanner myKB = new Scanner(System.in);
        double userInput = 0;
        boolean valid = false;

        do {
            System.out.println(prompt); //display prompt

            try {
                userInput = myKB.nextDouble(); //get a number -- causes an exception if not an int!
                valid = true; // must be OK
            } catch (Exception e) {

                System.out.println("Invalid input - you must enter an integer only");
                myKB.nextLine(); // get rid of return char
            }

        } while (!valid);

        return userInput;

    }

    /**
     * get an integer from the user by issuing a prompt must have a specified
     * min value
     *
     * @param prompt the prompt to be issued
     * @param minValue the minimum value allowed
     * @return a VALID int bigger than minValue
     */
    public int getUserInt(String prompt, int minValue) {

        Scanner myKB = new Scanner(System.in);
        int userInput = 0;
        boolean valid = false;

        do {
            System.out.println(prompt); //display prompt
            System.out.println("You must enter a number bigger than " + minValue);
            try {
                userInput = myKB.nextInt(); //get a number -- causes an exception if not an int!
                valid = true; // must be OK
            } catch (Exception e) {

                System.out.println("Invalid input - you must enter an integer only");
                myKB.nextLine(); // get rid of return char
            }
            //keep going while input is outside valid range, or is not a number at all
        } while (!valid || userInput < minValue);

        return userInput;
    }

    /**
     * Get an integer from the user by issuing a prompt Must be within a range
     * provided (min/max)
     *
     * @param prompt the prompt message to issue
     * @param minValue the lowest value allowed
     * @param maxValue the highest value allowed
     * @return a valid int between minValue and maxValue
     */
    public int getUserInt(String prompt, int minValue, int maxValue) {

        Scanner myKB = new Scanner(System.in);
        int userInput = 0;
        boolean valid = false;

        do {
            System.out.println(prompt); //display prompt
            System.out.println("You must enter a number beetwen " + minValue + " and " + maxValue);
            try {
                userInput = myKB.nextInt(); //get a number -- causes an exception if not an int!
                valid = true; // must be OK
            } catch (Exception e) {

                System.out.println("Invalid input - you must enter an integer only");
                myKB.nextLine(); // get rid of return char
            }

            //keep going while input is outside valid range, or is not a number at all
        } while (!valid || userInput < minValue || userInput > maxValue);

        return userInput;
    }

    /**
     * Get an integer from the user by issuing a prompt Must be within a range
     * provided (min/max)
     *
     * @param prompt the prompt message to issue
     * @param minValue the lowest value allowed
     * @param maxValue the highest value allowed
     * @return a valid Boolean if 1 true and 2 return false
     * @author Douglas Dierings and Felipe Marques
     */
    public boolean getUserBollean(String prompt, int minValue, int maxValue) {

        Scanner myKB = new Scanner(System.in);
        int userInput = 0;
        boolean valid = false;
        boolean validBoolean = false;

        do {
            System.out.println(prompt); //display prompt
            System.out.println("You must enter a number beetwen " + minValue + " and " + maxValue);
            try {
                userInput = myKB.nextInt(); //get a number -- causes an exception if not an int!
                valid = true; // must be OK
                if (userInput == 1) {
                    validBoolean = true;
                } else {
                    validBoolean = false;
                }
            } catch (Exception e) {

                System.out.println("Invalid input - you must enter an integer only");
                myKB.nextLine(); // get rid of return char
            }

            //keep going while input is outside valid range, or is not a number at all
        } while (!valid || userInput < minValue || userInput > maxValue);

        return validBoolean;
    }

    /**
     * Get a valid email address from the user by issuing a prompt. Keep asking
     * if the user does not enter a valid email address.
     * Reference: https://www.baeldung.com/java-email-validation-regex
     * @param prompt the prompt to issue
     * @return a VALID email address
     * @author Douglas Dierings and Felipe Marques
     */
    public String getUserEmail(String prompt) {
        Scanner myKB = new Scanner(System.in);
        String userEmail = "";
        boolean valid = false;

        do {
            System.out.println(prompt); // Display prompt

            try {
                userEmail = myKB.nextLine(); // Get user input

                // Check if contains '@' and at least one dot after '@'
                if (userEmail.contains("@") && userEmail.indexOf('@') < userEmail.length() - 1 && userEmail.contains(".")) {
                    valid = true; // É um endereço de e-mail válido
                } else {
                    System.out.println("Invalid email address. Please enter a valid email.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

        } while (!valid);

        return userEmail;
    }

    /**
     * Get a valid password from the user by issuing a prompt. Keep asking if
     * the user does not enter a valid password.
     * Reference: https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
     * @param prompt the prompt to issue
     * @return a VALID password
     * @author Douglas Dierings and Felipe Marques
     */
    public String getUserPassword(String prompt) {
        Scanner myKB = new Scanner(System.in);
        String userPassword;
        boolean valid;

        do {
            System.out.println(prompt); // Display prompt

            userPassword = myKB.nextLine(); // Get user input
            valid = isValidPassword(userPassword);//Call the method to test the password

            if (!valid) {
                System.out.println("Invalid password. Please enter a password with at least one number, one capital letter, and one symbol.");
            }

        } while (!valid);

        return userPassword;
    }

    //Method to check if the password is valid, this method will be called inside the loop
    private boolean isValidPassword(String password) {
        return password.matches(".*\\d.*") && password.matches(".*[A-Z].*") && password.matches(".*[!@#$%^&*()-=_+{};':\",.<>?/|`~\\[\\]\\\\].*");
    }

    /**
     * Get a valid date of birth from the user by issuing a prompt. Keep asking
     * if the user does not enter a valid date of birth in the "AAAA-MM-DD"
     * format.
     *
     * @param prompt the prompt to issue
     * @return a VALID date of birth
     * @author Douglas Dierings and Felipe Marques
     */
    public String getUserDateOfBirth(String prompt) {
        Scanner myKB = new Scanner(System.in);
        String userDateOfBirth;
        boolean valid;

        do {
            System.out.println(prompt); // Display prompt

            userDateOfBirth = myKB.nextLine(); // Get user input
            valid = isValidDateOfBirth(userDateOfBirth);

            if (!valid) {
                System.out.println("Invalid date of birth. Please enter a valid date in the format 'AAAA-MM-DD'.");
            }

        } while (!valid);

        return userDateOfBirth;
    }

    // Method to check if the date of birth is valid to store later in the SQL database, so we do not get errors
    private boolean isValidDateOfBirth(String dateOfBirth) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); //Disable lenient mode to ensuring that the date format must match exactly so i do not have problams when insert the date to DB

        try {
            Date parsedDate = (Date) dateFormat.parse(dateOfBirth);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Get a valid PPSN from the user by issuing a prompt. Keep asking if the
     * user does not enter a valid PPSN in the format '1234567A' or '1234567AB'.
     * Reference: https://stackoverflow.com/questions/20385963/validate-a-string-pps-number#:~:text=A%20valid%20PPS%20number%20will,be%20considered%20invalid%20PPS%20numbers.
     * @param prompt the prompt to issue
     * @return a VALID PPSN
     * @author Douglas Dierings and Felipe Marques
     */
    public String getUserPPSN(String prompt) {
        Scanner myKB = new Scanner(System.in);
        String userPPSN;
        boolean valid;

        do {
            System.out.println(prompt); // Display prompt
            userPPSN = myKB.nextLine().toUpperCase(); // Get user input and convert to uppercase
            valid = isValidPPSN(userPPSN);

            if (!valid) {
                System.out.println("Invalid PPSN. Please enter a valid PPSN in the format '1234567A' or '1234567AB'.");
            }

        } while (!valid);

        return userPPSN;
    }

    //method to check if the PPSN is valid
    private boolean isValidPPSN(String ppsn) {
        // PPSN should have 7 digits followed by 1 or 2 letters (https://www.citizensinformation.ie)
        return ppsn.matches("\\d{7}[A-Za-z]{1,2}");

    }
    //This method was created because the password for the admin user do not follow the Valid passoword that I use to create an user
    //So it will allow the user to use the default password for CCT admin, but it will stil checking if the password is valid, and is case sensitive
    public String basicInput(String prompt) {
        System.out.println(prompt); // Display prompt
        Scanner myKB = new Scanner(System.in);
        String userInput;

        userInput = myKB.nextLine();
        return userInput;
    }
}
