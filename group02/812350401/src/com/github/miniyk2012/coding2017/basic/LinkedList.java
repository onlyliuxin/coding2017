package com.github.miniyk2012.coding2017.basic;


public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	/**
	 * node接收的index一定是范围内的值，不可能越界
	 * @param index
	 * @return a Node
	 */
	Node node(int index) {
		Node x = head;
		for (int i=0; i<index; i++) {
			x = x.next;
		}
		return x;
	}
	
	public void add(Object o){
		add(size, o);
	}
	
	public void add(int index , Object o){
		checkPositionIndex(index);
		Node after = node(index);
		Node newNode = new Node(o, after);
		if (index == 0) {
			head = newNode;
		}
		else {
			Node before = node(index-1);
			before.next = newNode;
		}
		size++;
	}
	
	public Object get(int index){
		checkElementIndex(index);
		Node thisNode = node(index);
		return thisNode.data;
	}
	
	public Object remove(int index){
		checkElementIndex(index);
		Node toRemove = node(index);
		if (index == 0)
			head = toRemove.next;
		else {
			Node before = node(index-1);
			before.next = toRemove.next;
		}
		size--;
		return toRemove.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		add(0, o);
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
		Iterator iterator = new IteratorImp(this);
		return iterator;
	}
	
	private static class Node{
		Object data;
		Node next;
		
		Node(){}
		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }
	
	private void checkPositionIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
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
	public void remove(int i, int length){
		
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
