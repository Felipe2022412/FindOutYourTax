/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 * Enum for each salary range in Ireland All gross incomes are calculated on
 * annual basis
 *
 * @author felip
 */
public enum TaxRange {

    PayeSingleRange(40000), //MAX GROSS INCOME TO BE TAXED IN THE FIRST RANGE AS SINGLE
    PayeMarriedOneIncomeRange(49000), //MAX GROSS INCOME TO BE TAXED IN THE FIRST RANGE AS MARRIED WITH ONE INCOME
    PayeMarriedTwoIncomesRange(80000), //MAX GROSS INCOME TO BE TAXED IN THE FIRST RANGE AS MARRIED WITH TWO INCOMES

    USCRangeOne(12012), //MAX GROSS INCOME TO BE TAXED IN THE FIRST RANGE 
    USCClassTwoRange(22920), //MAX GROSS INCOME TO BE TAXED IN THE SECOND RANGE
    USCClassThreeRange(70044), //MAX GROSS INCOME TO BE TAXED IN THE THIRD RANGE

    PRSIRange(18354.30);  //MAX GROSS INCOME TO BE TAXED IN THE FIRST RANGE

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
