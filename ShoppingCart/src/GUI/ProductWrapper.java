/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Repositories.ProductRepository.Product;
import Store.ProductSize;
import java.math.BigDecimal;

/**
 *
 * @author volen
 */
public class ProductWrapper {
    private final Product mWrappedProduct;
    private boolean mSelected;
    private int mQuantity;
    
    public ProductWrapper(Product product)
    {
        mWrappedProduct = product;
        mQuantity = 0;
        mSelected = false;
    }
    
    public String getColor()
    {
        return mWrappedProduct.getColor();
    }
    
    public String getCategory()
    {
        return mWrappedProduct.getCategory();
    }
    
    public void setSelected(boolean value)
    {
        mSelected = value;
    }
    
    public boolean isSelected()
    {
        return mSelected;
    }
    
    public String getDescription()
    {
        return mWrappedProduct.getDescription();
    }
    
    public ProductSize getSize()
    {
        return mWrappedProduct.getSize();
    }
    
    public int getQuantity()
    {
        return mQuantity;
    }
    
    public void setQUantity(int quantity)
    {
        mQuantity = quantity;
    }
    
    public BigDecimal getPrice()
    {
        return mWrappedProduct.getSalePrice();
    }
}