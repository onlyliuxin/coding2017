package com.coding.basic.linklist;

import java.util.Arrays;
import java.util.Stack;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class LinkedList implements List {
	private Node head;
	private int size;
	
	public LinkedList(){
		this.head = null;
		this.size = 0;
	}
	
	private static class Node {
		Object data;
		Node next;
		
		private Node(Object data){
			this.data = data;
			this.next = null;
		}
	}
	
	public void add(Object o){
		Node node = new Node(o);
		if(head == null){
			head = node;
		}else{
			Node pNode = head;
			while(pNode.next != null){
				pNode = pNode.next;
			}
			pNode.next = node;
		}
		size++;
	}

	public void add(int index , Object o){
		checkIndex(index);
		
		Node newNode = new Node(o);
		Node node = new Node(null);
		Node pNode = head;
		for(int i = 0; i < index; i++){
			node = pNode;
			pNode = pNode.next;
		}
		
		node.next = newNode;
		newNode.next = pNode;
		size++;
	}
	public Object get(int index){
		checkIndex(index);
		
		Node pNode = head;
		for(int i = 0; i < index; i++){
			pNode = pNode.next;
		}
			
		return pNode.data;
	}
	public Object remove(int index){
		checkIndex(index);
		if(head == null){
			return null;
		}
		
		if(index == 0){
			removeFirst();
		}//忘了考虑这种情况了
		
		Node node = new Node(null);
		Node pNode = head;
		for(int i = 0; i < index; i++){
			node = pNode;
			pNode = pNode.next;
		}
		node.next = pNode.next;
		size--;
		
		return pNode.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node(o);
		
		if(head == null){
			head = newNode;
		}else{
			newNode.next = head;
			head = newNode;	
		}
		size++;
	}
	
	public void addLast(Object o){
		Node newNode = new Node(o);
		if(head == null){
			head = newNode;
		}
		
		Node pNode = head;
		while(pNode.next != null){
			pNode = pNode.next;
		}
		pNode.next = newNode;
		newNode.next = null;
		size++;
	}
	
	public Object removeFirst(){
		if(head == null){
			return null;
		}
		
		Node pNode = head;
		head = pNode.next;
		head.next = pNode.next.next;
		size--;
		return pNode.data;
	}
	
	public Object removeLast(){
		if(head == null){
			return null;
		}
		
		Node pNode = head;
		Node node = new Node(null);
		while(pNode.next != null){
			node = pNode;
			pNode = pNode.next;
		}
		
		node.next = null;
		size--;
		return pNode.data;
	}
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	
	//注意这一个问题
	public class LinkedListIterator implements Iterator {
		private int position;
		
		@Override
		public boolean hasNext() {
			return position < size();
		}

		@Override
		public Object next() {
			if(hasNext()){
				return get(position++);
			}
			return null;
		}
		
	}
	
	public void checkIndex(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){	
		if(head == null){
			return;
		}
		
		Stack<Node> s = new Stack<>();
		
		Node currentNode = head;
		while(currentNode != null){
			s.push(currentNode);
			
			Node nextNode = currentNode.next;
			currentNode.next = null; //把链断开
			currentNode = nextNode;
		}
		
		head = s.pop();
		
		currentNode = head;
		while(!s.isEmpty()){
			Node nextNode = s.pop();
			currentNode.next = nextNode;
			currentNode = nextNode;
		}
	}
		
	/*if(head == null || head.next == null){
		return;
	}
	
	Node next = null;//当前节点的后一个节点
	Node pre = null;//当前节点的前一个节点
	
	while(head != null){
		next = head.next;
		head.next = pre;
		pre = head;
		head = next;
	}
	head = pre;
	}*/
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if(head == null || head.next == null){
			return;
		}
		
		for(int i = 0; i <= size/2; i++){
			removeFirst();
			size--;
		}
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(head == null){
			return;
		}
		
		for(int k = i; k < i + length; k++){
			checkIndex(k);
			remove(k);
		}
		size -= length;
	}
	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * list = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(LinkedList list){
		if(list == null){
			return new int[0];
		}
		
		int[] array = new int[list.size];
		int count = 0;
		for(int i = 0; i < list.size; i++){
			int index = (int) list.get(i);
			if(index > -1 && index < size){
				array[count] = (int) get(index);
				count++;
			}
		}
		
		return Arrays.copyOf(array, count); //Arrays.copyOf(a,count)截取数组a的count长度
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		if(list == null){
			return;
		}
		
		for(int i = 0; i < list.size; i++){
			for(int j = 0; j < size; j++){
				if(list.get(i).equals(get(j))){
					remove(j);
					size --;
				}
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		if(head == null){
			return;
		}
	
		for(int i = 0; i < size; i++){
			for(int j = i+1; j < size; j++){
				if(get(i).equals(get(j))){
					remove(j);
					size--;
				}
			}
		}
		/*if(head == null){
			throw new RuntimeException("LinkedList is empty!");
		}else{
			Node pre = head;
			Node cur = head;
			while(cur.next != null){
				cur = cur.next;
				Object data = pre.data;
				while(cur.data == data){
					if(cur.next == null){
						pre.next = null;
						break;
					}
					pre.next = cur.next;
					size--;
					cur =cur.next;
					if(cur == null){
						break;
					}
				}
				pre = pre.next;
			}
		}
		*/
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		if(head == null){
			return;
		}
		
		Node pre = head;
		Node cur = head;
		
		while(cur.next != null){
			cur = cur.next;
			int data = (int)cur.data;
			if(data < max && data > min){
				pre.next = cur.next;
				cur = cur.next;
			} else {
				pre = pre.next;
				cur = cur.next;
			}
		}

	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection(LinkedList list){
		if(head == null || list.size ==0 ){
			return null;
		}
		
		LinkedList result = new LinkedList();
		
		int i = 0;
		int j = 0;
		
		while(i < this.size && j < list.size()){
			
			int value1 = (int)this.get(i);
			int value2 = (int)list.get(j);
			
			if(value1 == value2){
				result.add(value1);
				i++;
				j++;
			} else if (value1 < value2) {
				i++;
			} else {
				j++;
			}
		}
		return result;
	}
	
	/*
	 * 为了测试方便，引入toString()方法
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		Node node = head;
		while(node != null){
			buffer.append(node.data);
			if(node.next != null){
				buffer.append(",");
			}
			node = node.next;
		}
		buffer.append("]");
		
		return buffer.toString();
	}
	
	public static void main(String args[]){
		LinkedList list7 = new LinkedList();
		
		list7.add(1);
		list7.add(2);
		list7.add(3);
		list7.add(4);
		list7.add(5);
		list7.add(6);
		list7.add(12);
		
		for(int i = 0; i < list7.size(); i++){
			System.out.println(list7.get(i));
		}
	}
}


