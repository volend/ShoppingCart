/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.UserRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author volen
 */
public class UserRepository implements IUserRepository {

    private final HashMap<String, UserInfo> mUsers;
    private final HashMap<String, HashSet<BillingInformation>> mBillingInfo;
    private final HashSet<String> mLoggedIn;

    public UserRepository() {
        mUsers = new HashMap<>();
        mLoggedIn = new HashSet<>();
        mBillingInfo = new HashMap<>();
        initializeUsers();
    }

    @Override
    public UserInfo login(String emailAddress, String password) {
        if (!mUsers.containsKey(emailAddress)) {
            return null;
        }
        UserInfo info = mUsers.get(emailAddress);
        if (!info.AccessToken.equals(transform(password))) {
            return null;
        }
        mLoggedIn.add(emailAddress);
        return info;
    }
    
    public HashMap<String, UserInfo> getUserList(){
        return mUsers;
    }
    
    @Override
    public void logout(UserInfo user) {
        mLoggedIn.remove(user.EmailAddress);
    }

    @Override
    public UserInfo changePassword(String email, String currentPassword, String newPassword) {
        if (!mUsers.containsKey(email)) {
            return null;
        }

        UserInfo info = mUsers.get(email);
        if (!info.AccessToken.equals(transform(currentPassword))) {
            return null;
        }

        UserInfo info2 = new UserInfo(info.FirstName, info.LastName, info.EmailAddress, transform(newPassword), info.PhoneNumber, info.Privileges);
        mUsers.remove(email);
        mUsers.put(email, info2);
        return info2;
    }

    @Override
    public UserInfo createUser(UserInfo info) {
        if (mUsers.containsKey(info.EmailAddress)) {
            return null;
        }

        UserInfo info2 = new UserInfo(info.FirstName, info.LastName, info.EmailAddress,
                transform(info.AccessToken), info.PhoneNumber, info.Privileges);
        mUsers.put(info2.EmailAddress, info2);
        mLoggedIn.add(info2.EmailAddress);
        return info2;
    }

    @Override
    public Set<BillingInformation> getBillingInfo(UserInfo user) {
        if (!mBillingInfo.containsKey(user.EmailAddress)) {
            return null;
        }

        return mBillingInfo.get(user.EmailAddress);
    }

    @Override
    public void addBillingInfo(UserInfo owner, BillingInformation info) {
        HashSet<BillingInformation> billingInfos;
        if (!mBillingInfo.containsKey(owner.EmailAddress)) {
            billingInfos = new HashSet<>();
            mBillingInfo.put(owner.EmailAddress, billingInfos);
        } else {
            billingInfos = mBillingInfo.get(owner.EmailAddress);
        }

        billingInfos.add(info);
    }

    @Override
    public void removeBillingInfo(UserInfo owner, BillingInformation info) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void authorizeUser(UserInfo manager, UserInfo user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void initializeUsers() {
        UserInfo u = new UserInfo("Peter", "Thompson", "pthompson@fau.edu", "passkey1", "44100001", AccessPrivileges.Limited);
        mUsers.put(u.EmailAddress, u);

        u = new UserInfo("Mark", "Lu", "mlu@fau.edu", "passkey2", "44100002", AccessPrivileges.Limited);
        mUsers.put(u.EmailAddress, u);

        u = new UserInfo("Tom", "Chen", "tchen@fau.edu", "passkey3", "44100003", AccessPrivileges.Limited);
        mUsers.put(u.EmailAddress, u);

        u = new UserInfo("Stanley", "Frank", "sfrank@fau.edu", "passkey4", "44100004", AccessPrivileges.Full);
        mUsers.put(u.EmailAddress, u);
    }

    private String transform(String password) {
        //TODO: Transform password into an access token to protect its secrecy
        return password;
    }
}
