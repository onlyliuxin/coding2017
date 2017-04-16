package com.coderising.array;

import java.util.Arrays;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class ArrayList implements List {
	
	private int size = 0;
	
	private static final int GROW_BY_SIZE = 10;
	
	private Object[] elementData = new Object[GROW_BY_SIZE];
	
	public void add(Object o){
		if(size == elementData.length){
			grow();
		}
		elementData[size]=o;
		size++;
	}
	public void add(int index, Object o){
		validate(index);
		if(size == elementData.length){
			grow();
		}
		for(int i=size;i>index+1;i--){
			elementData[i]=elementData[i-1];
		}
		elementData[index]=o;
		size++;
	}
	
	public Object get(int index){
		validate(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		validate(index);
		Object result = elementData[index];
		for(int i =index;i<size-1;i++){
			elementData[i]=elementData[i+1];
		}
		elementData[size-1]=null;
		size--;
		return result;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator{
		int pos=0;

		@Override
		public boolean hasNext() {
			return pos<size;
		}

		@Override
		public Object next() {
			if(pos>=size)throw new IndexOutOfBoundsException("Invalid Index:"+pos);
			Object result = elementData[pos];
			pos++;
			return result;
		}
		
		
	}
	
	private void grow(){
		elementData = Arrays.copyOf(elementData, elementData.length+GROW_BY_SIZE);
	}
	private void validate(int index){
		if(index<0||index>=size)throw new IndexOutOfBoundsException("Invalid Index:"+index);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("[");
		for(int i=0;i<size;i++){
			if(sb.length()>1)sb.append(",");
			sb.append(elementData[i]);
		}
		sb.append("]size=").append(this.size());
		return sb.toString();
	}
	
	public static void main(String[] args){
		ArrayList l = new ArrayList();
		for(int i=0;i<12;i++){
			l.add(i+"");
		}
		System.out.println(l);
		l.add("aaa");
		System.out.println("After adding aaa:"+l);
		l.add(2,"bbb");
		System.out.println("After adding bbb:"+l);
		System.out.println(l.get(2));
		System.out.println("After getting:"+l);
		System.out.println(l.remove(2));
		System.out.println("After removing:"+l);
		Iterator it = l.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
}
