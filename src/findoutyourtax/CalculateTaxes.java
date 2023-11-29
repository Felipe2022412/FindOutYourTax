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
public class CalculateTaxes extends UserIncome {

    private double paye;
    private double usc;
    private double prsi;
    protected double totalTaxesOwen;

    public double getPaye() {
        return paye;
    }

    public void setPaye(double paye) {
        this.paye = paye;
    }

    public double getUsc() {
        return usc;
    }

    public void setUsc(double usc) {
        this.usc = usc;
    }

    public double getPrsi() {
        return prsi;
    }

    public void setPrsi(double prsi) {
        this.prsi = prsi;
    }

    public double getTotalTaxesOwen() {
        return totalTaxesOwen;
    }

    public void setTotalTaxesOwen(double totalTaxesOwen) {
        this.totalTaxesOwen = totalTaxesOwen;
    }

    private double PAYECalc() {

        return 0;

    }

    private double USCCalc() {

        return 0;
    }

    private double PRSICalc() {

        return 0;
    }

    private double incomeAfterCredit() {
        return 0;
    }
}
