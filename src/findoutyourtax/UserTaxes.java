/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 *
 * @author dougl
 */
public class UserTaxes {
    
    protected User user;
    protected double grossIncome;
    protected double taxCredits;
    protected double incomeAfterCredits;
    protected double partnerGrossIncome;
    protected double partnerTaxCredits;
    protected double partnerIncomeAfterCredits;
    protected double coupleTotalIncomeAfterCredits;
    protected double totalTaxesDue;
    protected double liquidAmount;

    public UserTaxes(User user, double grossIncome, double taxCredits, double partnerGrossIncome, double partnerTaxCredits, double totalTaxesDue, double liquidAmount) {
        this.user = user;
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
        this.partnerGrossIncome = partnerGrossIncome;
        this.partnerTaxCredits = partnerTaxCredits;
        this.totalTaxesDue = totalTaxesDue;
        this.liquidAmount = liquidAmount;

    }

    public UserTaxes(User user, double grossIncome, double taxCredits) {
        this.user = user;
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
    }

    public UserTaxes(User user, double grossIncome, double taxCredits, double partnerGrossIncome, double partnerTaxCredits) {
        this.user = user;
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
        this.partnerGrossIncome = partnerGrossIncome;
        this.partnerTaxCredits = partnerTaxCredits;
        this.incomeAfterCredits = grossIncome - taxCredits;
        this.partnerIncomeAfterCredits = partnerGrossIncome - partnerTaxCredits;
        this.coupleTotalIncomeAfterCredits = incomeAfterCredits + partnerIncomeAfterCredits;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public double getTaxCredits() {
        return taxCredits;
    }

    public void setTaxCredits(double taxCredits) {
        this.taxCredits = taxCredits;
    }

    public double getIncomeAfterCredits() {
        return incomeAfterCredits;
    }

    public void setIncomeAfterCredits(double incomeAfterCredits) {
        this.incomeAfterCredits = incomeAfterCredits;
    }

    public double getPartnerGrossIncome() {
        return partnerGrossIncome;
    }

    public void setPartnerGrossIncome(double partnerGrossIncome) {
        this.partnerGrossIncome = partnerGrossIncome;
    }

    public double getPartnerTaxCredits() {
        return partnerTaxCredits;
    }

    public void setPartnerTaxCredits(double partnerTaxCredits) {
        this.partnerTaxCredits = partnerTaxCredits;
    }

    public double getPartnerIncomeAfterCredits() {
        return partnerIncomeAfterCredits;
    }

    public void setPartnerIncomeAfterCredits(double partnerIncomeAfterCredits) {
        this.partnerIncomeAfterCredits = partnerIncomeAfterCredits;
    }

    public double getCoupleTotalIncomeAfterCredits() {
        return coupleTotalIncomeAfterCredits;
    }

    public void setCoupleTotalIncomeAfterCredits(double coupleTotalIncomeAfterCredits) {
        this.coupleTotalIncomeAfterCredits = coupleTotalIncomeAfterCredits;
    }

    public double getTotalTaxesDue() {
        return totalTaxesDue;
    }

    public void setTotalTaxesDue(double totalTaxesDue) {
        this.totalTaxesDue = totalTaxesDue;
    }

    public double getLiquidAmount() {
        return liquidAmount;
    }

    public void setLiquidAmount(double liquidAmount) {
        this.liquidAmount = liquidAmount;
    }
    
    public boolean isMarried(){
        return user.isMarried();
    }

    public User getUser() {
        return user;
    }
    

    @Override
    public String toString() {
        return "User details as follow:\n" +
                "User: " + user + "\n"
                + "Gross income: " + Math.ceil(grossIncome) + "\n"
                + "Tax credits: " + Math.ceil(taxCredits) + "\n" 
                + "Income after credits: " + Math.ceil(incomeAfterCredits) + "\n"
                + "Partner gross income: " + Math.ceil(partnerGrossIncome) + "\n"
                + "Partner tax credits: " + Math.ceil(partnerTaxCredits) + "\n"
                + "Partner income after credits: " + Math.ceil(partnerIncomeAfterCredits)  + "\n"
                + "Couple total income after credits: " + Math.ceil(coupleTotalIncomeAfterCredits) + "\n"
                + "Total taxes due: " + Math.ceil(totalTaxesDue) + "\n"
                + "Liquid amount: " + Math.ceil(liquidAmount);
    }

}
