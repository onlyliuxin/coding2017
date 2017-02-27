package com.coding.basic;

/**
 * 
 * LinkedList集合-链
 * 
 * @ClassName LinkedList
 * @author msh
 * @date 2017年2月21日 下午4:08:01
 */
public class LinkedList implements List {
	//链头
	private Node head;
	//集合大小
	private int size=0;
	/**
	 * 
	 * 向链中添加元素
	 *
	 * @Method add 添加
	 * @param o 元素
	 * @see com.coding.basic.List#add(java.lang.Object)
	 */
	public void add(Object o){
		Node newNode = new Node(o, null);
		if (null == head) {
			head = newNode;
		} else {
			Node lastNode = null;
			for (int i = 0; i < size; i++) {
				lastNode = (Node) get(i);
			}
			lastNode.next = newNode;
		}
		size++;
	}
	/**
	 * 
	 * 向链中添加元素 
	 *
	 * @Method add 增加
	 * @param index 下标
	 * @param o 元素
	 * @see com.coding.basic.List#add(int, java.lang.Object)
	 */
	public void add(int index , Object o){
		validate(index);
		Node newNode = null;
		Node perNode = null;
		Node nextNode = null;
		// 当为最后插入时
		if (index == size - 1) {
			newNode = new Node(o, null);
			for (int i = 0; i < index; i++) {
				Node tempNode = (Node) get(i);
				perNode = tempNode.next;
			}
			perNode.next = newNode;
		} else if (0 == index) {
			nextNode = head.next;
			newNode = new Node(o, nextNode);
			head = newNode;
		} else {
			for (int i = 0; i < index; i++) {
				Node tempNode = (Node) get(i);
				perNode = tempNode.next;
			}
			nextNode = perNode.next.next;
			newNode = new Node(o, nextNode);
			perNode.next = newNode;
		}
		size++;
	}
	/**
	 * 
	 * 取得元素 
	 *
	 * @Method get 取得
	 * @param index 下标
	 * @return
	 * @see com.coding.basic.List#get(int)
	 */
	public Object get(int index){
		validate(index);
		Node tempNode = head;	
		for (int i = 0; i <= index; i++) {
			tempNode = tempNode.next;
		}
		return tempNode;
	}
	/**
	 * 
	 * 删除元素
	 *
	 * @Method remove 删除
	 * @param index 下标
	 * @return
	 * @see com.coding.basic.List#remove(int)
	 */
	public Object remove(int index){
		Node removeNode = (Node) get(index);
		validate(index);
		if (index == size - 1) {
			Node tempNode = head;
			for (int i = 0; i < index; i++) {
				tempNode = tempNode.next;
			}
			tempNode.next = null;
		} else if (index == 0) {
			Node tempNode = head.next;
			head.next = null;
			head = tempNode;
		} else {
		}
		size--;
		return removeNode;
	}
	/**
	 * 
	 * 取得集合大小 
	 *
	 * @Method size 集合大小
	 * @return 集合大小
	 * @see com.coding.basic.List#size()
	 */
	public int size(){
		return size;
	}
	/**
	 * 
	 * 想链头中插入元素
	 *
	 * @MethodName addFirst
	 * @author msh
	 * @date 2017年2月21日 下午4:10:56
	 * @param o
	 */
	public void addFirst(Object o){
		Node newNode = new Node(o, head);
		head = newNode;
	}
	/**
	 * 
	 * 向链后加入元素 
	 *
	 * @MethodName addLast
	 * @author msh
	 * @date 2017年2月21日 下午4:11:43
	 * @param o
	 */
	public void addLast(Object o){
		add(o);
	}
	/**
	 * 
	 * 删除链头 
	 *
	 * @MethodName removeFirst
	 * @author msh
	 * @date 2017年2月21日 下午4:12:14
	 * @return
	 */
	public Object removeFirst(){
		if(null==head)
			throw new IndexOutOfBoundsException("Size: " + size);
		Node orgHead = head;
		Node tempNode = head.next;
		head.next = null;
		head = tempNode;
		return orgHead;
	}
	/**
	 * 
	 * 删除链尾
	 *
	 * @MethodName removeLast
	 * @author zhaogd
	 * @date 2017年2月21日 下午4:12:44
	 * @return
	 */
	public Object removeLast(){
		if(null==head)
			throw new IndexOutOfBoundsException("Size: " + size);
		Node lastNode = (Node) get(size);
		Node tempNode = head;
		for (int i = 0; i < (size - 1); i++) {
			tempNode = tempNode.next;
		}
		tempNode.next = null;
		return lastNode;
	}
	public Iterator iterator(){
		return null;
	}
	
	/**
	 * 
	 * 验证 
	 *
	 * @MethodName validate 下标
	 * @author msh
	 * @date 2017年2月21日 下午3:54:21
	 * @param index
	 */
	private void validate(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	}
	/**
	 * 
	 * 链中元素
	 * 
	 * @ClassName Node
	 * @author zhaogd
	 * @date 2017年2月21日 下午4:13:10
	 */
	private static  class Node{
		Object data;
		Node next;
		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
}
