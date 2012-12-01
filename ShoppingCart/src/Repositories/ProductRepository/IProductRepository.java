
package Repositories.ProductRepository;

import java.util.Set;

/**
 * 
 * @author volen
 */
public interface IProductRepository {
    
    Set<Product> getProducts();
    ProductInfo getProductInfo(String sku);
    boolean reserveProduct(String sku, int count);
    
    void updateProductInfo(ProductInfo info);
    void addNewProduct(ProductInfo info);
    void removeProduct(ProductInfo info);
}
