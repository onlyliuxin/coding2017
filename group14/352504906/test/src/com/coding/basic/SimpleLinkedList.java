package com.coding.basic; 
/**
 * @Description 简单实现linkedList
 */
public class SimpleLinkedList implements SimpleList{
	private Node head;
	
	private int size = 0;
	/**
	 * 返回集合长度
	 * @return 长度
	 */
	public int size(){
		return this.size;
	}
	/**
	 * 返回指定索引出元素
	 * @param index 索引
	 * @return Object 元素
	 */
	public Object get(int index){
		rangeCheck(index);
		Node current = head;
		for(int i =0;i<index;i++){
			current = current.next;
		}
		return current.o;
	}
	/**
	 * 插入元素o，默认是往头部插入
	 * @param o 待插入元素
	 */
	public void add(Object o){
		addFirst(o);
	}
	/**
	 * 在指定索引处插入元素
	 * @param index 索引
	 * @param o 待插入元素
	 */
	public void add(int index,Object o){
		rangeCheck(index);
		if(index == 0){
			addFirst(0);
			return;
		}
		if(index == size){
			addLast(o);
			return;
		}
		Node newNode = new Node(o);
		Node current = head;
		for(int i = 0;i < index -1;i++){
			current = current.next;
		}
		newNode.next = current.next;
		current.next = newNode;
		size ++;
		
	}
	/**
	 * 索引越界处理
	 * @param index 索引
	 */
	private void rangeCheck(int index) {
		if(index > size ||  index < 0){
			throw new RuntimeException("找不到该节点");
		}
	}
	//头插法
	public void addFirst(Object o){
		Node newNode = new Node(o);
		newNode.next = head;
		head = newNode;
		size++;
	}
	//尾插法
	public void addLast(Object o){
		Node newNode = new Node(o);
		Node current = head;//设定一个当前节点，便于遍历
		while(current.next!=null){
			current = current.next;
		}
		if(current.next == null)//尾节点
		{
			current.next = newNode;
			size++;
		}
	}
	/**
	 * 移除该索引处元素
	 * @param index 索引位置
	 * @return 移除元素
	 */
	public Object remove(int index){
		rangeCheck(index+1);
		Node current = head;
		if(index == 0){//头部删除
			Object removeObj = head.o;
			head = head.next;
			size --;
			return removeObj;
		}
		int i;
		for(i=0;i<index-1;i++){
			current = current.next;
		}
		Object removeObject = get(i);
		if(index == size-1){//尾部删除
			current.next = null;
			size--;
			return removeObject;
		}
		current.next = current.next.next;
		size--;
		return removeObject;
	}
	//内部类，声明为static 与实例无关
	private static class Node{
		private Object o;//单链表的数据域
		private Node next;//单链表的指针域
		private Node(Object o){
			this.o = o;
		}
	}
}
