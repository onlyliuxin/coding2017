package com.coding.basic;

public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[10];

    public void add(Object o) {
        ensureCapacity(size + 1);
        elementData[size++] = o;
    }

    public void add(int index, Object o) {
        checkForLength(index);

        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }

    public Object get(int index) {
        checkForLength(index);
        return elementData[index];
    }

    public Object remove(int index) {
        checkForLength(index);
        Object oldValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return oldValue;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return null;
    }

    private void checkForLength(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("out of memory");
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);//增大容量
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        elementData = copyOf(elementData, newCapacity);
    }

    private Object[] copyOf(Object[] src, int newCapacity) {
        Object[] target = new Object[newCapacity];
        System.arraycopy(src, 0, target, 0, src.length);
        return target;
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        String num = "num";
        for (int i = 0; i < 100; i++) {
            list.add(num + String.valueOf(i));
            System.out.println(String.valueOf(i) + ":size:" + list.size());
            System.out.println(String.valueOf(i) + ":length:" + list.elementData.length);
        }
        System.out.println(list.size());

        for (int i = 0; i < 100; i++) {
            list.add(i, num + String.valueOf(i));
            System.out.println(String.valueOf(i) + ":size:" + list.size());
            System.out.println(String.valueOf(i) + ":length:" + list.elementData.length);
        }
        System.out.println(list.size());

        for (int i = 0; i < 200; i++) {
            System.out.println(list.remove(0));
        }

        System.out.println(list.size());
    }
}
