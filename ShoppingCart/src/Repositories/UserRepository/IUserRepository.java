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

    User login(String emailAddress, String password);

    void logout(User user);

    User changePassword(String newPassword);

    User createUser(UserInfo info);

    Set<BillingInformation> getBillingInfo(User user);

    void addBillingInfo(User owner, BillingInformation info);

    void removeBillingInfo(User owner, BillingInformation info);
    
    void authorizeUser(User user);
}
