package com.github.PingPi357.coding2017.basic;

import java.util.Arrays;

public class ArrayList implements List {
	private static final int DEFAULT_CAPACITY = 100; // 定义静态final类型，
														// final在一个对象类唯一，
														// static
														// final在多个对象中都唯一，如果作为常量用的话，只是final的话就得在别的类引用的时候要创建对象，会占用不必要的空间，而加上static的话在编译的时候占用一个空间，其他时候就不会再占用空间了。所以常量一般用static修饰，其他时候看你自己的需要
	// 理解：比如我是一个维修工人....public相当于我接任何活,static相当于10分钟之内赶到,final相当于就我一家.
	// reference: http://bbs.itheima.com/thread-162971-1-1.html

	int size = 0; // 如果是中文分号，报错：Syntax error on token "invalid Character", ;
					// excepted;

	private Object[] elementData = new Object[DEFAULT_CAPACITY]; // new
																	// Object后面接的是中括号

	@Override
	public void add(Object o) {
		// TODO Auto-generated method stub
		ensureCapacity(++size); // size自加操作
		elementData[size - 1] = o;
	}

	private void ensureCapacity(int minCapacity) {
		// TODO Auto-generated method stub
		if (minCapacity <= elementData.length) { // 当满足小于等于的情况 ???
			return;
		} else {
			elementData = Arrays.copyOf(elementData, minCapacity); // elementData写错为elementDta，导致报错 elementDta
																	// cannot be
																	// resolved
																	// as a
																	// variable
			// 这里为什么不直接是minCapacity 而要加上原始elementData.length？？？？
		}
	}

	@Override
	public void add(int index, Object o) {
		// TODO Auto-generated method stub
		if (index > size || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		ensureCapacity(++size); // size自加操作
		for (int i = size - 2; i > index - 1; i--) { // for中用';'号隔开;
														// ？？？i应该从size-2开始赋值
														// ？？？终止条件为i>index-1
			elementData[i + 1] = elementData[i];
		}
		elementData[index] = o; // 插入元素
	}

	@Override
	public Object remove(int index) {
		if (this.size > 0 && index < (this.size)) {
			Object o = elementData[index];
			for (int i = index; i < this.size; i++) {
				elementData[i] = elementData[i + 1];
			}
			this.size--;
			return o;
		} else {
			return null;
		}
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return elementData[index]; // 必须进行角标越界检查吗？？？
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

}
