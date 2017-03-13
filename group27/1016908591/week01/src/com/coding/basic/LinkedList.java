package com.coding.basic;



public class LinkedList implements List {
	
	private Node head;
	private int length;
	//构造函数
	public LinkedList(){
		clear();
	}
	public final void clear(){
		head = null;
		length = 0;
	}
	
	public void add(Object o){
		Node newNode = new Node(o);
		if(length == 0)
		{
			head = newNode;
		}
		else{
			Node lastNode = getNodeAt(length);
		   lastNode.next = newNode;
		
		}
		length++;
		
		
	}
	public void add(int index , Object o){
		Node newNode = new Node(o);
		Node nodeBefor = getNodeAt(index-1);
		Node nodeAfter = nodeBefor.next;
		newNode.next = nodeAfter;
		nodeBefor.next = newNode;
		length++;
		
		
	}
	public Object get(int index){
		if((1<=index)&&(index<=length))
		{
			Node currentNode = head;
			for(int i= 0;i<index;i++)
			{
				currentNode = currentNode.next;
						
			}
			return currentNode.data;
		}
		else
			return null;
		
		
		
	}
	public Object remove(int index){
		Node nodeBefor = getNodeAt(index-1);
		Node nodeToRemove = nodeBefor.next;
		Node nodeAfter = nodeToRemove.next;
		nodeBefor.next = nodeAfter;
		length--;
		return nodeToRemove.data;
	}
	
	public int size(){
		return length;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node(o);
		newNode.next = head;
		head = newNode;
		length++;
		
	}
	public void addLast(Object o){
		Node newNode = new Node(o);
		
			Node lastNode = getNodeAt(length);
			lastNode.next = newNode;
			length++;
		
		
	}
	public Object removeFirst(){
		head = head.next;
		length--;
		return head.data;
		
	}
	public Object removeLast(){
		Node nodeLastBefore = getNodeAt(length-1);
		Object result = nodeLastBefore.next.data;
		nodeLastBefore.next =null;
		length--;
		
		return result;
	}
	public Iterator iterator(){
		return null;
	}
	private Node getNodeAt(int givenPosition){
		
		if((1<=givenPosition)&&(givenPosition <=length)){
			Node currentNode = head;
			for (int counter = 1;counter<givenPosition;counter++)
				currentNode = currentNode.next;
			return currentNode;
		
		}else
			return null;
			
		
			
	}
	
	
	private static  class Node{
		private Object data;
		private Node next;
		private Node(Object dataPortion){
			data = dataPortion;
			next = null;
		}
		private Node(Object dataPortion,Node nextNode)
		{
			data = dataPortion;
			next = nextNode;
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
	public  LinkedList intersection( LinkedList list){
		return null;
	}
}
