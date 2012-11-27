/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.UserRepository;

import Store.ICustomerStoreView;
import Store.IManagerStoreView;

/**
 *
 * @author volen
 */
public class Manager extends User {

    private final IManagerStoreView mStoreView;

    public Manager(IManagerStoreView customerStore, IUserRepository repository) {
        super(repository);
        mStoreView = customerStore;
    }
}
