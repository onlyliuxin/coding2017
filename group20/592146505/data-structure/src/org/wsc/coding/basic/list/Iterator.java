package org.wsc.coding.basic.list;

public interface Iterator<E> {
	/**
	 * 是否存在下一个元素
	 * @return
	 */
	boolean hasNext();

	/**
	 * 获取下一个元素
	 * @return
	 */
	E next();

	/**
	 * 删除当前元素
	 */
	void remove();

}
