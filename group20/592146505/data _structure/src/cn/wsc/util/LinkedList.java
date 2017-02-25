package cn.wsc.util;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * LinkedList类
 *
 * @author Administrator
 * @date 2017年2月25日上午10:52:41
 * @version v1.0
 *
 * @param <E>
 */
public class LinkedList<E> implements List<E> {

	private int size;
	Node<E> first; // 链表的头节点
	Node<E> last; // 链表的尾节点

	private static class Node<E> {
		E item; // 存储数据
		Node<E> prev; // 上一个节点
		Node<E> next; // 下一个节点

		Node(Node<E> prev, E element, Node<E> next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<E> {
		int cursor; // 当前索引
		int lastRet = -1;// 上一次索引

		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@Override
		public E next() {
			if (cursor > size) {
				throw new NoSuchElementException();
			}
			return get(lastRet = cursor++);
		}

		@Override
		public void remove() {
			if (lastRet < 0)
				throw new IllegalStateException();
			try {
				LinkedList.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;
				size--;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}

	}

	@Override
	public boolean add(E e) {
		linkLast(e);
		return true;
	}

	@Override
	public boolean add(int index, E element) {
		checkPositionIndex(index);// 位置范围检查
		// 如果索引等于元素个数，则直接插入尾部
		if (index == size) {
			linkLast(element);
		} else {
			linkBefore(element, node(index));
		}
		return true;
	}

	@Override
	public E get(int index) {
		return node(index).item;
	}

	/**
	 * 维护头节点
	 * 
	 * @param e
	 */
	void linkFirst(E e) {
		Node<E> f = first;
		// 创建新节点，将原头节点作为新节点的下一个节点
		Node<E> newNode = new Node<E>(null, e, f);
		// 将新节点设置为头节点
		first = newNode;
		// 如原头节点为null，则尾节点也为newNode
		if (f == null) {
			last = newNode;
		} else {// 否则将新节点作为原头节点的上一个节点
			f.prev = newNode;
		}
		size++;

	}

	/**
	 * 维护尾节点
	 * 
	 * @param e
	 */
	void linkLast(E e) {
		Node<E> l = last;
		// 创建新节点，将尾节点作为新节点的上一个节点
		Node<E> newNode = new Node<E>(l, e, null);
		// 将新节点设置为尾节点
		last = newNode;
		// 如原尾节点为null，则头节点也为newNode
		if (l == null) {
			first = newNode;
		} else {// 否则将新节点作为原尾节点的下一个节点
			l.next = newNode;
		}
		size++;
	}

	/**
	 * 在指定节点前插入新节点
	 * 
	 * @param e
	 * @param node
	 */
	void linkBefore(E e, Node<E> node) {
		// 获取node的上一个节点，并创建新节点，将pred做为新节点的上一个节点，将node作为新节点的下一个节点
		final Node<E> pred = node.prev;
		final Node<E> newNode = new Node<>(pred, e, node);
		// 将node的上一个节点指向newNode
		node.prev = newNode;
		// 如prev为null，则说明node为first，那么将新节点设为first
		if (pred == null) {
			first = newNode;
		} else {// 否则，将新节点设为pred的下一个节点
			pred.next = newNode;
		}
		size++;
	}

	/**
	 * 获取节点
	 * 
	 * @param index
	 * @return
	 */
	Node<E> node(int index) {
		// 索引小于长度的2分之一则从前向后遍历，否则从后向前遍历，减少遍历次数
		if (index < (size >> 1)) {
			Node<E> x = first;
			for (int i = 0; i < index; i++)
				x = x.next;
			return x;
		} else {
			Node<E> x = last;
			for (int i = size - 1; i > index; i--)
				x = x.prev;
			return x;
		}
	}

	/**
	 * 获取头节点
	 * 
	 * @return
	 */
	public E getFirst() {
		Node<E> f = first;
		if (f == null)
			throw new NoSuchElementException();
		return f.item;
	}

	/**
	 * 获取尾节点
	 * 
	 * @return
	 */
	public E getLast() {
		Node<E> l = last;
		if (l == null)
			throw new NoSuchElementException();
		return l.item;
	}

	@Override
	public E set(int index, E e) {
		checkElementIndex(index);// 索引范围检查
		// 获取索引处节点，填入新值，返回原值
		Node<E> x = node(index);
		E oldVal = x.item;
		x.item = e;
		return oldVal;
	}

	@Override
	public E remove(int index) {
		checkElementIndex(index);// 索引范围检查
		return unlink(node(index));
	}

	/**
	 * 删除节点
	 * 
	 * @param x
	 * @return
	 */
	E unlink(Node<E> x) {
		// 获取此节点的上一个节点和下一个节点
		E element = x.item;
		Node<E> prev = x.prev;
		Node<E> next = x.next;
		// 如prev节点为null，则说明x节点为first，那么将next节点设为first
		if (prev == null) {
			first = next;
		} else {// 否则，将prev节点的下一个节点设为next
			prev.next = next;
		}
		// 如next节点为null，则说明x节点为last，那么将prev节点设为last
		if (next == null) {
			last = prev;
		} else {// 否则，将next节点的上一个节点设为prev
			next.prev = prev;
		}
		x.item = null;
		size--;
		return element;
	}

	/**
	 * 位置范围检查 >0 && <=size
	 * 
	 * @param index
	 */
	private void checkPositionIndex(int index) {
		if (index > this.size || index < 0)// 添加可以往末位插入，所以这里索引等于元素个数也可以
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	/**
	 * 索引范围检查 >0 && <size
	 * 
	 * @param index
	 */
	private void checkElementIndex(int index) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	/**
	 * 以字符串形式返回索引和元素个数信息
	 * 
	 * @param index
	 * @return
	 */
	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + this.size;
	}
}
