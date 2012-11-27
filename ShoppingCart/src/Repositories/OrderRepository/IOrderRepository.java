/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author volen
 */
public interface IOrderRepository {
    public Set<OrderSummary> getOrdersByCustomer(String emailAddress);
    public void completeOrder(OrderSummary summary);
    
    public BigDecimal getTotalRevenue(Date from, Date to);
    public BigDecimal getTotalExpense(Date from, Date to);
}
