package coding;

import java.util.NoSuchElementException;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		int len = size + 1;
		// 判断list的长度是否大于容器长度
		if (len > elementData.length) {
			// 创建新容器
			Object[] newElemDate = new Object[elementData.length + 1];
			// 复制旧容器所有元素到新容器
			System.arraycopy(elementData, 0, newElemDate, 0, elementData.length);
			elementData = newElemDate;
		}
		elementData[size] = o;
		size++;
	}

	public void add(int index, Object o) {
		// 检查是否越界
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index:" + index + "size:" + size);
		}
		// 插入元素到末尾直接调用add方法
		if (index == size) {
			add(o);
		} else {
			// 创建新容器
			Object[] newElemData = new Object[elementData.length + 1];
			// 复制index以前的所有元素到新容器
			System.arraycopy(elementData, 0, newElemData, 0, index);
			newElemData[index] = o;
			// 复制index 及以后的元素到新容器
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
		//不是最后一个元素的索引值才需要操作
		if(index != (size-1)){
			// 创建新容器
			Object[] newElemData = new Object[elementData.length];
			// 复制index以前的所有元素到新容器
			System.arraycopy(elementData, 0, newElemData, 0, index);
			// 复制index 以后的元素到新容器
			System.arraycopy(elementData, index+1, newElemData, index, size - index -1);			
		}
		//最后一个元素的索引值直接减短list长度
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
