/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author volen
 */
// Implement the iterator pattern
public class ShoppingCart implements Iterable<Product> {

    private final HashMap<Product, Integer/*count*/> mOrderItems;

    public ShoppingCart() {
        mOrderItems = new HashMap<>();
    }

    public void addItem(Product item, int quantity) {
        assert (quantity > 0);
        mOrderItems.put(item, quantity);
    }

    public void removeItem(Product item) {
        mOrderItems.remove(item);
    }

    public void updateQuantity(Product item, int newQuantity) {
        assert (mOrderItems.containsKey(item));
        int quantity = mOrderItems.get(item);

        mOrderItems.remove(item);

        if (--quantity > 0) {
            mOrderItems.put(item, quantity);
        }
    }

    @Override
    public Iterator<Product> iterator() {
        return mOrderItems.keySet().iterator();
    }
}