
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import Repositories.UserRepository.Customer;
import Repositories.UserRepository.User;
import java.util.Set;

/**
 *
 * @author volen
 */
public interface ICustomerStoreView {

    Set<OrderDetails> getOrdersByCustomerEmail(String emailAddress);

    Boolean applyCoupon(Coupon coupon);

    String/*order number*/ placeOrder(Customer buyer, OrderDetails order);

    OrderDetails getOrder(String orderNumber);

    void requestOrderCancelatlion(String orderNumber);

    void addToCart(ShoppingCart userCart, Product product, int quantity);

    void removeFromCart(ShoppingCart userCart, Product product);

    Inventory getInventory();

    User login(String email, String password);
}
