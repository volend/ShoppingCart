/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenceEntities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author volen
 */
@Entity
@Table(name = "PAYMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
    @NamedQuery(name = "Payment.findByPaymentMethodName", query = "SELECT p FROM Payment p WHERE p.paymentMethodName = :paymentMethodName"),
    @NamedQuery(name = "Payment.findByAddressLineOne", query = "SELECT p FROM Payment p WHERE p.addressLineOne = :addressLineOne"),
    @NamedQuery(name = "Payment.findByAddressLineTwo", query = "SELECT p FROM Payment p WHERE p.addressLineTwo = :addressLineTwo"),
    @NamedQuery(name = "Payment.findByCity", query = "SELECT p FROM Payment p WHERE p.city = :city"),
    @NamedQuery(name = "Payment.findByState", query = "SELECT p FROM Payment p WHERE p.state = :state"),
    @NamedQuery(name = "Payment.findByZip", query = "SELECT p FROM Payment p WHERE p.zip = :zip"),
    @NamedQuery(name = "Payment.findByExpirationMonth", query = "SELECT p FROM Payment p WHERE p.expirationMonth = :expirationMonth"),
    @NamedQuery(name = "Payment.findByExpirationYear", query = "SELECT p FROM Payment p WHERE p.expirationYear = :expirationYear"),
    @NamedQuery(name = "Payment.findBySecurityCode", query = "SELECT p FROM Payment p WHERE p.securityCode = :securityCode")})
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PAYMENT_METHOD_NAME")
    private String paymentMethodName;
    @Basic(optional = false)
    @Column(name = "ADDRESS_LINE_ONE")
    private String addressLineOne;
    @Basic(optional = false)
    @Column(name = "ADDRESS_LINE_TWO")
    private String addressLineTwo;
    @Basic(optional = false)
    @Column(name = "CITY")
    private String city;
    @Basic(optional = false)
    @Column(name = "STATE_")
    private String state;
    @Basic(optional = false)
    @Column(name = "ZIP")
    private String zip;
    @Basic(optional = false)
    @Column(name = "EXPIRATION_MONTH")
    private int expirationMonth;
    @Basic(optional = false)
    @Column(name = "EXPIRATION_YEAR")
    private int expirationYear;
    @Basic(optional = false)
    @Column(name = "SECURITY_CODE")
    private int securityCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentMethodName", fetch = FetchType.EAGER)
    private Set<Orders> ordersSet;
    @JoinColumn(name = "EMAIL_ADDRESS", referencedColumnName = "EMAIL_ADDRESS")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users emailAddress;

    public Payment() {
    }

    public Payment(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public Payment(String paymentMethodName, String addressLineOne, String addressLineTwo, String city, String state, String zip, int expirationMonth, int expirationYear, int securityCode) {
        this.paymentMethodName = paymentMethodName;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.securityCode = securityCode;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    @XmlTransient
    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    public Users getOwner() {
        return emailAddress;
    }

    public void setEmailAddress(Users emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentMethodName != null ? paymentMethodName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.paymentMethodName == null && other.paymentMethodName != null) || (this.paymentMethodName != null && !this.paymentMethodName.equals(other.paymentMethodName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Payment[ paymentMethodName=" + paymentMethodName + " ]";
    }
    
}
