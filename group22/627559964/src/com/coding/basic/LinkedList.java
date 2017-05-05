package com.coding.basic;

/**
 * 自定义LinkList
 * 
 * @author xiongrui233
 *
 */
public class LinkedList implements List {

	/**
	 * 定义链表节点结构
	 * 
	 * @author xiongrui233
	 *
	 */
	private static class Node {
		Object data;
		Node next;
	}

	// 链表节点
	private Node head = new Node();

	private int size = 0;
	
	public LinkedList() {
		head.next = head;
	}

	/**
	 * 添加元素
	 * 
	 * @param o
	 */
	public void add(Object o) {
		addLast(o);
	}

	/**
	 * 添加元素
	 * 
	 * @param index
	 * @param o
	 */
	public void add(int index, Object o) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		Node node = head;
		Node temp = new Node();
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		temp.data = o;
		if (index == size -1) {
			node.next = temp;
			temp.next = head;
		} else {
			temp.next = node.next;
			node.next = temp;
		}
		size ++;
	}

	/**
	 * 获取元素
	 * 
	 * @param index
	 */
	public Object get(int index) {
		Node node = head;
		for (int i = 0; i <= index; i++) {
			node = node.next;
		}
		return node.data;
	}

	/**
	 * 删除元素
	 * 
	 * @param index
	 */
	public Object remove(int index) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		Node node = head;
		Node temp = new Node();
		for (int i = 0; i <= index - 1; i++) {
			node = node.next;
		}
		if (index == size -1) {
			temp = node.next;
			node.next = head;
		} else {
			temp = node.next;
			node.next = node.next.next;
		}
		size --;
		return temp.data;
	}

	/**
	 * 返回LinkedList的大小
	 * 
	 * @return size
	 */
	public int size() {
		return size;
	}

	/**
	 * 在LinkedList第一的位置添加元素
	 * 
	 * @param o
	 */
	public void addFirst(Object o) {
		add(0, o);
	}

	/**
	 * 在LinkedList最后添加元素
	 * @param o
	 */
	public void addLast(Object o) {
		Node node = head;
		Node temp = new Node();
		for (int i = 0; i < size; i++) {
			node = node.next;
		}
		temp.data = o;
		node.next = temp;
		size ++;
	}

	/**
	 * 移除链表第一位元素
	 * 
	 * @return obj
	 */
	public Object removeFirst() {
		return remove(0);
	}

	/**
	 * 移除链表最后一位元素
	 * 
	 * @return obj
	 */
	public Object removeLast() {
		return remove(size - 1);
	}

	/**
	 * 实现Iterator接口
	 * 
	 * @return Iterator
	 */
	public Iterator iterator() {
		
		class IteratorImpl implements Iterator {

			private Node node = head.next;
			
			private Object temp = null;
			
			@Override
			public boolean hasNext() {
				if (node != null && node.data != null) {
					temp = node.data;
					node = node.next;
					return true;
				}
				return false;
			}

			@Override
			public Object next() {
				return temp;
			}
			
		}
		return new IteratorImpl();
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public LinkedList reverse() {
		LinkedList lis = new LinkedList();
		for (int i = this.size - 1; i >= 0; i--) {
			lis.add(this.get(i));
		}
		return lis;
	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf() {
		int mid = size/2;
		for (int i = 0; i < mid; i++) {
			remove(0);
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {
		if (i > length) {
			throw new IllegalArgumentException();
		}
		if (i < 0 || i > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		for (int j = i; j <= length; j++) {
			remove(i);
		}
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		if (this.size < (Integer)list.get(list.size - 1)) {
			throw new IllegalArgumentException();
		}
		int[] elements = new int[list.size];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = (Integer) this.get((Integer)list.get(i));
		}
		return elements;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			for (int i = 0; i < this.size; i++) {
				if (obj.equals(this.get(i))) {
					this.remove(i);
				}
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {
		for (int i = 0; i < this.size; i++) {
			if (i + 1 >= this.size) {
				return;
			}
			if (this.get(i).equals(this.get(i+1))) {
				remove(i+1);
				i--;
			}
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
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < this.size; i++) {
			if ((Integer)this.get(i) > max) {
				remove(i, this.size-1);
			}
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * TODO
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		return null;
	}

	@Override
	public String toString() {
		StringBuffer list = new StringBuffer();
		list.append("List:[");
		Node node = head.next;
		for (int i = 0; i < size; i++) {
			list.append(node.data);
			node = node.next;
			if (i != size -1) {
				list.append(", ");
			}
		}
		list.append("]");
		return list.toString();
	}
}