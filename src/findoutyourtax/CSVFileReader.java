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

        String filePath;
        if (user.isMarried()) {
            filePath = "taxesInfoMarriedDemo.csv"; // File for married users
        } else {
            filePath = "taxesInfoSingleDemo.csv"; // File for single users
        }

        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                UserTaxes userTaxes = null;

                if (user.isMarried()) {
                    // User is married
                    if (userData.length == 4) {
                        double grossIncome = Double.parseDouble(userData[0].replace("\"", "").trim());
                        double taxCredits = Double.parseDouble(userData[1].replace("\"", "").trim());
                        double partnerGrossIncome = Double.parseDouble(userData[2].replace("\"", "").trim());
                        double partnerTaxCredits = Double.parseDouble(userData[3].replace("\"", "").trim());
                        userTaxes = new UserTaxes(user, grossIncome, taxCredits, partnerGrossIncome, partnerTaxCredits);
                    } else {
                        System.out.println("Insufficient data for a married user: " + line);
                    }
                } else {
                    // User is single
                    if (userData.length == 2) {
                        double grossIncome = Double.parseDouble(userData[0].replace("\"", "").trim());
                        double taxCredits = Double.parseDouble(userData[1].replace("\"", "").trim());
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
            System.out.println("File not found: " + filePath);
        } catch (IOException ex) {
            System.out.println("Error reading file: " + filePath);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                System.out.println("Error closing file: " + filePath);
            }
        }

        // Convert ArrayList to Array
        return userTaxesList.toArray(new UserTaxes[0]);
    }
}
