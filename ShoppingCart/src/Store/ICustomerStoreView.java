
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import Repositories.UserRepository.*;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author volen
 */
public interface ICustomerStoreView extends IBaseStoreView {

    Set<OrderDetails> getOrdersByCustomerEmail(String emailAddress);

    OrderDetails startOrder(Customer buyer, Date orderDate, BillingInformation payment, String customerEmail);
    void completeOrder(OrderDetails order);
    void requestOrderCancelatlion(OrderDetails order);

    Inventory getInventory();
}
