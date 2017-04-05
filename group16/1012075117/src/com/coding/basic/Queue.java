package com.coding.basic;

/**
 * 实现 Queue - 第一次作业
 * @author stackwei
 * @date 2017/2/25
 * @status ok
 */
public class Queue {

	private LinkedList ll = new LinkedList();
	
	/**
	 * 在队尾添加数据
	 * @param element
	 */
	public void enQueue(Object element) {
		ll.addLast(element);
	}
	
	/**
	 * 删除队头数据
	 * @return
	 */
	public Object deQueue() {
		return ll.removeFirst();
	}
	
	/**
	 * 队列是否为空
	 * @return
	 */
	public boolean isEmpty() {
		if (ll.size() > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 测试用例
	 * @param args
	 */
	public static void main(String[] args) {
		Queue q = new Queue();
		q.enQueue(97);
		q.enQueue(98);
		q.enQueue(99);
		System.out.println(q.isEmpty());
		System.out.println(q.deQueue());
	}

}
