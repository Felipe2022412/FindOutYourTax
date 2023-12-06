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


    //Method to calculate the PAYE - the tax is progressive an over the gross amount
    protected double PAYECalc(UserTaxes user) {
        double PAYE;
        if (!user.isMarried()) { //to check if the user is married
            if (user.getIncomeAfterCredits() <= TaxRange.PAYESINGLERANGE.getRange()) { //checking if the income after tax credits is lower than the first range for a single person
                PAYE = Tax.PAYESINGLELOWERRATE.getRate() * user.getIncomeAfterCredits();
            } else {
                PAYE = (TaxRange.PAYESINGLERANGE.getRange() * Tax.PAYESINGLELOWERRATE.getRate())
                        + ((user.getIncomeAfterCredits() - TaxRange.PAYESINGLERANGE.getRange()) * Tax.PAYESINGLEOVERRATE.getRate());
            }
        } else {
            if (user.getCoupleTotalIncomeAfterCredits() <= TaxRange.PAYEMARRIEDONEINCOMERANGE.getRange()) {//checking if the income after tax credits is lower than the first range for a married person
                PAYE = Tax.PAYEMARRIEDLOWERRATE.getRate() * user.getCoupleTotalIncomeAfterCredits();
            } else {
                PAYE = (TaxRange.PAYEMARRIEDONEINCOMERANGE.getRange() * Tax.PAYEMARRIEDLOWERRATE.getRate())
                        + ((user.getCoupleTotalIncomeAfterCredits() - TaxRange.PAYEMARRIEDONEINCOMERANGE.getRange()) * Tax.PAYEMARRIEDOVERRATE.getRate());
            }
        }
        return PAYE;
    }

    //Method to calculate the USC - the tax is progressive an over the gross amount
    protected double USCCalc(UserTaxes user) {
        double USC;
        if (user.getIncomeAfterCredits() <= TaxRange.USCRANGEONE.getRange()) {//checking if income after credits is lower than the USC first range
            USC = Tax.USCCLASSONERATE.getRate() * user.getIncomeAfterCredits();
        } else if (user.getIncomeAfterCredits() > TaxRange.USCRANGEONE.getRange() && user.getIncomeAfterCredits() <= TaxRange.USCRANGETWO.getRange()) {//checking if income after credits is higher than the USC first range and lower than the second range
            USC = (Tax.USCCLASSONERATE.getRate() * TaxRange.USCRANGEONE.getRange())
                    + ((user.getIncomeAfterCredits() - TaxRange.USCRANGEONE.getRange()) * Tax.USCCLASSTWORATE.getRate());
        } else if (user.getIncomeAfterCredits() > TaxRange.USCRANGETWO.getRange() && user.getIncomeAfterCredits() <= TaxRange.USCRANGETHREE.getRange()) {//checking if income after credits is higher than the USC second range and lower than the third range
            USC = (Tax.USCCLASSONERATE.getRate() * TaxRange.USCRANGEONE.getRange())
                    + ((TaxRange.USCRANGETWO.getRange() - TaxRange.USCRANGEONE.getRange()) * Tax.USCCLASSTWORATE.getRate())
                    + ((user.getIncomeAfterCredits() - TaxRange.USCRANGETHREE.getRange()) * Tax.USCCLASSTHREERATE.getRate());
        } else {//otherwise if income after credits is higher than the USC third range 
            USC = (Tax.USCCLASSONERATE.getRate() * TaxRange.USCRANGEONE.getRange())
                    + ((TaxRange.USCRANGETWO.getRange() - TaxRange.USCRANGEONE.getRange()) * Tax.USCCLASSTWORATE.getRate())
                    + ((TaxRange.USCRANGETHREE.getRange() - TaxRange.USCRANGETWO.getRange()) * Tax.USCCLASSTHREERATE.getRate())
                    + ((user.getIncomeAfterCredits() - TaxRange.USCRANGETHREE.getRange()) * Tax.USCCLASSFOURRATE.getRate());

        }
        return USC;
    }

    //Method to calculate the PRSI - the tax is progressive an over the gross amount
    protected double PRSICalc(UserTaxes user) {
        double PRSI;
        if (user.getIncomeAfterCredits() <= TaxRange.PRSIRANGE.getRange()) {//checking if income after credits is higher than the PRSI range
            PRSI = user.getIncomeAfterCredits() * Tax.PRSICLASSONERATE.getRate();
        } else {//otherwise if income after credits is higher than the PRSI range
            PRSI = (user.getIncomeAfterCredits() - TaxRange.PRSIRANGE.getRange()) * Tax.PRSICLASSTWORATE.getRate();// as the first 18,354 is not taxed
        }
        return PRSI;
    }

}
