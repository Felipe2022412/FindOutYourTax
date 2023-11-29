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

   private double PAYECalc() {
    if (!isMarried()) {
        if (getGrossIncome() <= 40000) {
            return Tax.PayeSingleLower.getRate() * getGrossIncome();
        } else {
            return Tax.PayeSingleOver.getRate() * getGrossIncome();
        }
    } else {
        if (getGrossIncome() > 40000) {
            return Tax.PayeMarriedLower.getRate() * getGrossIncome();
        } else {
            return Tax.PayeMarriedOver.getRate() * getGrossIncome();
        }
    }
}

    private double USCCalc() {

        if () {
        } else {
        }
    }

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
