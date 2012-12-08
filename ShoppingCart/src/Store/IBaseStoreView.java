/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import Repositories.UserRepository.BillingInformation;
import Repositories.UserRepository.Customer;
import Repositories.UserRepository.Manager;
import Repositories.UserRepository.UserInfo;
import java.util.Set;

/**
 *
 * @author volen
 */
public interface IBaseStoreView {
    public Customer customerLogin(String email, String password);
    public Manager managerLogin(String email, String password);
    public UserInfo login(String email, String password);
    public UserInfo registerUser(UserInfo info);
    public Set<BillingInformation> getBillingInfo(UserInfo user);
    public BillingInformation addBillingInfo(UserInfo user, BillingInformation info);
}
