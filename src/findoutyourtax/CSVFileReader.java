/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dougl
 */
public class CSVFileReader {

    public UserTaxes[] CSVFileReaderUser(User user) {
        ArrayList<UserTaxes> userTaxesList = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("taxesInfoMeriedDemo.csv"));//this file is only for a single user because I am not allow to use GUI so the user can select the file from the sistem
                                                                                     //If needed can change the file name for taxesInfoMeriedDemo.csv that is on the directory folder of this program
            String line;

            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                UserTaxes userTaxes = null;

                // User is married
                if (userData.length == 4) {
                    double grossIncome = Double.parseDouble(userData[0]);
                    double taxCredits = Double.parseDouble(userData[1]);
                    double partnerGrossIncome = Double.parseDouble(userData[2]);
                    double partnerTaxCredits = Double.parseDouble(userData[3]);
                    userTaxes = new UserTaxes(user, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits);

                    // User is single
                } else if (userData.length == 2) {
                    double grossIncome = Double.parseDouble(userData[0].replace("\"", "").trim());//Need to use the replace method because of the Excel format and trim to remove spaces (https://stackoverflow.com/questions/12961114/java-replace-character-with)
                    double taxCredits = Double.parseDouble(userData[1].replace("\"", "").trim());
                    userTaxes = new UserTaxes(user, grossIncome, taxCredits);
                } else {
                    System.out.println("Insufficient data for a single user: " + line);
                }

                if (userTaxes != null) {
                    userTaxesList.add(userTaxes);
                }
            }
        } catch (IOException ex) {
            System.out.println("File not found.");
        }

        // Convert ArrayList to Array, so it eassy to manege
        return userTaxesList.toArray(new UserTaxes[0]);
    }
}
