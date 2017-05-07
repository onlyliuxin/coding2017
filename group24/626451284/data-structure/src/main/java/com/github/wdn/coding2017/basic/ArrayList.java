package com.github.wdn.coding2017.basic;

import java.util.Arrays;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData;
	public ArrayList(){
		elementData = new Object[10];
	}
	public ArrayList(int size){
		elementData = new Object[size];
	}
	public void add(Object o){
		if(size>=elementData.length){
			elementData = Arrays.copyOf(elementData,elementData.length*2);
		}
		elementData[size]=o;
		size++;
	}
	public void add(int index, Object o){
		Object[] newElementData;
		if(size()+1>=elementData.length){
			newElementData=new Object[elementData.length*2];

		}else{
			newElementData=new Object[elementData.length];
		}
		for (int i = 0; i < elementData.length; i++) {
			if(index==1){
				newElementData[i]=o;
			}else if(i>index) {
				newElementData[i]=elementData[i-1];
			}else{
				newElementData[i]=elementData[i];
			}
		}
		elementData = newElementData;
		size++;
	}

	public Object get(int index){
		if(index>=size){
			throw new IndexOutOfBoundsException();
		}
		return elementData[index];
	}

	public Object remove(int index) {
		if(index>=size){
			throw new IndexOutOfBoundsException();
		}
		Object returnO = elementData[index];
		for (int i = index; i < size; i++) {
			if(i==size-1){
				elementData[i]=null;
			}else {
				elementData[i] = elementData[i + 1];
			}
		}
		size--;
		return returnO;
	}

	public int size(){
		return size;
	}

	public Iterator iterator(){
		return null;
	}

	public static void main(String[] args) {
		ArrayList list = new ArrayList(2);
		list.add("1");
		list.add("2");
		list.remove(1);
		list.add("3");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		int[] i = {};
		System.out.println(i[0]);
	}
	
}
