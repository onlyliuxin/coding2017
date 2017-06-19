package edu.coerscnu.basic.list;

import edu.coerscnu.basic.Iterator;

public interface MyList<E> {

	/**
	 * 在末尾添加元素
	 * 
	 * @param e
	 * @return
	 */
	public boolean add(E e);

	/**
	 * 在指定位置添加元素
	 * 
	 * @param index
	 * @param e
	 * @return
	 */
	public boolean add(int index, E e);

	/**
	 * 在指定位置设置元素
	 * 
	 * @param index
	 * @param e
	 * @return
	 */
	public E set(int index, E e);

	/**
	 * 获取指定位置的元素
	 * 
	 * @param index
	 * @return
	 */
	public E get(int index);

	/**
	 * 删除指定位置的元素
	 * 
	 * @param index
	 * @return
	 */
	public E remove(int index);

	/**
	 * 删除整个列表
	 * 
	 * @return
	 */
	public boolean clear();

	/**
	 * 判断列表是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * 返回列表元素个数
	 * 
	 * @return
	 */
	public int size();

	/**
	 * 获得列表的迭代器
	 * 
	 * @return
	 */
	public Iterator<E> iterator();
}
