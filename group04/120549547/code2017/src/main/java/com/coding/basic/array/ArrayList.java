package com.coding.basic.array;

import com.coding.basic.Iterator;
import com.coding.basic.List;

import java.util.Objects;

public class ArrayList<T> implements List<T> {

	private int size;
	private Object[] dataArray = new Object[0];

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public boolean contains(Object o) {
		for (Object obj : dataArray) {
			if (Objects.equals(obj, o)){
				return true;
			}
		}
		return false;
	}

	public Object[] toArray() {
		Object[] array = new Object[size];
		System.arraycopy(dataArray, 0, array, 0, size);
		return array;
	}

	public boolean add(T o) {
		ensureCapacity(size+1);
		dataArray[size] = o;
		size++;
		return true;
	}



	public boolean remove(T o) {
		int index = indexOf(o);
		if (index < 0){
			return false;
		}

		System.arraycopy(dataArray, index + 1, dataArray, index, size - 1 - index);
		dataArray[size - 1] = null;
		size--;
		return  true;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			dataArray[i] = null;
		}
		size = 0;
	}
    @SuppressWarnings("unchecked")
	public T get(int index) {
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}

		return (T) dataArray[index];
	}

	public T set(int index, T o) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        dataArray[index] = o;
        return  o;
	}

	public void add(int index, T o) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(size + 1);
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);

        dataArray[index] = o;

        size++;
	}

	public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T removeData = (T) dataArray[index];
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index -1 );
        dataArray[size - 1] = null;
        size--;
        return  removeData;
	}

	public int indexOf(T o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, dataArray[i])){
                return  i;
            }
        }
        return  -1;
    }

	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

	@Override
	public void printf() {
		for (int i = 0; i < size; i++) {
			if (i == size - 1){
				System.out.print(dataArray[i]);
			}else {
				System.out.print(dataArray[i] + "->");
			}
		}
	}

	private void ensureCapacity(int minCapacity) {
		if (minCapacity > dataArray.length){
			int newCapacity = Math.max(minCapacity, dataArray.length * 2);
			Object[] newDataArray = new Object[newCapacity];
			System.arraycopy(dataArray, 0, newDataArray, 0, dataArray.length);
			dataArray = newDataArray;
		}

	}


    private class ArrayListIterator implements Iterator<T> {

        private int pos;

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public T next() {
            if (hasNext()){
                return get(pos++);
            }
            return null;
        }
    }
}
