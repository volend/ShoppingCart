/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.UserRepository;

import Store.ICustomerStoreView;
import Store.Product;
import Store.ShoppingCart;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author volen
 */
public class Customer extends User {

    final HashSet<BillingInformation> mPaymentOptions;
    final ShoppingCart mShoppingCart;
    ICustomerStoreView mStore;

    public Customer(ICustomerStoreView store, IUserRepository repository, UserInfo info) {
        super(info, repository);
        mStore = store;
        mShoppingCart = new ShoppingCart();
        mPaymentOptions = new HashSet<>();
    }

    public Set<BillingInformation> getBillingInfo() {
        return mRepository.getBillingInfo(this);
    }

    public void addBillingInfo(BillingInformation info) {
        mRepository.addBillingInfo(this, info);
    }

    public void removeBillingInfo(BillingInformation info) {
        mRepository.removeBillingInfo(this, info);
    }
    
    public void addToShoppingCart(Product product, int quantity)
    {
        assert(product != null);
        
        mShoppingCart.addItem(product, quantity);
    }
    
    public void removeFromShoppingCart(Product product)
    {
        assert(product != null);
        
        mShoppingCart.removeItem(product);
    }
}