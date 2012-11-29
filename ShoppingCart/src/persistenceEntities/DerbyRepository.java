/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenceEntities;

import Repositories.OrderRepository.IOrderRepository;
import Repositories.OrderRepository.OrderSummary;
import Repositories.ProductRepository.IProductRepository;
import Repositories.ProductRepository.Product;
import Repositories.ProductRepository.ProductInfo;
import Repositories.UserRepository.BillingInformation;
import Repositories.UserRepository.IUserRepository;
import Repositories.UserRepository.UserInfo;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author volen
 */
public class DerbyRepository implements IOrderRepository, IUserRepository, IProductRepository {

    private final EntityManager mManager;

    public DerbyRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ShoppingCartPU");
        mManager = factory.createEntityManager();
    }

    @Override
    public Set<OrderSummary> getOrdersByCustomer(String emailAddress) {

        TypedQuery<Orders> query = mManager.createNamedQuery("Orders.findAll", Orders.class);
        List<Orders> results = query.getResultList();
        return null;
    }

    @Override
    public void completeOrder(OrderSummary summary) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getNextOrderNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BigDecimal getTotalRevenue(Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BigDecimal getTotalExpense(Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserInfo login(String emailAddress, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void logout(UserInfo user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserInfo changePassword(String email, String currentPassword, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserInfo createUser(UserInfo info) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<BillingInformation> getBillingInfo(UserInfo user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addBillingInfo(UserInfo owner, BillingInformation info) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeBillingInfo(UserInfo owner, BillingInformation info) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void authorizeUser(UserInfo manager, UserInfo user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Product> getProducts() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProductInfo getProductInfo(String sku) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean reserveProduct(String sku, int count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void releaseProduct(String sku, int count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateProductInfo(ProductInfo info) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addNewProduct(ProductInfo info) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeProduct(ProductInfo info) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

//    private static OrderSummary convert(Orders order) {
//        OrderSummary result = new OrderSummary(order.getOrderNumber(), order.getOrderDate(), order.getEmailAddress(), order.getPaymentMethodName()
//    
    
//}

//    private static BillingInformation convert(Payment payment) {
//        BillingInformation result = new BillingInformation();
//        result.setAccountHolder(payment.getOwner().getEmailAddress());
//        result.setAddressLine1(payment.getAddressLineOne());
//        result.setAddressLine2(payment.getAddressLineTwo());
//        result.setCardNumber(payment.getPaymentMethodName()); //NOTE: payment method name is actually the card number
//        result.setSecurityCode(payment.getSecurityCode());
//        result.setCity(payment.getCity());
//        result.setExpirationMonth(payment.getExpirationMonth());
//        result.setExpirationYear(payment.getExpirationYear());
//        result.setZipCode(payment.getZip());
//        return result;
//    }
//
//    private static Payment convert(BillingInformation payment) {
//        
//        Payment result = new Payment(payment.getCardNumber()); // NOTE: the card number is the payment method name
//        result.setAddressLineOne(payment.getAddressLine1());
//        result.setAddressLineTwo(payment.getAddressLine2());
//        result.setCity(payment.getCity());
//        result.setZip(payment.getZipCode());
//        result.setSecurityCode(payment.getSecurityCode());
//        result.set
//        
//        BillingInformation result = new BillingInformation();
//        result.setAccountHolder(payment.getOwner().getEmailAddress());
//        result.setAddressLine1(payment.getAddressLineOne());
//        result.setAddressLine2(payment.getAddressLineTwo());
//        result.setCardNumber(payment.getPaymentMethodName()); //NOTE: payment method name is actually the card number
//        result.setSecurityCode(payment.getSecurityCode());
//        result.setCity(payment.getCity());
//        result.setExpirationMonth(payment.getExpirationMonth());
//        result.setExpirationYear(payment.getExpirationYear());
//        result.setZipCode(payment.getZip());
//        return result;
//    }
}

