package com.coding.basic;

import java.util.Arrays;

/**
 * ArrayList ʵ�� ��14С�� 296933284
 * 
 * @author Tonnyson
 *
 */
public class ArrayList implements List {

	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	private Object[] elementData;
	

	public ArrayList() {
		elementData = new Object[DEFAULT_CAPACITY];
	}

	public ArrayList(int initCapacity) {
		elementData = new Object[initCapacity];
	}

	/**
	 * ������ĩβ���ָ��Ԫ�أ����������������Զ���չΪԭ�����ȵ�����
	 */
	public void add(Object obj) {
		
		ensureCapacityInternal(size);

		elementData[size] = obj;
		size++;
	}
	

	/**
	 * �������ָ��λ�ò���Ԫ��
	 */
	public void add(int index, Object obj) {

		rangCheckForAdd(index);
		ensureCapacityInternal(size + 1);
		
		for (int i = size - 1; i >= index; i--)
			elementData[i + 1] = elementData[i];

		elementData[index] = obj;
		size++;
	}

	/**
	 * ����������
	 */
	private void ensureCapacityInternal(int minCapacity) {
		if (minCapacity - elementData.length > 0) {
			int newCapacity = elementData.length * 2;
			elementData = Arrays.copyOf(elementData, newCapacity);
			// elementData = tempElementData;
		}
	}
	
	/**
	 * ������ add() �м�������±��Ƿ�Խ��
	 */
	private void rangCheckForAdd(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException();
	}

	/**
	 * ����ָ������λ�õ�Ԫ��ֵ
	 */
	public Object get(int index) {

		rangCheck(index);

		return elementData[index];
	}

	/**
	 * ɾ��ָ������λ�õ�Ԫ�أ������ظ�ֵ
	 */
	public Object remove(int index) {
		rangCheck(index);

		Object obj = elementData[index];

		for (int i = index; i < size; i++)
			elementData[i] = elementData[i + 1];

		size--;

		return obj;
	}

	/**
	 * ��������±��Ƿ�Խ��
	 * 
	 * @param index
	 */
	private void rangCheck(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException();
	}

	/**
	 * �������鳤��
	 */
	public int size() {
		return size;
	}
	
	/**
	 * ������
	 * 
	 * @return
	 */
	public Iterator iterator() {
		return new Iter();
	}
	
	//�������ڲ���
	private class Iter implements Iterator {
		int current;
		
		@Override
		public boolean hasNext() {
			return current != size;
		}

		@Override
		public Object next() {
			
			int i = current;
			rangCheck(i);
			current++;
			
			return elementData[i];
		}
		
	}

}


