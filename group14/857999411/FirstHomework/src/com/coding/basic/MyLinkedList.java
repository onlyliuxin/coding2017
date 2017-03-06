package com.coding.basic;

import java.util.*;

public class MyLinkedList implements MyList{
	//用内部类定义链表中的节点
	private class Node{
		//节点中包含数据和引用
		 Object data;
		 Node next;
		
		public Node (){
			
		}
		
		//每个节点包含数据和引
		public Node (Object data,Node next){
			this.data =data;
			this.next =next;
		}
	}
	//定义头节点和尾节
	public Node head;
	public Node tail;
	public int size;
	
	//无参数构造函数创建空链表
	public MyLinkedList(){
		head =null;
		tail =null;
	}
	
	//链表中传入元
	public MyLinkedList(Object element){
		head.data =element;
		head.next =tail;
		size++;
	}
	
	public void add(Object o){
		addLast(o);
	}
	 public void  addFirst(Object element) {

		 head =new Node(element,head);		 
	    if(tail == null){ 		  
			tail=head;
		 }
		   size++;
	 }
	 
	 public void addLast(Object element) {
		 if(head == null) {
			 head =new Node (element,null);
			 tail =head;
		 }else{
			 Node newNode =new Node(element,null);
			 tail.next =newNode;
			 tail=newNode;
		 }
		 size++;
		 
	 }
	 
	 public void add(int index,Object element){
		 
		 if(index < 0 || index > size) {
			 throw new IndexOutOfBoundsException("索引越界");
		 }
		 if(index == 0) {
			 head =new Node(element,head);			 
		 }
		 Node frontNode =getNode(index-1); 
		 frontNode.next =new Node(element,frontNode.next);
		 size++;
	 }
	 public Node  getNode(int index)
	 {
		 if(index < 0 || index > size-1) {
			 
			 throw new IndexOutOfBoundsException("索引越界");
		 }
		 Node current=head;
		 for(int i=0;i < size; i++,current =current.next) {
			 if(i == index) {
				  return current;
			 }		
		 }
		 return null;
	 }
	 
	 public Object get(int index){
		 return getNode(index).data;
	 }
	 
	 public Object remove(int index){
		 if(index < 0 || index > size-1) {
			 throw new IndexOutOfBoundsException("索引越界");
		 }
		 Node delNode =null;
		 if(index == 0) {
			 delNode =head;
			 head =head.next;
		 }else{			
			 Node frontNode =getNode(index-1);
			 delNode =frontNode.next;
			 frontNode.next =delNode.next;
			 delNode.next =null;		 
		 }
		 size--;
		 return delNode.data;
	 }
	 
	 public Object removeFirst(){
		 if(head == null || head.next == null)
			 throw new NoSuchElementException();
		 Node oldhead =head; 
		 head =head.next;
		 oldhead.next =null;
		 size--;
		 return oldhead.data;
				 
	 }
	 
	 public Object removeLast(){
		 return remove(size - 1);
		
	 }

	 
	 public int size() {
		 return size;
	 }
		
	
}


