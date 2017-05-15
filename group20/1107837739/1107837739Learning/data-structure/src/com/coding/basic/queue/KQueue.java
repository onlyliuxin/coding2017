package com.coding.basic.queue;

/**
 * Korben's Queue Interface
 *
 * Created by Korben on 18/02/2017.
 */
public interface KQueue<T> {
    boolean add(T t);

    boolean offer(T t);

    T remove();

    T poll();

    T element();

    T peek();
}
