package com.liam.learn.code2017;



public class LinkedList implements List {
	private int size;
	
	private Node head;

	private Node tail;

	public void add(Object o){
		Node node = new Node(o, null);
		if(head == null){
			head = node;
			tail = node;
		}else{
			tail.setNext(node);
			tail = node;
		}
		size++;
	}
	public void add(int index , Object o){
		if (index < 0 || index >= size){
			throw new IllegalArgumentException("链表越界");
		}
		if (index == 0){
			addFirst(o);
		}else if(index == size-1){
			addLast(o);
		}else{
			Node indexNode = getNode(index);
			Node newNode = new Node(o, indexNode);
			Node previousNode = getNode(index-1);
			previousNode.setNext(newNode);
		}
		size++;
	}
	public Object get(int index){
		Node node = getNode(index);
		return node.getData();
	}

	private Node getNode(int index){
		if (index < 0 || index >= size){
			throw new IllegalArgumentException("链表越界");
		}
		if(index == 0){
			return head;
		}
		if(index == size-1){
			return tail;
		}
		Node temp = head;
		for(int i=0; i<index; i++){
			temp = temp.next;
		}
		return temp;
	}

	public Object remove(int index){
		if (index < 0 || index >= size){
			throw new IllegalArgumentException("链表越界");
		}

		if (index == 0){
			return removeFirst();
		}else if(index == size-1){
			return removeLast();
		}else{
			Node previousNode = getNode(index-1);
			Node nextNode = getNode(index+1);
			previousNode.setNext(nextNode);
			size--;
		}
		return get(index);
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node node = new Node(o, null);
		if(head == null){
			head = node;
			tail = node;
		}else{
			node.setNext(head);
			head = node;
		}
		size++;
	}
	public void addLast(Object o){
		Node node = new Node(o, null);
		if(head == null){
			head = node;
			tail = node;
		}else{
			tail.setNext(node);
			tail = node;
		}
		size++;
	}
	public Object removeFirst(){
		if (head == null){
			throw new RuntimeException("链表为空");
		}
		if (size ==1){
			Object ret = head.getData();
			head = null;
			tail = null;
			return ret;
		}
		Object headData = head.getData();
		head = getNode(1);
		size--;
		return headData;
	}
	public Object removeLast(){
		if (head == null){
			throw new RuntimeException("链表为空");
		}
		Object tailData = tail.getData();
		tail = getNode(size-2);
		size--;
		return tailData;
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		private Object data;
		private Node next;

		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
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
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		if(size == 0 || size ==1){
			return;
		}
		/*LinkedList linkedList = new LinkedList();
		for(int i=size-1; i>=0; i--){
			linkedList.add(getNode(i));
		}*/

		Node temp = head;
		head = tail;
		tail = temp;
		for(int i=size-2; i>=1; i--){
			getNode(i+1).setNext(getNode(i));
		}
		getNode(1).setNext(tail);
	}

	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int pre = size/2;
		for (int i=0; i<pre; i++){
			remove(i);
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		for (int x=i; x<i+length; i++){
			remove(i);
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
