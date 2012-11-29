/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import Repositories.OrderRepository.OrderDetails;
import Repositories.ProductRepository.Product;
import Repositories.OrderRepository.IOrderRepository;
import Repositories.OrderRepository.OrderSummary;
import Repositories.ProductRepository.IProductRepository;
import Repositories.UserRepository.BillingInformation;
import Repositories.UserRepository.Customer;
import Repositories.UserRepository.IUserRepository;
import Repositories.UserRepository.Manager;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import sun.security.util.BigInt;

/**
 *
 * @author volen
 */
public class Store implements ICustomerStoreView, IManagerStoreView {

    private static final Store sSingleton;
    private IOrderRepository mOrderRepository;
    private IProductRepository mProductRepository;
    private IUserRepository mUserRepository;
    private Boolean mInitialized;

    static {
        sSingleton = new Store();
    }

    private Store() {
        mInitialized = false;
    }

    public static Store getInstance() throws IllegalStateException {
        if (!sSingleton.mInitialized) {
            throw new IllegalStateException("initializeStore(Inventory items) not invoked");
        }

        return sSingleton;
    }

    public static void initializeStore(IProductRepository productRepository, IUserRepository userRepository, IOrderRepository orderRepository) {
        sSingleton.mUserRepository = userRepository;
        sSingleton.mProductRepository = productRepository;
        sSingleton.mOrderRepository = orderRepository;
        sSingleton.mInitialized = true;
    }

    @Override
    public Set<OrderSummary> getOrdersByCustomerEmail(String emailAddress) {
        return mOrderRepository.getOrdersByCustomer(emailAddress);
    }

    @Override
    public Inventory getInventory() {
        return new Inventory(mProductRepository);
    }

    @Override
    public Customer customerLogin(String email, String password) {
        Customer customer = new Customer(this, mUserRepository);
        customer.login(email, password);
        return customer;
    }

    @Override
    public Manager managerLogin(String email, String password) {
        Manager manager = new Manager(this, mUserRepository);
        manager.login(email, password);
        return manager;
    }

    public void updateItem(String sku, Product product) {
          throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeItem(String sku) {
    }

    @Override
    public void addItem(Product product) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public OrderDetails startOrder(Customer buyer, BillingInformation payment) {
        
        OrderDetails o = new OrderDetails(new Date(System.currentTimeMillis()), payment, buyer.getEmailAddress(),mOrderRepository.getNextOrderNumber());
        return o;
    }

    @Override
    public void completeOrder(OrderDetails order) {
        OrderSummary summary = new OrderSummary(order.getOrderNumber(), order.getOrderDate(), order.getCustomerEmail(), order.getPaymentMethod(), 
               order.getOrderTotal(), order.getOrderDiscount(), getOrderCost(order));
        mOrderRepository.completeOrder(summary);
    }

    @Override
    public void requestOrderCancelatlion(OrderDetails order) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private BigDecimal getOrderCost(OrderDetails order) {
        //TODO: Compute total cost of order
        return BigDecimal.ZERO;
    }
}