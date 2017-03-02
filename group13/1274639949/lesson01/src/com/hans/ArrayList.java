package com.hans;

import java.util.Arrays;

import javax.management.RuntimeErrorException;

public class ArrayList implements List {
	
	private int size = 0;//ArrayList���Ѿ��洢��Ԫ�ظ�����Ҳ����һ�������Ԫ�ص��±ꡣ
	
	private Object[] elementData = null;
	
	public ArrayList() {
		elementData = new Object[3];
	}
	
	public ArrayList(int capacity){
		elementData = new Object[capacity];
	}
	
//	private static final final default
	
	/**
	 * ������o��ӵ�ArrayList�����һ��Ԫ�صĺ���
	 * @param o ��ӵ�Ԫ��
	 */
	public void add(Object o){
		checkSize();
//		add(size++, o);
		elementData[size++] = o;
	}
	
	/**
	 * ������o��ӵ�ָ����indexλ��
	 * indexӦ���� index >= 0 && index <= size() Ϊ true
	 * @param index ��ӵ�λ��
	 * @param o ��ӵ�Ԫ��
	 */
	public void add(int index, Object o){
		if(index > size || index < 0){
			//size�����������Ԫ�ص��±꣬����index����sizeҲ���ԡ�
			throw new ArrayIndexOutOfBoundsException("Index:" + index);
		}
		checkSize();
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++; 
	}
	
	/**
	 * ��ȡ�����indexλ�ô�Ԫ��
	 * indexӦ���� index >= 0 && index < size() Ϊ true
	 */
	public Object get(int index){
		checkIndex(index);
		return elementData[index];
	}
	
	/**
	 * ɾ�������indexλ�ô���Ԫ��
	 * indexӦ���� index >= 0 && index < size() Ϊ true
	 * @return ��ɾ����Ԫ��
	 */
	public Object remove(int index){
		checkIndex(index);
		Object obj = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		size--;
		return obj;
	}
	
	/**
	 * ��ȡ��ǰArrayList���Ѿ��洢��Ԫ�صĸ���
	 */
	public int size(){
		return this.size;
	}
	
	/**
	 * ��ȡ��ǰArrayList�ĵ�����
	 * @return ��ǰArrayList�ĵ�����
	 */
	public Iterator iterator(){
		return new Iterator(){
			private int count = 0;
			
			@Override
			public boolean hasNext() {
				return size > count;
			}

			@Override
			public Object next() {
				if(count == size){
					throw new RuntimeErrorException(null, "û�и����Ԫ�أ�");
				}
				return get(count++);
			}

			@Override
			public void remove() {
				ArrayList.this.remove(count - 1);
			}
			
		};
	}
	
	/**
	 * �������get��remove������������±��Ƿ�Խ��
	 * @param index ������±�
	 */
	private void checkIndex(int index){
		if(index >= size || index < 0){
			//size�����������Ԫ�ص��±꣬����index�±��ϻ�û�д洢���ݣ��޷�����get��remove����
			throw new ArrayIndexOutOfBoundsException("Index:" + index);
		}
	}
	
	/**
	 * ���ArrayList�Ƿ�Ҫ������
	 * ��ArrayList��Ҫ����ʱ����������ݣ�ÿ�����ݽ�ʹ����������20��
	 */
	private  void checkSize(){
		if(size < elementData.length) return;
		elementData = Arrays.copyOf(elementData, elementData.length + 20/*(int)(elementData.length * 1.2)*/);
	}
	
}
