package com.coding.basic.homework_03.linkedListImpl;

import com.coding.basic.homework_01.Iterator;
import com.coding.basic.homework_01.List;

/**
 * 重新实现的LinkedList
 * @author yanght
 *
 */
public class LinkedList implements List {
	
	private Node head; //头节点
	private Node end; //当前链表的末尾结点 
	private int num = 0; //链表中元素的个数
	
	/**
	 * 直接从链表的尾部添加节点
	 */
	public void add(Object o){
		Node node = new Node(o);
		if(head == null){ //当前List为空，从第一个节点开始添加
			head = end = node;
		}else{
			end.next = node;
			end = node;
		}
		num++;
	}
	
	/**
	 * 将结点添加到index处(从0开始)
	 */
	public void add(int index , Object o){
		if(index == num){
			add(o);
		}else if(index < num && index >= 0){
			addNode(index, o);
		}	
	}
	
	/**
	 * 在链表头部和中间添加节点
	 * @param index
	 * @param node
	 */
	private void addNode(int index,Object o){
		if(index == 0){
			addFirst(o);
		}else{
			Node node = new Node(o);
			Node tem = getNode(index - 1);
			Node pos = tem.next;
			tem.next = node;
			node.next = pos;
			num++;
		}
	}
	
	/**
	 * 根据索引获取节点
	 * @param index
	 * @return
	 */
	private Node getNode(int index){
		Node node = head;
		if(index < num){
			for(int i = 1; i<= index; i++){
				node = node.next;
			}
			return node;
		}
		return node;
	}
	
	public Object get(int index){
		if(index < 0 && index >= num){
			throw new IndexOutOfBoundsException();
		}
		return getNode(index).data;
	}
	
	public Object remove(int index){
		if(index < 0 && index >= num){
			throw new IndexOutOfBoundsException();
		}
		Object result = null;
		if(index == 0){
			removeFirst();
		}else if(index == num -1){
			removeLast();
		}else{
			Node pre = getNode(index - 1);
			result = pre.next.data;
			pre.next = pre.next.next;
			num--;
		}
		return result;
	}
	
	public int size(){
		return num;
	}
	
	public void addFirst(Object o){
		if(o != null){
			Node node = new Node(o);
			node.next = head;
			head = node;
			num++;
		}
	}
	
	public void addLast(Object o){
		add(o);
	}
	
	public Object removeFirst(){
		Object result = null;
		if(size() > 1){
			result = head.data;
			head = head.next;
			num--;
		}else if(size() == 1){
			result = head.data;
			head = end = null;
			num-- ;
		}
		return result;
	}
	
	public Object removeLast(){
		Object result = null;
		if(size() > 1){
			Node pre = getNode(num - 2);
			result = end.data;
			pre.next = null;
			end = pre;
			num--;
		}else if(size() == 1){
			result = head.data;
			head = end = null;
			num-- ;
		}
		return result;
	}
	
	
	public Iterator iterator(){
		return new Iterator(){
			private int cur = 0;
			private Node node = null;
			@Override
			public boolean hasNext() {

				return num - cur > 0;
			}

			@Override
			public Object next() {
				if(node == null){
					node = head;
				}else{
					node = node.next;
				}
				cur++;
				return node.data;
			}
		};
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		@SuppressWarnings("unused")
		public Node(){}
		public Node(Object data){
			this.data = data;
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		Object[] obj = new Object[num];
		Node node = head;
		for(int i = obj.length-1; i >= 0; i--){
			obj[i] = node.data;
			node = node.next;
		}
		node = head;
		for(int i = 0; i < obj.length; i++){
			node.data = obj[i];
			node = node.next;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		Node node = getNode(num / 2);
		head = node;
		if(num % 2 == 0){
			num = num / 2;
		}else{
			num = num / 2 + 1;
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if(num - length < i + 1){
			throw new IndexOutOfBoundsException();
		}
		if(num - length == i){ //i后面的全部删除
			Node node = getNode(i - 1);
			end = node;
			num = i;
		}else{
			Node pre = getNode(i - 1);
			Node pos = getNode(i + length);
			pre.next = pos;
			num = num - length;
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
	public static int[] getElements(LinkedList list, LinkedList listB){
		int[] result = new int[listB.size()];
		for(int i = 0; i < result.length; i++){
			result[i] =(int) list.getNode((Integer)listB.get(i)).data;
		}
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		for(int i = 0; i < list.size(); i++){
				findPos(list.get(i), this);
			}
	}
	
	/**
	 * 在原数组中找相同的元素
	 * @param pos 起始位置
	 * @param obj 要找的元素
	 * @param list 原数组
	 * @return 下一次要传入的位置
	 */
	private void findPos(Object obj, LinkedList list){
		for(int i = 0; i < list.size(); i++){
			if(compare(list.get(i), obj) == 0){
				list.remove(i);
				break;
			}
			if(compare(list.get(i), obj) > 0){
				break;
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		int pos = 0;
		while(pos < this.size() - 1){
			if(compare(this.get(pos), this.get(pos + 1)) == 0){
				this.remove(pos);
				continue;
			}
			pos++;
		}
	}
	
	/**
	 *比较大小 
	 * @param o1
	 * @param o2
	 * @return
	 */
	private int compare(Object o1, Object o2){
		int i1 = (Integer)o1;
		int i2 = (Integer)o2;
		return i1 > i2 ? 1 : (i1 < i2 ? -1 : 0);
	}
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		Integer start = null;
		Integer end = null;
		for(int i = 0; i < this.size(); i++){
			if(compare(this.get(i), min) > 0 && compare(this.get(i), max) < 0){
				if(start == null){
					start = end = i;
				}else{
					end = i;
				}
			}
		}
		remove(start, end - start);
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		int point1 = 0;
		int point2 = 0;
		LinkedList result = new LinkedList();
		while(point1 < this.size() && point2 < list.size()){
			if(compare(this.get(point1), list.get(point2)) < 0){
				point1++;
			}else if(compare(this.get(point1), list.get(point2)) > 0){
				point2++;
			}else{
				result.add(list.get(point2));
				point1++;
				point2++;
			}
		}
		return result;
	}
}











