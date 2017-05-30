package com.xiaol.study;

import java.util.LinkedList;

/**
 * @Description TODO
 * @date 创建时间：2017年3月5日 上午12:51:34
 */
public class Queue {

	// 队列
	private LinkedList elementData = new LinkedList();
	// 队列长度
	private int size;

	// 添加元素到队列尾
	public void enQueue(Object o) {
		elementData.add(o);
		size++;
	}

	// 移除队列头的元素
	public Object deQueue() {
		Object data = elementData.removeLast();
		size--;
		return data;
	}

	// 队列是否为空
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	// 队列长度
	public int size() {
		return size;
	}
}
