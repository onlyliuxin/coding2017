package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		if( size <= elementData.length){
			elementData[size + 1] = o;
			size++;
		}else{
			elementData = grow(elementData, 1);
			elementData[size+1] = o;
			size++;
		}
	}
	public void add(int index, Object o){
		Object[] temp = new Object[elementData.length];
		for(int i = 0; i<elementData.length; i++){
			temp[i] = elementData[i];
		}
		if(index <= size){
			elementData = grow(elementData, 1);
			elementData[index] = o;
			System.arraycopy(temp, index, elementData, index+1, temp.length - index);
			size++;
		}
	}
	
	public Object get(int index){
		if(index < elementData.length){
			return elementData[index];
		}else{
			return null;
		}
	}
	
	public Object remove(int index){
		Object[] temp = new Object[elementData.length];
		for(int i = 0; i<elementData.length; i++){
			temp[i] = elementData[i];
		}
		if(index < elementData.length){
			System.arraycopy(temp, index+1, elementData, index, temp.length - index - 1);
			size--;
			return elementData;
		}else{
			return null;
		}
	}
	
	public int size(){
		for(int i = 0; i < elementData.length;i++){
			if(elementData[i] != null){
				size++;
			}
		}
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator(this);
	}
	
	private class ArrayListIterator implements Iterator {
		ArrayList arraylist = null;
		int pos = 0;
		
		private ArrayListIterator(ArrayList arraylist) {
			this.arraylist = arraylist;
		}
		@Override
		public boolean hasNext() {
			pos ++;
			if(pos >= size){
				return false;
			}else{
				return true;
			}
		}

		@Override
		public Object next() {
			return elementData[pos];
		}
		
	}
	public static Object[] grow(Object[]src, int size){
		Object[] target = new Object[src.length + size];
		System.arraycopy(src, 0, target, 0, src.length);
		return target;
	}
}
