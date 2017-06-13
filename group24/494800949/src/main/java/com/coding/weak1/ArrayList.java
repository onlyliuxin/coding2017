package com.coding.weak1;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		ensureCapcity(size + 1);
		elementData[size++] = o;
	}

	/**
	 * 当数组长度不够时进行扩容
	 */
	private void ensureCapcity(int requireSize){
		int oldCapacity = elementData.length;
		int newCapacity ;
		if(oldCapacity < requireSize){
			newCapacity = oldCapacity + (oldCapacity >> 1);

			if(newCapacity < 0){
				newCapacity = Integer.MAX_VALUE;
			}

            if(newCapacity > Integer.MAX_VALUE - 8){
				newCapacity = Integer.MAX_VALUE;
			}

			Object[] elementDataNew = new Object[newCapacity];
			System.arraycopy(elementData, 0, elementDataNew, 0, oldCapacity);
			elementData = elementDataNew;
		}
	}


	public void add(int index, Object o){
		indexCheckForAdd(index);
        ensureCapcity(index + 1);
        Object[] newElementData = new Object[elementData.length];
        if(index > 0)
            System.arraycopy(elementData, 0, newElementData, 0, index-1);
        System.arraycopy(elementData, index, newElementData, index+1, size-index);
        elementData = newElementData;
        elementData[index] = o;
        size++;
	}


    private void indexCheckForAdd(int index) {
        if(index < 0 || index >= size-1){
           throw new IndexOutOfBoundsException("max index is: "+(size-1));
        }
    }

    public Object get(int index){
        indexCheck(index);
		return elementData[index];
	}

    private void indexCheck(int index) {
        if(index < 0 || index > size-1){
            throw new IndexOutOfBoundsException("max index is: "+(size-1));
        }
    }

    public Object remove(int index){
        indexCheck(index);
        Object obj = elementData[index];
        System.arraycopy(elementData, index+1, elementData, index, size-index-1);
        elementData[size--] = null;
		return obj;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ListIter();
	}

    private class ListIter implements Iterator{

        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Object next() {
            int index = cursor;
            if(index >= size)
                throw new IndexOutOfBoundsException();
            cursor++;
            return elementData[index];
        }
    }
}
