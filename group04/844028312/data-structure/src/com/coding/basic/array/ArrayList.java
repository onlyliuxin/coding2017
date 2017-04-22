package com.coding.basic.array;

import java.util.Arrays;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class ArrayList implements List {
	
	private int size = 0;
	private static final int DEFAULT_CAPACITY = 10;
	private Object[] elementData = new Object[100];
	/**
	 * ���Ԫ��
	 */
	public void add(Object o){
		
		if(size<elementData.length){//û�г�����������
			elementData[size]=o;
		}
		
		else{						//����������������
			Object[] newData = new Object[elementData.length+DEFAULT_CAPACITY];	//�½�һ���������
			System.arraycopy(elementData, 0, newData, 0, elementData.length);	//��ԭ�����������ݸ��Ƶ����������
			newData[size]=o;		//���oԪ��
			elementData=newData;	//elementData���������˵�����
		}
		size++;						//��¼��ݵĴ�С
	}
	
	public void add(int index, Object o){
		if(index>=0 && index<=size){			//�ж� index�Ƿ���size��Χ��  1 2 3 4
			if(size+1<elementData.length){	//�ж������Ƿ�Խ��
				System.arraycopy(elementData, index, elementData, index+1, elementData.length-index-1);	//��index���������ݺ���һλ
				elementData[index]=o;		//index�¸���o
			}
			else{
				Object[] newData = new Object[elementData.length+DEFAULT_CAPACITY];						//�½�һ���������
				System.arraycopy(elementData, 0, newData, 0, elementData.length);						//��ԭ���鸴�Ƶ��µ�������
				System.arraycopy(newData, index, newData, index+1, newData.length-index-1);				//���µ������index���������ݺ���һλ
				newData[index]=o;																		//���µ������index����o
				elementData=newData;																	//�������鸳���elementData
			}
			size++;					//��С��һ
		}
		else{
			System.out.println("index����size��Χ��");
		}
		
	}
	
	public Object get(int index){
		if(index<size){					//�ж��Ƿ���size��Χ��
			return elementData[index];  //��ȡindex�±����
		}
		else{
			return null;
		}
	}
	
	public Object remove(int index){
		Object o=null;
		if(index<size){					//�ж��Ƿ���size��Χ��
			o=elementData[index];		//��¼�Ƴ�����
			elementData[index]=null;	//�ÿ�
			System.arraycopy(elementData, index+1, elementData, index, elementData.length-index-1);// ��index+1����������ǰһλ 
			size--;						//��С��һ
		}
		return o;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Ir();
	}
	private class Ir implements Iterator{
		private int cursor;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cursor != size;
		}
		@Override
		public Object next() {
			// TODO Auto-generated method stub
			int i=cursor;
			if(cursor<size){
				cursor=i+1;
				return elementData[i];
			}
			return null;
		}
		
	}
	
	
}
