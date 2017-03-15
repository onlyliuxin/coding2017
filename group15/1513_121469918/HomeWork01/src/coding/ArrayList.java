package coding;

import java.util.NoSuchElementException;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		int len = size + 1;
		// �ж�list�ĳ����Ƿ������������
		if (len > elementData.length) {
			// ����������
			Object[] newElemDate = new Object[elementData.length + 1];
			// ���ƾ���������Ԫ�ص�������
			System.arraycopy(elementData, 0, newElemDate, 0, elementData.length);
			elementData = newElemDate;
		}
		elementData[size] = o;
		size++;
	}

	public void add(int index, Object o) {
		// ����Ƿ�Խ��
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index:" + index + "size:" + size);
		}
		// ����Ԫ�ص�ĩβֱ�ӵ���add����
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
			throw new IndexOutOfBoundsException("index:" + index + "size:" + size);
		}
		return elementData[index];
	}

	public Object remove(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException("index:" + index + "size:" + size);
		}
		Object removeElement = elementData[index];
		//�������һ��Ԫ�ص�����ֵ����Ҫ����
		if(index != (size-1)){
			// ����������
			Object[] newElemData = new Object[elementData.length];
			// ����index��ǰ������Ԫ�ص�������
			System.arraycopy(elementData, 0, newElemData, 0, index);
			// ����index �Ժ��Ԫ�ص�������
			System.arraycopy(elementData, index+1, newElemData, index, size - index -1);			
		}
		//���һ��Ԫ�ص�����ֱֵ�Ӽ���list����
		size--;
		return removeElement;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new MyIterator(this);
	}

	private class MyIterator implements Iterator {
		private int poi = -1;
		private ArrayList array = null;

		private MyIterator(ArrayList array) {
			this.array = array;
		}

		@Override
		public boolean hasNext() {
			return (poi + 1) < array.size;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			poi++;
			if (poi >= array.size) {
				poi--;
				throw new IndexOutOfBoundsException();
			}

			return array.get(poi);
		}

		@Override
		public Object remove() {
			// TODO Auto-generated method stub
			if (poi < 0) {
				throw new NoSuchElementException();
			}
			Object val = array.remove(poi);
			poi--;
			return val;
		}

	}
}
