package com.coding.basic;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		IncrementsCapacity(size + 1);
		elementData[size++] = o;

	}

	public void add(int index, Object o) {
		checkIndex(index);
		IncrementsCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		checkIndex(index);
		return elementData[index];
	}

	public Object remove(int index) {
		checkIndex(index);
		Object o = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		elementData[--size] = null;
		return o;
	}

	public int size() {
		return size;
	}
	
	public int length() {
		return elementData.length;
	}

	// ����
	private void IncrementsCapacity(int num) {
		if (num > elementData.length) {
			int oldCapacity = elementData.length; // ��ǰ���鳤��
			int newCapacity = ((num + oldCapacity) * 3) >> 2; // ��ǰ���鳤�ȵ�1.5��
			if (newCapacity - num < 0) {
				newCapacity = num; // �����������ǲ���,ֱ����Ϊ����ֵ
			}
			Object[] oldelements = elementData;
			elementData = new Object[newCapacity];
			System.arraycopy(oldelements, 0, elementData, 0, size);
		}
	}

	// �±�Խ���ж�
	private void checkIndex(int index) {
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException("�����±�Խ��");
	}
}
