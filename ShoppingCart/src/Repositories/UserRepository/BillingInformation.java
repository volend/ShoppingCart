/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.UserRepository;

/**
 *
 * @author volen
 */
public class BillingInformation
{
	private String mAddressLine1;
        private String mAddressLine2;        
	private String mCity;
        private String mState;        
	private String mZipCode;        
        private String mAccountHolder;
        private String mCardNumber;
        private Integer mExpirationMonth;
        private Integer mExpirationYear;
        private Integer mSecurityCode;

    /**
     * @return the mAddressLine1
     */
    public String getAddressLine1() {
        return mAddressLine1;
    }

    /**
     * @param mAddressLine1 the mAddressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        assert(addressLine1 != null);
        
        this.mAddressLine1 = addressLine1;
    }

    /**
     * @return the mAddressLine2
     */
    public String getAddressLine2() {
        return mAddressLine2;
    }

    /**
     * @param mAddressLine2 the mAddressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        assert(addressLine2 != null);
        
        this.mAddressLine2 = addressLine2;
    }

    /**
     * @return the mCity
     */
    public String getCity() {
        return mCity;
    }

    /**
     * @param mCity the mCity to set
     */
    public void setCity(String city) {
        assert(city != null);
        
        this.mCity = city;
    }

    /**
     * @return the mState
     */
    public String getState() {
        return mState;
    }

    /**
     * @param mState the mState to set
     */
    public void setState(String state) {
        assert(state != null);
        
        this.mState = state;
    }

    /**
     * @return the mZipCode
     */
    public String getZipCode() {
        return mZipCode;
    }

    /**
     * @param mZipCode the mZipCode to set
     */
    public void setZipCode(String zipCode) {
        assert(zipCode != null);
        this.mZipCode = zipCode;
    }

    /**
     * @return the mAccountHolder
     */
    public String getAccountHolder() {
        return mAccountHolder;
    }

    /**
     * @param mAccountHolder the mAccountHolder to set
     */
    public void setAccountHolder(String accountHolder) {
        assert(accountHolder != null);
        
        this.mAccountHolder = accountHolder;
    }

    /**
     * @return the mCardNumber
     */
    public String getCardNumber() {
        return mCardNumber;
    }

    /**
     * @param mCardNumber the mCardNumber to set
     */
    public void setCardNumber(String cardNumber) {
        assert(cardNumber != null);
        
        this.mCardNumber = cardNumber;
    }

    /**
     * @return the mExpirationMonth
     */
    public Integer getExpirationMonth() {
        return mExpirationMonth;
    }

    /**
     * @param mExpirationMonth the mExpirationMonth to set
     */
    public void setExpirationMonth(Integer expirationMonth) {
        assert(expirationMonth >= 1 && expirationMonth <= 12);
        
        this.mExpirationMonth = expirationMonth;
    }

    /**
     * @return the mExpirationYear
     */
    public Integer getExpirationYear() {
        return mExpirationYear;
    }

    /**
     * @param mExpirationYear the mExpirationYear to set
     */
    public void setExpirationYear(Integer expirationYear) {
        assert(expirationYear > 2012 && expirationYear <= 2020);
        
        this.mExpirationYear = expirationYear;
    }

    /**
     * @return the mSecurityCode
     */
    public Integer getSecurityCode() {
        return mSecurityCode;
    }

    /**
     * @param mSecurityCode the mSecurityCode to set
     */
    public void setSecurityCode(Integer securityCode) {
        this.mSecurityCode = securityCode;
    }
}