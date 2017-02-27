package org.korben.list;

/**
 * Created by Korben on 24/02/2017.
 */
public interface KIterator<T> {
    boolean hasNext();

    T next();
}
