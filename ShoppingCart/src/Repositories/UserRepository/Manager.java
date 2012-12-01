
package Repositories.UserRepository;

import Store.ICustomerStoreView;
import Store.IManagerStoreView;

/**
 *
 * @author volen
 */
public class Manager extends User {
   
    private final IManagerStoreView mStoreView;

    /**
     * Creates new Manager object.
     * @param customerStore The value to construct the Manager object.
     * @param repository The repository value to construct the Manager object.
     */
    public Manager(IManagerStoreView customerStore, IUserRepository repository) {
        super(repository);
        mStoreView = customerStore;
    }
}
