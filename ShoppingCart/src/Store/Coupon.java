
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

    /**
     * Creates new Coupon object.
     * @param category
     * @param flatDiscount 
     */
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