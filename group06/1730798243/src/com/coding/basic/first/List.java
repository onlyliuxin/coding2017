package com.coding.basic.first;
/**
 * 数组的接口
 * @author zap
 *
 */

public interface List {
	/**
	 * 向数组中添加一个元素
	 * @param o 添加的元素
	 */
	public void add(Object o);
	/**
	 * 向数组中某一个位置添加一个元素
	 * @param index 添加元素的位置
	 * @param o 添加的元素
	 */
	public void add(int index,Object o);
	/**
	 * 从数组中根据位置获取一个元素
	 * @param index 想要获取的元素的位置
	 * @return 想获取的元素
	 */
	public Object get(int index);
	/**
	 * 根据位置移除数组中的一个元素
	 * @param index 被移除元素的位置
	 * @return 被移除的元素
	 */
	public Object remove(int index);
	/**
	 * 获取数组的长度
	 * @return 返回数组的长度
	 */
	public int size();
}
