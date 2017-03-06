/**
 * 
 */
package com.coding.basic.container;

/**
 * @author Administrator
 *
 */
public interface List {
    public boolean add(Object element);

    public void add(int index, Object element);

    public Object remove(int index);

    public boolean remove(Object element);

    public Object set(int index, Object element);

    public Object get(int index);

    public int size();

    public boolean isEmpty();

    public Iterator iterator();
}
