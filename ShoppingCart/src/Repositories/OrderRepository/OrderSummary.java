/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.OrderRepository;

import Repositories.UserRepository.BillingInformation;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author volen
 */
public class OrderSummary {

    public final String OrderNumber;
    public final Date OrderDate;
    public final String CustomerEmail;
    public final BillingInformation PaymentMethod;
    public final BigDecimal RevenueTotal;
    public final BigDecimal DiscountTotal;
    public final BigDecimal CostTotal;

    public OrderSummary(String number, Date date, String email, BillingInformation payment, BigDecimal total, BigDecimal discount, BigDecimal cost) {
        OrderNumber = number;
        OrderDate = date;
        CustomerEmail = email;
        PaymentMethod = payment;
        RevenueTotal = total;
        DiscountTotal = discount;
        CostTotal = cost;
    }
}
