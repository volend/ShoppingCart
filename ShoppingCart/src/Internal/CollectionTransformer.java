
package Internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author volen
 * Transforms collections to a new list with an added item.
 */
public abstract class CollectionTransformer<E, F> {

    public abstract F transform(E e);
    
    /**
     * A method to transform(add) input objects to a collection.
     * @param e the input object to add to the list.
     * @return new list with added item.
     */   
    public Set<F> transform(Collection<E> list) {
        Set<F> newList = new HashSet<>();
        
        for (E e : list) {
            newList.add(transform(e));
        }
        return newList;
    }
}