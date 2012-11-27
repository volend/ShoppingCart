/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import Repositories.OrderRepository.IOrderRepository;
import Repositories.OrderRepository.OrderSummary;
import Repositories.ProductRepository.IProductRepository;
import Repositories.UserRepository.BillingInformation;
import Repositories.UserRepository.Customer;
import Repositories.UserRepository.IUserRepository;
import Repositories.UserRepository.Manager;
import java.util.Date;
import java.util.Set;

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
    public OrderDetails startOrder(Customer buyer, Date orderDate, BillingInformation payment, String customerEmail) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void completeOrder(OrderDetails order) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void requestOrderCancelatlion(OrderDetails order) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
//	public void initializeStore(Inventory items);	
//	void updateItem(String sku, ProductInfo product);
//	void removeItem(String sku); // Does not reflect total expenses
//	void addItem(ProductInfo product); // Reflects total expenses
//	
//	String/*order number*/ placeOrder(Customer buyer, OrderDetails order);
//	OrderDetails getOrder(String orderNumber);
//	boolean modifyOrder(OrderDetails order);
//	
//	Set<OrderDetails> getOrdersByState(OrderState state);
//	Set<OrderDetails> getOrdersByCustomerEmail(String emailAddress);
//	void UpdateOrderStatus(String orderNumber, OrderState newState);
//	Decimal getTotalRevenue(Date from, Date to); // Retrieved from all order sales
//	Decimal getTotalExpenses(Date from, Date to); // Retrieved from all manager purchases of items
//}
