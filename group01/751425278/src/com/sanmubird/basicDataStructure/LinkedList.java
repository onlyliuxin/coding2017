package com.sanmubird.basicDataStructure;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	/**		链表：是由节点（Node）组成的，
	 * 		而向外暴露的只有一个头节点；我们对链表的所有操作，都是直接或简洁地通过头节点来实现的。
	 * 		节点（Node）是由 一个 存储的对象和一个对下个节点的引用组成的。
	 * 		Node中最重要的就是引用了，如果说对ArrayList的增删是ArrayCopy的话，那对LinkedList的增删就是改变引用的指向。
	 * 		因为节点的添加顺序是自右向左的，且最左边的节点是头节点；
	 * 		所以，新添加的节点会在左边，而是新添加的节点会成为新的t头节点。
	 * 		头节点可以通过对下一个节点的引用，来找到所有的节点；
	 * 		所以：链表里面的属性就有两个，一个是头节点，一个是节点的数量。
	 ***/
	private Node head; 	//定义一个头节点
	private int size  ;	//节点的数量
	
	public LinkedList(){
		this.head = null ;
		this.size = 0 ;
	}
	
//	检查输入的参数是否合法
	public void argumentCheckForAdd(int index){
		if(index > size || index < 0 ){		//这个地反需要验证 index = 0 的情况
			throw new IndexOutOfBoundsException("输入的参数超出了边界！");
		}
	}
	
//	检查输入的参数是否合法
	public void argumentCheckForOther(int index){
		if(index >= size || index < 0 ){
			throw new IndexOutOfBoundsException("输入的参数超出了边界！");
		}
	}	
	

	public Node getHead(){
		return head ;
	}
	
	public Object get(int index){
		argumentCheckForOther(index);
		Node node = head ;
		for(int i = 0 ; i < index ; i++){
			node = head.next ;
		}
		return node.data;
	}
	public int size(){
		return size;	// return this.size 跟 return  size ；有区别没有？
	}
	
//	
	public void add(Object o){
		Node newNode = new Node(o);	//实例化一个要添加的节点
		if(head == null ){
			head = newNode ;
		}else{
			Node temp = head ;
			while (temp.next != null ){	//这个地方为什么是 temp.next != null ?
					// 这个地方的作用就是：取出下一个节点；那么当然是在存在下个节点的情况下，才能取出下个节点
				temp = temp.next ;
				// 这样就找到了最后一个节点： temp
			}
			temp.next = newNode ;		
		}
		size++;
	}
	
//	这个通过画图，然后看图说话就很容易弄出来。
	public void add(int index , Object o){
		argumentCheckForAdd(index);
		if(size == index )
			add(o);
		else{
			Node preNode = head ;
			for (int i = 0 ; i < index ; i++){
				preNode = preNode.next;
			}
			Node nextNode = preNode.next ;
			Node node = new Node(o);
			preNode.next = node;
			node.next = nextNode;
			size++;
		}
	}

	public Object remove(int index){
		argumentCheckForOther(index);
		Node temp = head ;
		for(int i = 0 ; i < index  ; i++){
			temp = temp.next ;
		}
		Node removedNode = temp.next ;
		Node nextNode = removedNode.next ;
		temp.next = nextNode ;
		size--;
		return removedNode.data;
	}
	
	
	public void addFirst(Object o){
		Node node = new Node(o);
		node.next = head ;
		head = node ;
		size++;
	}
	
	public void addLast(Object o){
		Node lastNode = head ;
		while(lastNode.next != null ){
			lastNode = lastNode.next ;
		}
		Node node = new Node(o);
		lastNode.next = node ;
		size++;
	}
	
	public void noSuchElement(){
		if(head.next == null){
			throw new NoSuchElementException("没有这个元素");
		}
	}
	
	
	public Object removeFirst(){
		noSuchElement();
		Node node = head.next ;
		head.next = node.next ;
		size--;
		return node.data;
	}
	
	public Object removeLast(){
		noSuchElement();
		Node temp = head ;
		for(int i = 0 ; i <size -1 ; i++){
			temp = temp.next ;
		}
		Node removedNode = temp.next ;
		temp.next = null ;
		size--;
		return removedNode.data;
	}
	public Iterator iterator(){
		return null;
	}
	
	static  class Node{	//此处为什么要使用static来修饰?
		Object data;	//元素域；当前节点存放的元素对象
		Node next;		//指针域；对一下节点的引用；
		
		public Node(Object data){
			this.data = data ;
			this.next = null ;	//因为对下一个节点的引用可以为空，所以这个地方可以为空
		}
	}
}