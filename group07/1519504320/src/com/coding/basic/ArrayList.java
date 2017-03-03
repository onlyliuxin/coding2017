package com.coding.basic;

public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[100];

    public void add(Object o) {
        if (elementData.length == 101 && elementData[100] == null) {
            for (int i = 0; i < elementData.length; i++) {
                if (elementData[i] == null) {
                    elementData[i] = o;
                }
            }
        } else {
            Object[] elementData2 = new Object[elementData.length + 1];
            for (int i = 0; i < elementData.length; i++) {
                elementData2[i] = elementData[i];
            }
            elementData2[elementData2.length - 1] = o;
            elementData = elementData2;
        }

    }

    public void add(int index, Object o) {
        if (index < 0 || index > elementData.length - 1) {
            return;
        }
        if (index <= elementData.length - 1) {
            Object[] elementData2 = new Object[elementData.length + 1];
            elementData2[index] = o;
            for (int i = 0; i < elementData2.length; i++) {
                if (i < index) {
                    elementData2[i] = elementData[i];
                }
                if (i > index) {
                    elementData2[i + 1] = elementData[i];
                }
            }
            elementData = elementData2;
        }

    }

    public Object get(int index) {
        if (elementData.length - 1 < index || index < 0) {
            return null;
        } else {
            return elementData[index];
        }
    }

    public Object remove(int index) {
        if (index > elementData.length - 1 || index < 0) {
            return null;
        } else {
            Object result = elementData[index];
            for (int i = index; i < elementData.length - 1; i++) {
                elementData[index] = elementData[index + 1];
            }
            elementData[elementData.length - 1] = null;
            return result;
        }
    }

    public int size() {
        return elementData.length;
    }

    public Iterator iterator() {

        return new Iterator() {
            int cursor = -1;


            @Override
            public boolean hasNext() {
                if (cursor < elementData.length - 1) {
                    return true;
                }
                return false;
            }

            @Override
            public Object next() {

                cursor++;
                return elementData[cursor];
            }
        };
    }


}
