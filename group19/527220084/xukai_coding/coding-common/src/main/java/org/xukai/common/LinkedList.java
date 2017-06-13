package org.xukai.common;

import org.junit.Test;


public class LinkedList implements List {
	
	private Node head;

	private int size = 0;
	
	public void add(Object o){
		Node node = new Node();
		node.data = o;
		if (head == null) {
			head = node;
		} else {
			Node next = head.next;
			if (next == null) {
				head.next = node;
			} else {
				while (next.next != null){
					next = next.next;
				}
				next.next = node;
			}
		}
		size++;
	}

	public void add(int index , Object o){
		if (index < 0 || index > size ) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			size++;
			Node node = new Node();
			node.data = o;
			if (index == 0) {
				node.next = head;
				head = node;
				return;
			}
			int pos = 1;
			Node next = head;
			//index 2
			while(index > pos){
				next = next.next;
				pos++;
			}
			node.next = next.next;
			next.next = node;
		}
	}
	public Object get(int index){
		if (index < 0 || index > size ) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			if (index == 0) {
				return head.data;
			}
			int pos = 0;
			Node next = head;
			//index 2
			while(index > pos){
				next = next.next;
				pos++;
			}
			return next.data;
		}
	}

	public Object remove(int index){
		if (index < 0 || index > size - 1 ) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			if (index == 0) {
				Node result = head;
				head = head.next;
				return result.data;
			}
			int pos = 1;
			Node next = head;
			//index 1
			while(index > pos){
				next = next.next;
				pos++;
			}
			Node result = next.next;
			next.next = next.next.next;
			size--;
			return result.data;
		}
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node();
		node.data = o;
		node.next = head;
		head = node;
		size++;
	}

	public void addLast(Object o){
		add(o);
	}

	public Object removeFirst(){
		Node result = head;
		head = head.next;
		size--;
		return result.data;
	}

	public Object removeLast(){
		return remove(size-1);
	}

	public Iterator iterator(){
		return new LinkedListIterator();
	}

	class LinkedListIterator implements Iterator {

		private Node currentNode ;

		@Override
		public boolean hasNext() {
			if (currentNode == null) {
				if (head != null) {
					return true;
				} else {
					return false;
				}
			}
			return currentNode.next != null;
		}

		@Override
		public Object next() {
			if (currentNode == null) {
				currentNode = head;
				return currentNode.data;
			}
			currentNode = currentNode.next;
			return currentNode.data;
		}
	}

	public void display(){
		System.out.print("[");
		Iterator iterator = iterator();
		while (iterator.hasNext()){
			System.out.print(" " + iterator.next());
		}
		System.out.print(" ]");
	}

	private static  class Node{
		Object data;
		Node next;
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 * 7->3  10->11
	 */
	public  void reverse(){
		if (head == null || head.next == null) {
			return;
		}
		Node temp0 = head;
		Node temp1 = temp0.next;
		temp0.next = null;
		while (temp1.next != null){
			Node temp2 = temp1.next;
			temp1.next = temp0;
			temp0 = temp1;
			temp1 = temp2;
		}
		temp1.next = temp0;
		head = temp1;
	}
	@Test
	public void reverseTest(){
		LinkedList list = new LinkedList();
		list.add(3);
		list.add(7);
		list.add(10);
		list.add(12);
		list.add(15);
		list.display();
		list.reverse();
		list.display();
	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if (head == null || head.next == null) {
			return;
		}
		int count = size % 2 == 0 ? size/2:(size -1) / 2;
		Node temp = null;
		for (int i = 0; i < count; i++) {
			temp = head.next;
			head = temp;
		}

	}

	@Test
	public void removeFirstHalfTest(){
		LinkedList list = new LinkedList();
		list.add(3);
		list.add(7);
		list.add(10);
		list.add(12);
		list.add(15);
		list.display();
		list.removeFirstHalf();
		list.display();
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if (i < 0 || i > size - 1 || i+length > size-1 || length < 1) {
			throw new NegativeArraySizeException();
		}
		if (i == 0) {
			Node tail = head;
			for (int j = 0; j < length -1 ; j++) {
				tail = tail.next;
			}
			head = tail.next;
			return;
		}
		Node next = head;
		for (int j = 0; j < i-1; j++) {
			next = next.next;
		}
		Node tail = next;
		for (int j = 0; j < length; j++) {
			tail = tail.next;
		}
		next.next = tail.next;

	}

	@Test
	public void removeTest(){
		LinkedList list = new LinkedList();
		list.add(3);
		list.add(7);
		list.add(10);
		list.add(12);
		list.add(15);
		list.display();
		list.remove(0,2);
		list.display();
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		Iterator it = list.iterator();
		int count = 0;
		int[] result = new int[list.size()];
		while (it.hasNext()){
			int index = (int)it.next();
			if (index > size - 1) {
				throw new ArrayIndexOutOfBoundsException();
			}
			int o = (int)get(index);
			result[count] = o;
			count++;

		}


		return result;

	}

	@Test
	public void getElementsTest(){
		LinkedList list = new LinkedList();
		list.add(3);
		list.add(7);
		list.add(10);
		list.add(12);
		list.add(15);
		list.display();
		LinkedList list2 = new LinkedList();
		list2.add(0);
		list2.add(4);
		int[] elements = list.getElements(list2);
		list.display();
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素

	 * @param list
	 */

	public void subtract(LinkedList list){
		Iterator it = list.iterator();
		while (it.hasNext()){
			Object element = it.next();
			remove(element);
		}
	}
	public Object remove(Object obj){
		Node next = head;
		Node pre = null;
		while(next != null){
			if (next.data == obj) {
				pre.next = next.next;
				return next;
			}
			pre = next;
			next = next.next;
		}
		return null;

	}

	@Test
	public void subtractTest(){
		LinkedList list = new LinkedList();
		list.add(3);
		list.add(7);
		list.add(10);
		list.add(12);
		list.add(15);
		list.display();
		LinkedList list2 = new LinkedList();
		list2.add(7);
		list2.add(10);
		list.subtract(list2);
		list.display();
	}
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node next = head;
		while(next != null && next.next != null){
			if (next.data == next.next.data) {
				next.next = next.next.next;
			}
			next = next.next;
		}
	}

	@Test
	public void removeDuplicateValuesTest(){
		LinkedList list = new LinkedList();
		list.add(3);
		list.add(7);
		list.add(7);
		list.add(12);
		list.add(12);
		list.display();
		list.removeDuplicateValues();
		list.display();
	}
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		Node next = head;
		Node start = null;
		Node end = null;
		if (next == null || (int)next.data >= max) {
			return;
		}
		while(next != null && (int)next.data < max){
			if ((int)next.data < min) {
				start = next;
			}
			next = next.next;
		}
		end = next;
		if (start == null) {
			head = end;
		} else {
			start.next = end;
		}
	}

	@Test
	public void removeRangeTest(){
		LinkedList list = new LinkedList();
		list.add(3);
		list.add(7);
		list.add(10);
		list.add(12);
		list.add(15);
		list.display();
		list.removeRange(5,19);
		list.display();
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList linkedList = new LinkedList();
		int i = 0;
		Node next1  = head;
		Node next2  = list.head;
		while (next1 != null || next2 != null ){
			if (next2 != null && next1 != null) {
				if ((int)next1.data < (int)next2.data ) {
					linkedList.add(next1.data);
					next1 = next1.next;
				} else if(next1 == null ||(int)next2.data < (int)next1.data) {
					linkedList.add(next2.data);
					next2 = next2.next;
				} else {
					linkedList.add(next2.data);
					next1 = next1.next;
					next2 = next2.next;
				}
			}else {
				if (next2 == null) {
					linkedList.add(next1.data);
					next1 = next1.next;
				} else {
					linkedList.add(next2.data);
					next2 = next2.next;
				}
			}


		}

		return linkedList;
	}

	@Test
	public void intersectionTest(){
		LinkedList list1 = new LinkedList();
		list1.add(3);
		list1.add(7);
		list1.add(10);
		list1.add(12);
		list1.add(15);
		LinkedList list2 = new LinkedList();
		list2.add(2);
		list2.add(7);
		list2.add(9);
		list2.add(15);
		list2.add(18);
		LinkedList list = list1.intersection(list2);
		list.display();
	}
}
