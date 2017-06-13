package week01.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		add(size , o);
	}
	public void add(int index, Object o){
		if(index<0||index > size){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		size++;
		if(size>=elementData.length){
			expand();
		}
		for(int i = size -1 ;i> index; i--){
			elementData[i] = elementData[i-1];
		}
		elementData[index] = o;
	}
	
	public Object get(int index){
		if(index<0||index>=size){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index<0||index>=size){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		Object o = elementData[index];
		for(int i = index ;i< size - 1; i++){
			elementData[i] = elementData[i+1];
		}
		elementData[size - 1] = null;
		size--;
		return o;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	class ArrayListIterator implements Iterator{
        
		int count = 0;
		@Override
		public boolean hasNext() {
			count++;
			if(size<= count){
				return false; 
			}
			return true;
		}

		@Override
		public Object next() {
			return elementData[count];
		}
		
	}
	
	
	private void expand(){
		elementData = Arrays.copyOf(elementData, elementData.length*2);
	}
	
}
