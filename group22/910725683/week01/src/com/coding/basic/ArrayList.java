package com.coding.basic;
import java.util.Arrays;

public class ArrayList implements List {
	
	//�����ʼ����//
	private final int DEFAULT_CAPICITY=7;
	
	//����Ԫ�ظ���//
	private int size = 0;
	
	private Object[] elementData = new Object[DEFAULT_CAPICITY];
	
	public void add(Object o){
		ensureCapcity(size+1);
		elementData[size++]=o;
	}
	public void add(int index, Object o){
		//indexҪ����������//
		checkIndex(index);
		ensureCapcity(size+1);
		/* index�������Ԫ������һλ,����index��ʼ�ƶ���ע��index��0��ʼ��
		 * ����Ҫ+1���򳤶�Ϊsize-((index)+1)+1
		 */
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index]=o;
		size++;
	}
	
	public Object get(int index){
		checkIndex(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		checkIndex(index);
		Object temp=elementData[index];
		/* index�����Ԫ������һλ,����index+1��ʼ�ƶ���ע��index��0��ʼ��
		 * ����Ҫ+1���򳤶�Ϊsize-((index+1)+1)+1
		 */
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		size--;
		return temp;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Iterator();
	}
	
	private class Iterator{
		private int index=0;
		public boolean hasNext(){
			return index<size;
		}
		public Object next(){
			checkIndex(index);
			return elementData[index++];
		}
	}
	
	//�ж�������±��Ƿ�Խ�粢��ʾ//
	private void checkIndex(int index){
		if (index<0 || index >=size){
			throw new IndexOutOfBoundsException("get " + index+" in "+size);
		}		
	}
	
	//�ж��Ƿ���Ҫ���ݲ��������//
	private void ensureCapcity(int size){
		int oldLength=elementData.length;
		if (size>=oldLength){
			//util.ArrayList�еĹ�ʽ��Դ����ʹ�õ�����1������2//
			int newLength=oldLength/2+oldLength;
			if (newLength<size){
				newLength=size;
			}
			//Arrays.copyOf�����µ�ָ�����ȵ�����//
			elementData=Arrays.copyOf(elementData, newLength);
		}
	}

	@Override
	public String toString(){
		//��������д��toString//
		StringBuilder sb = new StringBuilder("[");
		for (int i=0 ;i<size-1;i++){
			sb.append(elementData[i]+",");
		}
		sb.append(elementData[size-1]);
		sb.append("]");
		return sb.toString();
	}
	
}