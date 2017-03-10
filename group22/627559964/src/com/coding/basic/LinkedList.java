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
	 * @author xiongrui233
	 *
	 */
	private static class Node {
		Object data;
		Node next;
	}
	//链表节点
	private Node head = new Node();

	/**
	 * 添加元素
	 * 
	 * @param o
	 */
	public void add(Object o) {
		Node node = head;
		while (node.data != null) {
			node = node.next;
		}
		node.data = o;
	}

	/**
	 * 添加元素
	 * 
	 * @param index
	 * @param o
	 */
	public void add(int index, Object o) {
		Node node = head;
		Node oldNode = head;
		Node newNode = new Node();
		for (int i = 0; i <= index; i++) {
			if (i == index - 1) {
				oldNode = node.next;
			}
			node = node.next;
		}
		newNode.data = o;
		newNode.next = node;
		oldNode.next = newNode;
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
		Node node = head;
		Node oldNode = head;
		Node newNode = new Node();
		for (int i = 0; i <= index; i++) {
			if (i == index - 1) {
				oldNode = node.next;
			}
			node = node.next;
		}
		if (node.next != null) {
			newNode = node.next;
		} else {
			newNode = node;
		}
		oldNode.next = newNode;
		return node.data;
	}

	public int size() {
		int size = 0;
		while (head.next != null) {
			size ++;
		}
		return size;
	}

	public void addFirst(Object o) {

	}

	public void addLast(Object o) {

	}

	public Object removeFirst() {
		return null;
	}

	public Object removeLast() {
		return null;
	}

	public Iterator iterator() {
		return null;
	}

	/**
	 * 把该链表逆置 例如链表为 3->7->10 , 逆置后变为 10->7->3
	 */
	public void reverse() {

	}

	/**
	 * 删除一个单链表的前半部分 例如：list = 2->5->7->8 , 删除以后的值为 7->8 如果list = 2->5->7->8->10
	 * ,删除以后的值为7,8,10
	 */
	public void removeFirstHalf() {

	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length) {

	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数 从当前链表中取出那些list所指定的元素 例如当前链表 =
	 * 11->101->201->301->401->501->601->701 listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * 
	 * @param list
	 */
	public static int[] getElements(LinkedList list) {
		return null;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 从当前链表中中删除在list中出现的元素
	 * 
	 * @param list
	 */

	public void subtract(LinkedList list) {

	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues() {

	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * 
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {

	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 
	 * @param list
	 */
	public LinkedList intersection(LinkedList list) {
		return null;
	}
}