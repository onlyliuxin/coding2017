package cn.wsc.util;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * ArrayList类
 * 
 * @author c_malina007
 * @param <E>
 */
public class ArrayList<E> implements List<E> {

	/** 元素个数 */
	private int size;

	/** 默认容量 */
	private static final int DEFAULT_CAPACITY = 10;

	/** 默认最大容量 */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	/** 默认数组 */
	private static final Object[] EMPTY_ELEMENTDATA = {};

	/** 用于存放元素 */
	private Object[] elementData;

	/**
	 * 无参构造将使用默认数组
	 */
	public ArrayList() {
		elementData = EMPTY_ELEMENTDATA;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	// @Override
	// public boolean contains(Object o) {
	// // TODO Auto-generated method stub
	// return false;
	// }

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<E> {
		int cursor; // 当前索引
		int lastRet = -1;//

		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if (cursor > size) {
				throw new NoSuchElementException();
			}
			Object[] elementData = ArrayList.this.elementData;
			if (cursor >= elementData.length) {
				throw new ConcurrentModificationException();
			}
			return (E) elementData[lastRet = cursor++];
		}

		@Override
		public void remove() {
			if (lastRet < 0)
				throw new IllegalStateException();
			try {
				ArrayList.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;
				size--;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}

	}

	// @Override
	// public Object[] toArray() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public <T> T[] toArray(T[] a) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public boolean add(E element) {
		// 保证数组长度正确
		ensureCapacityInternal(size + 1);
		// 使用后缀自操作符
		elementData[size++] = element;
		return true;
	}

	@Override
	public boolean add(int index, E element) {
		rangeCheckForAdd(index);// 进行添加操作的索引范围检查
		// 保证数组长度正确
		ensureCapacityInternal(size + 1);
		// 源数组中位置在 srcPos 到 srcPos+length-1 之间的组件被分别复制到目标数组中的 destPos 到
		// destPos+length-1 位置
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = element;
		size++;
		return true;
	}

	private void ensureCapacityInternal(int minCapacity) {
		if (elementData == EMPTY_ELEMENTDATA)
			minCapacity = Math.max(minCapacity, DEFAULT_CAPACITY);
		// 传入最小容量大于当前数组长度，则扩容
		if (minCapacity > elementData.length)
			grow(minCapacity);
	}

	/**
	 * 扩容
	 * 
	 * @param minCapacity
	 */
	private void grow(int minCapacity) {
		int oldCapacity = elementData.length;// 获取原数组长度
		// 计算新容量
		int newCapacity = oldCapacity + (oldCapacity >> 1);// 原容量+(原容量/2)，使用位移符提高运行速度
		newCapacity = newCapacity < minCapacity ? minCapacity : newCapacity;
		if (newCapacity > MAX_ARRAY_SIZE)
			newCapacity = hugeCapacity(newCapacity);
		// 将原数组数据复制到一个长度为newCapacity的新数组中
		elementData = Arrays.copyOf(elementData, newCapacity);
	}

	/**
	 * 传入容量是否大于最大容量常量，如大于最大容量，则返回int类型所能表示的最大值 ArrayList最大容量为int类型所能表示的最大值
	 * 
	 * @param minCapacity
	 * @return
	 */
	private int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) {
			throw new OutOfMemoryError("The index cannot be negative");
		}
		return minCapacity > MAX_ARRAY_SIZE ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		rangeCheck(index);// 进行索引的范围检查
		return (E) elementData[index];
	}

	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E element) {
		rangeCheck(index);// 进行索引的范围检查
		// 取到指定索引元素，将新元素置入该索引位，并返回原元素
		E oldValue = (E) elementData[index];
		elementData[index] = element;
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		rangeCheck(index);// 进行索引的范围检查
		// 获取指定索引处元素
		E rmValue = (E) elementData[index];
		// 源数组中位置在 srcPos 到 srcPos+length-1 之间的组件被分别复制到目标数组中的 destPos 到
		// destPos+length-1 位置
		System.arraycopy(elementData, index + 1, elementData, index, size - (index - 1));
		size--;
		return rmValue;
	}

	// @Override
	// public int indexOf(Object o) {
	// // TODO Auto-generated method stub
	// return 0;
	// }

	/**
	 * 添加时的索引范围检查
	 * 
	 * @param index
	 */
	private void rangeCheckForAdd(int index) {
		if (index > this.size || index < 0)// 添加可以往末位插入，所以这里索引等于元素个数也可以
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	/**
	 * 索引范围检查
	 * 
	 * @param index
	 */
	private void rangeCheck(int index) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	/**
	 * 以字符串形式返回索引和元素个数信息
	 * 
	 * @param index
	 * @return
	 */
	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + this.size;
	}

}
