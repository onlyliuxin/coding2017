package com.coding.basic;

import java.util.Arrays;
import java.util.Collection;

public class TarrayList implements Tlist {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		ensureCapacity(size+1);
		elementData[size++]=o;
	}
	
	

	private void ensureCapacity(int minCapacity) {
		int oldCapacity = elementData.length;
		if(minCapacity>oldCapacity){
			int newCapacity = (oldCapacity * 3)/2+1;
			if(minCapacity>newCapacity)
				newCapacity=minCapacity;
			Arrays.copyOf(elementData, newCapacity);
		}
		
	}



	public void add(int index, Object o){
		//判断边界条件
		if(index>=size || index<0)
			throw new IndexOutOfBoundsException("Index:" +index+"Size:"+size);
		//扩容
		ensureCapacity(size+1);
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index]=o;
		size++;
	}
	
	public Object get(int index){
		//判断边界条件
		if(index>=size || index<0)
			throw new IndexOutOfBoundsException("Index:" +index+"Size:"+size);
		return elementData[index];
	}
	
	public Object remove(int index){
		//判断边界条件
		if(index>=size || index<0)
			throw new IndexOutOfBoundsException("Index:" +index+"Size:"+size);
		Object ele = elementData[index];
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		elementData[size--]=null;
		return ele;
	}
	public Object set(int index, Object o){
		//判断边界条件
		if(index>=size || index<0)
			throw new IndexOutOfBoundsException("Index:" +index+"Size:"+size);
		Object ele = elementData[index];
		elementData[index] = o;
		return ele;
	}
	public  <E> boolean addAll(Collection<? extends E> c){
		Object[] arr = c.toArray();
		int len = arr.length;
		ensureCapacity(size+len);
		System.arraycopy(arr, 0, elementData, size, len);
		size+=len;
		return len != 0;
	}
	
	public <E> boolean addAll(int index, Collection<? extends E> c){
		//判断边界条件
		if(index>=size || index<0)
			throw new IndexOutOfBoundsException("Index:" +index+"Size:"+size);
		
		Object[] arr = c.toArray();
		int len = arr.length;
		System.arraycopy(elementData, index, elementData, index+len, size-index);
		System.arraycopy(arr, 0, elementData, index, len);
		size+=len;
		return len !=0;
	}
	
	public boolean remove(Object o){
		if(o==null){
			for(int i = 0; i<size;i++){
				if(elementData[i]==null)
					fastRemove(i);
				return true;
			}
		}else{
			for(int i=0;i<size;i++){
				if(o.equals(elementData[i]))
					fastRemove(i);
				return true;
			}
		}
		return false;
		
	}
	
	public void fastRemove(int index){
		//判断边界条件
		if(index>=size || index<0)
			throw new IndexOutOfBoundsException("Index:" +index+"Size:"+size);
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		elementData[size--]=null;
		
	}
	
	
	public int size(){
		return this.size;
	}
	
	public Iterator iterator(){
		return new ArrayIterator(this);
	}
	
	private class ArrayIterator implements Iterator{

		private int index;
		private TarrayList list = null;
		
		ArrayIterator(Tlist al){
			list= (TarrayList) al;
		}
		
		@Override
		public boolean hasNext() {
			return index <list.size();
		}

		@Override
		public Object next() {
			if(hasNext()){
				return list.get(index++);
			}
			
			return null;
		}
		
	}
	
}
