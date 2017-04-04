package week1.collections;

import java.util.Arrays;

public class ArrayList implements List {
	private int size = 0;	//声明数组长度
	
	private Object[] elementData = new Object[0];	//声明一个Object数组，初始大小为10
	
	/**
	 * 将元素添加到末尾
	 */
	public boolean add(Object o){
		ensureCapacity(size+1);
		elementData[size] = o;
		size++;
		return true;
	}
	
	/**
	 * 将元素插入到index位置
	 */
	public void add(int index, Object o){
		exception(index);
		ensureCapacity(size+1);
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o;
		size++;
	}
	
	/**
	 * 获得index位置的元素
	 */
	public Object get(int index){
		exception(index);
		return elementData[index];
	}
	
	/**
	 * 删除index位置的元素并返回
	 */
	public Object remove(int index){
		exception(index);
		Object o = elementData[index];
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		size--;
		return o;
	}

	private void exception(int index) {
		if(index > size || index < 0){
			throw new ArrayIndexOutOfBoundsException("index"+index+"越界");
		}
	}
	
	/**
	 * 获取元素个数
	 */
	public int size(){
		return size;
	}
	
	/**
	 * 获取迭代器
	 * @return
	 */
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator{
		int pos = 0;
		@Override
		public boolean hasNext() {
			return pos<size();
		}

		@Override
		public Object next() {
			int newPos = pos;
			pos++;
			return elementData[newPos];
		}
	}
	
	/**
	 * 自动扩容
	 * @param minCapacity
	 */
	public void ensureCapacity(int minCapacity){
		if(minCapacity > elementData.length){
			int newCapacity = Math.max(minCapacity, elementData.length*2);
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}
}
