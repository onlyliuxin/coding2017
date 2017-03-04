/**
 * 
 */
package com.coding.basic;

/**
 * 定义了最基本的线性结构序列，在序列中元素按一定顺序存储，并且其容量是可变的。这个接口定义了序列中元素操作的添加、获取、删除及获取元素数量的方法。
 * 
 * @author patchouli
 *
 */
public interface List {

	/**
	 * 在序列的结尾添加一个元素
	 * 
	 * @param o 要添加的对象
	 */
	public void add(Object o);

	/**
	 * 把对象o添加到index的位置，其他元素依次后移
	 * 
	 * @param index
	 * @param o 要添加的对象
	 * @throws ListIndexException 
	 */
	public void add(int index, Object o) throws ListIndexException;

	/**
	 * 获取位置为index的元素，返回该元素中的对象.如果index超出范围，抛出ListIndexException异常。
	 * 
	 * @param index
	 * @return 获取的元素
	 * @throws ListIndexException 
	 */
	public Object get(int index) throws ListIndexException;

	/**
	 * 删除位置为index的元素，返回该元素中的对象.如果index超出范围，抛出ListIndexException异常。
	 * 
	 * @param index
	 * @return 删除的元素
	 * @throws ListIndexException 
	 */
	public Object remove(int index) throws ListIndexException;

	/**
	 * 获取序列中当前元素的总数
	 * 
	 * @return 当前元素的总数
	 */
	public int size();
}
