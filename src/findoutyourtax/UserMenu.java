/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package findoutyourtax;

/**
 *
 * @author dougl
 */
public interface UserMenu {
    
    public void viewProfile(User user);
    public void changeProfile(User user);
    public void calculateTaxes(User user);
    public void seePreviouslyCalcs(User user);
    public void exit();
    
}