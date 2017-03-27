package com.sprint.basic;

import com.sprint.basic.exception.ConcurrentModificationException;
import java.util.Objects;
public class LinkedList implements List {
	
	private Node head;
	private int size;
	public LinkedList() {
		head = new Node(null, null);	
		size = 0;
	}

	public void add(Object o){
		add(size, o);		
	}

	public void add(int index , Object o){
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
		Node frontNode = getNode(index-1);
		Node newNode = new Node(o, frontNode.next);		
		frontNode.next = newNode;
		size++;

	}

	/*getNode getPreNodeByElement getNextNodeByElement的效率低些*/
	private Node getNode(int index) {
		Node node = head;
		int i = 0;
		while(node.next != null && i <= index) {
			node = node.next;
			i++;
		}
		return node;
	}

	private Node getPreNodeByElement(Object obj) {
		if (obj != null) {
			for (int i = 0; i < size(); i++) {
				if (getNode(i).data == obj) {
					return getNode(i-1);
				}
			}		
		}
		return null;
	}

	private Node getNextNodeByElement(Object obj) {
		if (obj != null) {
			for (int i = 0; i < size(); i++) {
				if (getNode(i).data == obj) {
					return getNode(i+1); 
				}
			}	
		}
		return null;
	}
	public Object get(int index){
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}

		Node node = getNode(index);	
		return node.data;
	}

	public Object remove(int index){
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
		Node frontNode = getNode(index-1);
		Node oldNode = getNode(index);
		frontNode.next = oldNode.next;
		size--;
		return oldNode.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(0, o);
	}

	public void addLast(Object o){
		add(size, o);
	}

	public Object removeFirst(){
		return remove(0);
	}

	public Object removeLast(){
		return remove(size-1);
	}

	public Iterator iterator(){
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator {
		int index;
		final int capacity = size;
		LinkedListIterator() {
			index = 0;
		}
		@Override
		public boolean hasNext() {
			if (capacity != size)
				throw new ConcurrentModificationException("此对象没有修改同步");
			return index < capacity;	
		}

		@Override 
		public Object next() {
			if (capacity != size)
				throw new ConcurrentModificationException("此对象没有修改同步");
			if (index >= capacity)
				throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
			return get(index++);	
		}
	}

	private String outOfBoundsMsg(int index) {
		return "index:" + index + ", size:" + size;
	}	

	private static  class Node {
		Object data;
		Node next;
		
		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}

		void setData(Object data) {
			this.data = data;	
		}

		Object getData() {
			return data; 
		}

		void setNext(Node next) {
			this.next = next;
		}

		Object getNext() {
			return next;
		}
	}


	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){	
		Object[] oldData = new Object[size];
		Node temp = head;
		int index = 1;
		while(temp.next != null) {
			temp = temp.next;
			oldData[size - index] = temp.data;
			index++;
		}

		index = 0;
		temp = head;
		while(temp.next != null) {
			temp = temp.next;
			temp.data = oldData[index];
			index++;
		}
	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */

	public  void removeFirstHalf(){
		int count = size;
		if (count % 2 != 0) {
			for (int i = 0; i <= count/2; i++) {
				removeFirst();
			}
		} else {
			for (int i = 0; i < count/2; i++) {
				removeFirst();
			}
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */

	public  void remove(int i, int length){
		if (i < 0 || length < 0) {
			return;
		}
		if (i == 0) {
			for (int k = 0; k < length; k++)
				removeFirst();
		} else {
			while (length > 0) {
				remove(i-1);
				length--;
			}
		}
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */

	public int[] getElements(LinkedList list){
		if (list.size() == 0) {
			return new int[0];
		}
		int[] array = new int[list.size()];	
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			array[i] = (int)get((int)list.get(i));
		}
		return array;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	public  void subtract(LinkedList list){
		if (list.size() == 0) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			removeElement(list.get(i));
		}
	}

	private Object removeElement(Object obj) {
		if (obj == null) {
			return null;
		}	
		Node preNode = getPreNodeByElement(obj);
		Node nextNode = getNextNodeByElement(obj);
		if (preNode == null && nextNode == null)
			return null;
		preNode.next = nextNode;
		return obj;
	}


	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */

	public  void removeDuplicateValues(){
		if (size == 0 || size == 1) {
			return;
		}

		Node p1 = head;
		Node p2 = head.next;

		while (p1 != null && p2 != null) {
			if (Objects.equals(p1.data, p2.data)) {
				p2 = p2.next;
				p1.next = p2;
				size--;
			} else {
				p1 = p2;
				p2 = p2.next;
			}
		}	
	}

	

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */

	public  void removeRange(int min, int max){
	}


	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */

	public  LinkedList intersection( LinkedList list){

		return null;

	}
}
