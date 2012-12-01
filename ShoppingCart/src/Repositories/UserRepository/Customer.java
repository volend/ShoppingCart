
package Repositories.UserRepository;

import Store.ICustomerStoreView;

/**
 *
 * @author volen
 */
public class Customer extends User {
    
    private final ICustomerStoreView mStoreView;
   
    /**
     * Creates new Customer object.
     * @param customerStore The value to construct the Customer object.
     * @param repository The repository value to construct the Customer object.
     */
    public Customer(ICustomerStoreView customerStore, IUserRepository repository) {
        super(repository);
        mStoreView = customerStore;
    }
}
