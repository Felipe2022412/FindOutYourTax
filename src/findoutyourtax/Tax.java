/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findoutyourtax;

/**
 *Enum for each tax in Ireland
 * @author felip
 */
public enum Tax {
    
     
    INCOME_TAX(0.2),  // Example value: 20%
    USC(0.05),        // Example value: 5%
    PRSI(0.1);        // Example value: 10%

    private final double rate;

    Tax(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
    
