/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 * Class created to display the results of each tax, its total and, liquid
 * amount
 *
 * @author felip
 */
public class DisplayTaxes extends CalculateTaxes {

    //USAR PARA COLOCAR NA BASE DE DADOS!!!!
    //MUDAR PARAMETROS???????????? DOUGLAAAAAS HEEEEEEELP!!!!
    public DisplayTaxes(String firstName, String lastName, String userName, String password, String dateOfBirth, String ppsNo, String email, boolean married) {
        super(firstName, lastName, userName, password, dateOfBirth, ppsNo, email, married);
    }

    //Class to display PAYE DUE
    private double displayPAYEDue() {
        return PAYECalc();

    }

    //Class to display USC DUE
    private double displayUSCIDue() {
        return USCCalc();
    }

    //Class to display PRSI DUE
    private double displayPRSIDue() {
        return PRSICalc();

    }

    //Class to display the liquid amount - after tax
    private double displayLiquidAmount() {
        return liquidAmount();

    }

    //Class to display total tax due
    private double displayTotalTaxesDue() {
        return totalTaxesDue();

    }

    //Class to display Gross Income
    private double displayGrossIncome() {
        return grossIncome;
    }

    //Class to display Tax credits
    private double displayTaxCredits() {
        return taxCredits;

    }

    //Class to display the income after credits, before taxes
    private double displayIncomeAfterCredits() {
        return incomeAfterCredits;

    }

    //Class to display the partner's Gross Income
    private double displayPartnerGrossIncome() {
        return partnerGrossIncome;

    }

    //Class to display the partner's tax credits
    private double displayPartnerTaxCredits() {
        return partnerTaxCredits;

    }

    //Class to display the partner's income after credits
    private double displayPartnerIncomeAfterCredits() {
        return partnerIncomeAfterCredits;

    }

    //Class to display the couple's total income after credits and before taxes
    private double displayCoupleTotalIncomeAfterCredits() {
        return coupleTotalIncomeAfterCredits;

    }


}
