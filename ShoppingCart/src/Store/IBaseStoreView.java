/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import Repositories.UserRepository.Customer;
import Repositories.UserRepository.Manager;
import Repositories.UserRepository.UserInfo;

/**
 *
 * @author volen
 */
public interface IBaseStoreView {
    public Customer customerLogin(String email, String password);
    public Manager managerLogin(String email, String password);
    public UserInfo login(String email, String password);
}
