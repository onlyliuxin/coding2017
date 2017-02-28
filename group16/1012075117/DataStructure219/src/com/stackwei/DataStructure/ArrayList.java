package com.stackwei.DataStructure;

/**
 * 
 * @author stackwei -2017.2.25
 *
 */
public class ArrayList implements List {

	private int flag = -1;
	private static final int DEFAULT_CAPACITY = 1;
	private Object[] elementData = new Object[DEFAULT_CAPACITY];
	
	@Override
	public void add(Object element) {
		// 当要添加数据的位置已经超过数组长度时，增长数组长度
		if (size() + 1 == elementData.length) {
			grow();
		}
		elementData[flag + 1] = element;
		flag++;
	}

	@Override
	public void add(int index, Object element) {
		if (index < 0 || index > getFlag() + 1) {
			System.out.println("在--" + index + "--添加的--" + element + "--无效，因为越界了！");
			return;
		}
		// 数组长度永远比已存数据大一个。
		if (size() + 1 == elementData.length) {
			grow();
		}
		elementData[index] = element;
		if (index > getFlag()) {
			flag++;
		}
	}

	@Override
	public Object get(int index) {
		if (index < 0 || index > getFlag()) {
			System.out.print("在--" + index + "--的get无效,因为越界了！");
			return null;
		}
		return elementData[index];
	}

	@Override
	public Object remove(int index) {
		if (index < 0 || index > getFlag()) {
			System.out.println("在--" + index + "--的remove无效,因为越界了！");
			return null;
		}
		Object oldValue = elementData[index];
		elementData[index] = null;
		// 将删除处后面的数据往前移一格。
		Object[] data2 = new Object[elementData.length - 1];
		System.arraycopy(elementData, 0, data2, 0, getFlag());
		elementData = data2;
		flag--;
		return oldValue;
	}

	@Override
	public int size() {
		return getFlag() + 1;
	}

	public int getFlag() {
		return flag;
	}

	private void grow() {
		Object[] data2 = new Object[elementData.length + 1];
		System.arraycopy(elementData, 0, data2, 0, getFlag() + 2);// 最后一个参数是需要复制的数据的数量。
		elementData = data2;
	}

	/**
	 * 测试用例
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList al = new ArrayList();
		al.add(0, 99);
		al.add(1, 100);
		System.out.println(al.get(1));
		al.remove(1);
		System.out.println(al.get(1));
		System.out.println(al.size());
	}
}