/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.ProductRepository;

import Store.ProductSize;
import java.awt.Color;
import java.math.BigDecimal;

/**
 *
 * @author volen
 */
public class Product {

    private final String mSKU;
    private String mTitle;
    private String mDescription;
    private BigDecimal mSalePrice;
    private String mColor;
    private String mCategory;
    private ProductSize mSize;

    public Product(String sku) {
        mSKU = sku;
        mSalePrice = BigDecimal.ZERO;
    }

    /**
     * @return the mSKU
     */
    public String getSKU() {
        return mSKU;
    }

    /**
     * @return the mTitle
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * @param mTitle the mTitle to set
     */
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * @return the mDescription
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * @param mDescription the mDescription to set
     */
    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    /**
     * @return the mSalePrice
     */
    public BigDecimal getSalePrice() {
        return mSalePrice;
    }

    /**
     * @param mSalePrice the mSalePrice to set
     */
    public void setSalePrice(BigDecimal mSalePrice) {
        this.mSalePrice = mSalePrice;
    }

    /**
     * @return the mColor
     */
    public String getColor() {
        return mColor;
    }

    /**
     * @param mColor the mColor to set
     */
    public void setColor(String mColor) {
        this.mColor = mColor;
    }

    /**
     * @return the mCategory
     */
    public String getCategory() {
        assert (mCategory != null);
        return mCategory;
    }

    /**
     * @param mCategory the mCategory to set
     */
    public void setCategory(String category) {
        assert (category != null);
        if (category == null) {
            return;
        }
        mCategory = category;
    }
    
    public ProductSize getSize() {
        return mSize;
    }
    
    public void setSize(ProductSize size)
    {
        this.mSize = size;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        // object must be Test at this point
        Product second  = (Product) obj;
        return this.mSKU.equals(second.mSKU);
    }

    @Override
    public int hashCode() {
        return this.mSKU.hashCode();
    }
}