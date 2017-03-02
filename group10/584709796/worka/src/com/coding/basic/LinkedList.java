package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size=0;			//表长
	//0.单链表存储结构
	private static  class Node{
			Object data;   //数据域
			Node next;   //指针域
	}
	
	//1.末尾添加元素
	public void add(Object o){//不带头结点
		 Node node = new Node();
		 node.data=o;
		 		 
		 node.next=head;
		 head=node;
		 
		 size++;
	}
	//2. 索引插入
	public void add(int index , Object o){
		int j=0;//索引
		Node temp=head;
		
		Node node = new Node();
		 node.data=o;
		 
		 if(index==0){
			 node.next=temp;
			 head=node;
			 size++;
		 }
		 else{
			 while(j<=index-1){
				 temp=temp.next;
				 j++;
			 }
			 node.next=temp.next;
			 temp.next=node;
			 size++;
		 }		 
	}
	//3.索引获取元素
	public Object get(int index){
		Object e=0;
		
		int j=0;//索引
		Node temp=head;
		 
		 if(index==0){
			 e=(Object)temp.data;
		 }
		 else{
			 while(j<=index-1){
				 temp=temp.next;
				 j++;
			 }
			  e=(Object)temp.data;
		 }		 
		
		System.out.println(index+"index数据域："+e);
		return e;
	}
	//4.索引移除元素
	public Object remove(int index){
		
		Object e=0;
		
		int j=0;//索引
		Node temp=head;
		 
		 if(index==0){
			 e=temp.data;
			 head=temp.next;
			 size--;
		 }
		 else{
			 while(j<index-1){
				 temp=temp.next;
				 j++;
			 }
			 e=temp.next.data;
			 temp.next=temp.next.next;
			 
			 size--;
		 }		 
		
		System.out.println(index+"index数据域："+e);
		return e;
	}
	//5.获取大小
	public int size(){
		return size;
	}
	//6.表头插入元素
	public void addFirst(Object o){
		 Node node = new Node();
		 node.data=o;
		 		 
		 node.next=head;
		 head=node;
		 
		 size++;
	}
	//7.尾插法
	public void addLast(Object o){
		Node p = new Node();
		Node t=null;
		for(t = head; t.next!=null; t=t.next); //结束时t指向尾节点
		p.next = null;        //进行插入
		t.next=p;
	}
	//8.头删法
	public Object removeFirst(){
		Object e=0;
		Node temp=head;
		e=temp.data;
		head=temp.next;
		size--;
		return e;
	}
	//9.尾删法
	public Object removeLast(){
		//Node p = new Node();
		Node t=head;
		Object e=null;
		int j=0;  
	       
	     while(j<size)  
	     {  
	         t=t.next;  
	         j++;  
	     }  
	      
	     t.next = t.next.next;   		
		 size--;
		return e;
	}
	
	//10.遍历
	public void PrintLinkList(){
		Node current = head;
        while (current != null) {
        	System. out.print( current.data + "	");     
        	current = current.next;
        }
        System. out.println(); 
   	}
	
	////11.判断是否为空
	public boolean isEmpty(){
		boolean s=false;
		if(size==0)
		{
			s=true;
		}
		else s=false;
		return s;
	}
	public Iterator iterator(){
		return null;
	}
	
	
}
