package org.xukai.common;



public class ArrayList implements List {
	
	private int size = 0;

	private static final int DEFAULT_CAPICITY = 10;

	public ArrayList() {
		elementData = new Object[DEFAULT_CAPICITY];
	}

	public ArrayList(int size) {
		elementData = new Object[size];
	}

	private Object[] elementData ;
	
	public void add(Object o){
		elementData[size] = o;
		size++;
	}
	public void add(int index, Object o){
		if (index < 0 || index > size ) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			if (isNeedResize(index)) {
				elementData = grown(elementData, Math.max(elementData.length << 1, index + 1));
			}
			if(index < size){
				System.arraycopy(elementData,index,elementData,index+1,size-index);
			}
			elementData[index] = o;
		}
		size++;
	}



	public Object get(int index){
		if (index > size-1 || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if (index > size-1 || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		Object result = elementData[index];
		size--;
		System.arraycopy(elementData,index+1,elementData,index,size-index);
		return result;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}

	class ArrayListIterator implements Iterator {

		private int pos = -1;

		@Override
		public boolean hasNext() {
			return pos < size - 1;
		}

		@Override
		public Object next() {
			pos++;
			return elementData[pos];
		}
	}

	public void display(){
		System.out.print("[");
		Iterator iterator = iterator();
		while (iterator.hasNext()){
			System.out.print(" " + iterator.next());
		}
		System.out.print(" ]");
	}

	private boolean isNeedResize(int index){
		return index > elementData.length-1 || size > elementData.length-1;
	}

	private Object[] grown(Object[] src,int capacity){
		Object[] des = new Object[capacity];
		System.arraycopy(elementData,0,des,0,elementData.length);
		return des;
	}

}
