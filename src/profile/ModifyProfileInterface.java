/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package profile;

import user.User;

/**
 * Interface for modifier methods that can be implemented by classes for
 * modifying User profiles.
 *
 * @author Douglas and Felipe
 */
public interface ModifyProfileInterface {

    //Modifiers that will belong to the admin user and regular user and change their information. 
    void modifyProfile(User user);

}
