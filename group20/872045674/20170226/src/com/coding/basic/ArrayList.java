package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[7];
	
	public void add(Object o){
		if(size>elementData.length-1){
			ensureCapacity(size);
		}
		elementData[size++] = o;
	}

	public void add(int index, Object o){
		System.out.println(elementData.length+" length");
		System.out.println(size+" size");
		size++;
		if(index<0||index>size||index>Integer.MAX_VALUE){
			System.out.println("add 位置输入错误，请输入合理的位置");
			return;
		}
		if(size>elementData.length-1){
			ensureCapacity(size);
		}
		System.arraycopy(elementData,index,elementData,index+1,size-index-1);
		elementData[index] = o;
	}
	
	public Object get(int index){
		if(index<0||index>size-1){
			System.out.println("get 位置输入错误，请输入合理的位置");
			return null;
		}

		return elementData[index];
	}
	
	public Object remove(int index){
		if(index<0||index>size-1){
			System.out.println("remove 位置输入错误，请输入合理的位置");
			return false;
		}
		System.arraycopy(elementData,index+1,elementData,index,size-index-1);
		elementData[--size]=null;
		return true;
	}
	
	public int size(){
		return size;
	}

	private void ensureCapacity(int nimCapacity){
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity+(oldCapacity/2+1);
		if(newCapacity < nimCapacity){
			newCapacity = nimCapacity;
		}
		if(newCapacity>Integer.MAX_VALUE){
			newCapacity = Integer.MAX_VALUE;
		}
		elementData = Arrays.copyOf(elementData,newCapacity);
	}

	public static void main(String[] args) {
		ArrayList list=new ArrayList();

		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(2,10);
		list.remove(3);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		//System.out.println("temp"+list.toString());

		//int x=2;
		//String[] l = {"a","d","v","cd","s"};
		//System.out.println(l[x--]);
	}

}
