package week1.collection;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size=0;

	private Object[] elementData=new Object[100];
	
	public void add(Object o){
		
		add(size,o);
		
	}

	public void add(int index, Object o){
		
		ListUtils.checkIndexRange(index,size);
		
		if(size == elementData.length){
			elementData = Arrays.copyOf(elementData, elementData.length+50);
		}
			
		if(index < size){
			for(int i=size-1;i>=index;i--){
				elementData[i+1]=elementData[i];
			}
		}			
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		
		ListUtils.checkIndexRange(index+1,size);
		
		return elementData[index];
	}
	
	public Object remove(int index){
		
		ListUtils.checkIndexRange(index+1,size);
		
		Object object=elementData[index];
		for(int i=index;i<size-1;i++){
			elementData[i]=elementData[i+1];
		}
		elementData[size-1]=null; // GC回收
		size--;
		return object;
	}
	
	public int size(){
		
		return size;
		
	}
	
	public Iterator iterator(){
		
		return new Iterator(){

			private int cursor=0;
			
			@Override
			public boolean hasNext() {	
				return cursor!=size;
			}

			@Override
			public Object next() {
				int i=cursor;
				cursor++;
				return elementData[i];
			}
		};
	}
}
