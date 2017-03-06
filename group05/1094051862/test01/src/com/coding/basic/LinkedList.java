package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private Node last;
	private int size = 0;
	
	public void add(Object o){
		if (head == null) {
			head = new Node(o, null);
			size ++;
			return;
		}
		Node n = new Node(o, null);
		if (last == null) {
			last = n;
			head.next = last;
		}
		last.next = n;
		last = n;
		size ++;
	}
	public void add(int index , Object o){
		if (index < 0 || index > size) {
			System.out.println("linkedList.add: index < 0 || index > size");
			return;
		}
		if (index == size) {
			add(o);
			return;
		}
		if (index == 0) {
			addFirst(o);
			return;
		}
		Node pre = head;
		for (int i = 1; i < index; i++) {
			pre = pre.next;
		}
		Node post = pre.next;
		Node n = new Node(o,post);
		pre.next = n;
		size ++;
	}
	public Object get(int index){
		if (index == 0) {
			return head.data;
		}
		Node n = head;
		for (int i = 1; i <= index; i++) {
			n = n.next;
		}
		return n.data;
	}
	public Object remove(int index){
		if (index < 0 || index >= size) {
			System.out.println("remove :index < 0 || index >= size");
			return null;
		}
		if (index == 0) {
			return removeFirst();
		}
		if (index == size - 1) {
			return removeLast();
		}
		Node pre = head;
		for (int i = 1; i < index; i++) {
			pre = pre.next;
		}
		Node n = pre.next;
		Node post = n.next;
		n.next = null;
		pre.next = post;
		size --;
		return n.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node n = new Node(o,head);
		head = n;
		size ++;
		return;
	}
	public void addLast(Object o){
		Node n = new Node(o,null);
		last.next = n;
		last = n;
		size ++;
		return;
	}
	public Object removeFirst(){
		Object o = head.data;
		Node n = head.next;
		head.next = null;
		head = n;
		size --;
		return o;
	}
	public Object removeLast(){
		Node preLast = head;
		for (int i = 1; i < size; i++) {
			preLast = preLast.next;
		}
		preLast.next = null;
		Object o = last.data;
		last = preLast;
		size --;
		return o;
	}
	public Iterator iterator(){
		return new Iterator() {
			int cusor = 0;
			Node current = head;
			@Override
			public Object next() {
				if (!hasNext()) {
					System.out.println("next : !hasNext");
					return null;
				}
				Object o = current.data;
				current = current.next;
				cusor ++;
				return o;
			}
			
			@Override
			public boolean hasNext() {
				return cusor < size;
			}
		};
	}
	
	
	private static class Node {
		
		Object data;
		Node next;
		
		public Node (Object data, Node next) {
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
