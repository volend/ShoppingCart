/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import java.math.BigDecimal;

/**
 *
 * @author volen
 */
public class Coupon
{
    private String mDiscountCategory;
    private BigDecimal mFlatDiscount;

    public Coupon(String category, BigDecimal flatDiscount )
    {
        assert(category != null);
        assert(mFlatDiscount.floatValue() > 0);
        mFlatDiscount = flatDiscount;
        mDiscountCategory = category;
    }

    /**
     * @return the mDiscountCategory
     */
    public String getDiscountCategory() {
        assert(mDiscountCategory != null);
        return mDiscountCategory;
    }

    /**
     * @return the mFlatDiscount
     */
    public BigDecimal getFlatDiscount() {
        return mFlatDiscount;
    }
}