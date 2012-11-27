/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.UserRepository;

import Store.ICustomerStoreView;

/**
 *
 * @author volen
 */
public class Customer extends User {
    
    private final ICustomerStoreView mStoreView;
    
    public Customer(ICustomerStoreView customerStore, IUserRepository repository) {
        super(repository);
        mStoreView = customerStore;
    }
}
