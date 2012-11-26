/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

/**
 *
 * @author volen
 */
public class LineItem extends Product
{
    final Product Product;
    final Integer PercentDiscount;
    
    public LineItem(Product product, Integer percentDiscount)            
    {
        super(product.getSKU());
        Product = product;
        PercentDiscount = percentDiscount;
    }
}