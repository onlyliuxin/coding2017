/**
 * 
 */
package com.coding.basic.container;

/**
 * @author Administrator
 *
 */
public class Queue {

    private ArrayList list = new ArrayList();

    public void enQueue(Object o) {
        list.add(o);
    }

    public Object deQueue() {
        final int size = list.size();
        if (0 == size)
            return null;
        Object o = list.remove(size);
        return o;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

}
