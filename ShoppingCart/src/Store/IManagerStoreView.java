/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import Repositories.ProductRepository.Product;

/**
 *
 * @author volen
 */
public interface IManagerStoreView extends IBaseStoreView {

    void updateItem(Product product);

    void removeItem(String sku);

    void addItem(Product product);
}
