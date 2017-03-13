package collections;

/**
 * 
 * @author Mahone Wu
 * @data:2017-03-10
 * @description:ArrayList的自我实现,暂时没有自动扩展 
 * @version：1.0.0
 */

public class ArrayList implements List {

	// 数组存储数据大小
	private int size = 0;
	
	int cap = 5;

	// 数组
	private Object[] elementData = new Object[cap];

	/**
	 * 新增元素
	 */
	public void add(Object o) {
		elementData[size] = o;
		size++;
	}

	/**
	 * 指定位置新增数据
	 */
	public void add(int index, Object o) {
		// 保存现在该位置有的元素
		Object temp = elementData[index];

		// 如果当前存放数据等于初始化数组大小，则数组需要进行扩容操作
		if (elementData.length == size) {
			// TODO
		}
		// 如果没有超过大小，则把从插入开始点的数据都往后移一位
		for (int i = size; i > index; i--) {
			elementData[i] = elementData[i - 1];
		}
		// 将现在赋值的数据放到该位置
		elementData[index] = o;
		size++;
	}

	/**
	 * 获取数据
	 */
	public Object get(int index) {
		if (index > elementData.length) {
			return null;
		}
		return elementData[index];
	}

	// 返回要删除的数据
	public Object remove(int index) {
		// 要被删除的数据
		Object data = elementData[index];
		for (int i = index; i < size; i++) {
			elementData[i] = elementData[i + 1];
		}
		size--;
		return data;
	}

	public int size() {
		return size;
	}

	// TODO
	/**
	 * 循环输出
	 * 
	 * @return
	 */
	public Iterator iterator() {
		int current = 0;
		if (hasNext(current)) {
			// return elementData[current+1];
		}
		return null;
	}

	public boolean hasNext(int current) {
		return current < size();
	}

}
