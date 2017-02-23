package com.coding.basic;

public class ArrayList implements List {

    private int size = 0;

    private final static Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 默认容量
     */
    private static int DEFAULT_CAPACITY = 10;

    private Object[] elementData;

    public ArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        System.out.println(list.elementData == list.EMPTY_ELEMENTDATA);
    }

    public void add(Object o) {
        if (elementData == EMPTY_ELEMENTDATA) {
            elementData = new Object[DEFAULT_CAPACITY];
        }else if(size < elementData.length){
            size++;
        }else{
            _grow();
        }
        elementData[size] = o;
    }

    private void _grow() {

    }

    public void add(int index, Object o) {

    }

    public Object get(int index) {
        return null;
    }

    public Object remove(int index) {
        return null;
    }

    public int size() {
        return -1;
    }

    public Iterator iterator() {
        return null;
    }

}
