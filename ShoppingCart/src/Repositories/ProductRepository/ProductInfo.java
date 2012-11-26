/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.ProductRepository;

import Store.Product;
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
     * @return the mProduct
     */
    public Product getProduct() {
        return mProduct;
    }

    /**
     * @param mProduct the mProduct to set
     */
    public void setProduct(Product product) {
        this.mProduct = product;
    }

    /**
     * @return the mInStock
     */
    public int getInStock() {
        return mInStock;
    }

    /**
     * @param mInStock the mInStock to set
     */
    public void setInStock(int inStock) {
        this.mInStock = inStock;
    }

    /**
     * @return the mReserved
     */
    public int getReserved() {
        return mReserved;
    }

    /**
     * @param mReserved the mReserved to set
     */
    public void setReserved(int reserved) {
        this.mReserved = reserved;
    }

    /**
     * @return the mPurchasePrice
     */
    public BigDecimal getPurchasePrice() {
        return mPurchasePrice;
    }

    /**
     * @param mPurchasePrice the mPurchasePrice to set
     */
    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.mPurchasePrice = purchasePrice;
    }

    /**
     * @return the mPercentDiscount
     */
    public double getPercentDiscount() {
        return mPercentDiscount;
    }

    /**
     * @param mPercentDiscount the mPercentDiscount to set
     */
    public void setPercentDiscount(double percentDiscount) {
        this.mPercentDiscount = percentDiscount;
    }
}