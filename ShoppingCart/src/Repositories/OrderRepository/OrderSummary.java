
package Repositories.OrderRepository;

import Repositories.UserRepository.BillingInformation;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Class OrderSummary to deal with order summaries.
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

    /**
     * Creates new OrderSummary object.
     * @param number The given order number to construct the summary.
     * @param date The given date to construct the summary.
     * @param email The given email to construct the summary.
     * @param payment The given payment to construct the summary.
     * @param total The given total to construct the summary.
     * @param discount The given discount to construct the summary.
     * @param cost The given cost to construct the summary.
     */
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
