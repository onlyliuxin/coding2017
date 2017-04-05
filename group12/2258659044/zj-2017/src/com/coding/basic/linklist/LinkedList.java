package com.coding.basic.linklist;

import java.util.NoSuchElementException;

import com.coding.basic.Iterator;
import com.coding.basic.List;
import com.coding.basic.Stack;

public class LinkedList implements List {
	
	private Node head;
	
	private int size = 0;
	
	public void add(Object o){
		
		Node addNode = new Node();
		addNode.data = o;
	    if(size==0){	    	
	    	head = addNode;
	    }else{
	    	//获取最后一个节点
	    	Node lastNode = getPointNode(size-1);
	    	lastNode.next = addNode;	    		    	
	    }
	    size++;
	}
	public void add(int index , Object o){
		
		Node addNode = new Node();
		addNode.data = o;
		if(index == 0){	//添加头结点		
			addFirst(o);	
	    }else if(index == size){//添加尾节点
        	addLast(o);
		}else{//在投节点与尾部添加节点			
			Node prePointNode = getPointNode(index-1);
			Node pointNode = prePointNode.next;
			prePointNode.next = addNode;
			addNode.next = pointNode;	
			size ++;
		}			
	}
	public Object get(int index){

		Node node = getPointNode(index);
		return node.data;
	}
	
	public Object remove(int index){
		
		Node pointNode = getPointNode(index);
		Node nextPointNode = pointNode.next;
		if(index ==0){
			head = nextPointNode;			 
		}else{
			Node prePointNode = getPointNode(index-1);			
			prePointNode.next = nextPointNode;
		}						
		size --;
		return pointNode.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){	
		
		Node secondNode = head;	
		head = new Node();
		head.data = o;
		if(size>0){		
			head.next = secondNode;
		}				
		size ++;
	}
	
	public void addLast(Object o){
		add(o);
	}
	
	public Object removeFirst(){

		return remove(0);
	}
	
	public Object removeLast(){
		
		return remove(size-1);
	}
	public Iterator iterator(){
		return new Itr();
	}
	
	private class Itr implements Iterator{

		int cursor;
		@Override
		public boolean hasNext() {
			return cursor != LinkedList.this.size;
		}

		@Override
		public Object next() {
			
			int i = this.cursor;
			if (i >= LinkedList.this.size){
				throw new NoSuchElementException();
			}			
			this.cursor = (i + 1);
			return LinkedList.this.get(i);
		}
		
	}
	
	/**
	 * 获取指定的节点
	 * @return
	 */
	private Node getPointNode(int index){

		if(index<0||index>size){
			throw new IndexOutOfBoundsException("Index: "+index+",Size:"+size+"");	
		}
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
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
		
		Stack stack = new Stack();
		Node node;
		//缓存原链表数据
		for (node = head; node!=null;node = node.next) {
			stack.push(node.data);
		}
		//重新赋值
		for (node = head; node!=null;node = node.next) {
			node.data = stack.pop();
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	public  void removeFirstHalf(){
		int newSize = size/2;
		head = getPointNode(newSize);
		size = size%2>0?newSize+1:newSize;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
		if(i==0){
			if(length<size){
				head = getPointNode(length);
				size = size - length;
			}else{
				head = null;
				size = 0;
			}			
		}else{
			if(length<(size-i)){
				getPointNode(i-1).next = getPointNode(i+length);
				size = size-length;
			}else{
				getPointNode(i-1).next = null;
				size = i;
			}
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
	public int[] getElements(LinkedList list){
		int[] array = new int[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) get((int)list.get(i));
		}
		return array;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 
	 * @param list
	 */
	
	public  void subtract(LinkedList list){		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < list.size(); j++) {
				if(get(i).equals(list.get(j))){
					remove(i);
					i--;
				}
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		
		for (int i = 0; i < size-1; i++) {
			if(get(i).equals(get(i+1))){
				remove(i);
				i --;
			}
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		
		for (int i = 0; i < size; i++) {
			if((int)get(i)>min&&(int)get(i)<max){
				remove(i);
				i--;
			}
		}
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		
		LinkedList newList = new LinkedList();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < list.size(); j++) {
				if(get(i).equals(list.get(j))){
					newList.add(get(i));
				}
			}
		}
		return newList;
	}
}