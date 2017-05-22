package com.dudy.learn01.data_structure.list;


import com.dudy.learn01.data_structure.MyIterator;
import com.dudy.learn01.data_structure.MyList;

import java.util.LinkedList;

/**
 * 单链表：
 * 因为没有尾节点
 * 存放时  add  1 2 3 4  实际是 4 3 2 1
 */
public class MyLinkedList implements MyList {

	private int size = 0;

	private Node head;

	public void add(Object o) {
		Node newNode = new Node(o);
		newNode.next = head;// next --> head
		head = newNode;     // head --> newNode
		size++;
	}

	public void add(int index, Object o) {
		checkRange(index);
		Node current = getCurrentNode(index);
		Node newNode = new Node(o);
		newNode.next = current.next;//new next --> next 
		current.next = newNode; // next -->new 
		size++;
	}

	private Node getCurrentNode(int index) {// 获取当前节点
		checkRange(index);
		Node current = head;
		for(int i = 0; i< size-index -1; i++){
			current = current.next;
		}
		return current;
	}

	private void checkRange(int index) {
		if(index > size || index < 0){
			throw new RuntimeException("indexOutOfException:" + "Index: " + index + ", Size: " + size);
		}
	}

	public Object get(int index) {
		checkRange(index);
		Node node = getCurrentNode(index);
		return node.data;
	}

	public Object remove(int index) {
		if(size < 1){// ①没有元素的情况
			throw new RuntimeException("NoSuchElementException: size= " + size);
		}
		if(index == 0) return removeFirst();
		if(index == size - 1) return removeLast();
			
		Node node = getCurrentNode(index+1);
		node.next = node.next.next;
		size--;
		return node.data;
	}

	public  int size() {
		return size;
	}

	public void addLast(Object o) {
		add(o);
	}

	public void addFirst(Object o) {
		Node current = head;
		while(current.next != null){// 找到最后一个节点
			current = current.next;
		}
		Node newNode = new Node(o);
		current.next = newNode;
		size++;
	}

	public Object removeFirst() {
		Node currentNode = getCurrentNode(1);
		Node tmp = currentNode.next;
		currentNode.next = null;
		size--;
		return tmp ==null? currentNode.data:tmp.data;// 可能只有一个数据
	}

	public Object removeLast() {
		Node tmp = head;
		head = head.next;
		size--;
		return tmp.data;
	}

	public MyIterator iterator() {
		return new MyLinkedListItr();
	}


	public class  MyLinkedListItr implements  MyIterator {

	    int cursor;

        public boolean hasNext() {
            return cursor != size;
        }

        public Object next() {
            Node currentNode = getCurrentNode(cursor++);
            return currentNode.data;
        }
    }


	private static class Node {
		Object data;
		Node next;
		public Node() {
		}
		public Node(Object data) {
			this.data = data;
		}
	}


	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){

		if(head == null || head.next == null){
			return ;
		}

		Node current = head; // 当前节点  我的头节点是有数据的

		while (current.next != null){
			Node p = current.next; // 当前节点的下一个
			current.next = p.next; // 当前节点的next ->  current.next.next (p.next)
			p.next = head; //   current.next(p) -> head.next (插入到 head 和 第一个数据之间)
			head = p;
		}
	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){

		int base = size;
		Node currentNode = getCurrentNode(base / 2);
		currentNode = null;
		size = size - base/2;
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){

        Node preNode = getCurrentNode(size - i -1);
        Node nextNode = getCurrentNode(size - i - length-1);
        nextNode.next = preNode;
        size = size -length;
    }
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * @param src
	 */
	public Object[] getElements(int[] src){
		Object des[] = new Object[src.length];

		for (int i = 0; i < src.length; i++){
		    des[i] = getCurrentNode(size - 1 - i).data;
        }

	    return des;
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