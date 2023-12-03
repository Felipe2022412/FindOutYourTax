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

    @Override
    public String toString() {
        return "UserTaxes{" + "user=" + user + ", grossIncome=" + grossIncome + ", taxCredits=" + taxCredits + ", incomeAfterCredits=" + incomeAfterCredits + ", partnerGrossIncome=" + partnerGrossIncome + ", partnerTaxCredits=" + partnerTaxCredits + ", partnerIncomeAfterCredits=" + partnerIncomeAfterCredits + ", coupleTotalIncomeAfterCredits=" + coupleTotalIncomeAfterCredits + ", totalTaxesDue=" + totalTaxesDue + ", liquidAmount=" + liquidAmount + '}';
    }

}
