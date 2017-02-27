package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[8];

	public int test = 10;
	
	public void add(Object o){
		if(size + 1 >elementData.length){
			expand();
		}
		elementData[size] = o;
		size++;
	}
	/**
	 *  Parameters:
	 *	index index at which the specified element is to be inserted
	 *	element element to be inserted
	 *	Throws:
	 *	IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
	 */
	
	public void add(int index, Object o){
		if(index <0 || index > size()) throw new IndexOutOfBoundsException(index + ": Invalid Index");
		if(size()+1>elementData.length){
			expand();
		}
		if(index < size())
			System.arraycopy(elementData, index, elementData, index+1, size() - index);
		elementData[index] = o;
		size ++;
	}
	
	public Object get(int index){
		if(index <0 || index >= size()) throw new ArrayIndexOutOfBoundsException(index + ": Invalid Index.");
		return elementData[index];
	}
	
	public Object remove(int index) {
		if(index <0 || index >= size()) throw new ArrayIndexOutOfBoundsException(index + ": Invalid Index.");
		Object item = elementData[index];
		if(size() == 1){
			size--;
		}else{
			if(index<size()){
				
				System.arraycopy(elementData, index+1, elementData, index, size-index);
				size--;
			}
		}
		return item;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterImpl(this);
	}
	
	private void expand(){
		Object[] newArray = new Object[elementData.length * 2];
		System.arraycopy(elementData, 0, newArray, 0, size);
		elementData = newArray;
	}
	
	public void dump(){
		for(int i = 0; i< size(); i++){
			System.out.print(elementData[i] + " ");
		}
		System.out.println();
	}

	private class ArrayListIterImpl implements Iterator{

		Object[] oArray = null;
		int cursor = 0;
		public ArrayListIterImpl(ArrayList al){
			if(al == null) throw new NullPointerException("Linkedlist Object is NULL");
			oArray = new Object[al.size()];
			for(int i = 0; i< al.size(); i++){
				oArray[i] = al.get(i);
			}
		}
		
		@Override
		public boolean hasNext() {
			if(cursor < oArray.length){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			Object o = oArray[cursor];
			cursor ++;
			return o;
		}
		
	}
}
