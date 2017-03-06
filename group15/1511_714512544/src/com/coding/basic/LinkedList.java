package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	private Node head;  //首节点
	private int size;   //节点个数
	
	public void add(Object o){  //在链表尾部添加node
		if(head == null){
			head = new Node(o, null);
		}else {
			Node last = head;
			while(last.next != null){
				last = last.next;
			}
			last.next = new Node(o, null);
		}
		size ++;
	}

	public void add(int index , Object o){  //在指定索引处插入node
		if(index > size || index < 0) throw new RuntimeException("IndexOutOfBounds");
		if(head == null){
			head = new Node(o, null);
		}else {
			if(index == 0){  //插入位置在头部
				head = new Node(o, head);
			}else {  //后面位置插入
				Node temp = head;
				int i = 0;
				while(i != index - 1){
					temp = temp.next;
					i ++;
				}
				Node tempNext = temp.next;
				temp.next = new Node(o, tempNext);
			}
		}
		size ++;
	}

	public Object get(int index){  //取出指定节点处的元素,从0开始
		if(index > size -1 || index < 0) throw new RuntimeException("IndexOutOfBounds");
		int i = 0;
		Node temp = head;
		while(i != index){
			i ++;
			temp = temp.next;
		}
		return temp.data;
	}

	public Object remove(int index){  //删除指定索引处的节点
		if(index > size -1 || index < 0) throw new RuntimeException("IndexOutOfBounds");
		if(index == 0) {  //第一个元素或只有一个元素
			Object o = head.data;
			head = head.next;
			size --;
			return o;
		}else {  //其他元素
			int i = 0;
			Node temp = head;  //被删除节点之前的节点
			while(i != index - 1){
				i ++;
				temp = temp.next;
			}
			Node delete = temp.next; //被删除的节点
			Object o = delete.data;
			temp.next = delete.next; //删除
			size --;
			return o;
		}
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){  //在表头添加节点
		head = new Node(o, head);
		size ++;
	}

	public void addLast(Object o){  //在链表尾部添加节点
		if(head == null){
			head = new Node(o,null);
			size ++;
			return;
		}
		Node last = head;
		while(last.next != null){
			last = last.next;
		}
		last.next = new Node(o, null);
		size ++;
	}

	public Object removeFirst(){  //在链表头部删除节点
		if(size() == 0) throw new RuntimeException("Underflow");
		Object o = head.data;
		head = head.next;
		size --;
		return o;
	}

	public Object removeLast(){  //在链表尾部删除节点
		if(size() == 0) throw new RuntimeException("Underflow");
		if(size() == 1){
			Object o = head.data;
			head = null;
			size --;
			return o;
		}
		Node temp = head;
		int i = 0;
		while(i != size-2){
			temp = temp.next;
			i ++;
		}
		Object o = temp.next.data;
		temp.next = null;
		size --;
		return o;
	}

	public Iterator iterator(){ //迭代器
		return new ListIterator();
	}

	private class ListIterator implements Iterator{ //实例内部类
		private Node current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Object next() {
			if(size() == 0) throw new NoSuchElementException("Underflow");
			Object o = current.data;
			current = current.next;
			return o;
		}
	}
	
	//这里内部类须为static,在类级别上一一对应，非实例级别
	private static class Node{
		Object data;
		Node next;
		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){

	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){

	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){

	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * @param list
	 */
	public static int[] getElements(LinkedList list){
		return null;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素

	 * @param list
	 */

	public  void subtract(LinkedList list){

	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){

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
