package edu.coerscnu.basic.queue;

import edu.coerscnu.basic.Iterator;

/**
 * 链式队列
 * 
 * @author xujie
 *
 * @param <E>
 */
public class MyQueue<E> {

	private static class Node<E> {

		private Node<E> next; // 后置节点

		private E ele; // 节点数据

		public Node(E ele, Node<E> next) {
			this.ele = ele;
			this.next = next;
		}
	}

	private int size;
	
	private Node<E> front;
	
	private Node<E> rear;
	
	public MyQueue() {
		
	}
	
	/**
	 * 入队
	 * @param e
	 * @return
	 */
	public boolean enQueue(E e) {
		if (front == null) {
			front = new Node<E>(e, null);
			rear = front;
		} else {
			Node<E> newNode = new Node<E>(e, null);
			rear.next = newNode;
			rear = newNode;
		}
		size++;
		return true;
	}
	
	/**
	 * 出队
	 * 
	 * @return
	 */
	public E deQueue() {
		if (isEmpty())
			throw new IndexOutOfBoundsException("空队列异常");
		Node<E> oldFront = front;
		E ele = oldFront.ele;
		front = front.next;
		oldFront.ele = null;
		oldFront.next = null;
		size--;
		return ele;
	}
	
	public E element() {
		if (isEmpty())
			throw new IndexOutOfBoundsException("空队列异常");
		return front.ele;
	}
	
	public boolean clear() {
		front = null;
		rear = null;
		size = 0;
		return true;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public Iterator<E> iterator() {
		return new MyQueueIterator();
	}
	
	private class MyQueueIterator implements Iterator<E> {

		private Node<E> current = front;
		
		@Override
		public boolean hasNext() {
			return current != rear.next;
		}

		@Override
		public Object next() {
			E ele = current.ele;
			current = current.next;
			return ele;
		}
	}
}
