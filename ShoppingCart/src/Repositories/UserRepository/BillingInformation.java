
package Repositories.UserRepository;

/**
 * Class BillingInformation to deal with billing information details.
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
        private int mExpirationMonth;
        private int mExpirationYear;
        private int mSecurityCode;

    /**
     * @return the mAddressLine1 The value of the address line 1 of the current payment details.
     */
    public String getAddressLine1() {
        return mAddressLine1;
    }

    /**
     * @param mAddressLine1 the mAddressLine1 to set the address line 1 of the current payment details.
     */
    public void setAddressLine1(String addressLine1) {
        assert(addressLine1 != null);
        
        this.mAddressLine1 = addressLine1;
    }

    /**
     * @return the mAddressLine2 the The value of the address line 2 of the current payment details.
     */
    public String getAddressLine2() {
        return mAddressLine2;
    }

    /**
     * @param mAddressLine2 the mAddressLine2 to set the address line 2 of the current payment details.
     */
    public void setAddressLine2(String addressLine2) {
        assert(addressLine2 != null);
        
        this.mAddressLine2 = addressLine2;
    }

    /**
     * @return the mCity The value of the city of the current payment details.
     */
    public String getCity() {
        return mCity;
    }

    /**
     * @param mCity the mCity to set the city of the current payment details.
     */
    public void setCity(String city) {
        assert(city != null);
        
        this.mCity = city;
    }

    /**
     * @return the mState The value of the state of the current payment details.
     */
    public String getState() {
        return mState;
    }

    /**
     * @param mState the mState to set the state of the current payment details.
     */
    public void setState(String state) {
        assert(state != null);
        
        this.mState = state;
    }

    /**
     * @return the mZipCode The value of the zip code of the current payment details.
     */ 
    public String getZipCode() {
        return mZipCode;
    }

    /**
     * @param mZipCode the mZipCode to set the zip code of the current payment details.
     */
    public void setZipCode(String zipCode) {
        assert(zipCode != null);
        this.mZipCode = zipCode;
    }

    /**
     * @return the mAccountHolder The value of the account holder of the current payment details.
     */
    public String getAccountHolder() {
        return mAccountHolder;
    }

    /**
     * @param mAccountHolder the mAccountHolder to set the account holder of the current payment details.
     */
    public void setAccountHolder(String accountHolder) {
        assert(accountHolder != null);
        
        this.mAccountHolder = accountHolder;
    }

    /**
     * @return the mCardNumber The value of the card number of the current payment details.
     */
    public String getCardNumber() {
        return mCardNumber;
    }

    /**
     * @param mCardNumber the mCardNumber to set the card number of the current payment details.
     */
    public void setCardNumber(String cardNumber) {
        assert(cardNumber != null);
        
        this.mCardNumber = cardNumber;
    }

    /**
     * @return the mExpirationMonth The value of the expiration month of the current payment details.
     */
    public int getExpirationMonth() {
        return mExpirationMonth;
    }

    /**
     * @param mExpirationMonth the mExpirationMonth to set the expiration month of the current payment details.
     */
    public void setExpirationMonth(int expirationMonth) {
        assert(expirationMonth >= 1 && expirationMonth <= 12);
        
        this.mExpirationMonth = expirationMonth;
    }

    /**
     * @return the mExpirationYear The value of the expiration year of the current payment details.
     */
    public int getExpirationYear() {
        return mExpirationYear;
    }

    /**
     * @param mExpirationYear the mExpirationYear to set the expiration year of the current payment details
     */
    public void setExpirationYear(int expirationYear) {
        assert(expirationYear > 2012 && expirationYear <= 2020);
        
        this.mExpirationYear = expirationYear;
    }

    /**
     * @return the mSecurityCode The value of the security code of the current payment details.
     */
    public int getSecurityCode() {
        return mSecurityCode;
    }

    /**
     * @param mSecurityCode the mSecurityCode to set the security code of the current payment details.
     */
    public void setSecurityCode(int securityCode) {
        this.mSecurityCode = securityCode;
    }
}