package com.coding.refactor.List;

public class MyArrayList<T> implements MyList<T>{
	private int size = 0;
	private Object[] dataArray = new Object[0];
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void add(T o) {
		ensureCapacity(size + 1);
		dataArray[size++] = o;
	}

	@Override
	public void add(int index, T o) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException(OutOfBoundsMsg(index));
		}
		
		ensureCapacity(size + 1);
		System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
		dataArray[index] = o;
	}

	@Override
	public boolean contains(Object o) {
		for(Object ele : dataArray){
			if(o.equals(ele)){
				return true;
			}
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		Object[] newArray = new Object[size];
		System.arraycopy(dataArray, 0, newArray, 0, size);
		return newArray;
	}

	@Override
	public boolean remove(T o) {
		int index = indexOf(o);
		if(index < 0){
			return false;
		}
		System.arraycopy(dataArray, index + 1, dataArray, index, size - 1 - index);
		dataArray[--size] = null;
		return true;
	}

	@Override
	public T remove(int index) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException(OutOfBoundsMsg(index));
		}
		
		T ele = (T)dataArray[index];
		System.arraycopy(dataArray, index, dataArray, index+1, size-1-index);
		dataArray[--size] = null; 
		return ele;
	}

	@Override
	public T get(int index) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException(OutOfBoundsMsg(index));
		}
		T ele = (T)dataArray[index];
		return ele;
	}
	
	@Override
	public T set(int index, T element) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException(OutOfBoundsMsg(index));
		}
		dataArray[index] = element;
		return element;
	}

	@Override
	public void clear() {
		for(int i=0; i<size; i++){
			dataArray[i] = null;
		}
		size = 0;
		
	}

	@Override
	public int indexOf(T o) {
		for(int i=0; i<dataArray.length; i++){
			if(o.equals(dataArray[i])){
				return i;
			}
		}
		return -1;
	}

	@Override
	public MyIterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void ensureCapacity(int minCapacity) {
		if(minCapacity > dataArray.length){
			int newCapacity = Math.max(minCapacity, dataArray.length * 2);
			Object[] newDataArray = new Object[newCapacity];
			System.arraycopy(dataArray, 0, newDataArray, 0, dataArray.length);
			dataArray = newDataArray;
		}
		
	}
	
	private String OutOfBoundsMsg(int index) {
		return "Index: "+index+", Size: "+size;
	}
	
}
