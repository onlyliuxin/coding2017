package com.coding.basic.third;

import java.util.Iterator;


public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public void add(Object o){
		add(size,o);
	}
	public void add(int index , Object o){
		if(size<0 || size < index){
			new  ArrayIndexOutOfBoundsException();
		}
		Node node = new Node();
		node.data = o;
		if(head==null){
			head = node;
		}else{
			Node next = head.next;
			if(next == null){
				head.next = node;
			}else{
				while(next.next!=null){
					next = next.next;
				}
				next.next = node;
			}
		}
		size++;
	}
	public Object get(int index){
		if(size<0 || size < index){
			new  ArrayIndexOutOfBoundsException();
		}
		if(index == 0){
			return head.data;
		}
		int temp = 0;
		Node next = head;
		while(index > temp){
			temp++;
			next = next.next;
		}
		return next.data;
	}
	public Object remove(int index){
		if(index<0 || size < index){
			new  ArrayIndexOutOfBoundsException();
		}
		Node res = head;
		if(index == 0){
			head = head.next;
			size--;
			return res.data;
		}
		int temp = 1;
		Node next = head;
		while(index > temp){
			temp++;
			next = next.next;
		}
		res = next.next;
		next.next = next.next.next;
		size--;
		return res.data;
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
		Node result = head;
		head = head.next;
		return result.data;
	}
	public Object removeLast(){
		return remove(size-1);
	}
	public Iterator iterator(){
		return null;
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
		Object[] obj = new Object[size];
		int tem = 0;
		Node next = head;
		while(size>tem){
			obj[tem] = next.data;
			next = next.next;
			tem++;
		}
		Node nextF = head;
		for(int k=obj.length-1;k>=0;k--){
			nextF.data = obj[k];
			nextF = nextF.next;			
		}
		
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if(head == null || head.next ==null){
			return;
		}
		int len = size/2;
		int temp = 0;
		Node next = head;
		while(len<temp){
			temp++;
			next = next.next;
		}
		head = next;
		head.next = next.next;
		size = size-len;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int index, int length){
		if(index<0 || size < index){
			new  ArrayIndexOutOfBoundsException();
		}
		int temp = 1;
		Node next = head;
		while(index > temp){
			temp++;
			next = next.next;
		}
		Node nextF = next;
		
		int len = length;
		while(length>0){
			length--;
			next = next.next;
		}
		nextF.next = next.next;
		size = size-len;
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
		int[] ret = new int[list.size()];
		for(int i=0;i<ret.length;i++){
			ret[i] = (int) get((int)list.get(i));
		}
		return ret;
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
		Node next = head;
		while(next!= null && next.next!=null){
			if(next.data == next.next.data){
				next.next = next.next.next;
				size--;
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
		Node next = head;
		if (next == null || (int)next.data >= max) {
			return;
		}
		Node start = head;
		while(next!=null && (int)next.data < min){
			start = next;
			next = next.next;
		}
		Node end = head;
		while(next!=null && (int)next.data < max){
			end = next;
			next = next.next;
		}
		if(start ==null){
			head = end;
		}else{
		start.next = end.next;
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList linkedList = new LinkedList();
		int i = 0;
		Node next  = head;
		Node nextF  = list.head;
		while (next != null || nextF != null ){
			if (nextF != null && next != null) {
				if ((int)next.data < (int)nextF.data ) {
					linkedList.add(next.data);
					next = next.next;
				} else if(next == null ||(int)nextF.data < (int)next.data) {
					linkedList.add(nextF.data);
					nextF = nextF.next;
				} else {
					linkedList.add(nextF.data);
					next = next.next;
					nextF = nextF.next;
				}
			}else {
				if (nextF == null) {
					linkedList.add(next.data);
					next = next.next;
				} else {
					linkedList.add(nextF.data);
					nextF = nextF.next;
				}
			}
		}
		return linkedList;
	}
}
