package datastructure.nongeneric;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Haochen on 2017/2/20.
 * TODO:
 */
public abstract class MyBaseList<T> implements List<T> {

    protected final boolean checkGetIndex(int index) {
        return index >= 0 && index < size();
    }

    protected final void checkGetIndexAndThrow(int index) {
        if (!checkGetIndex(index)) {
            indexOuOfBound(index);
        }
    }

    protected final boolean checkAddIndex(int index) {
        return index >= 0 && index <= size();
    }

    protected final void checkAddIndexAndThrow(int index) {
        if (!checkAddIndex(index)) {
            indexOuOfBound(index);
        }
    }

    protected final void indexOuOfBound(int index) {
        throw new IndexOutOfBoundsException(indexOutOfBoundMessage(index));
    }

    @Override
    public T remove(int index) {
        checkGetIndexAndThrow(index);
        return removeNoCheck(index);
    }

    protected abstract T removeNoCheck(int index);

    protected final String indexOutOfBoundMessage(int index) {
        return "Index: " + index + ", size: " + size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override
    public boolean add(T t) {
        int before = size();
        add(size(), t);
        return size() != before;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(size(), c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int before = size();
        c.parallelStream().forEach(this::remove);
        return size() != before;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int before = size();
        parallelStream().forEach((o) -> {
            if (!c.contains(o)) {
                remove(o);
            }
        });
        return size() != before;
    }

    protected abstract T getNoCheck(int index);
    protected abstract T setNoCheck(int index, T element);
    protected abstract void addNoCheck(int index, T element);

    @Override
    public T get(int index) {
        checkGetIndexAndThrow(index);
        return getNoCheck(index);
    }

    @Override
    public T set(int index, T element) {
        checkGetIndexAndThrow(index);
        return setNoCheck(index, element);
    }

    @Override
    public void add(int index, T element) {
        checkAddIndexAndThrow(index);
        addNoCheck(index, element);
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    public void print() {
        for (T t : this) {
            System.out.print(t + " ");
        }
        System.out.println("\nsize: " + size());
    }
}
