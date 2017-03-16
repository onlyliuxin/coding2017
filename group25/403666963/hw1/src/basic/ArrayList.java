package basic;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){

		if (size+1 > elementData.length) {
			elementData = Arrays.copyOf(elementData,elementData.length * 2);
		}
		elementData[size++] = o;
	}

	public void add(int index, Object o){

		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index is incorrect");
		}

		if (size + 1 >elementData.length) {
			elementData = Arrays.copyOf(elementData,elementData.length * 2);
		}

		if (size == index) {
			add(o);
			return;
		}

		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;

	}
	
	public Object get(int index){

		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index is incorrect");
		}
		return elementData[index];
	}
	
	public Object remove(int index){

		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index is incorrect");
		}
		Object removed = elementData[index];
		System.arraycopy(elementData,index + 1,elementData,index ,size - index - 1);
		elementData[size - 1] = null;
		size--;
		return removed;
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
