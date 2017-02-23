package datastructure.jdklist;

import datastructure.nongeneric.MyBaseList;

import java.util.*;

/**
 * Created by Haochen on 2017/2/15.
 * TODO:
 */
public class MyArrayList<T> extends MyBaseList<T> {

    private Object[] array;
    private int size;
    private int initSize;

    public MyArrayList() {
        initSize = 10;
        this.array = new Object[initSize];
    }

    public MyArrayList(int initSize) {
        this.initSize = initSize;
        this.array = new Object[initSize];
    }

    protected int nextSize(int size) {
        return size * 2;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = -1;
            @Override
            public boolean hasNext() {
                return index < size() - 1;
            }

            @Override
            public T next() {
                index++;
                return (T) array[index];
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(index);
                index--;
            }
        };
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size()) {
            return (T1[]) Arrays.copyOf(array, size(), a.getClass());
        } else {
            System.arraycopy(array, 0, a, 0, size());
            return a;
        }
    }

    private boolean isFull() {
        return size() >= array.length;
    }

    private void expand(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size());
        this.array = newArray;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0) {
            remove(index);
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        int addCount = c.size();
        int capacityAfterAdd = array.length;
        int countAfterAdd = size + addCount;
        while (capacityAfterAdd < countAfterAdd) {
            capacityAfterAdd = nextSize(capacityAfterAdd);
        }
        if (capacityAfterAdd > array.length) {
            expand(capacityAfterAdd);
        }
        System.arraycopy(array, index, array, index + addCount, size - index);
        for (Object o : c) {
            array[size] = o;
            size++;
        }
        return true;
    }

    @Override
    protected T getNoCheck(int index) {
        return (T) array[index];
    }

    @Override
    protected T setNoCheck(int index, T element) {
        T t = (T) array[index];
        array[index] = element;
        return t;
    }

    @Override
    protected void addNoCheck(int index, T element) {
        if (isFull()) {
            expand(nextSize(array.length));
        }
        System.arraycopy(array, index, array, index + 1, size() - index);
        array[index] = element;
        size++;
    }

    @Override
    public void clear() {
        array = new Object[10];
        size = 0;
    }

    @Override
    protected T removeNoCheck(int index) {
        T t = (T) array[index];
        if (index < size() - 1) {
            System.arraycopy(array, index + 1, array, index, size() - index);
        }
        size--;
        return t;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); ++i) {
            if (array[i] == null ? o == null : array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (array[i] == null ? o == null : array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        final int beginIndex = index;
        return new ListIterator<T>() {
            private int point = beginIndex - 1;
            @Override
            public boolean hasNext() {
                return point < size - 1;
            }

            @Override
            public T next() {
                point++;
                return (T) array[point];
            }

            @Override
            public boolean hasPrevious() {
                return point > beginIndex;
            }

            @Override
            public T previous() {
                point--;
                return (T) array[point];
            }

            @Override
            public int nextIndex() {
                return point + 1;
            }

            @Override
            public int previousIndex() {
                return point - 1;
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(point);
                point--;
            }

            @Override
            public void set(T t) {
                MyArrayList.this.set(point, t);
            }

            @Override
            public void add(T t) {
                MyArrayList.this.add(point + 1, t);
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        //这是错误实现
        MyArrayList<T> result = new MyArrayList<>(toIndex - fromIndex + 1);
        for (int i = fromIndex; i <= toIndex; ++i) {
            result.array[result.size()] = array[i];
            result.size++;
        }
        return result;
    }

    public static void main(String[] args) {
        MyBaseList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; ++i) {
            list.add(i);
            list.add(10 - i);
        }
        System.out.println("------------------size");
        System.out.println("size: " + list.size());

        System.out.println("------------------for(int i)");
        for (int i = 0; i < list.size(); ++i) {
            System.out.print(list.get(i) + " ");
        }

        System.out.println("\n-----------------foreach");
        for (int i : list) {
            System.out.print(i + " ");
        }

        System.out.println("\n-----------------iterator");
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println("\n-----------------listIterator");
        iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println("\n-----------------indexOf 0~10");
        for (int i = 0; i <= 10; ++i) {
            System.out.print("[" + i + "]" + list.indexOf(i) + " ");
        }

        System.out.println("\n-----------------lastIndexOf 0~10");
        for (int i = 0; i <= 10; ++i) {
            System.out.print("[" + i + "]" + list.lastIndexOf(i) + " ");
        }

        System.out.println("\n-----------------add at index 0    100~104");
        for (int i = 100; i < 105; ++i) {
            list.add(0, i);
        }
        list.print();
        System.out.println("-----------------add at last    200~204");
        for (int i = 200; i < 205; ++i) {
            list.add(list.size(), i);
        }
        list.print();

        System.out.println("-----------------removeFirst x4");
        for (int i = 0; i < 4; ++i) {
            list.remove(0);
        }
        list.print();

        System.out.println("\n-----------------removeLast x4");
        for (int i = 0; i < 4; ++i) {
            list.remove(list.size() - 1);
        }
        list.print();

        System.out.println("-----------------iterator remove");
        iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "[" + list.size() + "] ");
            iterator.remove();
        }
        System.out.println();
        list.print();
    }
}
