
package Repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Order interface class.
 * @author volen
 */
public interface IOrderRepository {

    /**
     * Method to set to list customer orders by email address.
     * @param emailAddress the parameter to get to retrieve order by customer.     
     */
    public Set<OrderSummary> getOrdersByCustomer(String emailAddress);
    
    /**
     * Method to retrieve order summary.
     * @param summary 
     */
    public void completeOrder(OrderSummary summary);
   
    /**
     * Method to retrieve the next order number.     
     */
    public String getNextOrderNumber();
    
    /**
     * Method to retrieve total revenue as BigDecimal object.
     * @param from The From date parameter criteria to use.
     * @param to The To date parameter criteria to use.
     */
    public BigDecimal getTotalRevenue(Date from, Date to);
    
    /**
     * Method to retrieve total expense as BigDecimal object.
     * @param from The From date parameter criteria to use.
     * @param to The TO date parameter criteria to use.
     */
    public BigDecimal getTotalExpense(Date from, Date to);
}