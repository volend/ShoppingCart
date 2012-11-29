/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenceEntities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author volen
 */
@Entity
@Table(name = "ORDERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOrderNumber", query = "SELECT o FROM Orders o WHERE o.orderNumber = :orderNumber"),
    @NamedQuery(name = "Orders.findByOrderDate", query = "SELECT o FROM Orders o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "Orders.findByOrderRange", query = "SELECT o FROM Orders o WHERE o.orderDate BETWEEN :from and :to")})
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ORDER_NUMBER")
    private String orderNumber;
    @Basic(optional = false)
    @Column(name = "ORDER_DATE")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @JoinColumn(name = "EMAIL_ADDRESS", referencedColumnName = "EMAIL_ADDRESS")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users emailAddress;
    @JoinColumn(name = "PAYMENT_METHOD_NAME", referencedColumnName = "PAYMENT_METHOD_NAME")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Payment paymentMethodName;

    public Orders() {
    }

    public Orders(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Orders(String orderNumber, Date orderDate) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Users getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(Users emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Payment getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(Payment paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderNumber != null ? orderNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderNumber == null && other.orderNumber != null) || (this.orderNumber != null && !this.orderNumber.equals(other.orderNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Orders[ orderNumber=" + orderNumber + " ]";
    }
    
}
