/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.UserRepository;

import java.util.Set;

/**
 *
 * @author volen
 */
public interface IUserRepository {

    UserInfo login(String emailAddress, String password);

    void logout(UserInfo user);

    UserInfo changePassword(String newPassword);

    UserInfo createUser(UserInfo info);

    Set<BillingInformation> getBillingInfo(UserInfo user);

    void addBillingInfo(UserInfo owner, BillingInformation info);

    void removeBillingInfo(UserInfo owner, BillingInformation info);
    
    void authorizeUser(UserInfo user);
}
