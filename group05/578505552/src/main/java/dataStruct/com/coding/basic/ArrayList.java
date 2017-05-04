package dataStruct.com.coding.basic;

import java.util.NoSuchElementException;

/**
 * Created by songbao.yang on 2017/2/21.
 *
 */
public class ArrayList<T> implements List<T> {
	
	private int size = 0;
	private Object[] elementData;
    private static final int MIN_CAPACITY = 10;

    public ArrayList(int size) {
        if (size < 0){
            throw new IllegalArgumentException("illega size: " + size);
        }
        this.elementData = new Object[size];
    }

    public ArrayList() {
        this.elementData = new Object[0];
    }

    public void add(T o){
        ensureCapacity(size + 1);
		elementData[size++] = o;
	}

	private void ensureCapacity(int minCapacity){
	    if (minCapacity < 0 ){
	        throw new OutOfMemoryError();
        }

	    int newCapcity = size;
	    if(minCapacity < MIN_CAPACITY){
	        newCapcity = MIN_CAPACITY;
        } else if(minCapacity > elementData.length){
            int tmp = elementData.length << 1;
            newCapcity = tmp > elementData.length ? tmp : Integer.MAX_VALUE;
        }

        newCapcity = minCapacity;
        Object[] newData = new Object[newCapcity];
        System.arraycopy(elementData, 0, newData, 0, size);
         elementData = newData;
    }

	public void add(int index, T o){
	    indexCheck(index);
        ensureCapacity(size+1);
        System.arraycopy(elementData, index, elementData, index+1, size-index);
        elementData[index] = o;
        size++;
	}
	
	public T get(int index){
		indexCheck(index);
		return (T) elementData[index];
	}

	private void indexCheck(int index){
		if(index < 0){
			throw new IllegalArgumentException("illegal index: " + index);
		}
		if(index >= size){
		    throw new IndexOutOfBoundsException();
        }
	}

	public T remove(int index){
        indexCheck(index);
        Object rm = elementData[index];
        System.arraycopy(elementData, index+1, elementData, index, size-index-1);
        size--;
		return (T) rm;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Itr();
	}

	//静态内部类的访问权限不同有何区别？？
	private class Itr implements Iterator {
        private int cursor = 0;

        public boolean hasNext() {
            return cursor != size;
        }

        public Object next() {
            if (hasNext()){
                return elementData[cursor++];
            }
            throw new NoSuchElementException();
        }
    }
}
