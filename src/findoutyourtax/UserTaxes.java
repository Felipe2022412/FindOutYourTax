/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 *
 * @author dougl
 */
public final class UserTaxes extends CalculateTaxes {

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
    protected double PAYE;
    protected double USC;
    protected double PRSI;

    public UserTaxes(User user, double grossIncome, double taxCredits, double partnerGrossIncome, double partnerTaxCredits, double totalTaxesDue, double liquidAmount) {
        this.user = user;
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
        this.partnerGrossIncome = partnerGrossIncome;
        this.partnerTaxCredits = partnerTaxCredits;
        this.totalTaxesDue = totalTaxesDue;
        this.liquidAmount = liquidAmount;
       // Calculate taxes
        
        calculatePAYE();
        calculateUSC();
        calculatePRSI();
        this.totalTaxesDue = calculateTotalTaxesDue(); // This line calculates total taxes due
        this.liquidAmount = calculateLiquidAmount(); // This line calculates liquid amount

    }
    //Create a single user
    public UserTaxes(User user, double grossIncome, double taxCredits) {
        this.user = user;
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
        // Calculate taxes
        
        this.PAYE = calculatePAYE();
        this.USC = calculateUSC();
        this.PRSI = calculatePRSI();
        this.totalTaxesDue = calculateTotalTaxesDue(); // This line calculates total taxes due
        this.liquidAmount = calculateLiquidAmount(); // This line calculates liquid amount
    }

    public UserTaxes(User user, double grossIncome, double taxCredits, double partnerGrossIncome, double partnerTaxCredits) {

        if (user.isMarried()) {
            this.user = user;
            this.grossIncome = grossIncome;
            this.taxCredits = taxCredits;
            this.partnerGrossIncome = partnerGrossIncome;
            this.partnerTaxCredits = partnerTaxCredits;
            this.incomeAfterCredits = grossIncome - taxCredits;
            this.partnerIncomeAfterCredits = partnerGrossIncome - partnerTaxCredits;
            this.coupleTotalIncomeAfterCredits = incomeAfterCredits + partnerIncomeAfterCredits;

          
        } else {
            this.user = user;
            this.grossIncome = grossIncome;
            this.taxCredits = taxCredits;
            this.incomeAfterCredits = grossIncome - taxCredits;

        }

        // Calculate taxes
        
        calculatePAYE();
        calculateUSC();
        calculatePRSI();
        this.totalTaxesDue = calculateTotalTaxesDue(); // This line calculates total taxes due
        this.liquidAmount = calculateLiquidAmount(); // This line calculates liquid amount
    
    
     

    }

    private double calculateTotalTaxesDue() {
        return calculatePAYE() + calculateUSC() + calculatePRSI();
    }

    private double calculateLiquidAmount() {
        return getIncomeAfterCredits() - calculateTotalTaxesDue();
    }

    private double calculatePAYE() {
        return PAYECalc(this);
    }

    private double calculateUSC() {
        return USCCalc(this);
    }

    private double calculatePRSI() {
        return PRSICalc(this);
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

    public boolean isMarried() {
        return user.isMarried();
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "User details as follow:\n"
                + "User: " + user + "\n"
                + "Gross income: " + Math.ceil(getGrossIncome()) + "\n"
                + "Tax credits: " + Math.ceil(getTaxCredits()) + "\n"
                + "Income after credits: " + Math.ceil(getIncomeAfterCredits()) + "\n"
                + "Partner gross income: " + Math.ceil(getPartnerGrossIncome()) + "\n"
                + "Partner tax credits: " + Math.ceil(getPartnerTaxCredits()) + "\n"
                + "Partner income after credits: " + Math.ceil(getPartnerIncomeAfterCredits()) + "\n"
                + "Couple total income after credits: " + Math.ceil(getCoupleTotalIncomeAfterCredits()) + "\n"
                + "Total taxes due: " + Math.ceil(getTotalTaxesDue()) + "\n"
                + "Liquid amount: " + Math.ceil(getLiquidAmount());
    }

}
