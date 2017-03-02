package hw1;

public class LinkedList implements List {
//	public static void main(String[] args) {
//		
//	}
	private Node head = null;
	private int size; // 链表的节点个数，从1开始计数的哦
	
	public void add(Object o) {
		// 没有头节点，则创建头节点
		if (null == head) {
			head = new Node();
			head.next = null;
		} 
//		Node temp = new Node();
//		temp = head.next;
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = head.next;
		head.next = newNode;
		
		size++;
	}
	public void add(int index, Object o) {
		
		size++;
	}
	// 头节点的索引为0
	public Object get(int index) {
		Node ptr = new Node();
		ptr = head;
		// 如果index越界，则抛出异常啊？要抛出异常吗？
		if (index >=  size() || index < 0) {
			System.out.println("要抛出异常吗？");
			return null;
		} else {
			// 假设头节点有数据吧，节约点资源
			for (int i = 0; i < index; i++) {
				ptr = ptr.next;
			}
		}
		return ptr;
	}
	// 删除特定索引位置的对象
	public Object remove(int index) {
		Node ptr = new Node();
		Node temp = new Node();
		ptr = head;
		// 如果index越界，则抛出异常啊？要抛出异常吗？
		if (index >=  size() || index < 0) {
			System.out.println("要抛出异常吗？");
			return null;
		} else {
			// 假设头节点有数据吧，节约点资源
			for (int i = 0; i < index; i++) {
				ptr = ptr.next;
			}
			// 此时ptr指向的就是index这个节点，但是我们要的是前一个节点
			temp = ptr.next;
			ptr.next = ptr.next.next;
		}
		size--;
		return temp;
	}
	public int size() {
		// 隐藏的成本就是多写一个函数，没别的用途就是隐藏安全用的
		return size;
	}
	// 因为头节点head里面也是有数据的，所以这里的插入指的是插到头前面
	public void addFirst(Object o) {
		// 不能发呆了
		// 首先创建节点
		Node ptr = new Node();
		ptr.data = o;
		ptr.next = head;
		head = ptr;
		size++;
	}
	// 最后一个节点指向 null嘛
	public void addLast(Object o) {
		Node ptr = new Node();
		ptr.data = o;
		ptr.next = null;
		// 取尾节点,size正好比索引大一个
		Node lastNode = head;
		for (int i = 0; i < size-1; i++) {
			lastNode = lastNode.next;
		}
		lastNode.next = ptr;
		size++;
	}
	public Object removeLast() {
		// 取尾节点前一个,size正好比索引大一个
		Node lastNode = head;
		for (int i = 0; i < size-2; i++) {
			lastNode = lastNode.next;
		}
		lastNode.next = null;
		size--;
		return null;
	}
	public Object removeFirst() {
		Node firstNode = new Node();
		firstNode.next = head.next;
		head = firstNode;
		size--;
		return null;
	}
	// 节点静态内部类
	private static class Node {
		Object data; // Object 是一个通用的类型
		Node next;
	}
}
