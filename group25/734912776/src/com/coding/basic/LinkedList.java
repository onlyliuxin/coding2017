package com.coding.basic;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class LinkedList implements List {

	private transient Node head;
	private transient Node end;
	private transient int size;
	
	
	private void linkedBefore(Object o,Node currNode){
		Node preNode=currNode.pre;
		Node newNode=new Node(preNode,o,currNode);
		currNode.pre=newNode;
		if(preNode==null){
			head=newNode;
		}else{
			preNode.next=newNode;
		}
		size++;
	}
	
	public void add(Object o){
		if(size>1){
			Node newNode=new Node(end,o,null);
			end.next=newNode;
			end=newNode;
		}else if(size==1){
			Node newNode=new Node(head,o,null);
			end=newNode;
			head.next=newNode;
		}else if(size==0){
			Node newNode=new Node(null,o,null);
			head=newNode;
		}
		size++;
	}
	public void add(int index , Object o){
		checkPositionIndex(index);
		linkedBefore(o,node(index));
	}
	
	private void checkPositionIndex(int index){
		if(index<0&&index>size){
			throw new IndexOutOfBoundsException();
		}
	}
	
	private void checkElementIndex(int index){
		if(index<0&&index>=size){
			throw new IndexOutOfBoundsException();
		}
	}
	
	private Node node(int index){
		if(index<(size>>1)){
			Node x=head;
			for(int i=0;i<index;i++){
				x=x.next;				
			}
			return x;
		}else{
			Node x=end;
			for(int i=0;i<size-index-1;i++){
				x=x.pre;				
			}
			return x;
		}
	}
	
	public Object get(int index){
		checkElementIndex(index);
		return node(index).data;
	}
	public Object remove(int index){
		checkElementIndex(index);
		return unlink(node(index));
	}
	
	private Object unlink(Node x){
		final Object obj= x.data;
		final Node pre=x.pre;
		final Node next=x.next;
		if(pre==null){
			head=next;
		}else{
			pre.next=next;
			x.pre=null;
		}
		if(next==null){
			end=pre;
		}else{
			next.pre=pre;
			x.pre=null;
		}
		x.data=null;
		size--;
		return obj;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node newNode=new Node(null,o,head);
		head.pre=newNode;
		head=newNode;
		size++;
	}
	public void addLast(Object o){
		Node newNode=new Node(end,o,null);
		end.next=newNode;
		end=newNode;
		size++;
	}
	public Object removeFirst(){
		if (head == null){
            throw new NoSuchElementException();
		}
		Object data=head.data;
		Node newHead=head.next;
		head.next=null;
		newHead.pre=null;
		head=newHead;
		size--;
		return data;
	}
	public Object removeLast(){
		if (end == null){
            throw new NoSuchElementException();
		}
		Object data=end.data;
		Node newEnd=end.pre;
		end.pre=null;
		newEnd.next=null;
		end=newEnd;
		size--;
		return data;
	}
	public Iterator iterator(){
		return new Itr();
	}
	
	private class Itr implements Iterator{

		int cursor;
		@Override
		public boolean hasNext() {
			return cursor!=size;
		}

		@Override
		public Object next() {

			int i = cursor;
            if (i >= size){
                throw new NoSuchElementException();
            }
            cursor = i + 1;
            return LinkedList.this.node(i).data;
		}
		
	}
	
	
	private static class Node{
		Object data;
		Node next;
		Node pre;
		Node(Node pre,Object data,Node next){
			this.pre=pre;
			this.data=data;
			this.next=next;
		}
		
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
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(LinkedList list){
		return null;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

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
