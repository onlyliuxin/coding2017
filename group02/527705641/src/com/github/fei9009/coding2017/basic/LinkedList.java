package com.github.fei9009.coding2017.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public void add(Object o){
		add(size, o);
	}
	
	public void add(int index , Object o){
		if (index > this.size || index < 0) {
			throw new IndexOutOfBoundsException("index " + index + "beyond the size " + size );
		}
		Node dummy = node(index);
		Node newNode = new Node(o);
		newNode.next = dummy;
		if (index == 0) {
			head = newNode;
		}
		else {
			Node before = node(index-1);
			before.next = newNode;
		}
		this.size++;
		
	}
	
	public Object get(int index){
		if (index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException("index " + index + "beyond the size " + size );
		}
		Node node = node(index);
		return node.data;
		
	}
	public Object remove(int index){
		if (index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException("index " + index + "beyond the size " + size );
		}
		Node delNode = node(index);
		if (index == 0)
			head = delNode.next;
		else {
			Node before = node(index-1);
			before.next = delNode.next;
		}
		size--;
		return delNode.data;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		add(0, o);
	}
	public void addLast(Object o){
		add(o);
	}
	public Object removeFirst(){
		return remove(0);
	}
	
	public Object removeLast(){
		return remove(this.size -1);
	}
	public Iterator iterator(){
		return null;
	}
	
	Node node(int index) {
		Node x = head;
		for (int i=0; i<index; i++) {
			x = x.next;
		}
		return x;
	}
	
	private static  class Node{
		Object data;
		Node next;
		Node(Object o) {
			this.data = o;
			this.next = null;
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
		int length = size/ 2;
		for (int i = 0; i < length; i++) {
			head = head.next;
		}
		size = size - length;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if (i == 0) {
			while (i < length) {
				if (head == null) {
					size = 0;
					break;
				}
				head = head.next;
				i++;
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
		int[] result = new int[list.size];
		for (int i = 0; i < result.length; i++) {
			result[i] = (int)get((int)list.get(i));
		}
		return result;
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
		while (head.next != null && head.data == head.next.data) {
			head = head.next;
			size--;
		}
		Node dummy = head;
		while (dummy.next != null) {
			if (dummy.data == dummy.next.data) {
				dummy.next = dummy.next.next;
				size --;
			} else {
				dummy = dummy.next;
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
		if ((int)head.data > max) {
			return ;
		} 
		if ((int)get(size-1) < min) {
			return;
		}
		while ((int)head.data > min && (int) head.data < max) {
			head = head.next;
			size--;
			if (head == null) {
				break;
			}
		}
		Node dummy = head;
		if (dummy == null) {
			return;
		}
		while (dummy.next != null) {
			if ((int)dummy.next.data > min && (int) dummy.next.data < max) {
				dummy.next = dummy.next.next;
				size --;
			}else {
				dummy = dummy.next;
			}	
		}
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
