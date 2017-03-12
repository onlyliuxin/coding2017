package week1_0226;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	public void add(Object o){
		if (head == null) {
			head = new Node(o);
		} else {
			//遍历到链表的尾部
			Node tail = head;
			while (tail.next != null) {
				tail = tail.next;
			}
			Node node = new Node(o);
			tail.next = node;
		}
		size ++;
	}
	public void add(int index , Object o){
		rangeCheckForAdd(index);
		if (index ==0) {
			Node node = new Node(o);
			node.next = head;
			head = node;
		} else {
			Node preHead = head;
			for (int i = 0; i < index -1; i ++){
				preHead = head.next;
			}
			Node node = new Node(o);
			node.next = preHead.next;
			preHead.next = node;
		}
	}
	public Object get(int index){
		rangeCheck(index);
		Node dest = head;
		for (int i = 0; i< index; i++){
			dest = dest.next;
		}
		return dest.data;
	}
	private void rangeCheck(int index){
		if (index >= size || index <0){
			throw new IndexOutOfBoundsException(); 
		}
	}
	public Object remove(int index){
		rangeCheck(index);
		Node preDest = head;
		for (int i = 0; i < index; i++){
			preDest = preDest.next;
		}
		Node dest = preDest.next;
		preDest.next = dest.next;
		size --;
		return dest;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o);
		node.next = head;
		head = node;
		size ++;
	}
	public void addLast(Object o){
		Node lastNode = head;
		while (lastNode.next != null){
			lastNode = lastNode.next;
		}
		Node node = new Node(o);
		lastNode.next = node;
		size++;
	}
	public Object removeFirst(){
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node headTmp = head;
		head = head.next;
		size --;
		return headTmp;
	}
	public Object removeLast(){
		if (head == null) {
			throw new NoSuchElementException();
		}
		if (head.next == null) {
			Node dest = head;
			head = null;
			size --;
			return dest;
		}
		Node preLastNode = head;
		while(preLastNode.next.next != null) {
			preLastNode = preLastNode.next;
		}
		Node dest = preLastNode.next;
		preLastNode.next = null;
		size --;
		return dest;
	}
	public Iterator iterator(){
		return null;
	}
	private void rangeCheckForAdd(int index){
		if (index > size || index <0){
			throw new IndexOutOfBoundsException();
		}
	}
	
	private static  class Node{
		Object data;
		Node next;
		Node (Object data) {
			this.data = data;
			next = null;
		}
	}
	/********************************************************************************************************************************************
	 * 
	 * 第二次作业
	 * 
	 * 
	 * 
	 */
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		Node reversedNode = null;
		while (head != null) {
			Node temp = head;
			head = head.next;
			temp.next = reversedNode;
			reversedNode = temp;
		}
		head = reversedNode;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int newStartIndex = size/2;
		for (int i =0;i<newStartIndex;i++){
			head = head.next;
		}
		size = size - newStartIndex;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if (i< 0) {
			throw new IllegalArgumentException();
		}
		if (i +length > size) {
			length = size -i;
		}
		if (i == 0) {
			for (int j = 0; j< length; j ++) {
				head = head.next;
			}
		} else {
			Node startNode = (Node) this.get(i);
			Node endNode = (Node) this.get(i + length);
			startNode.next = endNode;
		}
		size = size - length;
		
		
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
		checkList(list);
		int[] dest = new int[list.size];
		int arrayIndex = 0;
		while (list.head != null) {
			dest[arrayIndex] = (int)this.get((int)list.head.data);
			++ arrayIndex;
			list.head = list.head.next;
		}
		return dest;
 	}
	
	private void checkList(LinkedList list) {
		for (int i = 0; i <list.size; i++){
			rangeCheck((int)list.get(i));
		}
		
	}
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		if (list == null || list.size == 0 || this.size == 0) {
			return;
		}
		int index = 0;
		int listIndex = 0;
		Node temp = head;
		while (true) {
			if((int)temp.data <= (int)list.get(listIndex)){
				index ++;
				temp = temp.next;
			} else if ((int)temp.data == (int)list.get(listIndex)) {
				this.remove(index);
				index ++;
				listIndex ++;
			} else if (index >= this.size || listIndex >= list.size) {
				break;
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		if (this.size == 0) {
			return;
		}
		Node subHead = head;
		Node subTail = head;
		while(true) {
			if (subTail == null) {
				subHead.next = null;
				break;
			}
			if ((int)subTail.data == (int)subHead.data) {
				if (!(subTail ==subHead)){
					this.size --;
				}
				subTail = subTail.next;
			} else {
				subHead.next = subTail;
				subHead = subHead.next;
			}
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	//一地bug。。。
	public  void removeRange(int min, int max){
		if (this.size ==0) {
			return;
		}
		if ((int)head.data > max) {
			throw new IllegalArgumentException();
		}
		Node temp = head;
		int index = 0;
		int from = 0;
		int to = 0;
		while (temp != null) {
			if ((int)temp.data <= min) {
				temp = temp.next;
			} else if ((int)temp.data > min && from == 0){
				from = index;
			}
			if ((int)temp.data < max && from > 0) {
				temp = temp.next;
			} else if((int)temp.data > max && to == 0) {
				to = index;
			}
			++ index;
		}
		if (to == 0) {
			this.remove(from, this.size - from);
		} else {
			this.remove(from, to - from);
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		if (this.size ==0 || list.size == 0){
			return null;
		}
		Node tempHead = head;
		int listIndex = 0;
		LinkedList newList = new LinkedList();
		while (true) {
			if(tempHead == null || list.size < listIndex) {
				break;
			}
			if ((int)tempHead.data < (int)list.get(listIndex)){
				tempHead = head.next;
			} else if ((int)tempHead.data > (int)list.get(listIndex)){
				listIndex ++;
			} else if ((int)tempHead.data == (int)list.get(listIndex)){
				newList.add(tempHead);
				tempHead = tempHead.next;
				++ listIndex;
			}
		}
		
		return newList;
	}
}
