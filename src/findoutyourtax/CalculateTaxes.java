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
    protected double incomeAfterCredits = grossIncome - taxCredits;
    protected double partnerGrossIncome;
    protected double totalTaxesOwed;
    protected double liquidAmount;

    public CalculateTaxes(String firstName, String lastName, String userName, String password, String dateOfBirthday, String ppsNo, String email, boolean married) {
        super(firstName, lastName, userName, password, dateOfBirthday, ppsNo, email, married);
    }
   

    //Method to calculate the PAYE - the tax is progressive an over the gross amount
    private double PAYECalc() {
        if (!isMarried()) {
            if (getIncomeAfterCredits() <= TaxRange.PayeSingleRange.getRange()) {
                return Tax.PayeSingleLowerRate.getRate() * getIncomeAfterCredits();
            } else {
                return (TaxRange.PayeSingleRange.getRange() * Tax.PayeSingleLowerRate.getRate())
                        + ((getIncomeAfterCredits() - TaxRange.PayeSingleRange.getRange()) * Tax.PayeSingleOverRate.getRate());

            }
        } else {
            if (getIncomeAfterCredits() <= TaxRange.PayeMarriedOneIncomeRange.getRange()) {
                return Tax.PayeMarriedLowerRate.getRate() * getIncomeAfterCredits();
            } else {
                return (TaxRange.PayeMarriedOneIncomeRange.getRange() * Tax.PayeMarriedLowerRate.getRate())
                        + ((getIncomeAfterCredits() - TaxRange.PayeMarriedOneIncomeRange.getRange()) * Tax.PayeMarriedOverRate.getRate());
            }
        }
    }

    //Method to calculate the USC - the tax is progressive an over the gross amount
    private double USCCalc() {

        if (getIncomeAfterCredits() <= TaxRange.USCRangeOne.getRange()) {
            return Tax.USCClassOneRate.getRate() * getIncomeAfterCredits();
        } else if (getIncomeAfterCredits() > TaxRange.USCRangeOne.getRange() && getIncomeAfterCredits() < TaxRange.USCRangeTwo.getRange()) {
            return (Tax.USCClassOneRate.getRate() * TaxRange.USCRangeOne.getRange())
                    + ((getIncomeAfterCredits() - TaxRange.USCRangeOne.getRange()) * Tax.USCClassTwoRate.getRate());
        } else if (getIncomeAfterCredits() > TaxRange.USCRangeTwo.getRange() && getIncomeAfterCredits() < TaxRange.USCRangeThree.getRange()) {
            return (Tax.USCClassOneRate.getRate() * TaxRange.USCRangeOne.getRange())
                    + ((TaxRange.USCRangeTwo.getRange() - TaxRange.USCRangeOne.getRange()) * Tax.USCClassTwoRate.getRate())
                    + ((getIncomeAfterCredits() - TaxRange.USCRangeThree.getRange()) * Tax.USCClassThreeRate.getRate());
        } else {
            return (Tax.USCClassOneRate.getRate() * TaxRange.USCRangeOne.getRange())
                    + ((TaxRange.USCRangeTwo.getRange() - TaxRange.USCRangeOne.getRange()) * Tax.USCClassTwoRate.getRate())
                    + ((TaxRange.USCRangeThree.getRange() - TaxRange.USCRangeTwo.getRange()) * Tax.USCClassThreeRate.getRate())
                    + ((getIncomeAfterCredits() - TaxRange.USCRangeThree.getRange()) * Tax.USCClassFourRate.getRate());

        }
    }

//    // USCClassOneRate(0.005), //GROSS INCOME UNDER 12.012 EURO 0.5%
//    USCClassTwoRate(0.02), //GROSS INCOME FROM 12,012.01 TO 22,920 EURO 2%
//    USCClassThreeRate(0.045), //GROSS INCOME FROM 22,920.01 TO 70,044 EURO 4.5%
//    USCClassFourRate(
    private double PRSICalc() {

        if (getIncomeAfterCredits() <= TaxRange.PRSIRange.getRange()) {
            return getIncomeAfterCredits() * Tax.PRSIClassOneRate.getRate();
        } else {
            return getIncomeAfterCredits() * Tax.PRSIClassTwoRate.getRate();
        }
    }

    private double liquidAmount() {
        return getIncomeAfterCredits() - PAYECalc() - USCCalc() - PRSICalc();
    }

    private double totalTaxOwed() {
        return PAYECalc() + USCCalc() + PRSICalc();
    }
    
    public double getIncomeAfterCredits() {
        return incomeAfterCredits;
    }

    public double getTotalTaxesOwed() {
        return totalTaxesOwed;
    }

    public double getLiquidAmount() {
        return liquidAmount;
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
    
}
