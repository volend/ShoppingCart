
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import Repositories.OrderRepository.OrderDetails;
import Repositories.OrderRepository.OrderSummary;
import Repositories.UserRepository.*;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author volen
 */
public interface ICustomerStoreView extends IBaseStoreView {

    Set<OrderSummary> getOrdersByCustomerEmail(String emailAddress);

    OrderDetails startOrder(Customer buyer, BillingInformation payment);
    void completeOrder(OrderDetails order);
    void requestOrderCancelatlion(OrderDetails order);

    Inventory getInventory();
}
