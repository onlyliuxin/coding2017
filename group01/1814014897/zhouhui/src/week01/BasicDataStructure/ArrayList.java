package week01.BasicDataStructure;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		ensureCapacity(size + 1);      //size increase,in order to have enough capacity.
		elementData[size++] = o;     //similar to: elementData[size]=o; size++;
	}
	
	private void ensureCapacity(int minCapacity){
		if(minCapacity >  elementData.length){
				grow(minCapacity);
		}
	}
	
	private void grow(int minCapacity){
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + ( oldCapacity >> 1 );
		if(newCapacity < minCapacity){
			newCapacity = minCapacity;
		}
			elementData = Arrays.copyOf(elementData, newCapacity);
			
	}
	
	public void add(int index, Object o){
		if(index < 0 || index > size) throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
		ensureCapacity(size+1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
		Object data_index = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		elementData[size - 1] = null;
		size--;
		return data_index;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	private  class ArrayListIterator implements Iterator{

		private int pos = 0;
		
		public boolean hasNext() {
			return pos < size;
		}

		public Object next() {
			return elementData[pos++];
		}	
	}
	
}
