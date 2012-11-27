/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import Repositories.UserRepository.BillingInformation;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author volen
 */
public class OrderDetails
{
    private Coupon mDiscountCoupon;
    private String mOrderNumber;
    private Date mOrderDate;
    private String mCustomerEmail;
    private BillingInformation mPaymentMethod;
    private HashMap<Product /*Can be line item*/, Integer/*count*/> mOrderItems;
    private OrderState mState;

    public OrderDetails(Date orderDate, BillingInformation payment, String customerEmail, String orderNumber)
    {
        mState = OrderState.InProgress;
        mOrderDate = orderDate;
        mPaymentMethod = payment;
        mCustomerEmail = customerEmail;
        mOrderNumber = orderNumber;
        mOrderItems = new HashMap();
    }
    
    public OrderState getState()
    {
        return mState;
    }
    
    public void setState(OrderState state)
    {
        mState = state;
    }
    
    public void addProduct(Product product, int count)
    {
        if (mOrderItems.containsKey(product))
        {
            int currentCount  = (int) mOrderItems.get(product);
            mOrderItems.remove(product);
            count += currentCount;
        }
        
        mOrderItems.put(product, count);
    }
    
    public void updateQuantity(Product product, int quantity)
    {
        assert(mOrderItems.containsKey(product));
        
         int currentCount  = (int) mOrderItems.get(product);
         
         assert(currentCount >= quantity);
         mOrderItems.remove(product);
         
         currentCount -= quantity;
         if (currentCount > 0) {
            mOrderItems.put(product, currentCount);
        }
    }

    public Boolean applyDiscount(Coupon coupon)
    {
        mDiscountCoupon = coupon;
        return true;
    }

    public BigDecimal getOrderTotal()
    {
        BigDecimal currentTotal = new BigDecimal(0);
        for (Map.Entry<Product, Integer> pair : mOrderItems.entrySet())
        {
            BigDecimal price = pair.getKey().getSalePrice();                
            currentTotal = currentTotal.add(price.multiply(new BigDecimal(pair.getValue())));
        }

        return currentTotal;
    }

    public BigDecimal getOrderDiscount()
    {
        BigDecimal totalDiscount = new BigDecimal(0);
        for (Map.Entry<Product, Integer> pair : mOrderItems.entrySet())
        {
            if (pair.getKey() instanceof LineItem)
            {
                LineItem item = (LineItem)pair.getKey();
                BigDecimal discount = new BigDecimal(item.PercentDiscount / 100, MathContext.DECIMAL64);
                discount = discount.multiply(new BigDecimal(pair.getValue()));
                totalDiscount.add(pair.getKey().getSalePrice().multiply(discount));
            }
        }
        
        if (mDiscountCoupon != null)
        {
            BigDecimal additionalDiscount= new BigDecimal(0);

            for (Map.Entry<Product, Integer> pair : mOrderItems.entrySet())
            {
                if (pair.getKey().getCategory().equals(mDiscountCoupon.getDiscountCategory()))
                {
                    additionalDiscount.add(mDiscountCoupon.getFlatDiscount());
                }
            }
            
            totalDiscount = totalDiscount.add(additionalDiscount);
        }

        return totalDiscount;
    }
}