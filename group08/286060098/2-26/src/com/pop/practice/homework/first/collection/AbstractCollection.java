package com.pop.practice.homework.first.collection;

/**
 * @author haipop Date: 17-2-19 Time: 下午3:40
 */
public abstract class AbstractCollection<T> implements Collection<T> {

    @Override
    @SuppressWarnings("unchecked")
    public void addAll(Collection<T> collection) throws IllegalAccessException {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            add((T) iterator.next());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeAll(Collection<T> collection) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            remove((T) iterator.next());
        }
    }
}