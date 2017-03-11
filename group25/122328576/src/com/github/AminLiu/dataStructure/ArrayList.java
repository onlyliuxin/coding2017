package com.github.AminLiu.dataStructure;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[10];

	public void add(Object o) {
		this.checkSize(this.size + 1);
		this.elementData[this.size++] = o;
	}

	public void add(int index, Object o) {
		if (index > this.size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ this.size);
		}
		this.checkSize(this.size + 1);
		System.arraycopy(this.elementData, index, this.elementData, index + 1,
				this.size - index);
		this.elementData[index] = o;// 澶嶅埗瀹屽皢鐩爣瀵硅薄澶嶅埗鍒癷ndex浣嶇疆
		++this.size;
	}

	public Object get(int index) {
		if (index < 0 || index > this.size - 1) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ this.size);
		}
		return this.elementData[index];
	}

	public Object remove(int index) {
		if (index < 0 || index > this.size - 1) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ this.size);
		}
		Object o = this.elementData[index];
		System.arraycopy(this.elementData, index + 1, this.elementData, index,
				this.size - index - 1);
		this.elementData[--this.size] = null;
		return o;
	}

	public int size() {
		return this.size;
	}

	// 妫�鏌ユ暟缁勫ぇ灏忥紝灏忎簡鎵╁
	private void checkSize(int arg0) {
		if (arg0 > Integer.MAX_VALUE) {// 瓒呰繃int鏈�澶у��
			throw new OutOfMemoryError();
		}
		if (this.elementData.length < arg0) {
			addCapacity(arg0);
		}
	}

	// 鎵╁
	private void addCapacity(int arg0) {
		int arg1 = arg0 + (arg0 >> 1);// 澧炲姞鍚庢暟缁勫ぇ灏�=鍘熸暟缁�+鍘熸暟缁�/2
		this.elementData = Arrays.copyOf(this.elementData, arg1);
	}

	public Iterator iterator() {
		return new ite();
	}

	private class ite implements Iterator {
		int arg0;

		@Override
		public boolean hasNext() {
			return arg0 != size;
		}

		@Override
		public Object next() {
			int i = arg0;
			if (i >= size)
				throw new NoSuchElementException();
			Object[] elementData = ArrayList.this.elementData;
			if (i >= elementData.length)
				throw new ConcurrentModificationException();
			arg0 = i + 1;
			return elementData[i];
		}
	}

	public String toString() {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("[");
		for (int i = 0; i < this.size; i++) {
			if (i == this.size - 1) {
				sBuffer.append(this.elementData[i].toString());
			} else {
				sBuffer.append(this.elementData[i].toString() + ",");
			}
		}
		sBuffer.append("]");
		return sBuffer.toString();
	}
}