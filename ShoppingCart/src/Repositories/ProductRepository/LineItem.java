/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.ProductRepository;

import Repositories.ProductRepository.Product;

/**
 *
 * @author volen
 */
public class LineItem extends Product
{
    public final Product Product;
    public final Integer PercentDiscount;
    
    public LineItem(Product product, Integer percentDiscount)            
    {
        super(product.getSKU());
        Product = product;
        PercentDiscount = percentDiscount;
    }
}