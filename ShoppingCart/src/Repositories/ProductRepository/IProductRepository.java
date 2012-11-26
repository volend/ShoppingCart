/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.ProductRepository;

import Store.Product;
import java.util.Set;

/**
 *
 * @author volen
 */
public interface IProductRepository {
    Set<Product> getProducts();
    ProductInfo getProductInfo(String sku);
    Boolean reserveProduct(String sku, int count);
    void releaseProduct(String sku, int count);
}
