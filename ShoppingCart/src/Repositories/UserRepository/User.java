/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.UserRepository;

import java.util.Set;

/**
 *
 * @author volen
 */
public class User {

    private final String mFirstName;
    private final String mLastName;
    private final String mEmailAddress;
    private final String mPhoneNumber;
    private String mAccessToken;
    protected IUserRepository mRepository;

    public User(UserInfo info, IUserRepository repository) {
        mFirstName = info.FirstName;
        mLastName = info.LastName;
        mEmailAddress = info.EmailAddress;
        mPhoneNumber = info.PhoneNumber;
        mRepository = repository;
    }

    /**
     * @return the mFirstName
     */
    public String getFirstName() {
        return mFirstName;
    }

    /**
     * @return the mLastName
     */
    public String getLastName() {
        return mLastName;
    }

    /**
     * @return the mEmailAddress
     */
    public String getEmailAddress() {
        return mEmailAddress;
    }

    /**
     * @return the mAccessToken
     */
    public String getAccessToken() {
        return mAccessToken;
    }

    /**
     * @return the mPhoneNumber
     */
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void logout() {
        mRepository.logout(this);
    }

    public void changePassword(String newPassword) {
        mRepository.changePassword(newPassword);
    }
}
