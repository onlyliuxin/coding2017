package com.github.zhanglifeng.coding2017.basic;

import java.util.*;

/**
 * 功能：实现ArrayList.
 * @author zhanglifeng.
 */
public class ArrayList implements List {
	private int size = 0;  //当前数组大小

	private Object[] elementData = new Object[5];  //初始数组

	/**
	 * 将对象o添加到ArrayList中.
	 * @param o:需要添加的对象.
	 */
	public void add(Object o) {
		ensureCapacity(size + 1);  //确保数组的容量可以装的下size + 1个元素，如果不够则扩容

		elementData[size] = o;  //将o添加到数组中
		size++;  //数组大小增加1
	}	

	/**
	 * 将对象o添加到ArrayList的指定位置.
	 * @param index: 指定位置.
	 * @param o: 需要添加的对象.
	 */
	public void add(int index, Object o) {
		rangeCheck(index);   //判断指定的位置index是否合法

		ensureCapacity(size + 1); //确保数组的容量可以装的下size + 1个元素，如果不够则扩容

		System.arraycopy(elementData, index, elementData, index + 1, size - index); //将index位置到结束位置所有的数组往后移动一个位置
		elementData[index] = o; //将对象o添加到index位置
		size++;//数组大小增加1
	}

	public Object get(int index) {
		rangeCheck(index);
		return elementData[index];
	}

	public Object remove(int index) {		
		rangeCheck(index);
		
		if (index != elementData.length - 1) {
			System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
		}

		size--;
		return elementData;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new ArrayListIterator(this);
	}
	
	private void ensureCapacity(int number) {
		if (number > elementData.length) {
			elementData = grow(elementData, 1);
		}
	}

	public Object[] grow(Object[] src, int step) {
		Object[] target = new Object[src.length + step];
		System.arraycopy(src, 0, target, 0, src.length);
		return target;
	}
	
	public void rangeCheck(int index){
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}

	private class ArrayListIterator implements Iterator {
		ArrayList arrayList = null;
		int current = 0;

		private ArrayListIterator(ArrayList arrayList) {
			this.arrayList = arrayList;
		}

		@Override
		public boolean hasNext() {
			current++;
			return current > arrayList.size() ? false : true;
		}

		@Override
		public Object next() {
			return elementData[current];
		}

	}

	public static void main(String[] args) {
		ArrayList arrayList = new ArrayList();
		arrayList.add("s1");
		arrayList.add("s2");
		arrayList.add("s3");
		arrayList.add("s4");
		arrayList.add(3, "s33");
		arrayList.add("s5");

		System.out.println(arrayList.size());

		System.out.println(arrayList.get(2));

		arrayList.remove(3);

		System.out.println(arrayList.size());

		arrayList.add("s1");
		System.out.println(arrayList.size());
		arrayList.remove(5);
		System.out.println(arrayList.size());
		
		Iterator it = arrayList.iterator();
		while(it.hasNext()){
			System.out.print(it.next() + " ");
		}
		System.out.println();
	}

}
