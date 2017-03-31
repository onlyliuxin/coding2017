package com.coding.basic;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList implements List {
	private Node head;  //首节点
	private int size;   //节点个数
	
	public void add(Object o){  //在链表尾部添加node
		if(head == null){
			head = new Node(o, null);
		}else {
			Node last = head;
			while(last.next != null){
				last = last.next;
			}
			last.next = new Node(o, null);
		}
		size ++;
	}

	public void add(int index , Object o){  //在指定索引处插入node
		if(index > size || index < 0) throw new RuntimeException("IndexOutOfBounds");
		if(head == null){
			head = new Node(o, null);
		}else {
			if(index == 0){  //插入位置在头部
				head = new Node(o, head);
			}else {  //后面位置插入
				Node temp = head;
				int i = 0;
				while(i != index - 1){
					temp = temp.next;
					i ++;
				}
				Node tempNext = temp.next;
				temp.next = new Node(o, tempNext);
			}
		}
		size ++;
	}

	public Object get(int index){  //取出指定节点处的元素,从0开始
		if(index > size -1 || index < 0) throw new RuntimeException("IndexOutOfBounds");
		int i = 0;
		Node temp = head;
		while(i != index){
			i ++;
			temp = temp.next;
		}
		return temp.data;
	}


	public Object remove(int index){  //删除指定索引处的节点
		if(index > size -1 || index < 0) throw new RuntimeException("IndexOutOfBounds");
		if(index == 0) {  //第一个元素或只有一个元素
			Object o = head.data;
			head = head.next;
			size --;
			return o;
		}else {  //其他元素
			int i = 0;
			Node temp = head;  //被删除节点之前的节点
			while(i != index - 1){
				i ++;
				temp = temp.next;
			}
			Node delete = temp.next; //被删除的节点
			Object o = delete.data;
			temp.next = delete.next; //删除
			size --;
			return o;
		}
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){  //在表头添加节点
		head = new Node(o, head);
		size ++;
	}

	public void addLast(Object o){  //在链表尾部添加节点
		if(head == null){
			head = new Node(o,null);
			size ++;
			return;
		}
		Node last = head;
		while(last.next != null){
			last = last.next;
		}
		last.next = new Node(o, null);
		size ++;
	}

	public Object removeFirst(){  //在链表头部删除节点
		if(size() == 0) throw new RuntimeException("Underflow");
		Object o = head.data;
		head = head.next;
		size --;
		return o;
	}

	public Object removeLast(){  //在链表尾部删除节点
		if(size() == 0) throw new RuntimeException("Underflow");
		if(size() == 1){
			Object o = head.data;
			head = null;
			size --;
			return o;
		}
		Node temp = head;
		int i = 0;
		while(i != size-2){
			temp = temp.next;
			i ++;
		}
		Object o = temp.next.data;
		temp.next = null;
		size --;
		return o;
	}

	public Iterator iterator(){ //迭代器
		return new ListIterator();
	}

	private class ListIterator implements Iterator{ //实例内部类
		private Node current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Object next() {
			if(size() == 0) throw new NoSuchElementException("Underflow");
			Object o = current.data;
			current = current.next;
			return o;
		}
	}
	
	//这里内部类须为static,在类级别上一一对应，非实例级别
	private static class Node{
		Object data;
		Node next;
		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){
		Node next = null;  //当前节点下面一个
		Node pre = null;  //当前节点前面一个

		while(head != null){
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		head = pre;
	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if(size() == 0 || size() == 1) return;

		int half = size()/2; //一半数目
		int sum = 0;  //已移除总个数
		while(sum != half){
			head = head.next;
			sum ++;
			size --;
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){
		if(length < 0 ) throw new RuntimeException("长度非法");
		if(i < 0 || i >= size() || (i+length >size())) throw new RuntimeException("索引越界");

		if(i == 0){
			int sum = 0;
			while(sum != length){
				head = head.next;
				sum ++;
				size --;
			}
			return;
		}
		Node pre = findNode(i-1);  //前一节点
		Node next = findNode(i+length); //后一节点
		pre.next = next;
		size -= length;
	}
	//查找某个位置的Node
	private Node findNode(int i){
		if(i < 0 ||i > size()) throw new RuntimeException("索引越界");
		if(i == size() ) return null;
		int index = 0;
		Node temp = head;
		while(index != i){
			temp = temp.next;
			index ++;
		}
		return temp;
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
		int sum = list.size();
		int[] arr = new int[sum];
		for (int i = 0; i < sum; i++) {
			Integer index = (Integer) list.get(i);
			Node temp = findNode(index);
			if(temp == null){
				throw new RuntimeException("索引越界");
			}
			arr[i] = (Integer) temp.data;
		}
		return arr;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素

	 * @param list
	 */
	public  void subtract(LinkedList list){
		for (int i = 0; i < list.size; i++) {
			int index = indexOf(list.get(i));
			if(index < 0) continue; //没找到相关节点
			if(index == 0){
				head = head.next;
				size --;
			}else {
				Node pre = findNode(index-1);
				Node next = findNode(index+1);
				pre.next = next;
				size --;
			}
		}
	}
	//索引对象，返回索引值
	private int indexOf(Object o){//返回索引
		for (int i = 0; i < size(); i++) {
			if(Objects.equals(o, get(i))){  //判断两个Object对象是否相等
				return i;
			}
		}
		return -1;
	}


	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		if(size() == 0 || size() == 1) return;
		Node current = head;

		while(current.next != null){
			if(Objects.equals(current.data, current.next.data)){
				current.next = current.next.next;
				size --;
			}else {
				current = current.next;
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
		if(size() == 0) return;
		if(((Integer)get(0)) >= max ) return;
		if( ((Integer)get(size()-1)) <= min ) return;

		if(((Integer)get(0)) <= min && ((Integer)get(size()-1)) < max && ((Integer)get(size()-1)) > min){
			for (int i = 1; i < size(); i++) {
				if(((Integer)get(i)) >min) {findNode(i-1).next = null;size = i;return;}
			}
		}else if(((Integer)get(0)) > min && ((Integer)get(0)) < max && ((Integer)get(size()-1)) >= max){
			for (int i = 0; i < size(); i++) {
				if(((Integer)get(i)) >= max){head = findNode(i);size -= 4;return;}
			}
		}else if(((Integer)get(0)) <= min && ((Integer)get(size()-1)) >= max){
			Node t1=null, t2 =null;
			int index1=0, index2=0;
			for (int i = 1; i < size(); i++) {
				if(((Integer)get(i)) > min){index1 = i;t1 = findNode(i-1);break;}
			}
			for (int i = 1; i < size(); i++) {
				if(((Integer)get(i)) >= max){index2 = i;t2 = findNode(i);break;}
			}
			t1.next = t2;
			size -= (index2-index1);
		}else {
			head = null;
			size = 0;
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		int[] arr1 = new int[this.size()];
		int[] arr2 = new int[list.size()];

		for (int i = 0; i < this.size(); i++) {
			Integer num = (Integer) this.get(i);
			arr1[i] = num;
		}
		for (int i = 0; i < list.size(); i++) {
			Integer num = (Integer) list.get(i);
			arr2[i] = num;
		}
		LinkedList l = new LinkedList();
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr2.length; j++) {
				if(arr1[i] == arr2[j]){
					l.add(arr1[i]);
					break;
				}
			}
		}
		return l;
	}


}
