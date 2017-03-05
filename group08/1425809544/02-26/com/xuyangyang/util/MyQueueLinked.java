package com;

/**
 * ����ʵ�ֶ���---����
 * 
 * @Author xuyangyang
 * @Describe
 * @date 2017��2��23��
 */
public class MyQueueLinked {

	private Node head;

	private Node tail;
	// ���еĳ���
	private int size;

	/**
	 * ���ض��еĳ���
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��23��
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * ���
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��23��
	 */
	public void enQueue(Object obj) {

		// �������Ϊ��
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
	 * ����
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��23��
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
	 * ȡ����ɾ��
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��23��
	 * @return
	 */
	public Object peek() {
		if (head == null) {
			throw new NullPointerException();
		}
		return head.element;
	}

	/**
	 * �ڵ�
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��23��
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
	 * ����
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017��2��23��
	 * @param args
	 */
	public static void main(String[] args) {
		MyQueueLinked myQueueLinked = new MyQueueLinked();
		myQueueLinked.enQueue("��1�����");
		myQueueLinked.enQueue("��2�����");
		myQueueLinked.enQueue("��3�����");
		myQueueLinked.enQueue("��4�����");
		myQueueLinked.enQueue("��5�����");
		myQueueLinked.enQueue("��6�����");
		System.out.println(myQueueLinked.size);

		System.out.println(myQueueLinked.deQueue());
		System.out.println(myQueueLinked.size);

		System.out.println(myQueueLinked.deQueue());
		System.out.println(myQueueLinked.size);

		System.out.println(myQueueLinked.peek());
		System.out.println(myQueueLinked.size);

	}

}
