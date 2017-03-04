package tong.java.one;

import java.util.Arrays;

/**
 * 自定义ArrayList
 * 
 * @author tong
 *
 */
public class MyArrayList {
	private Object[] datas = new Object[10];
	private int size;

	// 默认在集合末尾添加元素
	public void add(Object o) {
		if (datas[0] == null) {
			datas[0] = o;
		} else {
			if (size < datas.length) {
				datas[size] = o;
			} else {
				datas = grow(5);
				datas[size] = o;
			}
		}
		size++;
	}

	// 指定索引处添加元素
	public void add(int index, Object o) {
		if (index > size - 1) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			if (size + 1 > datas.length) {
				datas = grow(5);
			}
			datas[index] = o;
			for (int i = index + 1; i < size - 1; i++) {
				datas[i] = datas[i + 1];
			}
			size++;
		}
	}

	// 获取指定索引处的的元素
	public Object get(int index) {
		if (index > size - 1) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			return datas[index];
		}
	}

	// 删除指定索引处的元素
	public Object remove(int index) {
		if (index > size - 1) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			Object removeData = datas[index];
			for (int i = index; i < size - 1; i++) {
				datas[index] = datas[index + 1];
			}
			size--;
			return removeData;
		}
	}

	public int size() {
		return size;
	}

	private Object[] grow(int length) {
		return Arrays.copyOf(datas, datas.length + length);
	}
}
