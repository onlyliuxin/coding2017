package liuxincourse;

import java.util.Arrays;


public class ArrayList implements List{
	
	private int size=0;
	
	private Object [] elementDataObjects = new Object[3];
	
	public void add (Object o){
		if (size>=elementDataObjects.length) {
			elementDataObjects=Arrays.copyOf(elementDataObjects, elementDataObjects.length+50);
		}
		elementDataObjects[size]=o;
		size++;
	}
	
	public void add (int index ,Object o){
		if (index>=size||index<0) {
			throw new IndexOutOfBoundsException();
		}
		if (size>=elementDataObjects.length) {
			elementDataObjects=Arrays.copyOf(elementDataObjects, elementDataObjects.length+50);
		}
		System.arraycopy(elementDataObjects, index, elementDataObjects, index+1, size-index);
		elementDataObjects[index]=o;
		size++;
	}

	public Object get (int index){
		if (index>=size||index<0) {
			throw new IndexOutOfBoundsException();
		}
		return elementDataObjects[index];
	}
	
	public Object remove(int index){
		if (index>=size||index<0) {
			throw new IndexOutOfBoundsException();
		}
		Object rem=elementDataObjects[index];
		System.arraycopy(elementDataObjects, index+1, elementDataObjects, index, size-index-1);
		size--;
		return rem;
	}

	public int size(){
		return size;
	}
	
//	public Iterator iterator(){
//		
//	}
}
