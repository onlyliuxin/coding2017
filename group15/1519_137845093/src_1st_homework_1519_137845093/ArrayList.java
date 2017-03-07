package com.coding.basic;

import java.util.NoSuchElementException;

public class ArrayList implements List {
  //Ԫ�ظ���
	private int size = 0;
  //��ʼ������Ϊ10
	private Object[] elementData = new Object[10];

	public void add(Object o) {
		int len = size + 1;
		// �ж�list�ĳ����Ƿ�������鳤��
		if (len > elementData.length) {
			// ����������
			Object[] newElemData = new Object[elementData.length + 1];
			// ���ƾ���������Ԫ�ص�������
			System.arraycopy(elementData, 0, newElemData, 0, elementData.length);
			elementData = newElemData;
		}
		elementData[size] = o;
		size++;
	}

	public void add(int index, Object o) {
		// ����±��Ƿ�Խ��
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index:" + index + "Խ�磻" );
		}
		// ����Ԫ�ص������ĩβֱ�ӵ���add����
		if (index == size) {
			add(o);
		} else {
			// ����������
			Object[] newElemData = new Object[elementData.length + 1];
			// ����index��ǰ������Ԫ�ص�������
			System.arraycopy(elementData, 0, newElemData, 0, index);
			newElemData[index] = o;
			// ����index ���Ժ��Ԫ�ص�������
			System.arraycopy(elementData, index, newElemData, index + 1, size - index);

			elementData = newElemData;
			size++;
		}
	}

	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index:" + index + "Խ�磻");
		}
		return elementData[index];
	}

	public Object remove(int index) {
		//�±�������鳤�ȵģ��׳��쳣
		if (index >= size) {
			throw new IndexOutOfBoundsException("index:" + index + "Խ�磻");
		}		
		//index�������һ��Ԫ�ص�����ֵ����Ҫɾ������
		if(index != (size-1)){
			// ����������
			Object[] newElemData = new Object[elementData.length];
			// ����index��ǰ������Ԫ�ص�������
			System.arraycopy(elementData, 0, newElemData, 0, index);
			// ����index �Ժ��Ԫ�ص�������
			System.arraycopy(elementData, index+1, newElemData, index, size - index -1);			
		}
		Object removeElement = elementData[index];
		//��С����ĳ���
		size--;
		return removeElement;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new MyItr(this);
	}

	private class MyItr implements Iterator {
		private int l = -1;
		private ArrayList array = null;

		private MyItr(ArrayList array) {
			this.array = array;
		}

		@Override
		public boolean hasNext() {
			return (l + 1) < array.size;
		}

		@Override
		public Object next() {
			l++;
			if (l >= array.size) {
				l = array.size - 1 ;
				throw new IndexOutOfBoundsException();
			}

			return array.get(l);
		}

		@Override
		public Object remove() {
			if (l < 0) {
				throw new NoSuchElementException();
			}
			Object val = array.remove(l);
			l--;
			return val;
		}

	}
}