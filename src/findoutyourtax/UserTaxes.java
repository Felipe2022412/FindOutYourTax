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

    private User user;
    private double grossIncome;
    private double taxCredits;
    private double incomeAfterCredits;
    private double partnerGrossIncome;
    private double partnerTaxCredits;
    private double partnerIncomeAfterCredits;
    private double coupleTotalIncomeAfterCredits;
    private double totalTaxesDue;
    private double liquidAmount;
    private double PAYE;
    private double USC;
    private double PRSI;

    public UserTaxes(User user, double grossIncome, double taxCredits, double partnerGrossIncome, double partnerTaxCredits, double totalTaxesDue, double liquidAmount) {
        this.user = user;
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
        this.incomeAfterCredits = grossIncome - taxCredits;
        this.partnerGrossIncome = partnerGrossIncome;
        this.partnerTaxCredits = partnerTaxCredits;
        this.partnerIncomeAfterCredits = partnerGrossIncome - partnerTaxCredits;
        this.coupleTotalIncomeAfterCredits = grossIncome + partnerGrossIncome - taxCredits - partnerTaxCredits;
        calculateTaxes();
        this.liquidAmount = grossIncome - totalTaxesDue;

        // Calculate taxes
    }

    // constructor if the user is not married
    public UserTaxes(User user, double grossIncome, double taxCredits) {

        this.user = user;
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
        this.incomeAfterCredits = grossIncome - taxCredits;
        calculateTaxes();
        this.liquidAmount = grossIncome - totalTaxesDue;

    }

    //Is good becaus in case the tax change the sistem will calculate with the new taxes and show to the user / constructor if the user is married
    public UserTaxes(User user, double grossIncome, double taxCredits, double partnerGrossIncome, double partnerTaxCredits) {
        if (user.isMarried()) {
            this.user = user;
            this.grossIncome = grossIncome;
            this.taxCredits = taxCredits;
            this.incomeAfterCredits = grossIncome - taxCredits;
            this.partnerGrossIncome = partnerGrossIncome;
            this.partnerTaxCredits = partnerTaxCredits;
            this.partnerIncomeAfterCredits = partnerGrossIncome - partnerTaxCredits;
            this.coupleTotalIncomeAfterCredits = grossIncome + partnerGrossIncome - taxCredits - partnerTaxCredits;
            calculateTaxes();
            this.liquidAmount = grossIncome - totalTaxesDue;

        }
    }

    private void calculateTaxes() {
        this.PAYE = PAYECalc(this);
        this.USC = USCCalc(this);
        this.PRSI = PRSICalc(this);
        totalTaxesDue = PAYE + USC + PRSI;

    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public double getTaxCredits() {
        return taxCredits;
    }

    public double getIncomeAfterCredits() {
        return incomeAfterCredits;
    }

    public void setPartnerIncomeAfterCredits(double partnerIncomeAfterCredits) {
        this.partnerIncomeAfterCredits = partnerIncomeAfterCredits;
    }

    public void setCoupleTotalIncomeAfterCredits(double coupleTotalIncomeAfterCredits) {
        this.coupleTotalIncomeAfterCredits = coupleTotalIncomeAfterCredits;
    }

    public void setIncomeAfterCredits(double incomeAfterCredits) {
        this.incomeAfterCredits = incomeAfterCredits;
    }

    public double getPartnerGrossIncome() {
        return partnerGrossIncome;
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

    public double getTotalTaxesDue() {
        return totalTaxesDue;
    }

    public double getLiquidAmount() {
        return liquidAmount;
    }

    public boolean isMarried() {
        return user.isMarried();
    }

    public User getUser() {
        return user;
    }

    public double getPAYE() {
        return PAYE;
    }

    public double getUSC() {
        return USC;
    }

    public double getPRSI() {
        return PRSI;
    }

    @Override
    public String toString() {
        return "User tax details as follows:\n"
                + "Gross income: " + Math.ceil(getGrossIncome()) + "\n"
                + "Tax credits: " + Math.ceil(getTaxCredits()) + "\n"
                + "Partner gross income: " + Math.ceil(getPartnerGrossIncome()) + "\n"
                + "Partner tax credits: " + Math.ceil(getPartnerTaxCredits()) + "\n"
                + "Couple total income after credits: " + Math.ceil(getCoupleTotalIncomeAfterCredits()) + "\n"
                + "PAYE: " + Math.ceil(getPAYE()) + "\n"
                + "USC: " + Math.ceil(getUSC()) + "\n"
                + "PRSI: " + Math.ceil(getPRSI()) + "\n"
                + "Total taxes due: " + Math.ceil(getTotalTaxesDue()) + "\n"
                + "Liquid amount: " + Math.ceil(getLiquidAmount());
    }

}
