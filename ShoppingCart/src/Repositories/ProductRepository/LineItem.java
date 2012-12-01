
package Repositories.ProductRepository;

import Repositories.ProductRepository.Product;

/**
 * Class LineItem inheriting from class Product.
 * @author volen
 */
public class LineItem extends Product
{
    public final Product Product;
    public final Integer PercentDiscount;
    
    /**
     * Creates new LineItem object.
     * @param product The given product to construct the line item.
     * @param percentDiscount The given percentDiscount to construct the line item.
     */
    public LineItem(Product product, Integer percentDiscount)            
    {
        super(product.getSKU());
        Product = product;
        PercentDiscount = percentDiscount;
    }
}