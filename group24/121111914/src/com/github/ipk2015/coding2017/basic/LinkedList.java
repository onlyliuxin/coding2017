package com.github.ipk2015.coding2017.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
		if(null==head){
			head=new Node();
			head.data=o;
		}else{
			Node node=head;
			while(null!=node.next){
				node=node.next;
			}
			Node addNode=new Node();
			addNode.data=o;
			node.next=addNode;   
		}
	}
	public void add(int index , Object o){
		int size=size();
		ListUtils.checkIndexInRange(index, size);
		if(index==size){
			add(o);
		}else{
			if(size==0){
				head=new Node();
				head.data=o;
			}else{
				Node node=head;
				Node addNode=new Node();
				addNode.data=o;
				for(int i=0;i<index-1;i++){
					node=node.next;
				}
				addNode.next=node.next;
				node.next=addNode;
			}
		}
	}
	public Object get(int index){
		ListUtils.checkIndexInRange(index, size()-1);
		Node node=head;
		for(int i=0;i<index;i++){
			node=node.next;
		}
		return node.data;
	}
	public Object remove(int index){
		int size=size();
		ListUtils.checkIndexInRange(index, size-1);
		Node removeNode=head;
		if(index==0){
			head=head.next;
		}else{
			Node node=head;
			for(int i=0;i<index-1;i++){
				node=node.next;
			}
			removeNode=node.next;
			node.next=removeNode.next;
		}
		return removeNode.data;
	}
	
	public int size(){
		int size=0;
		if(null==head){
			return size;
		}else{
			Node node=head.next;
			size=1;
			while(null!=node){
				node=node.next;
				size++;
			}
		}
		return size;
	}
	
	public void addFirst(Object o){
		if(null==head){
			head=new Node();
			head.data=o;
		}else{
			Node node=new Node();
			node.data=head.data;
			node.next=head.next;
			head.next=node;
			head.data=o;
		}
	}
	public void addLast(Object o){
		if(null==head){
			head=new Node();
			head.data=o;
		}else{
			Node node=head;
			while(null!=node.next){
				node=node.next;
			}
			Node addNode=new Node();
			addNode.data=o;
			node.next=addNode;
		}
	}
	public Object removeFirst(){
		if(null==head){
			throw new NoSuchElementException();
		}
		Node node=head;
		head=node.next;
		return node;
	}
	public Object removeLast(){
		if(null==head){
			throw new NoSuchElementException();
		}
		Node node=head;
		if(null==node.next){
			head=null;
			return node;
		}else{
			while(null!=node.next.next){
				node=node.next;
			}
			Node lastNode=node.next;
			node.next=null;
			return lastNode;
		}
	}
	public Iterator iterator(){
		return new Iterator(){
			private int currentPos=0;
			@Override
			public boolean hasNext() {
				int size=size();
				if(size==0){
					return false;
				}else{
					if(currentPos<size){
						return true;
					}else{
						return false;
					}
				}
			}

			@Override
			public Object next() {
				Object object= get(currentPos);
				currentPos++;
				return object;
			}
			
		};
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
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
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public static int[] getElements(LinkedList list){
		return null;
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
	public  LinkedList rsection( LinkedList list){
		return null;
	}
}
