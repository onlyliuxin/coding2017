package com.coding.basic;
import java.util.Arrays;
import java.lang.IndexOutOfBoundsException;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];

	public void ensure_length(int expect_length) {
		if (expect_length > elementData.length) {
            elementData = Arrays.copyOf(elementData,elementData.length * 2);
		}
	}

	public void add(Object o){
	    ensure_length(size + 1);
		elementData[size] = o;
		size += 1;
	}

	public void add(int index, Object o){
        ensure_length(size + 1);
        System.arraycopy(elementData,index,
                         elementData,index + 1,
                         size - index + 1);
        elementData[index] = o;
        size += 1;
	}

	public Object get(int index) {
	    if (index > size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        } else {
            return elementData[index];
        }
	}

	public Object remove(int index){
	    Object oldvalue = elementData[index];
        System.arraycopy(elementData,index + 1,elementData,index,size - index - 1);
        elementData[--size] = null;
		return oldvalue;
	}

	public int size(){
		return size;
	}

	public Iterator iterator(){
		return new Iterator_ip();
	}

	private class Iterator_ip implements Iterator {
	    private int index = 0;

        @Override
        public boolean hasNext() {
            return (index + 1) <= size;
        }

        @Override
        public Object next() {
            if (index > size) {
//                throw new IndexOutOfBoundsException("iterator next out of bounds");
                return null;
            }
            return elementData[index++];
        }
    }

}
