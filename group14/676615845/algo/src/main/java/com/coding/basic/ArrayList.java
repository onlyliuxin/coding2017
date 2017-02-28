package com.coding.basic;

public class ArrayList implements List {
	
	private int size;  // ArrayList 中的实际元素个数
	private Object[] elementData;

    public ArrayList() {
        size = 0;
        elementData = new Object[100];
    }

    public void add(Object o){
        if (size >= elementData.length) {
            elementData = Array.grow(elementData, 100);
        }
        elementData[size++] = o;
    }

	public void add(int index, Object o){
        if (size >= elementData.length) {
            elementData = Array.grow(elementData, 100);
        }

        if (index > size || index < 0) throw new ArrayIndexOutOfBoundsException();

        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }
	
	public Object get(int index){
        if (index > size) throw new ArrayIndexOutOfBoundsException();
        return elementData[index];
    }
	
	public Object remove(int index){

        if (index >= size || index < 0) throw new ArrayIndexOutOfBoundsException();

        Object result = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return result;
    }
	
	public int size() {
		return size;
	}
	
	public Iterator iterator(){

		return new Iterator() {

            private int next = 0; // 下一个返回元素所在的位置

            public boolean hasNext() {
                return next < size;
            }

            public Object next() {
                if (!hasNext()) throw new ArrayIndexOutOfBoundsException();
                return elementData[next++];
            }

            public Object remove() {
                if (next <= 0) throw new IllegalStateException();
                return ArrayList.this.remove(--next);
            }
        };
	}

}
