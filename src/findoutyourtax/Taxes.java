/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 *Class for all taxes in Ireland and total taxes owed (getters and setters)
 * @author felip
 */
public class Taxes {
    
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

    
    
    
}
