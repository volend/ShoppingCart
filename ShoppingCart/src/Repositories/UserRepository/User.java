
package Repositories.UserRepository;

import Repositories.ProductRepository.Product;
import Store.ShoppingCart;
import java.util.HashSet;
import java.util.Set;

/**
 * Class User to deal with user details.
 * @author volen
 */
public class User {

    private final IUserRepository mRepository;
    private final ShoppingCart mShoppingCart;
    private UserInfo mUserInfo;

    /**
     * Creates new User object.
     * @param repository 
     */
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
    
    public ShoppingCart getShoppingCart()
    {
        return mShoppingCart;
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

    public Boolean changePassword(String currentPassword, String newPassword) {
        if (mUserInfo == null) {
            return false;
        }

        mUserInfo = mRepository.changePassword(mUserInfo.EmailAddress, currentPassword, newPassword);
        return true;
    }

    public AccessPrivileges getPrivileges() {
        return mUserInfo.Privileges;
    }
}