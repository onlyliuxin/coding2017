package com.bjsxd.test;

public class ArrayList implements List {
    private Object[] elementData = new Object[100];
    private int size = 0;
    Object[] temp = null;

    public void add(Object o) {
        if (size < elementData.length) {
            size++;
            Object[] target = new Object[elementData.length + size];
            System.arraycopy(elementData, 0, target, 0, elementData.length);
            elementData[size] = o;
        }
    }

    public void add(int index, Object o) {
        if (index < 0 || o == null) {
            throw new IllegalArgumentException("��Ӷ������");
        } else if (index <= elementData.length) {
            add(o);
        } else if (index > elementData.length) {
            throw new IllegalArgumentException("��Ӷ���Խ��");
        }

        if (size <= elementData.length) {
            this.size++;
        }
        Object[] target = new Object[this.size];
        System.arraycopy(elementData, 0, target, 0, index);
        target[index] = o;
        System.arraycopy(elementData, index, target, index + 1, elementData.length - index);
    }

    public Object get(int index) {
        if (index < 0 || index >= elementData.length) {
            return false;
        } else {
            return elementData[index];
        }
    }

    public Object remove(int index) {
        if (index < 0 || index >= elementData.length) {
            throw new IllegalArgumentException("ɾ�����󲻴���");
        } else {
            for (int i = index; i < elementData.length; i++) {
                elementData[i] = elementData[i + 1];
            }
            return elementData[index];
        }
    }

    public int size() {
        return this.size;
    }

}
