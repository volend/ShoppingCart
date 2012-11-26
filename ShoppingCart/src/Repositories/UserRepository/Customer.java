/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.UserRepository;

import Store.ICustomerStoreView;
import Store.Inventory;
import Store.ShoppingCart;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author volen
 */
public class Customer extends User {

    String mFirstName, mLastName;
    String mEmailAddress;
    String mPhoneNumber;
    String mPasswordToken;
    HashSet<BillingInformation> mPaymentOptions;
    ShoppingCart mShoppingCart;
    ICustomerStoreView mStore;

    public Customer(ICustomerStoreView store, IUserRepository repository, UserInfo info) {
        super(info, repository);
        mStore = store;
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
    //TODO: provide the ability to update account information
}