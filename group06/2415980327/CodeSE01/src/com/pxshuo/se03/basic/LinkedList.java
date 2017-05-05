package com.pxshuo.se03.basic;

import com.pxshuo.se01.basic.Iterator;

public class LinkedList implements List {
	
	private Node head;
	private Node last;
	private int size = 0;
	
	private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
	
	private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }
	
	private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
	
	private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }
	
	private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
	
	/**
	 * 获取一个节点,不进行范围检查
	 * @param index 位置
	 */
	private Node getNode(int index){
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}
	
	/**
	 * 添加一个节点
	 * @param o
	 */
	public void add(Object o){
		Node node = new Node(o);
		Node l = last;
		last = node;
		
		if (head == null) {
			head = node;
		}
		else{
			l.next = last;
		}
		size ++;
	}
	
	/**
	 * 在某个位置进行插入
	 * @param index 位置
	 * @param o 	插入的元素
	 */
	public void add(int index , Object o){
		checkPositionIndex(index);
		
		if (size == index) {
			add(o);
		}
		else if (0 == index){
			addFirst(o);
		}
		else {
			Node node = new Node(o);
			Node preNode = getNode(index - 1);
			
			node.next = preNode.next;
			preNode.next = node;
			
			size++;
		}
	}
	
	/**
	 * 获取一个节点
	 * @param index 位置
	 */
	public Object get(int index){
		checkElementIndex(index);
		return getNode(index).data;
	}
	
	
	public Object remove(int index){
		checkElementIndex(index);
		Object data = null;
		if (index == 0) {
			data = removeFirst();
		}
		else if(index == size) {
			data = removeLast();
		}
		else {
			Node pre = getNode(index - 1);
			data = pre.next.data;
			pre.next = pre.next.next;
			size --;
		}
		return data;
	}
	
	public int size(){
		return size;
	}
	
	/**
	 * 添加第一个元素
	 * @param o
	 */
	public void addFirst(Object o){
		if (head == null) {
			add(o);
			return;
		}
		
		Node node = new Node(o);
		node.next = head;
		head = node;
		size ++;
	}
	/**
	 * 添加最后的元素
	 * @param o
	 */
	public void addLast(Object o){
		add(o);
	}
	/**
	 * 移除最初元素
	 * @return
	 */
	public Object removeFirst(){
		checkElementIndex(0);//检查首位是否有元素
		if (head == last) {
			last = null;
		}
		Object data = head.data;
		head = head.next;
		size --;
		return data;
	}
	
	/**
	 * 移除最后的元素
	 * @return
	 */
	public Object removeLast(){
		checkElementIndex(0);//检查首位是否有元素
		if (head == last) {
			return removeFirst();
		}
		Object data = last.data;
		last = getNode(size - 2);
		last.next = null;
		return data;
	}
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	
	public void display() {
		Node cur = head;
		int i = 0;
		while(cur != null && i < 1000)
		{
			System.out.println(i + ":" + cur.data.toString());
			cur = cur.next;
		}
	}
	
	public String getResult() {
		Node cur = head;
		int i = 0;
		String result = ""; 
		while(cur != null && i < 1000)
		{
			result += cur.data.toString();
			cur = cur.next;
			if (cur != null) {
				result += ",";
			}
		}
		
		return result;
	}
	
	private static  class Node{
		Object data;
		Node next;
		
		public Node(Object o){
			data = o;
		}
	}
	
	private class LinkedListIterator implements Iterator{
		Node position = null;
		
		public LinkedListIterator() {
			this.position = head;
		}
		
		@Override
		public boolean hasNext() {
			if (position == null) {
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			Object data = position.data;
			position = position.next;
			return data;
		}
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){
		checkElementIndex(0);
		if (size == 1) {
			return;
		}
		
		last = head;
		Node pre = null;
		Node next = null;
		while(head.next != null){
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		head.next = pre;	
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if (size < 2) {
			head = null;
			last = null;
			size = 0;
		}
		
		int half = size/2;
		Node halfPre = getNode(half - 1);
		head = halfPre.next;
		halfPre.next = null;
		size = size - half;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if (length < 1) {
			return;
		}
		checkElementIndex(i);
		checkElementIndex(i + length - 1);
		
		Node pre = null;//删除元素的前一个
		Node nextPre = getNode(i + length - 1);//删除元素的最后一个
		if (i != 0) {
			pre = getNode(i - 1);
		}
		
		if (pre != null) {
			pre.next = nextPre.next;
		}
		else {
			head = nextPre.next;
		}
		
		if (nextPre.next == null) {
			last = pre;
		}

		nextPre = null;
		size -= length;
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
		int[] listResult = new int[list.size];
		int i = 0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			int index = Integer.parseInt(iterator.next().toString());
			listResult[i] = Integer.parseInt(get(index).toString()); 
			i ++;
		}
		return listResult;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */	
	public void subtract(LinkedList list){
		int index = 0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object del = iterator.next();
			for (index = 0;index < size;index ++) {
				if (((String)get(index)).equals((String)del)) {
					remove(index);
				}
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		checkElementIndex(0);
		Node cur = head;
		while(cur.next != null){
			String curData = (String)(cur.data);
			String nextData = (String)(cur.next.data);
			if (curData.equals(nextData)) {
				size --;
				if (cur.next == last) {
					cur.next = null;
					last = cur;
				}else {
					cur.next = cur.next.next;
				}
			}
			else {
				cur = cur.next;
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
		checkElementIndex(0);
		Node minNode = null;//删除的前一个节点
		Node maxNode = null;
		Node cur = head;
		Node pre = null;
		int delSize = 0;
		
		while(cur != null){
			int curVal = Integer.parseInt((String)(cur.data));
			if (minNode == null && delSize == 0) {
				if (curVal > min) {//开始删除
					minNode = pre;
					delSize ++;
				}
			}
			
			if (delSize != 0) {
				if (curVal > max) {//结束删除
					maxNode = cur;
					break;//跳出循环
				}
				else {
					delSize ++;
				}
			}
			pre = cur;
			cur = cur.next;
		}
		//进行删除
		if (delSize - 1 == size) {//删除全部
			head = null;
			last = null;
			size = 0;
			return;
		}
		else if (delSize == 0) {//没有删除任务
			return;
		}
		else if (minNode == null) {//从头部开始删除
			head = maxNode;
		}
		else if (maxNode == null) {//删除尾部
			last = minNode;
			last.next = null;
		}
		else {//删除中间部分
			minNode.next = maxNode;
		}
		
		size -= delSize - 1;
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public LinkedList intersection( LinkedList list){
		LinkedList result = new LinkedList();
		Node cur = head;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object data = iterator.next();
			int dataNum = Integer.parseInt((String)data);
			while(cur != null){
				int curData = Integer.parseInt((String)(cur.data));
				if(curData == dataNum){
					result.add(data);
					break;
				}
				else if(dataNum < curData){
					break;
				}
				cur = cur.next;
			}
		}
		return result;
	}
}
