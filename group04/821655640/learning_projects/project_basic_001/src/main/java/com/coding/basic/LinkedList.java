package com.coding.basic;

import java.util.concurrent.CyclicBarrier;


/** 
 * @ClassName: LinkedList 
 * @Description: 带头结点的单向列表.
 * @author: tangxp
 * @date: 2017年2月23日 下午9:14:28  
 */
public class LinkedList implements List {
	
	private Node head = new Node();
	private int size ;
	
	public void add(Object o){
		add(size,o);
	}
	
	public void add(int index , Object o){
		if(index<0 || index>size) {
			throw new IllegalArgumentException("目前链表长度不够!");
		}
		Node tempHead = head;
		int i = 0;
		while(i++ < index) {
			tempHead = tempHead.next;
		}
		addDriect(getNode(o),tempHead);
	}


	/**
	 * @Title: get	
	 * @Description: TODO
	 * @param index
	 * @return 
	 * @see com.coding.basic.List#get(int) 
	 */
	public Object get(int index){
		if(index<0|| index>=size) {
			throw new IllegalArgumentException("下标超出链表范围!");
		}
		Node tempHead = head;
		int i = 0;
		while(i++ < index) {
			tempHead = tempHead.next;
		}
		return tempHead.next.data;
	}
	
	public Object remove(int index){
		if(index<0  || index>=size) {
			throw new IllegalArgumentException("下标超出链表范围!");
		}
		Node tempHead = head;
		int i = 0;
		while(i++ < index) {
			tempHead = tempHead.next;
		}
		
		Node removingNode = tempHead.next;
		tempHead.next = removingNode.next;
		removingNode.next = null;
		size--;
		return removingNode.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(0,o);
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
		return null;
	}
	
	private void addDriect(Node n,Node before) {
		Node temp =  before.next;
		n.next = temp;
		before.next = n;
		size++;
	}
	
	private Node getNode(Object o) {
		
		if(null == o) {
			throw new IllegalArgumentException("节点值不能为空");
		}
		
		Node  n = new Node();
		n.data = o;
		return n;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("size: "+size+" {null | ---}--->");
		if(null == head.next) {
			return sb.toString();
		}
		
		Node tempHead = head;
		while(null != tempHead.next ) {
			sb.append(tempHead.next.toString());
			tempHead = tempHead.next;
		}
		sb.append("null");
		return sb.toString();
	}
	
	private static  class Node{
		Object data;
		Node next;
		
		@Override
		public String toString() {
			return "{" + this.data +"  |---}--->";
		}
	}
	

	//数据结构习题
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  boolean reverse(){
		if (this.size()<=0) {
			return false;
		}
		Stack stack = new Stack();
		
		while(this.size()>0) {
			stack.push(this.removeFirst());
		}
		
		while(stack.size()>0) {
			this.addLast(stack.pop());
		}
		
		return true;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  boolean removeFirstHalf(){
		if (this.size()<=0) {
			return false;
		}
		//计算中间位置
		int tempCount =  this.size();
		tempCount = tempCount%2 == 0 ? tempCount/2 : (tempCount-1)/2;
		tempCount--;
		while(tempCount >= 0) {
			this.removeFirst();
			tempCount --;
		}
		return true;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(i<0|| i>=size) {
			throw new IllegalArgumentException("下标超出链表范围!");
		}
		if (!(length>0|| (i+1+length)<=this.size())) {
			throw new IllegalArgumentException("参数非法!");
		}
		
		Node tempHead = head;
		head = getNode(i-1);
		while(length-->0){
			this.removeFirst();
		}
		head = tempHead;
	}
	
	/**
	 * 获取第i个元素的引用
	 */
	public Node getNode(int index) {
		if(index<0|| index>=size) {
			throw new IllegalArgumentException("下标超出链表范围!");
		}
		Node tempHead = head;
		int i = 0;
		while(i++ < index) {
			tempHead = tempHead.next;
		}
		
		return tempHead.next;
	}
	
	
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  Integer[] getElements(LinkedList list){
		int listSizeB = list.size();
		int i=0;
		Integer res[] = new Integer[listSizeB];
		while(listSizeB-- > 0) {
			res[i] = (Integer) this.get((Integer)list.get(i));
			i++;
		}
		return res;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 
	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		Node tempNodePre = this.head;
		Node tempNode = this.head.next;
		Node tempNodeB = list.head.next;
		Node temp = null;
		while(null != tempNode && null != tempNodeB) {
			Integer a = (Integer) tempNode.data;
			Integer b = (Integer) tempNodeB.data;
			if (a < b) {
				tempNodePre = tempNodePre.next;
				tempNode = tempNode.next;
			} else if(a > b){
				tempNodeB = tempNodeB.next;
			}else {
				temp = tempNode;
				tempNodePre.next = tempNode.next;
				tempNode = tempNode.next;
				temp.next = null;
				temp = null;
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node tempPre = this.head;
		Node tempCur = this.head.next;
		Node temp = null;
		while(null != tempCur) {
			Integer a = (Integer) tempPre.data;
			Integer b = (Integer) tempCur.data;
			if(a == b) {
				temp = tempCur;
				tempPre.next = tempCur.next;
				tempCur = tempCur.next;
				temp.next = null;
				temp = null;
			}else {
				tempPre = tempPre.next;
				tempCur = tempCur.next;
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
		if (min<0 || max<min || this.size()<2) {
			throw new IllegalArgumentException();
		}
		
		Node pre = this.head.next;
		Node cur = this.head.next.next;
		Node preMin = null;
		Node preMax = null;
		while(null != cur) {

			Integer a = (Integer) pre.data;
			Integer b = (Integer) cur.data;
			
			if (a<min && b>=min) {
				preMin = pre;
			}
			if (a<=max && b>max) {
				preMax = pre;
			}
			pre = pre.next;
			cur = cur.next;
		}
		preMin.next = preMax.next;
		preMax = null;
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList listB){
		Node nodeA = this.head.next;
		Node nodeB = listB.head.next;
		LinkedList listC = new LinkedList();
		while (null != nodeA && null != nodeB) {
			Integer a = (Integer) nodeA.data;
			Integer b = (Integer) nodeB.data;
			if (a>b) {
				nodeB = nodeB.next;
			} else if(a<b){
				nodeA = nodeA.next;
			}else {
				listC.add(nodeA.data);
				nodeA = nodeA.next;
				nodeB = nodeB.next;
			}
		}
		return listC;
	}
	
}
