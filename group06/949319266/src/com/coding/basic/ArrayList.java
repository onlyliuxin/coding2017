package com.coding.basic;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {
	
	private int size;
	private int current ;
	private int point;
	private Object[] elementData = null;
	private static final int DEFAULT_SIZE=100;
	
	public ArrayList() {
		this(DEFAULT_SIZE);
	}
	
	public ArrayList(int size) {
		if(size < 0) {
			System.out.println("大小不能小于0");
		} else {
			this.elementData = new Object[size];
			this.current = 0;
			point = size;
		}
	}
	
	public void add(E e){
		judgeSize();
		this.elementData[current] = e;
		this.current++;		
	}
	public void add(int index, E e){
		judgeIndex(index);		
		for(int i=0 ; i<elementData.length;i++) {
			if(i >= index && i+2 < elementData.length) {
				elementData[i] = e;
				elementData[i+1] = elementData[i+2];
			}			
		}		
		current++;
	}
	
	public E get(int index){
		judgeIndex(index);
		return (E)this.elementData[index];
	}
	
	public void remove(int index) {
		judgeIndex(index);
		for(int i=0;i<elementData.length;i++) {
			if(i==index) {
				elementData[i] = elementData[i+1];
			}
		}
		current--;		
	}
	
	public int size(){
		return this.current;
	}
	
	public Iterator iterator(){
		return null;
	}
	public boolean contains(E e) {
		for(Object element : this.elementData) {
			if(e.equals(element)) {
				return true;
			}
		}
		return false;
	}
	public boolean isEmpty() {
		if(this.current>0) {
			return true;
		}
		return false;
	}
	public void clear() {
		elementData = new Object[DEFAULT_SIZE];
	}
	 private void judgeSize() {  
		    Object[] newarr = new Object[elementData.length + DEFAULT_SIZE];  
	        System.arraycopy(elementData, 0, newarr, 0, elementData.length); 
	        this.elementData = newarr;
	    }  
	public void judgeIndex(int index) {
		if(index < 0  || index > point) {
			System.out.println("下标越界");
		}
	}
	
}
