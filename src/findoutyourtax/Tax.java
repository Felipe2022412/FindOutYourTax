/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 *Enum for each tax in Ireland
 * All gross incomes are calculated on annual basis
 * @author felip
 */
public enum Tax {
    
     
    PayeSingleLowerRate(0.2),//GROSS INCOME UNDER 40.000 EURO, 20%
    PayeSingleOverRate(0.4),//GROSS INCOMEOVER 40.000 EURO, 40%
    PayeMarriedLowerRate(0.2),//GROSS INCOME UNDER 40.000 EURO, 20%
    PayeMarriedOverRate(0.4),//GROSS INCOME OVER 40.000 EURO, 40%

    USCClassOneRate(0.005), //GROSS INCOME UNDER 12.012 EURO 0.5%
    USCClassTwoRate(0.02), //GROSS INCOME FROM 12,012.01 TO 22,920 EURO 2%
    USCClassThreeRate(0.045), //GROSS INCOME FROM 22,920.01 TO 70,044 EURO 4.5%
    USCClassFourRate(0.08), //GROSS INCOME FROM 70,044.01 EURO 8%
    
    PRSIClassOneRate(0.0),  //GROSS INCOME UNDER 18,354.30 EURO
    PRSIClassTwoRate(0.04); //GROSS INCOME OVER 18,354.30 EURO
    
    private final double rate;

    // Created a constructor for my enums and passed their value as rate
    Tax(double rate) {
        this.rate = rate;
    }

    // A getter to get the rate assigned for each enum
    public double getRate() {
        return rate;
    }
}

