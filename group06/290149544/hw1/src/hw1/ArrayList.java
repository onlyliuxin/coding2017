package hw1;

import java.util.Arrays;

// 先不考虑线程安全，增删改查
// 业务导向就是复制
public class ArrayList implements List {
	// 又想到阁成员函数
	private int size = 0; // 属于elementData的属性
	// 暂时不要泛型，以后优化
	private Object[] elementData = new Object[10];
	public void add(Object o) {
		elementData = grow(elementData, 10);
		elementData[size()] = o;
	}
	public void add(int index, Object o) {
		elementData = grow(elementData, 10);
		for (int i = elementData.length-1; i >= index ; i--) {
			elementData[i+1] = elementData[i];
		}
		elementData[index] = o;
	}
	public Object get(int index) {
		// 入境检查
		if (index >= elementData.length) {
			System.out.println("如何抛出数组越界异常？");
		}
		return elementData[index];
	}
	public Object remove(int index) {
		// 入境检查
		if (index >= elementData.length || index < 0) {
			System.out.println("如何抛出数组越界异常？");
		}
		for (int i = index; i < elementData.length; i++) {
			elementData[i-1] = elementData[i];
		}
		return null;
	}
	public int size() { // 元素的个数
//		return -1;
		return size;
	}
	public void print() {
		for (int i = 0; i < size(); i++) {
			System.out.println(elementData[i]);
		}
	}
	// 注意有返回值
	private Object[] grow(Object[] src, int size) {
		if (size() < src.length) {
			return src; // 说明至少还能再放一个
		} else {
			// 放不下了，则增加10个，数据结构是层层服务的
			return Arrays.copyOf(src, src.length+size);
		}
	}
}