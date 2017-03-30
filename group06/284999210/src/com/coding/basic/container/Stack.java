/**
 * 
 */
package com.coding.basic.container;

/**
 * @author Administrator
 *
 */
public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o) {
        elementData.add(o);
    }

    public Object pop() {
        final int size = elementData.size();
        if (0 == size)
            return null;
        return elementData.get(size);
    }

    public Object peek() {
        final int size = elementData.size();
        if (0 == size)
            return null;
        Object o = elementData.remove(size - 1);
        return o;
    }

    public boolean isEmpty() {
        return elementData.isEmpty();
    }

    public int size() {
        return elementData.size();
    }
}
