package com.coding.basic.array;

import java.util.Arrays;

import org.junit.Test;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];

	/**
	 * 自动增长值
	 */
	private static final int GROW_UP_SIZE = 100;

	/**
	 * 在数组最末尾添加对象
	 */
	public void add(Object o) {
		growUp(size);
		elementData[size] = o;
		size++;
	}

	/**
	 * 向指定下标处添加对象
	 */
	public void add(int index, Object o) {
		checkIndex(index);
		growUp(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;

	}

	/**
	 * 根据index获取对象
	 */
	public Object get(int index) {
		checkIndex(index);
		return elementData[index];
	}

	/**
	 * 移除指定下标对象
	 */
	public Object remove(int index) {
		checkIndex(index);
		Object obj = elementData[index];
		int afterRemove = size - index - 1;
		// System.out.println("@@@@"+afterRemove+"---"+index);
		if (afterRemove > 0)
			System.arraycopy(elementData, index + 1, elementData, index, afterRemove);
		elementData = Arrays.copyOf(elementData, --size);
		return obj;
	}

	/**
	 * 获取数组大小
	 */
	public int size() {
		return size;
	}

	/**
	 * 迭代器
	 * 
	 * @return
	 */
	public Iterator iterator() {
		return new Iter();
	}

	/**
	 * 迭代器内部类
	 * 
	 * @author Adminstater
	 * 
	 */
	private class Iter implements Iterator {
		private int nextIndex = 0;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return nextIndex != size;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			int i = nextIndex++;
			if (i > size - 1)
				throw new IndexOutOfBoundsException();

			return elementData[i];
		}

	}

	/**
	 * 检查数组长度是否越界,越界就自动增长100
	 */
	private void growUp(int size) {
		if (size > elementData.length - 1) {
			Object[] elementGrow = new Object[elementData.length + GROW_UP_SIZE];
			System.arraycopy(elementData, 0, elementGrow, 0, elementData.length);
			elementData = elementGrow;
			// System.out.println(elementData.length);
		}
	}

	/**
	 * 检查下标是否越界,越界抛出异常
	 */
	private void checkIndex(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("下标越界");
		}
	}

	/*------------------------------------------------------单元测试----------------------------------------------------*/
	/**
	 * 测试自增长数组长度变化及增加功能
	 */
	@Test
	public void TestAddFunction() {
		ArrayList list = new ArrayList();
		for (int x = 0; x < 300; x++) {
			list.add(x);
		}
	}

	/**
	 * 测试add(int index,Object obj)方法
	 */
	@Test
	public void TestAddIndexFunction() {
		ArrayList list = new ArrayList();
		// System.out.println(list.size());
		list.add(0, 20);
		list.add(1, 30);
		System.out.println(list.get(1));

	}

	/**
	 * 测试get方法
	 */
	@Test
	public void TestGetFunction() {
		ArrayList list = new ArrayList();
		for (int x = 0; x < 300; x++) {
			list.add(x);
		}
		for (int x = 0; x < list.size; x++) {
			System.out.println(list.get(x));
		}
	}

	/**
	 * 测试size方法
	 */
	@Test
	public void TestSizeFunction() {
		ArrayList list = new ArrayList();
		for (int x = 0; x < 259; x++) {
			list.add(x);
		}
		/*
		 * for(int x=0;x<list.size;x++){ System.out.println(list.get(x)); }
		 */
		System.out.println(list.size());
	}

	/**
	 * 测试remove方法
	 */
	@Test
	public void TestRemoveFunction() {
		ArrayList list = new ArrayList();
		for (int x = 0; x < 10; x++) {
			list.add(x);
		}
		list.remove(3);
		for (int x = 0; x < list.size(); x++) {
			System.out.println(list.get(x));
		}
	}

	/**
	 * 测试Iterator方法
	 */
	@Test
	public void TestIter() {
		ArrayList list = new ArrayList();
		for (int x = 9; x < 100; x++) {
			list.add(x);
		}
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	public static void main(String[] args) {
		// java.util.ArrayList<E> list = null;
	}

}
