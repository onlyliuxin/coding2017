package com.basic.datastructure;

import java.util.Arrays;

import org.junit.Test;

public class ArrayList implements List{
	private Object [] array =new Object [15];
	private int size=0;
	private int aindex=0;
	@Override
	public void add(Object o) {
		if(aindex>(int)(array.length/3*2)){
			array=autoGrew(array);
		}
		array[aindex]=o;
		size++;
		aindex++;
	}

	@Override
	public void add(int index, Object o) {
		if(index>=array.length){
			throw new IndexOutOfBoundsException();
		}
		array[index]=o;
		size++;
		aindex=index;
	}

	@Override
	public Object get(int index) {
		if(index>=array.length){
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public Object remove(int index) {
		if(index>=array.length){
			throw new IndexOutOfBoundsException();
		}
		Object o=array[index];
		for(int i=index;i<array.length;i++){
			if(i==array.length-1){
				break;
			}
			array[i]=array[i+1];
		}
		size--;
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	public Object [] autoGrew(Object [] o){
		Object [] newArray;
		return newArray=Arrays.copyOf(o, (int) (o.length*1.5));
	}
	
}
