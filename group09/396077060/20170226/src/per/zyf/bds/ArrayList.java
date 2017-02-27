/**   
* @Title: ArrayList.java 
* @Description: ArrayList的实现
* @author glorychou
* @date 2017年2月22日 下午10:41:58 
*/
package per.zyf.bds;

import java.util.Arrays;

/**
 * ArrayList的存储结构其实就是一维数组
 * 只不过在这个类中将对数组的一些操作（包括添加元素、删除元素等）封装了起来
 * @author glorychou
 *
 * @param <E>
 * @see per.zyf.bds.List<E>
 */
public class ArrayList<E> implements List<E> {
	// 默认数组大小
	private static final int DEFAULT_CAPACITY = 10;
	
	// 数组实际大小
	private int size;
	
	// 存储元素的数组
	protected Object[] elementData;
	
	// 一个用来记录初始状态的空数组实例
	private static final Object[] CAPACITY_EMPTY_ELEMENTDATA = {};
	
	/***
	 * 构造初始元素数组
	 */
	public ArrayList() {
		this.elementData = CAPACITY_EMPTY_ELEMENTDATA;
	}
	
	/***
	 * 
	* @Description: 在末尾添加元素 
	* @param e    元素
	 */
	@Override
	public boolean add(E e) {
		int minCapacity = size + 1;

		// 判断数组中是否有元素
		if (elementData == CAPACITY_EMPTY_ELEMENTDATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}
		
		// 判断是否溢出 
		if (minCapacity - elementData.length > 0)
			grow(minCapacity);
		
		// 添加元素
		elementData[size++] = e;
		
		return true;
	}
	
	/***
	 * 
	* @Description: 在索引指定位置增加元素 
	* @param index    索引
	* @param e    元素
	 */
	@Override
	public boolean add(int index, E e) {
		int minCapacity = size + 1;
		
		// 索引位置不合法抛出异常
		rangeCheck(index);
		
		// 判断数组中是否有元素
		if (elementData == CAPACITY_EMPTY_ELEMENTDATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}
		
		// 判断是否溢出 
		if (minCapacity - elementData.length > 0)
			grow(minCapacity);
		
		// 插入点后的元素后移
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		
		// 在索引处加入数据
		elementData[index] = e;
		
		return true;
	}

	/***
	 * 
	* @Description: 得到索引指定位置的元素
	* @param index    索引
	* @return E    索引指定的元素
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E get(int index) {
		// 索引位置不合法抛出异常
		rangeCheck(index);
		
		return (E) elementData[index];
	}
	
	/***
	 * 
	* @Description: 删除索引指定位置的元素 
	* @param index    索引
	* @return void
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E remove(int index) {
		// 索引位置不合法抛出异常
		rangeCheck(index);
		
		E removeElement = (E) elementData[index];
		
		// 将要移除元素后的元素前移
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		// 数组大小减一，并清除多余元素
		elementData[--size] = null;
		
		return removeElement;
	}
	
	/* 
	 * @see per.zyf.bds.List#size()
	 */
	@Override
	public int size() {
		return size;
	}
	
	
	/* 
	 * @see per.zyf.bds.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		if (elementData == CAPACITY_EMPTY_ELEMENTDATA) {
			return true;
		}
		return false;
	}
	
	/***
	 * 
	* @Description: 溢出时增长空间 
	* @param minCapacity    最小容量
	* @return void
	 */
	private void grow(int minCapacity) {
		int oldCapacity = elementData.length;
		// 容量增大一半
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
	
	/***
	 * 
	* @Description: 索引范围检查 
	* @param index    索引
	* @return void 
	 */
	private void rangeCheck(int index) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	}
}