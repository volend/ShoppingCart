/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author volen
 */
public class OrderRepository implements IOrderRepository {

    private final HashMap<String, HashSet<OrderSummary>> mOrders;
    private long mOrderCount;

    public OrderRepository() {
        mOrders = new HashMap<>();
        initializeOrders();
    }

    @Override
    public Set<OrderSummary> getOrdersByCustomer(String emailAddress) {
        if (!mOrders.containsKey(emailAddress)) {
            return null;
        }
        return mOrders.get(emailAddress);
    }

    @Override
    public void completeOrder(OrderSummary summary) {
        HashSet<OrderSummary> orders;

        if (!mOrders.containsKey(summary.CustomerEmail)) {
            mOrders.put(summary.CustomerEmail, orders = new HashSet<>());
        } else {
            orders = mOrders.get(summary.CustomerEmail);
        }

        orders.add(summary);
        mOrderCount++;
    }

    @Override
    public BigDecimal getTotalRevenue(Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BigDecimal getTotalExpense(Date from, Date to) {
        BigDecimal totalExpense = BigDecimal.ZERO;
        for (Set<OrderSummary> orders : mOrders.values()) {
            for (OrderSummary order : orders) {
                if (order.OrderDate.after(to) && order.OrderDate.before(from)) {
                    totalExpense.add(order.CostTotal);
                }
            }
        }

        return totalExpense;
    }

    private void initializeOrders() {
    }
    
    @Override 
    public String getNextOrderNumber()
    {
        return mOrderCount + "";
    }
}
