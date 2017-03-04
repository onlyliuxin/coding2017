package com;

/**
 * 链表实现队列---单向
 * 
 * @Author xuyangyang
 * @Describe
 * @date 2017年2月23日
 */
public class MyQueueLinked {

	private Node head;

	private Node tail;
	// 队列的长度
	private int size;

	/**
	 * 返回队列的长度
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月23日
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * 入队
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月23日
	 */
	public void enQueue(Object obj) {

		// 如果队列为空
		if (head == null) {
			head = new Node(obj, null);
			tail = head;
			size++;
		} else {
			Node oldNode = tail;
			Node newNode = new Node(obj, null);
			tail.next = newNode;
			tail = newNode;
			size++;
		}

	}

	/**
	 * 出队
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月23日
	 * @return
	 */
	public Object deQueue() {

		if (head == null) {
			throw new NullPointerException();
		}
		Object oldNode = head.element;
		head = head.next;
		size--;
		return oldNode;
	}

	/**
	 * 取出不删除
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月23日
	 * @return
	 */
	public Object peek() {
		if (head == null) {
			throw new NullPointerException();
		}
		return head.element;
	}

	/**
	 * 节点
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月23日
	 */
	private class Node {
		private Object element;
		private Node next;

		public Node() {

		}

		public Node(Object element, Node next) {
			this.element = element;
			this.next = next;
		}

	}

	/**
	 * 测试
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月23日
	 * @param args
	 */
	public static void main(String[] args) {
		MyQueueLinked myQueueLinked = new MyQueueLinked();
		myQueueLinked.enQueue("第1次入队");
		myQueueLinked.enQueue("第2次入队");
		myQueueLinked.enQueue("第3次入队");
		myQueueLinked.enQueue("第4次入队");
		myQueueLinked.enQueue("第5次入队");
		myQueueLinked.enQueue("第6次入队");
		System.out.println(myQueueLinked.size);

		System.out.println(myQueueLinked.deQueue());
		System.out.println(myQueueLinked.size);

		System.out.println(myQueueLinked.deQueue());
		System.out.println(myQueueLinked.size);

		System.out.println(myQueueLinked.peek());
		System.out.println(myQueueLinked.size);

	}

}
