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
public class CalculateTaxes {



    //MUDAR PARAMETROS???????????? DOUGLAAAAAS HEEEEEEELP


    //Method to calculate the PAYE - the tax is progressive an over the gross amount
    protected double PAYECalc(UserTaxes user) {
        if (!user.isMarried()) { //to check if the user is married
            if (user.getIncomeAfterCredits() <= TaxRange.PayeSingleRange.getRange()) { //checking if the income after tax credits is lower than the first range for a single person
                return Tax.PayeSingleLowerRate.getRate() * user.getIncomeAfterCredits();
            } else {
                return (TaxRange.PayeSingleRange.getRange() * Tax.PayeSingleLowerRate.getRate())
                        + ((user.getIncomeAfterCredits() - TaxRange.PayeSingleRange.getRange()) * Tax.PayeSingleOverRate.getRate());

            }
        } else {
            if (user.getCoupleTotalIncomeAfterCredits() <= TaxRange.PayeMarriedOneIncomeRange.getRange()) {//checking if the income after tax credits is lower than the first range for a married person
                return Tax.PayeMarriedLowerRate.getRate() * user.getCoupleTotalIncomeAfterCredits();
            } else {
                return (TaxRange.PayeMarriedOneIncomeRange.getRange() * Tax.PayeMarriedLowerRate.getRate())
                        + ((user.getCoupleTotalIncomeAfterCredits() - TaxRange.PayeMarriedOneIncomeRange.getRange()) * Tax.PayeMarriedOverRate.getRate());
            }
        }
    }

    //Method to calculate the USC - the tax is progressive an over the gross amount
    protected double USCCalc(UserTaxes user) {

        if (user.getIncomeAfterCredits() <= TaxRange.USCRangeOne.getRange()) {//checking if income after credits is lower than the USC first range
            return Tax.USCClassOneRate.getRate() * user.getIncomeAfterCredits();
        } else if (user.getIncomeAfterCredits() > TaxRange.USCRangeOne.getRange() && user.getIncomeAfterCredits() < TaxRange.USCRangeTwo.getRange()) {//checking if income after credits is higher than the USC first range and lower than the second range
            return (Tax.USCClassOneRate.getRate() * TaxRange.USCRangeOne.getRange())
                    + ((user.getIncomeAfterCredits() - TaxRange.USCRangeOne.getRange()) * Tax.USCClassTwoRate.getRate());
        } else if (user.getIncomeAfterCredits() > TaxRange.USCRangeTwo.getRange() && user.getIncomeAfterCredits() < TaxRange.USCRangeThree.getRange()) {//checking if income after credits is higher than the USC second range and lower than the third range
            return (Tax.USCClassOneRate.getRate() * TaxRange.USCRangeOne.getRange())
                    + ((TaxRange.USCRangeTwo.getRange() - TaxRange.USCRangeOne.getRange()) * Tax.USCClassTwoRate.getRate())
                    + ((user.getIncomeAfterCredits() - TaxRange.USCRangeThree.getRange()) * Tax.USCClassThreeRate.getRate());
        } else {//otherwise if income after credits is higher than the USC third range 
            return (Tax.USCClassOneRate.getRate() * TaxRange.USCRangeOne.getRange())
                    + ((TaxRange.USCRangeTwo.getRange() - TaxRange.USCRangeOne.getRange()) * Tax.USCClassTwoRate.getRate())
                    + ((TaxRange.USCRangeThree.getRange() - TaxRange.USCRangeTwo.getRange()) * Tax.USCClassThreeRate.getRate())
                    + ((user.getIncomeAfterCredits() - TaxRange.USCRangeThree.getRange()) * Tax.USCClassFourRate.getRate());

        }
    }

    //Method to calculate the PRSI - the tax is progressive an over the gross amount
    protected double PRSICalc(UserTaxes user) {

        if (user.getIncomeAfterCredits() <= TaxRange.PRSIRange.getRange()) {//checking if income after credits is higher than the PRSI range
            return user.getIncomeAfterCredits() * Tax.PRSIClassOneRate.getRate();
        } else {//otherwise if income after credits is higher than the PRSI range
            return (user.getIncomeAfterCredits() - TaxRange.PRSIRange.getRange()) * Tax.PRSIClassTwoRate.getRate();
        }
    }

    /*//Method to calculate the LIQUID amount - after tax
    protected double liquidAmount() {
        return getIncomeAfterCredits() - PAYECalc() - USCCalc() - PRSICalc();
    }

    //Method to calculate the total taxes due 
    protected double totalTaxesDue() {
        return PAYECalc() + USCCalc() + PRSICalc();
    }

    public double getIncomeAfterCredits() {
        return incomeAfterCredits;
    }

    public double getTotalTaxesDue() {
        return totalTaxesDue;
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

    public double getPartnerTaxCredits() {
        return partnerTaxCredits;
    }

    public double getPartnerIncomeAfterCredits() {
        return partnerIncomeAfterCredits;
    }

    public double getCoupleTotalIncomeAfterCredits() {
        return coupleTotalIncomeAfterCredits;
    }

    public void setPartnerTaxCredits(double partnerTaxCredits) {
        this.partnerTaxCredits = partnerTaxCredits;
    }

    public void setpartnerGrossIncome(double partnerGrossIncome) {
        this.partnerGrossIncome = partnerGrossIncome;
    }*/

}