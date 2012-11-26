/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Internal;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author volen
 */
public abstract class CollectionTransformer<E, F> {

    public abstract F transform(E e);

    public Set<F> transform(Set<E> list) {
        Set<F> newList = new HashSet<>();
        
        for (E e : list) {
            newList.add(transform(e));
        }
        return newList;
    }
}