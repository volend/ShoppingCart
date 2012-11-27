/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import Repositories.ProductRepository.IProductRepository;
import Repositories.ProductRepository.ProductInfo;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author volen
 */
public class MainInstance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IProductRepository repository = new IProductRepository() {
            @Override
            public Set<Product> getProducts() {
                Set<Product> result = new HashSet<>();

                for (int i = 0; i < 10; i++) {
                    Product p = new Product(i + "");
                    p.setCategory("Category " + i % 3);
                    if (i % 2 == 0) {
                        p.setSize(ProductSize.Large);
                        p.setColor(Color.yellow);
                    } else if (i % 3 == 0) {
                        p.setSize(ProductSize.ExtraLarge);
                        p.setColor(Color.green);
                    } else {
                        p.setSize(ProductSize.Small);
                        p.setColor(Color.blue);
                    }

                    result.add(p);
                }

                return result;
            }

            @Override
            public ProductInfo getProductInfo(String sku) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Boolean reserveProduct(String sku, int count) {
                return true;
            }

            @Override
            public void releaseProduct(String sku, int count) {
               //
            }

            @Override
            public void updateProductInfo(ProductInfo info) {
               //
            }

            @Override
            public void addNewProduct(ProductInfo info) {
                //
            }

            @Override
            public void removeProduct(ProductInfo info) {
                //
            }
        };

        Inventory inventory = new Inventory(repository);

        //Change category and print
        System.out.println("\n\nSelected category 0");
        inventory.selectCategory("0");
        printItems(inventory);

        //Reset category and print
        System.out.println("\n\nReset category (select all categories)");
        inventory.selectCategory(null);
        printItems(inventory);

        System.out.println("\n\nSelect only yellow color products");
        BaseFilter filter = BaseFilter.createFilter(Color.yellow);
        inventory.addFilter(filter);
        printItems(inventory);

        inventory.removeFilter(filter);
        System.out.println("\n\nSelect only yellow or green color products");
        filter = filter.combineFilter(BaseFilter.createFilter(Color.green));
        inventory.addFilter(filter);
        printItems(inventory);
    }

    static void printItems(Inventory inventory) {
        if (inventory.count() == 0) {
            System.out.println("No items\n");
            return;
        }

        System.out.println("\n---------------------------------\n");
        for (Product p : inventory) {
            System.out.println(String.format("Product[sku=%s, category=%s, Color=%s, Size=%s]",
                    p.getSKU(), p.getCategory(), p.getColor(), p.getSize()));
        }
    }
}
