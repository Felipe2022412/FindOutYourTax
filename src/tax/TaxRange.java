/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tax;

/**
 * Enum representing different salary ranges in Ireland for tax calculation.
 * All gross incomes are calculated on an annual basis.
 * 60.06 218.16
 * @author Douglas and Felipe
 */
public enum TaxRange {

    PAYESINGLERANGE(40000), //MAX GROSS INCOME TO BE TAXED IN THE FIRST RANGE AS SINGLE
    PAYEMARRIEDONEINCOMERANGE(49000), //MAX GROSS INCOME TO BE TAXED IN THE FIRST RANGE AS MARRIED WITH ONE INCOME
    PAYEMARRIEDTWOINCOMESRANGE(80000), //MAX GROSS INCOME TO BE TAXED IN THE FIRST RANGE AS MARRIED WITH TWO INCOMES

    USCRANGEONE(12012), //MAX GROSS INCOME TO BE TAXED IN THE FIRST RANGE 
    USCRANGETWO(22920), //MAX GROSS INCOME TO BE TAXED IN THE SECOND RANGE
    USCRANGETHREE(70044), //MAX GROSS INCOME TO BE TAXED IN THE THIRD RANGE

    PRSIRANGE(18354.30);  //MAX GROSS INCOME TO BE TAXED IN THE FIRST RANGE

    private final double range;

    // Created a constructor for my enums and passed their value as range
    TaxRange(double range) {
        this.range = range;
    }

    // A getter to get the rate assigned for each enum
    public double getRange() {
        return range;
    }

}
