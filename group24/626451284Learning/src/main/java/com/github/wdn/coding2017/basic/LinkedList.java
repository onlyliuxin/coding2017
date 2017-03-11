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
		for (int i = 0; i < index; i++) {
			result = head.getNext();
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
		return indexNode.getData();
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node(o);
		head.setPre(newNode);
		newNode.setNext(head);
		head = newNode;
	}
	public void addLast(Object o){
		Node newNode = new Node(o);
		tail.setNext(newNode);
		newNode.setPre(tail);
		tail = newNode;
	}
	public Object removeFirst(){
		Node next = head.getNext();
		Node oldHead = head;
		head = next;
		head.setPre(null);
		return oldHead;
	}
	public Object removeLast(){
		Node oldTail = tail;
		Node pre = tail.getPre();
		tail = pre;
		tail.setNext(null);
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
