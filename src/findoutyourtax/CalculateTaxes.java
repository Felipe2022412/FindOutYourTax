/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 * Class to calculate each tax and total amount owed
 *
 * @author felip
 */
//extending UserIncome to be able to use their income,
public class CalculateTaxes extends User {

    protected double grossIncome;
    protected double taxCredits;
    protected double partnerGrossIncome;
    protected double totalTaxesOwed;
    protected double liquidAmount;

    public CalculateTaxes(String firstName, String lastName, String userName, String password, String dateOfBirthday, String ppsNo, String email, boolean married) {
        super(firstName, lastName, userName, password, dateOfBirthday, ppsNo, email, married);
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public double getTaxCredits() {
        return taxCredits;
    }

    public double getPartnerGrossIncome() {
        return partnerGrossIncome;
    }

    public void setTaxCredits(double taxCredits) {
        this.taxCredits = taxCredits;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public void setPartnerGrossIncome(double partnerGrossIncome) {
        this.partnerGrossIncome = partnerGrossIncome;
    }

    //Method to calculate the PAYE - the tax is progressive an over the gross amount
    private double PAYECalc() {
        if (!isMarried()) {
            if (getGrossIncome() <= TaxRange.PayeSingleRange.getRange()) {
                return Tax.PayeSingleLowerRate.getRate() * getGrossIncome();
            } else {
                return (TaxRange.PayeSingleRange.getRange() * Tax.PayeSingleLowerRate.getRate())
                        + ((getGrossIncome() - TaxRange.PayeSingleRange.getRange()) * Tax.PayeSingleOverRate.getRate());

            }
        } else {
            if (getGrossIncome() < TaxRange.PayeMarriedOneIncomeRange.getRange()) {
                return Tax.PayeMarriedLowerRate.getRate() * getGrossIncome();
            } else {
                return (TaxRange.PayeMarriedOneIncomeRange.getRange() * Tax.PayeMarriedLowerRate.getRate())
                        + ((getGrossIncome() - TaxRange.PayeMarriedTwoIncomesRange.getRange()) * Tax.PayeMarriedOverRate.getRate());
            }
        }
    }

    //Method to calculate the USC - the tax is progressive an over the gross amount
    private double USCCalc() {

        if (getGrossIncome() < TaxRange.USCRangeOne.getRange()) {
            return Tax.USCClassOneRate.getRate() * TaxRange.USCRangeOne.getRange();
        } else if (getGrossIncome() > TaxRange.USCRangeOne.getRange() && getGrossIncome() < TaxRange.USCRangeTwo.getRange()) {
            return (Tax.USCClassOneRate.getRate() * TaxRange.USCRangeOne.getRange())
                    + ((getGrossIncome() - TaxRange.USCRangeTwo.getRange()) * Tax.USCClassTwoRate.getRate());
        } else if (getGrossIncome() > TaxRange.USCRangeTwo.getRange() && getGrossIncome() < TaxRange.USCRangeThree.getRange()) {
            return (Tax.USCClassOneRate.getRate() * TaxRange.USCRangeOne.getRange())
                    + ((TaxRange.USCRangeOne.getRange() - TaxRange.USCRangeTwo.getRange()) * Tax.USCClassTwoRate.getRate())
                    + ((getGrossIncome() - TaxRange.USCRangeThree.getRange()) * Tax.USCClassThreeRate.getRate());
        } else {
            return (Tax.USCClassOneRate.getRate() * TaxRange.USCRangeOne.getRange())
                    + ((TaxRange.USCRangeOne.getRange() - TaxRange.USCRangeTwo.getRange()) * Tax.USCClassTwoRate.getRate())
                    + ((TaxRange.USCRangeTwo.getRange() - TaxRange.USCRangeThree.getRange()) * Tax.USCClassThreeRate.getRate())
                    + ((getGrossIncome() - TaxRange.USCRangeThree.getRange()) * Tax.USCClassFourRate.getRate());
                    
        }
    }



//    // USCClassOneRate(0.005), //GROSS INCOME UNDER 12.012 EURO 0.5%
//    USCClassTwoRate(0.02), //GROSS INCOME FROM 12,012.01 TO 22,920 EURO 2%
//    USCClassThreeRate(0.045), //GROSS INCOME FROM 22,920.01 TO 70,044 EURO 4.5%
//    USCClassFourRate(
private double PRSICalc() {

        if () {
        } else {
        }
    }

    private double liquidAmount() {
        if () {
        } else {
        }
    }

    private double totalTaxOwed() {

    }
}
