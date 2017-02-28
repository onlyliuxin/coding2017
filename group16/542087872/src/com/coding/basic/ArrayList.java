package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[10];

    // 每次乘2增长
    private void grow() {
        elementData = Arrays.copyOf(elementData, elementData.length * 2);
    }


	public void add(Object o){
        if (size >= elementData.length) {
            this.grow();
        }

        elementData[size++] = o;
	}
	public void add(int index, Object o){
        if (size >= elementData.length) {
            this.grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);

        elementData[index] = o;
        size++;
	}
	
	public Object get(int index){
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elementData[index];
	}
	
	public Object remove(int index){
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Object el = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);

        size--;
        return el;
	}
	
	public int size(){
		return size;
	}

    private class ArrIter implements Iterator {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Object next() {
            return elementData[cursor++];
        }
    }
	
	public Iterator iterator(){
        return new ArrIter();
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
