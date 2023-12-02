/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ioutils;

import java.util.Scanner;

/**
 * A set of useful Input methods
 * - get text
 * -get a int (no limits)
 * - get a decimal
 * - get an int (with a minimum value)
 *  - get a int (with a min and max value)
 * @author Ken Healy
 */
public class IOUtils {

    /**
     * Get a string / text from the user by issuing a prompt
     * if user enters invalid input - output an error and ask again
     * @param prompt the prompt to issue
     * @return a valid user string
     */
    public String getUserText(String prompt){
        
        Scanner myKB = new Scanner(System.in);
        String userInput;
        boolean valid=false;
        
        do{ 
            System.out.println(prompt); //display prompt
        
            userInput = myKB.nextLine();
        
            //check to see if input is not just letters and spaces
            if (!userInput.matches("[a-zA-Z ]+")){
            
                System.out.println("Invalid input - you must enter text only");
             }
            else{
                valid = true;
            }
            
        }while(!valid); //keep going wile input is not valid
        
        //i know the input is valid now
        return(userInput);
        
    }
    
    /**
     * Get an integer from the user by issuing a prompt
     * keep asking if user does not enter a integer
     * @param prompt the prompt to issue
     * @return a VALID integer 
     */
    public int getUserInt(String prompt){
        
         Scanner myKB = new Scanner(System.in);
        int userInput=0;
        boolean valid=false;
        
        do{
            System.out.println(prompt); //display prompt
        
            try{ 
                userInput = myKB.nextInt(); //get a number -- causes an exception if not an int!
                valid = true; // must be OK
            }
            catch(Exception e){
            
                System.out.println("Invalid input - you must enter an integer only");
                myKB.nextLine(); // get rid of return char
            }
            
        }while(!valid);
        
        return userInput;
    }
    
    /**
     * Get a decimal value from the user by issuing a prompt
     * @param prompt the prompt to be issued
     * @return a VALID decimal or -1 if not valid (THIS WILL CHANGE!!)
     */
    public double getUserDecimal(String prompt){

         Scanner myKB = new Scanner(System.in);
        double userInput=0;
        boolean valid=false;
        
        do{
            System.out.println(prompt); //display prompt
        
            try{ 
                userInput = myKB.nextDouble(); //get a number -- causes an exception if not an int!
                valid = true; // must be OK
            }
            catch(Exception e){
            
                System.out.println("Invalid input - you must enter an integer only");
                myKB.nextLine(); // get rid of return char
            }
            
        }while(!valid);
        
        return userInput;
    
    }
    
    /**
     * get an integer from the user by issuing a prompt
     * must have a specified min value
     * @param prompt the prompt to be issued
     * @param minValue the minimum value allowed
     * @return a VALID int bigger than minValue
     */
    public int getUserInt(String prompt, int minValue){
        
        Scanner myKB = new Scanner(System.in);
        int userInput=0;
        boolean valid=false;
        
        do{
            System.out.println(prompt); //display prompt
            System.out.println("You must enter a number bigger than " + minValue);
            try{ 
                userInput = myKB.nextInt(); //get a number -- causes an exception if not an int!
                valid = true; // must be OK
            }
            catch(Exception e){
            
                System.out.println("Invalid input - you must enter an integer only");
                myKB.nextLine(); // get rid of return char
            }
              //keep going while input is outside valid range, or is not a number at all
        }while(!valid || userInput < minValue);
        
        return userInput;
    }
    
    
    /**
     * Get an integer from the user by issuing a prompt
     * Must be within a range provided (min/max)
     * @param prompt the prompt message to issue
     * @param minValue the lowest value allowed
     * @param maxValue the highest value allowed
     * @return a valid int between minValue and maxValue
     */
     public int getUserInt(String prompt, int minValue, int maxValue){
        
        Scanner myKB = new Scanner(System.in);
        int userInput=0;
        boolean valid=false;
        
        do{
            System.out.println(prompt); //display prompt
            System.out.println("You must enter a number beetwen " + minValue + " and " + maxValue);
            try{ 
                userInput = myKB.nextInt(); //get a number -- causes an exception if not an int!
                valid = true; // must be OK
            }
            catch(Exception e){
            
                System.out.println("Invalid input - you must enter an integer only");
                myKB.nextLine(); // get rid of return char
            }
            
            //keep going while input is outside valid range, or is not a number at all
        }while(!valid || userInput < minValue || userInput > maxValue);
        
        return userInput;
    }
}
