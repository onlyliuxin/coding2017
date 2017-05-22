package com.coding.basic;

import java.util.Arrays;


public class LinkedList<T extends Comparable<T>> {
	
	private Node<T> head;//头结点
	private int size;//单链表中元素个数
	public LinkedList() {
		head = new Node<>();
		head.next=null;
		size=0;
	}
	public void add(T o){
		addLast(o);
		
	}
	public void add(int index , T o){
		rangeCheckAdd(index);
		Node<T> preNode=getNodeByIndex(index-1);
		Node<T> node=new Node<>();
		node.data=o;
		node.next=preNode.next;
		preNode.next=node;
		size++;
	}
	private Node<T> getNodeByIndex(int index) {
		rangeCheck(index);
		int count=-1;
		Node<T> node=head;
		while(count!=index){
			node=node.next;
			count++;
		}
		return node;
	}
	public T get(int index){
		return (T)getNodeByIndex(index).data;
	}
	public Node<T> getNodeByData(T object){
		Node<T> node=head;
		while(node.next!=null){
			node=node.next;
			if(node.data.equals(object))return node;
		}
		return null;
	}
	public Node<T> getPreNodeByData(T object){
		Node<T> preNode=head;
		while(preNode.next!=null){
			if(preNode.next.data.equals(object))return preNode;
			preNode=preNode.next;
		}
		return null;
	}
	public T remove(int index){
		rangeCheck(index);
		Node<T> preNode=getNodeByIndex(index-1);
		Node<T> node=preNode.next;
		preNode.next=preNode.next.next;
		size--;
		return (T)node.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(T o){
		Node<T> node=new Node<T>();
		node.data=o;
		node.next=head.next;
		head.next=node;
		size++;
	}
	public void addLast(T o){
		add(size, o);
	}
	public T removeFirst(){
		return remove(0);
	}
	public T removeLast(){
		return remove(size-1);
	}
	private class LinkedIterator implements Iterator{
		Node<T> nextNode=head.next;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return nextNode!=null;
		}
	
		@Override
		public T next() {
			// TODO Auto-generated method stub
			T object=(T)nextNode.data;
			nextNode=nextNode.next;
			return object;
		}
		
	}
	public Iterator iterator(){
		return new LinkedIterator();
	}
	private void rangeCheck(int index){//头结点为-1，用于get
		if(index<-1||index>size-1)throw new IndexOutOfBoundsException();
	}
	private void rangeCheckAdd(int index){//用于add
		if(index<0||index>size)throw new IndexOutOfBoundsException();
	}
	
	private static class Node<T extends Comparable<T>>{
		T data;
		Node<T> next;
	}
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){	
		Node<T> headNode=new Node<>();
		Node<T> curNode=head.next;
		while(curNode!=null){
			Node<T> nextNode=curNode.next;
			curNode.next=headNode.next;
			headNode.next=curNode;
			curNode=nextNode;	
		}
		head=headNode;
		
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int len=(int)size/2;
		int count=0;
		while(count++<len){
			head.next=head.next.next;
			size--;
		}
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		int count=0;
		Node<T> first=getNodeByIndex(i-1);
		while(count<length){
			if(first.next==null)return;
			first.next=first.next.next;
			count++;
			size--;
		}	
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(LinkedList<Integer> list){
		int[] tmp=new int[list.size];
		Iterator iterator=list.iterator();
		Node<T> curNode=head.next;
		int count=0;
		int curIndex=0;
		int arraysIndex=0;
		while(iterator.hasNext()){
			count=(int)iterator.next();
			while(count!=curIndex){
				if(curNode==null)break;
				curNode=curNode.next;
				curIndex++;
			}
			if(curNode!=null){
				tmp[arraysIndex++]=(Integer)curNode.data;
			}
			else break;
		}
		int[] answer=Arrays.copyOf(tmp, arraysIndex);
		return answer;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList<T> list){
		Iterator iterator=list.iterator();
		Node<T> preNode=head;
		while (iterator.hasNext()) {
			if(preNode==null)return;
			T tmp=(T)iterator.next();
			while(preNode.next!=null){
				if(preNode.next.data.compareTo(tmp)==0){
					preNode.next=preNode.next.next;
					size--;
					break;
				}
				else if(preNode.next.data.compareTo(tmp)<0)
					preNode=preNode.next;
				else break;
			}
		}
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node<T> node=head;
		if(node.next==null)return;
		node=node.next;
		while(node!=null){
			if(node.next==null)return;
			else if(node.data.compareTo(node.next.data)==0){
				node.next=node.next.next;
				size--;
			}
			node=node.next;
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(T min, T max){
		Node<T> preNode=head;
		Node<T> lastNode=null;
		if(preNode.next==null)return;
		while(preNode.next!=null){
			if(preNode.next.data.compareTo(min)>0)break;
			preNode=preNode.next;
			size--;
		}
		lastNode=preNode;
		if(lastNode.next==null)return;
		while (lastNode.next!=null) {
			if(lastNode.next.data.compareTo(max)>=0)break;
			lastNode=lastNode.next;
			size--;
		}
		preNode.next=lastNode;
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList<T> intersection( LinkedList<T> list){
		LinkedList<T> linkedList=new LinkedList<>();
		Node<T> node1=head;
		Node<T> node2=list.head;
		if(node1.next==null||node2.next==null)return linkedList;
		node1=node1.next;
		node2=node2.next;
		int count=0;
		while(node1!=null&&node2!=null){
//			System.out.print("node1: "+node1.data+"node2: "+node2.data);;
			
			if(node1.data.compareTo(node2.data)==0){
				linkedList.add(node2.data);
				node1=node1.next;
				node2=node2.next;
				
			}
			else if(node1.data.compareTo(node2.data)>0){
				node2=node2.next;
			}
			else {
				node1=node1.next;
			}
		}
		return linkedList;
	}
}
