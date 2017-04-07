package com.github.wdn.coding2017.basic;

/**
 * TODO 只是简单实现 缺少对边界的处理
 * 参考JDK源码改为更优雅的实现
 */

public class LinkedList implements List {
	
	private Node head;
	private Node tail;
	private int size;

	public LinkedList(){
		this.head=null;
		this.tail=null;
	}
	public void add(Object o){
		Node newNode = new Node(o);
		if (head == null) {
			head = newNode;
			tail = newNode;
		}else{
			tail.setNext(newNode);
			newNode.setPre(tail);
			tail = newNode;
		}
		size++;
	}
	public void add(int index , Object o){
		checkIndex(index);
		Node indexNode = getNode(index);
		Node newNode = new Node(o);
		Node pre = indexNode.getPre();
		if(pre!=null){
			pre.setNext(newNode);
		}else{
			head = newNode;
		}
		newNode.setPre(pre);
		newNode.setNext(indexNode);
		indexNode.setPre(newNode);
		size++;
	}
	private void checkIndex(int index){
		if(index >= size || index <0){
			throw new IndexOutOfBoundsException();
		}
	}
	private Node getNode(int index){
		checkIndex(index);
		// TODO这里可以优化，先判断index在前半部还是后半部分 然后确定从头部或者尾部查找
		Node result=null;
		if(index==0){
			return head;
		}
		if(index==size-1){
			return tail;
		}
		Node current = head;
		for (int i = 0; i < index; i++) {
			result = current.getNext();
			current = result;
		}
		return result;
	}
	public Object get(int index){
		return getNode(index).getData();
	}
	public Object remove(int index){
		checkIndex(index);
		Node indexNode = getNode(index);
		Node pre = indexNode.getPre();
		Node next = indexNode.getNext();
		if(pre!=null){
			pre.setNext(next);
		}else{
			head = next;
		}
		if(next!=null){
			next.setPre(pre);
		}else{
			tail = pre;
		}
		size--;
		return indexNode.getData();
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node(o);
		if (size==0){
			add(o);
			return;
		}
		head.setPre(newNode);
		newNode.setNext(head);
		head = newNode;
		size++;
	}
	public void addLast(Object o){
		if (size == 0) {
			add(o);
			return;
		}
		Node newNode = new Node(o);
		tail.setNext(newNode);
		newNode.setPre(tail);
		tail = newNode;
		size++;
	}
	public Object removeFirst(){
		if(size<1){
			throw new IllegalArgumentException();
		}
		if(size==1){
			tail=null;
		}
		Node next = head.getNext();
		Node oldHead = head;
		head.setPre(null);
		head = next;
		oldHead.setNext(null);
		size--;
		return oldHead;
	}
	public Object removeLast(){
		if(size<1){
			throw new IllegalArgumentException();
		}
		if(size==1){
			head=null;
		}
		Node oldTail = tail;
		Node pre = tail.getPre();
		tail = pre;
		tail.setNext(null);
		oldTail.setPre(null);
		size--;
		return oldTail;
	}
	public Iterator iterator(){

		return null;
	}
	
	/**
	 * JDK 中使用构造方法的方式设置next和pre减少不必要的getset方法更优雅
	 */
	private static  class Node{

		Object data;
		Node next;
		Node pre;
		public Node(){

		}
		public Node(Object data){
			this.data = data;
		}
		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPre() {
			return pre;
		}

		public void setPre(Node pre) {
			this.pre = pre;
		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		Node current = head;
		Node next = current.getNext();
		Node pre = current.getPre();
		while(next!=null){
			Node nNext = next.getNext();
			if(pre!=null){
				pre.setPre(current);
			}
			if(current!=null){
				current.setPre(next);
				current.setNext(pre);
			}
			if(next!=null){
				next.setNext(current);
			}
			pre = current;
			current = next;
			next = nNext;
		}
		Node oldHead = head;
		head = tail;
		tail = oldHead;
	 }

	 /**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int removeSize = size/2;
		for (int i = 0; i < removeSize; i++) {
			removeFirst();
		}
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param start 开始位置
	 * @param length 长度
	 */
	public  void remove(int start, int length){
		checkIndex(start);
		if(length<1){
			return;
		}
		if(start==0 && length>=size){
			int removeSum = size;
			for (int j = 0; j < removeSum; j++) {
				removeFirst();
			}
			size = size-length;
			return;
		}

		int customMaxIndex = start+length;
		int endIndex = customMaxIndex < size ? customMaxIndex : size;
		if(start==0){
			for (int j = 0; j < length; j++) {
				removeFirst();
			}
			size = size-length;
			return;
		}
		if(endIndex==size){
			int removeSum = size-start;
			for (int j = 0; j < removeSum; j++) {
				removeLast();
			}
			return;
		}
		Node startNode = getNode(start-1);
		Node endNode = getNode(endIndex);
		startNode.getNext().setPre(null);
		startNode.setNext(endNode);
		endNode.getPre().setNext(null);
		endNode.setPre(startNode);
		size = size-length;
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
		for (int i = 0; i < list.size; i++) {
			if(Integer.parseInt(list.get(i).toString())>=this.size){
				throw new IndexOutOfBoundsException();
			}
		}
		int[] result = new int[list.size];
		int index = 0;
		for (int i = 0; i < this.size; i++) {
			if (index==list.size()){
				break;
			}
			if(Integer.parseInt(list.get(index).toString())==i){
				result[index] = Integer.parseInt(this.get(i).toString());
				index++;
			}
		}
		return result;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		int index = 0;
		Object compare = list.get(index);
		Node current = head;
		while(current!=null && compare!=null){
			Node preCurrent = current;
			current = current.getNext();
			// TODO 这里不能删除重复元素只能删除一次
			if(preCurrent.getData().equals(compare)){
				preCurrent.getPre().setNext(preCurrent.getNext());
				preCurrent.getNext().setPre(preCurrent.getPre());
				preCurrent.setPre(null);
				preCurrent.setNext(null);
				size--;
				compare = ++index < list.size ? list.get(index) : null;
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node current = head;
		Node next = current.getNext();
		while (next!=null){
			if(next.getData().equals(current.getData())){
				Node preNext = next;
				next = preNext.getNext();
				current.setNext(preNext.getNext());
				if (next != null) {
					next.setPre(current);
				}
				preNext.setPre(null);
				preNext.setNext(null);
				size--;
			}else{
				current = next;
				next = next.getNext();
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
		int minIndex = -1;
		int maxIndex = -1;
		int index = 0;
		Node current = head;
		while (current!=null){
			if(current.getData().equals(min)){
				minIndex = index;
			}
			if(current.getData().equals(max)){
				maxIndex = index;
			}
			index++;
			current = current.getNext();
		}
		if (minIndex > -1 && maxIndex > -1 && min <= max && minIndex + 1 < size) {
			remove(minIndex + 1, maxIndex - minIndex - 1);
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		return null;
	}
	@Override
	public String toString(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("[");
		for (int i = 0; i < size; i++) {
			stringBuffer.append(get(i));
			if(i!=size-1){
				stringBuffer.append(",");
			}
		}
		stringBuffer.append("]");
		return stringBuffer.toString();
	}
}
