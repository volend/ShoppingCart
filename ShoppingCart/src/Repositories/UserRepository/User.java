/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.UserRepository;

import Store.Product;
import Store.ShoppingCart;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author volen
 */
public class User {

    private final IUserRepository mRepository;
    private final ShoppingCart mShoppingCart;
    private UserInfo mUserInfo;

    public User(IUserRepository repository) {
        mRepository = repository;
        mShoppingCart = new ShoppingCart();
    }

    public Set<BillingInformation> getBillingInfo() {
        if (mUserInfo == null) {
            return new HashSet<>();
        }
        return mRepository.getBillingInfo(mUserInfo);
    }

    public void addBillingInfo(BillingInformation info) {
        if (mUserInfo == null) {
            return;
        }
        mRepository.addBillingInfo(mUserInfo, info);
    }

    public void removeBillingInfo(BillingInformation info) {
        if (mUserInfo == null) {
            return;
        }
        mRepository.removeBillingInfo(mUserInfo, info);
    }

    public void addToShoppingCart(Product product, int quantity) {
        assert (product != null);

        mShoppingCart.addItem(product, quantity);
    }

    public void removeFromShoppingCart(Product product) {
        assert (product != null);

        mShoppingCart.removeItem(product);
    }

    /**
     * @return the mFirstName
     */
    public String getFirstName() {
        if (mUserInfo == null) {
            return "Guest";
        }
        return mUserInfo.FirstName;
    }

    /**
     * @return the mLastName
     */
    public String getLastName() {
        if (mUserInfo == null) {
            return "User";
        }
        return mUserInfo.LastName;
    }

    /**
     * @return the mEmailAddress
     */
    public String getEmailAddress() {
        if (mUserInfo == null) {
            return "";
        }
        return mUserInfo.EmailAddress;
    }

    /**
     * @return the mAccessToken
     */
    public String getAccessToken() {
        if (mUserInfo == null) {
            return "";
        }
        return mUserInfo.AccessToken;
    }

    /**
     * @return the mPhoneNumber
     */
    public String getPhoneNumber() {
        if (mUserInfo == null) {
            return "";
        }
        return mUserInfo.PhoneNumber;
    }

    public void logout() {
        if (mUserInfo != null) {
            mRepository.logout(mUserInfo);
            mUserInfo = null;
        }
    }

    public void login(String email, String password) {
        mUserInfo = mRepository.login(email, password);
    }

    public void changePassword(String newPassword) {
        mRepository.changePassword(newPassword);
    }

    public AccessPrivileges getPrivileges() {
        return mUserInfo.Privileges;
    }
}