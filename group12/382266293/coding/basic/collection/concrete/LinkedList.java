package collection.concrete;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static util.TestUtil.*;
import static util.Print.*;
import collection.AbstractList;
import collection.Iterator;

public class LinkedList<E> extends AbstractList<E> {

	private Node head;
	private int size;

	public LinkedList() {
		this.head = new Node<E>(null);
		this.size = 0;
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */

	@SuppressWarnings("unchecked")
	public void reverse() {
		if (head == null) {
			return;
		}
		Node<E> pre = head;
		Node<E> cur = head.next;
		Node<E> next;
		while (cur != null) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		head.next = null;
		head = pre;

	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 * 
	 */
	public void removeFirstHalf() {
		int deleteLength = size / 2;
		remove(0, deleteLength);
	}

	private void clearAndSetNewHead(int deleteIndex) {
		Node<E> x = head;
		for (int i = 0; i < deleteIndex; i++) {
			Node<E> next = x.next;
			x.data = null;
			x.next = null;
			x = next;
			size--;
			if (i == deleteIndex - 1)
				head = next;
		}
	}

	private void clearAndSetNewHead(Node node, int deleteLength) {
		Node<E> x = node;
		for (int i = 0; i < deleteLength; i++) {
			Node<E> next = x.next;
			x.data = null;
			x.next = null;
			x = next;
			size--;
			if (i == deleteLength - 1)
				node = next;
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		checkIndex(i);
		checkIndex(i + length);

		if (i == 0) {
			clearAndSetNewHead(length);
			return;
		}
		Node<E> pre = getNode(i - 1);
		Node<E> x = pre.next;
		checkIndex(length + i);
		for (int j = 0; j < length; j++) {
			Node<E> next = x.next;
			x.data = null;
			x.next = null;
			x = next;
			size--;
			if (i == length - 1)
				pre.next = next;
		}
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList<Integer> list) {

		if (size() == 0 || list.size() == 0) {
			return new int[0];
		}

		Iterator it2 = list.iterator();
		int size = list.size();
		int[] result = new int[size];
		int curr = (int) it2.next();
		Node<E> start = getNode(curr);
		result[0] = (int) start.data;
		int next, batch;
		int res = -1;
		for (int i = 1; i < size; i++) {
			next = (int) it2.next();
			batch = next - curr;
			Node<E> p = start;
			for (int j = 0; j < batch; j++) {
				p = p.next;
			}
			result[i] = (int) p.data;
			start = p;
			curr = next;
		}
		return result;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */
	
	public void subtract2(LinkedList<E> list) {
		LinkedList<E> result = new LinkedList<>();
		
		if (list.size == 0 || this.size == 0) {
			return;
		}
		
		Node<E> n1 = this.head;
		Node<E> n2 = list.head;
		Node<E> curr_list = list.head;
		for (int i = 0; i < this.size; i++, n1 = n1.next) {
			boolean equals = false;
			curr_list = list.head;
			while(curr_list != null && !equals) {
				if (Objects.equals(n1.data, curr_list.data)) {
					equals = true;
				}
				curr_list = curr_list.next;
			}
			
			if (!equals) {
				result.add(n1.data);
			}
			
		}

		clearAndSetNewHead(this.size);
		Node<E> p2 = result.head;
		while(p2.next != null) {
			add(p2.data);
			p2 = p2.next;
		}
		add(p2.data);
		this.size = result.size;

	}

	
	

	public void subtract(LinkedList<E> list) {

		Node<E> n1 = list.getNode(0);
		Node<E> n2 = head;
		int count = list.size();
		int index = 0;
		int iR = 0;
		while (count != 0 && n1 != null && n2 != null) {

			while (count > 1 && Objects.equals(n1.data, n1.next.data)) {
				n1 = n1.next;
				count--;
			}

			while (Objects.equals(n1.data, n2.data) == false) {
				index++;
				if (index > size() - 1)
					return;
				n2 = n2.next;
			}
			iR = index;

			while (n2 != null && Objects.equals(n1.data, n2.data)) {
				remove(iR);
				count--;
				n2 = n2.next;
			}
			index = iR;
			n1 = n1.next;
		}

	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		Node<E> n1 = head;
		int count = size();
		int index = 0;
		int iR = 0;
		while (count > 1 && n1 != null) {
			while (count > 1 && Objects.equals(n1.data, n1.next.data) == false) {
				n1 = n1.next;
				index++;
				count--;
			}
			iR = index;

			while (count > 1 && Objects.equals(n1.data, n1.next.data)) {
				remove(iR);
				n1 = n1.next;
				count--;
			}
			index = iR;
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException(min + " is greater than " + max);
		}

		if (size() == 0) {
			return;
		}

		Node<E> curr = head;
		if ((int) head.data > min) {
			curr = curr.next;
		}

		Node<E> p = head;
		while ((int) curr.data <= min) {
			p = curr;
			curr = curr.next;
		}

		Node<E> next = curr;
		for (Node<E> x = curr; (int) x.data < max;) {
			next = x.next;
			x.data = null;
			x.next = null;
			x = next;
			size--;
		}

		if ((int) p.data > min) {
			head = next;
			size--;
		} else {
			p.next = next;
		}
	}

	public static void main(String args[]) {

	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */

	public LinkedList<E> intersection(LinkedList<E> list) {

		LinkedList<E> result = new LinkedList<E>();
		Node<E> n1 = list.getNode(0);
		Node<E> n2 = head;

		int count = list.size();
		int index = 0;
		int iR = 0;
		while (count != 0 && n1 != null && n2 != null) {

			while (Objects.equals(n1.data, n2.data) == false) {
				index++;
				if (index > size() - 1)
					return result;
				n2 = n2.next;
			}
			iR = index;

			if (n2 != null && Objects.equals(n1.data, n2.data)) {
				result.add(n1.data);
				count--;
				n2 = n2.next;
			}
			index = iR;
			n1 = n1.next;
		}

		return result;

	}

	public void removeDuplicateValues2() {
		Node<E> n1 = head;
		int count = size();
		int index = 0;
		int iR = 0;
		while (count > 1 && n1 != null) {
			while (count > 1 && Objects.equals(n1.data, n1.next.data) == false) {
				n1 = n1.next;
				index++;
				count--;
			}
			iR = index;

			while (count > 1 && Objects.equals(n1.data, n1.next.data)) {
				Node<E> next = n1.next.next;
				n1.next.data = null;
				n1.next = null;
				n1 = n1.next;
				n1.next = next;
				count--;
			}
			index = iR;

		}
	}

	@Override
	public void add(E e) {
		addLast(e);
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		return getNode(index).data;
	}

	public E getFirst() {
		return get(0);
	}

	public E getLast() {
		return get(size - 1);
	}

	public void add(int index, E e) {
		if (index == size) {
			addLast(e);
			return;
		}

		if (index == 0) {
			addFirst(e);
			return;
		}

		checkIndex(index);
		Node<E> pNode = new Node<E>(e);
		Node<E> p = getNode(index);
		synchronized (this) {
			getNode(index - 1).next = pNode;
			pNode.next = p;
			size++;
		}
	}

	public void addFirst(E e) {
		checkCapacity();
		Node<E> pNode = new Node<E>(e);
		Node oldHead = head;
		head = pNode;
		pNode.next = oldHead;
		size++;
		return;
	}

	public void addLast(E e) {
		if (size == 0) {
			addFirst(e);
			return;
		}

		checkCapacity();
		Node res = new Node<E>(e);
		setLastNode(res);
		size++;
		return;
	}

	public E removeFirst() {
		return remove(0);
	}

	public E removeLast() {
		return remove(size - 1);
	}

	@SuppressWarnings("unchecked")
	public E remove(int index) {
		checkIndex(index);
		Node<E> pNode = null;
		E data = null;
		if (index == 0) {
			data = (E) head.data;
			head = head.next;
		} else if (index == size - 1) {
			pNode = getNode(index - 1);
			data = (E) pNode.next.data;
			pNode.next = null;
		} else {
			pNode = head;
			for (int i = 0; i < index - 1; i++) {
				pNode = pNode.next;
			}
			data = (E) pNode.next.data;
			pNode.next = pNode.next.next;
		}
		size--;
		return data;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator<E>(this);
	}

	private void checkCapacity() {
		if (size > MAX_SIZE)
			throw new IndexOutOfBoundsException("Reached max capacity: " + MAX_SIZE);
	}

	private Node<E> getNode(int index) {
		if (size == 0)
			return head;

		Node pNode = head;
		for (int i = 0; i < index; i++) {
			pNode = pNode.next;
		}
		return pNode;
	}

	private void setLastNode(Node res) {
		getNode(size - 1).next = res;
	}

	private static class Node<E> {
		E data;
		Node next;

		public Node(E data) {
			this.data = data;
			this.next = null;
		}

		@Override
		public String toString() {
			return data.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (data == null) {
				if (other.data != null)
					return false;
			} else if (!data.equals(other.data))
				return false;
			return true;
		}
	}

	@SuppressWarnings("hiding")
	private class LinkedListIterator<E> implements Iterator<E> {

		private LinkedList<E> myLinkedList;
		private int pos;

		public LinkedListIterator(LinkedList<E> linkedList) {
			myLinkedList = linkedList;
			pos = 0;
		}

		@Override
		public boolean hasNext() {
			return pos < size;
		}

		@Override
		public E next() {
			if (hasNext())
				return (E) get(pos++);
			throw new NoSuchElementException();
		}
	}
}
