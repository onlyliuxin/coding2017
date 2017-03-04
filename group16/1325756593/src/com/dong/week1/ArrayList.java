package com.dong.week1;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		/**
		 * ���ж������Ƿ��Ѿ����ˣ�����Ѿ����������ݴ���
		 */
		if(elementData.length==size){
			elementData = Arrays.copyOf(elementData, elementData.length*2+1);			
		}
		elementData[size++]=o;
			
	}
	public void add(int index, Object o){
		if(index >size || index < 0 ){
			throw new ArrayIndexOutOfBoundsException("����Խ��,��ǰ���鳤����"+size+",��������Ԫ�ص�������:"+index);
		}
		/**
		 * �����Ѿ����ˣ�������
		 */
		if(size==elementData.length){
			elementData = Arrays.copyOf(elementData, elementData.length*2+1);		
		}	
		Object[] elementDataClone = elementData.clone();
		System.arraycopy(elementData, index, elementDataClone, index+1, size-index);
		elementDataClone[index++]=o;
		size++;
		elementData = elementDataClone;
	}
	
	
	public Object get(int index){
		if(index >=size || index < 0 ){
			throw new ArrayIndexOutOfBoundsException("����Խ��,��ǰ���鳤����"+size+",��������Ԫ�ص�������:"+index);
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index >=size || index < 0 ){
			throw new ArrayIndexOutOfBoundsException("����Խ��,��ǰ���鳤����"+size+",����ɾ��Ԫ�ص�������:"+index);
		}
		elementData[index]=null;
		size--;
		Object[] elementDataClone = elementData.clone();
		System.arraycopy(elementData, index+1, elementDataClone, index, size-index-1);
		elementData = elementDataClone;
		return elementData[index];
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new IteratorArrayList(this);
	}
	@Override
	public String toString() {
		return "ArrayList [size=" + size + ", elementData=" + Arrays.toString(elementData) + "]";
	}

	
	private class IteratorArrayList implements Iterator{
		
		private ArrayList arrayList;
		private int index=0;
		

		public IteratorArrayList(ArrayList arrayList) {
			super();
			this.arrayList = arrayList;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return this.arrayList.size() >index;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			if(hasNext()){
				return  this.arrayList.get(index++);
			}
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		ArrayList arrayList= new ArrayList();
		
		Iterator iterator=  arrayList.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

	
}
