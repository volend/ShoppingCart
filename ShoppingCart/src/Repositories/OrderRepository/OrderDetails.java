package Repositories.OrderRepository;

import Repositories.ProductRepository.LineItem;
import Repositories.ProductRepository.Product;
import Repositories.UserRepository.BillingInformation;
import Store.Coupon;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class OrderDetails to deal with details on orders.
 * @author volen
 */
public class OrderDetails {
    
    private Coupon mDiscountCoupon;
    private String mOrderNumber;
    private Date mOrderDate;
    private String mCustomerEmail;
    private BillingInformation mPaymentMethod;
    private HashMap<Product /*Can be line item*/, Integer/*count*/> mOrderItems;
    private OrderState mState;

   /**
    * Creates new OrderDetails object.     
    * @param orderDate The date parameter of a new order.
    * @param payment The payment parameter of a new order.
    * @param customerEmail the customer email parameter of a new order.
    * @param orderNumber the order number parameter of a new order.
    */
    public OrderDetails(Date orderDate, BillingInformation payment, String customerEmail, String orderNumber) {
        mState = OrderState.InProgress;
        mOrderDate = orderDate;
        mPaymentMethod = payment;
        mCustomerEmail = customerEmail;
        mOrderNumber = orderNumber;
        mOrderItems = new HashMap();
    }

    /**
     * Returns the state of an order.
     * @return order state.
     */
    public OrderState getState() {
        return mState;
    }

    /**
     * Sets the state to an order.
     * @param state the parameter carrying the state value.
     */
    public void setState(OrderState state) {
        mState = state;
    }

    
    /**
     * Adds the product count.
     * @param product The product parameter to count.
     * @param count The counter containing the total count value.
     */
    public void addProduct(Product product, int count) {
        if (mOrderItems.containsKey(product)) {
            int currentCount = (int) mOrderItems.get(product);
            mOrderItems.remove(product);
            count += currentCount;
        }

        mOrderItems.put(product, count);
    }

    /**
     * Updates product quantity.
     * @param product The product parameter to update.
     * @param quantity The quantity amount. 
     */
    public void updateQuantity(Product product, int quantity) {
        assert (mOrderItems.containsKey(product));

        int currentCount = (int) mOrderItems.get(product);

        assert (currentCount >= quantity);
        mOrderItems.remove(product);

        currentCount -= quantity;
        if (currentCount > 0) {
            mOrderItems.put(product, currentCount);
        }
    }
    
    /**
     * Returns boolean value when coupons apply.
     * @param coupon the coupon parameter containing the value.
     * @return true value.
     */
    public Boolean applyDiscount(Coupon coupon) {
        mDiscountCoupon = coupon;
        return true;
    }

    /**
     * Returns an order total value.
     * @return the current order total.
     */
    public BigDecimal getOrderTotal() {
        BigDecimal currentTotal = new BigDecimal(0);
        for (Map.Entry<Product, Integer> pair : mOrderItems.entrySet()) {
            BigDecimal price = pair.getKey().getSalePrice();
            currentTotal = currentTotal.add(price.multiply(new BigDecimal(pair.getValue())));
        }

        return currentTotal;
    }
    
    /**
     * Returns total discount applied to an order.
     * @return the current order total discount.
     */
    public BigDecimal getOrderDiscount() {
        BigDecimal totalDiscount = new BigDecimal(0);
        for (Map.Entry<Product, Integer> pair : mOrderItems.entrySet()) {
            if (pair.getKey() instanceof LineItem) {
                LineItem item = (LineItem) pair.getKey();
                BigDecimal discount = new BigDecimal(item.PercentDiscount / 100, MathContext.DECIMAL64);
                discount = discount.multiply(new BigDecimal(pair.getValue()));
                totalDiscount.add(pair.getKey().getSalePrice().multiply(discount));
            }
        }

        if (mDiscountCoupon != null) {
            BigDecimal additionalDiscount = new BigDecimal(0);

            for (Map.Entry<Product, Integer> pair : mOrderItems.entrySet()) {
                if (pair.getKey().getCategory().equals(mDiscountCoupon.getDiscountCategory())) {
                    additionalDiscount.add(mDiscountCoupon.getFlatDiscount());
                }
            }

            totalDiscount = totalDiscount.add(additionalDiscount);
        }

        return totalDiscount;
    }
    
    /**
     * Returns the order number of the current order.
     * @return the mOrderNumber allocated to the current order.
     */
    public String getOrderNumber()
    {
        return this.mOrderNumber;
    }

    /**
     * Returns the order date of the current order.
     * @return the mOrderDate the date stamp of the current order.
     */
    public Date getOrderDate() {
        return mOrderDate;
    }

    /**
     * Returns the customer email of the current order.
     * @return the mCustomerEmail set to the current order.
     */
    public String getCustomerEmail() {
        return mCustomerEmail;
    }

    /**
     * Returns the payment method of the current order.
     * @return the mPaymentMethod set to the current order.
     */
    public BillingInformation getPaymentMethod() {
        return mPaymentMethod;
    }
}