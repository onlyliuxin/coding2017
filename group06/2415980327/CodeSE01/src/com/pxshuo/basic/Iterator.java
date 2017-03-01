package com.pxshuo.basic;

public interface Iterator {
	/**
	 * 查看是否有下一个元素
	 * @return 有的话返回true，否则返回false
	 */
	public boolean hasNext();
	/**
	 * 获得下一个元素，也就是当前元素
	 * @return 获取当前位置的元素
	 */
	public Object next();

}
