package ListServiceImpl;

import java.util.Arrays;
import java.util.Collection;

import ListService.KIQueueList;

public class KQueueList<T> implements KIQueueList<T>{

	/** 初始容量 */
	public static final int DEFAULT_SIZE = 10;
	/** 容量不足时翻倍数 */
	public static final float DEFAULT_INCREMENT = 1.5f;
	/** 数据 */
	private Object[] elementData;
	/** 元素个数 */
	private int elementCount;
	/** 数组的头部,即 下次删除数据的 index */
	private int head;
	/** 数组的尾部,即 下次插入数据的 index */
	private int tail;

	public KQueueList() {
		this(DEFAULT_SIZE);
	}

	public KQueueList(int size) {
		this.elementData = new Object[size];
		this.elementCount = 0;
		this.head = 0;
		this.tail = 0;
	}

	public KQueueList(Object[] data) {
		this.elementData = data;
		this.elementCount = data.length;
		this.head = 0;
		this.tail = 0;
	}

	public KQueueList(Collection<? extends T> c) {
		this(c.toArray());
	}

	/**
	 * 添加数据 到尾部
	 * 
	 * @param ele
	 * @return
	 */
	public T add(T ele) {
		if (tail >= elementData.length) {
			adjustData();
		}
		elementData[tail] = ele;
		elementCount++;
		tail++;
		return ele;
	};

	/**
	 * 删除数据 从头部
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T remove() {
		T e = (T) elementData[head];
		elementData[head] = null;
		elementCount--;
		head++;
		return e;
	};

	/**
	 * 获得当前的数据
	 * 
	 * @return
	 */
	public Object[] getData() {
		return Arrays.copyOfRange(this.elementData, this.head, this.tail);
	}

	public void adjustData() {
		if (tail >= elementData.length) { // tail 处空间不足时调用
			// head 的空位去掉
			int newSize = (elementData.length == elementCount) ? (int) Math.ceil(elementCount * DEFAULT_INCREMENT)
					: elementData.length;
			elementData = Arrays.copyOfRange(elementData, head, elementData.length);
			// 调整空间
			elementData = Arrays.copyOf(elementData, newSize);
			tail = elementCount;
			head = 0;
		}
	}

}
