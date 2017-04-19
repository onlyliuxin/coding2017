package com.coding.basic;

public interface MyList {
	
	/**
	 * 向集合中添加元素
	 * @param o 任意类型元素
	 */
	public void add(Object o);
	/**
	 * 向集合中添加元素
	 * @param index 指定位置下标
	 * @param o 任意类型元素
	 */
	public void add(int index, Object o);
	/**
	 * 获取对应下标的元素
	 * @param index 下标
	 * @return 对应下标的元素
	 */
	public Object get(int index);
	/**
	 * 移除对应下标的元素
	 * @param index 下标
	 * @return 
	 */
	public Object remove(int index);
	/**
	 * 获取集合的大小
	 * @return 大小数值
	 */
	public int size();
	
}
