package com.github.xiaozi123.coding2017.basic;

import java.util.Arrays;

import org.omg.CORBA.PUBLIC_MEMBER;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		if(size>=elementData.length){//���û���ڴ� ��ȡ�����ڴ� 
			elementData=Arrays.copyOf(elementData,size+1);
		}
			elementData[size]=o;
			size++;
	}
	public void add(int index, Object o){
		if(index<0||index>=elementData.length){
			throw new ArrayIndexOutOfBoundsException("OutOfBound");
		}
		
		if(size>=elementData.length){//���û���ڴ� ��ȡ�����ڴ� 
			elementData=Arrays.copyOf(elementData, size+1);
		}
			System.arraycopy(elementData, index, elementData, index+1, elementData.length-index-1);
			elementData[index]=o;
			size++;
	}
	
	public Object get(int index){
		if(index<0||index>=elementData.length){
			throw new ArrayIndexOutOfBoundsException("OutOfBound");
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index<0||index>=elementData.length){
			throw new ArrayIndexOutOfBoundsException("OutOfBound");
		}
		Object temp=elementData[index];
		System.arraycopy(elementData, index+1, elementData, index, elementData.length-index-1);
		size--;
		return temp;
	}

	/*
	 * (non-Javadoc)��ȡĿǰ����
	 * @see com.github.xiaozi123.coding2017.basic.List#size()
	 */
	public int size(){
		if(size>=elementData.length){//���û���ڴ� ��ȡ�����ڴ� 
			elementData=Arrays.copyOf(elementData,size+1);
		}
		return size;
	}
	
	// next() hasnext()����
	public Iterator iterator(){
		return new Iterator(){
			private int index = 0;
			public Object next(){
				return elementData[index++];
			}
			public boolean hasNext(){
				return index >= size;
			}
		};
	}
	
	public static void main(String[] args) {
		ArrayList arrayList=new ArrayList();
		
		arrayList.add(0);
		arrayList.add(1);
		arrayList.add(2);
		System.out.println("���ݸ���Ϊ3��"+(arrayList.size==3));
		
		System.out.print("����Ӧ��Ϊ0,1,2:   ");
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.print(arrayList.get(i)+" ");
		}
		System.out.println();
		
		arrayList.add(1,1);
		System.out.print("����Ӧ��Ϊ��0,1,1,2:   ");
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.print(arrayList.get(i)+" ");
		}
		System.out.println();
		
		System.out.print("����Ӧ��Ϊ0:  ");
		System.out.println(arrayList.remove(0));
		
		
	}
	
	
}

