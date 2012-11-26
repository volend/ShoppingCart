/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import Repositories.ProductRepository.IProductRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 *
 * @author volen
 */
public class Inventory implements Iterable<Product> {

    private static final String ALL_CATEGORIES = "All Categories";
    private final HashMap<String/*category*/, HashSet<Product>> mItems;
    private final FilterCollection mFilters;
    private String mSelectedCategory;
    private final ArrayList<Product> mTemporaryProducts;
    private final IProductRepository mRepository;

    public Inventory(IProductRepository productRepository) {
        mItems = new HashMap<>();
        mFilters = new FilterCollection();
        mTemporaryProducts = new ArrayList<>();
        mRepository = productRepository;

        readProducts();
    }

    // Strategy Pattern
    public void selectCategory(String category) {

        if (mSelectedCategory == null) {
            if (category == null) {
                return;
            }
        } else if (getCategory().equals(category)) {
            return;
        }

        mSelectedCategory = category;
        mTemporaryProducts.clear();

        updateItems();
    }

    public void clearCategory() {
        mSelectedCategory = null;
        updateItems();
    }

    public void addFilter(BaseFilter filter) {
        mFilters.addFilter(filter);
        updateItems();
    }

    public void removeFilter(BaseFilter filter) {
        mFilters.removeFilter(filter);
        updateItems();
    }

    public BaseFilter[] getFilters() {
        return mFilters.getFilters();
    }

    private String getCategory() {
        return mSelectedCategory != null ? mSelectedCategory : ALL_CATEGORIES;
    }

    private void filterMatchingProducts(Collection<Product> products) {
        for (Product product : products) {
            if (mFilters.isMatch(product)) {
                mTemporaryProducts.add(product);
            }
        }
    }

    private void updateItems() {
        mTemporaryProducts.clear();

        if (getCategory().equals(ALL_CATEGORIES)) {
            for (Entry<String, HashSet<Product>> entry : mItems.entrySet()) {
                filterMatchingProducts(entry.getValue());
            }
        } else {
            if (mItems.containsKey(getCategory())) {
                filterMatchingProducts(mItems.get(getCategory()));
            }
        }
        //TODO: Fire changed event
    }

    private void readProducts() {
        for (Product product : mRepository.getProducts()) {
            HashSet<Product> items;
            if (!mItems.containsKey(product.getCategory())) {
                mItems.put(product.getCategory(), items = new HashSet<>());
            } else {
                items = mItems.get(product.getCategory());
            }

            items.add(product);
        }

        updateItems();
    }

    @Override
    public Iterator<Product> iterator() {
        return mTemporaryProducts.iterator();
    }
    
    public int count()
    {
        return mTemporaryProducts.size();
    }
}