package org.wsc.coding.basic.list;

/**
 *
 * 队列
 * 
 * @author Administrator
 * @date 2017年2月25日下午6:08:01
 * @version v1.0
 *
 * @param <E>
 */
public interface Queue<E> {

	/**
	 * 入列
	 * 
	 * @param e
	 */
	public void enQueue(E e);

	/**
	 * 出列
	 * 
	 * @return
	 */
	public E deQueue();

	/**
	 * 是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * 元素长度
	 * 
	 * @return
	 */
	public int size();
}
