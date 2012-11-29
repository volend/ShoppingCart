/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Store;

import Repositories.ProductRepository.Product;
import java.util.HashSet;

/**
 *
 * @author volen
 */
public class FilterCollection {
    private final HashSet<BaseFilter> mFilters;
    
    public FilterCollection()
    {
        mFilters = new HashSet<>();
    }
    
    public void addFilter(BaseFilter filter)
    {
        assert(filter != null);
        mFilters.add(filter);
    }
    
    public void removeFilter(BaseFilter filter)
    {
        assert(filter != null);
        mFilters.remove(filter);
    }
    
    public Boolean isMatch(Product product)
    {
        for (BaseFilter filter : mFilters)
        {
            if (!filter.isMatch(product.getColor(), product.getSize(), product.getDescription())) {
                return false;
            }
        }
        return true;
    }
    
    public BaseFilter[] getFilters()
    {
        return (BaseFilter[])mFilters.toArray();
    }
}