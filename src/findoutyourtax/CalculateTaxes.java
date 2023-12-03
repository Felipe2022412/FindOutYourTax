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
    protected double partnerTaxCredits;
    protected double partnerIncomeAfterCredits = partnerGrossIncome - partnerTaxCredits;
    protected double coupleTotalIncomeAfterCredits = incomeAfterCredits + partnerIncomeAfterCredits;
    protected double totalTaxesDue;
    protected double liquidAmount;

    //MUDAR PARAMETROS???????????? DOUGLAAAAAS HEEEEEEELP
    public CalculateTaxes(String firstName, String lastName, String userName, String password, String dateOfBirthday, String ppsNo, String email, boolean married) {
        super(firstName, lastName, userName, password, dateOfBirthday, ppsNo, email, married);
    }

    //Method to calculate the PAYE - the tax is progressive an over the gross amount
    protected double PAYECalc() {
        if (!isMarried()) {
            if (getCoupleTotalIncomeAfterCredits() <= TaxRange.PayeSingleRange.getRange()) {
                return Tax.PayeSingleLowerRate.getRate() * getCoupleTotalIncomeAfterCredits();
            } else {
                return (TaxRange.PayeSingleRange.getRange() * Tax.PayeSingleLowerRate.getRate())
                        + ((getCoupleTotalIncomeAfterCredits() - TaxRange.PayeSingleRange.getRange()) * Tax.PayeSingleOverRate.getRate());

            }
        } else {
            if (getCoupleTotalIncomeAfterCredits() <= TaxRange.PayeMarriedOneIncomeRange.getRange()) {
                return Tax.PayeMarriedLowerRate.getRate() * getCoupleTotalIncomeAfterCredits();
            } else {
                return (TaxRange.PayeMarriedOneIncomeRange.getRange() * Tax.PayeMarriedLowerRate.getRate())
                        + ((getCoupleTotalIncomeAfterCredits() - TaxRange.PayeMarriedOneIncomeRange.getRange()) * Tax.PayeMarriedOverRate.getRate());
            }
        }
    }

    //Method to calculate the USC - the tax is progressive an over the gross amount
    protected double USCCalc() {

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

    protected double PRSICalc() {

        if (getIncomeAfterCredits() <= TaxRange.PRSIRange.getRange()) {
            return getIncomeAfterCredits() * Tax.PRSIClassOneRate.getRate();
        } else {
            return getIncomeAfterCredits() * Tax.PRSIClassTwoRate.getRate();
        }
    }

    protected double liquidAmount() {
        return getIncomeAfterCredits() - PAYECalc() - USCCalc() - PRSICalc();
    }

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
    }

}
