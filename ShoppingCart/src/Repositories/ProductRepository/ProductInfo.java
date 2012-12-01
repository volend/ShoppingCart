/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.ProductRepository;

import java.math.BigDecimal;

/**
 *
 * @author volen
 */
public class ProductInfo {

    private Product mProduct;
    private int mInStock;
    private int mReserved;
    private BigDecimal mPurchasePrice;
    private double mPercentDiscount;
    
    /**
     * Creates new ProductInfo object.
     */
    public ProductInfo()
    {
        mPurchasePrice = BigDecimal.ZERO;
    }

    /**
     * @return the mProduct The current product.
     */
    public Product getProduct() {
        return mProduct;
    }

    /**
     * @param mProduct The mProduct to set the current product.
     */
    public void setProduct(Product product) {
        this.mProduct = product;
    }

    /**
     * @return the mInStock The current product amount in stock.
     */
    public int getInStock() {
        return mInStock;
    }

    /**
     * @param mInStock the mInStock to set the current product amount in stock.
     */
    public void setInStock(int inStock) {
        this.mInStock = inStock;
    }

    /**
     * @return the mReserved the amount of reserved products.
     */
    public int getReserved() {
        return mReserved;
    }

    /**
     * @param mReserved the mReserved to set the amount of reserved products.
     */
    public void setReserved(int reserved) {
        assert (mInStock + mReserved > reserved);
        this.mInStock -= (mReserved - reserved);
        this.mReserved = reserved;
    }

    /**
     * @return the mPurchasePrice The current product price.
     */
    public BigDecimal getPurchasePrice() {
        return mPurchasePrice;
    }

    /**
     * @param mPurchasePrice the mPurchasePrice to set the current product price.
     */
    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.mPurchasePrice = purchasePrice;
    }

    /**
     * @return the mPercentDiscount The percent discount for the current product.
     */
    public double getPercentDiscount() {
        return mPercentDiscount;
    }

    /**
     * @param mPercentDiscount the mPercentDiscount to set to the current product discount.
     */
    public void setPercentDiscount(double percentDiscount) {
        this.mPercentDiscount = percentDiscount;
    }
}