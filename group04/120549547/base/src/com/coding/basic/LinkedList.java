package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size;  
	/*创建一个带头节点的单链表*/
	public LinkedList(){
		head = new Node(null);
	}
	
	/*添加一个元素(尾插法)*/
	public void add(Object o){
		
		Node node = new Node(o);
		Node pos = head;
		//找到最后一个元素位置
		while(pos.next != null){
			pos = pos.next;
		}
		pos.next = node;
		size++;
		
	}
	
	/*在index位置插入*/
	public void add(int index , Object o){
		//判断索引是否合法
		checkIndex(index);
		Node node = new Node(o);
		Node pos = head;
		//找到插入位置
		while(index-- != 0){
			pos = pos.next;
		}
		node.next = pos.next;
		pos.next = node;
		size++;
		
	}
	public Object get(int index){
		checkIndex(index);
		if(this.isEmpty()){
			throw new IllegalArgumentException ("链表为空");
		}
		Node pos = head.next;  //pos指向要获取的节点
		while(index-- !=0){
			pos = pos.next;
		}
	
		return pos.data;
	}
	public Object remove(int index){
		checkIndex(index);
		if(this.isEmpty()){
			throw new IllegalArgumentException ("链表为空");
		}
	
		Node pos = head;  //pos指向要删除的前一个结点
		while(index-- != 0){	
			pos = pos.next;
		}
		Node value = pos.next; //要删除的节点
		pos.next = value.next;
		size--;
		return value.data;
		

	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		
	}
	public void addLast(Object o){
		this.add(o);
	}
	public Object removeFirst(){
		if(this.isEmpty()){
			throw new IllegalArgumentException ("链表为空");
		}
		return remove(0);
	}
	public Object removeLast(){
		if(this.isEmpty()){
			throw new IllegalArgumentException ("链表为空");
		}
		return remove(size-1);
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	public Iterator iterator(){
		return null;
	}
	
	private void checkIndex(int index){
		if (index<0 || index>=size ){
			throw new IllegalArgumentException ("index不合法: " + index );
		}
	}
	
	@Override
	public String toString(){
		
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<size; i++){
			sb.append( this.get(i) + "->  ");
		}
		return sb.toString();
	}
	
	private static  class Node{
		public Object data;
		public Node next;
		
		public Node(Object data){
			this.data = data;
			this.next = null;
		}
		
	}
}
