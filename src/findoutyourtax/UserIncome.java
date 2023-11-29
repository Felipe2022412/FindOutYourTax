/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 * Class for user income input (getters and setters)
 *
 * @author felip
 */
public class UserIncome {

    protected double grossIncome;
    protected double taxCredits;
    protected double partnerGrossIncome;

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
