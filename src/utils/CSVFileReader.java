package utils;

import user.User;
import user.UserTaxes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is responsible for reading tax information from CSV files and
 * creating UserTaxes objects based on the data.
 * @author Douglas and Felipe
 */
public class CSVFileReader {

    /**
     * Reads a CSV file containing tax information and creates an array of UserTaxes
     * objects for a given user.
     *
     * @param user The User object for which tax information is being read. Is used to check if is married or not.
     * @return An array of UserTaxes objects containing tax information.
     */
    public UserTaxes[] CSVFileReaderUser(User user) {
        ArrayList<UserTaxes> userTaxesList = new ArrayList<>();// Create an arrayList to store the UserTaxes
        BufferedReader br = null;// Inicialize the bufferreader

        String filePath;
        // Determine the file path based on whether the user is married or single.
        if (user.isMarried()) {
            filePath = "taxesInfoMarriedDemo.csv"; // File for married users. This is only and example
        } else {
            filePath = "taxesInfoSingleDemo.csv"; // File for single users. This is only and example
        }

        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;

            // Read each line of the CSV file and process the data.
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                UserTaxes userTaxes = null;

                if (user.isMarried()) {
                    // User is married
                    if (userData.length == 4) {
                        double grossIncome = Double.parseDouble(userData[0].replace("\"", "").trim());// Parse the input from CSV data and remove quotes and trim removing whitespace
                        double taxCredits = Double.parseDouble(userData[1].replace("\"", "").trim());
                        double partnerGrossIncome = Double.parseDouble(userData[2].replace("\"", "").trim());
                        double partnerTaxCredits = Double.parseDouble(userData[3].replace("\"", "").trim());
                        // Create a UserTaxes object for a married user.
                        userTaxes = new UserTaxes(user, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits);
                    } else {
                        System.out.println("Insufficient data for a married user: " + line);
                    }
                } else {
                    // User is single
                    if (userData.length == 2) {
                        double grossIncome = Double.parseDouble(userData[0].replace("\"", "").trim());
                        double taxCredits = Double.parseDouble(userData[1].replace("\"", "").trim());
                        // Create a UserTaxes object for a single user.
                        userTaxes = new UserTaxes(user, grossIncome, taxCredits);
                    } else {
                        System.out.println("Insufficient data for a single user: " + line);
                    }
                }

                if (userTaxes != null) {
                    userTaxesList.add(userTaxes);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + filePath); //Handle the case where the CSV file is not found
        } catch (IOException ex) {
            System.out.println("Error reading file: " + filePath); //Handle errors that occur while reading the CSV file
        } finally {
            try {
                if (br != null) {
                    br.close();// Close the file
                }
            } catch (IOException ex) {
                System.out.println("Error closing file: " + filePath);
            }
        }

        // Convert ArrayList to Array and return the UserTaxes objects. I decide to convert so the data can not be changed unintentionally
        return userTaxesList.toArray(new UserTaxes[0]);
    }
}
