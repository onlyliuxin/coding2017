package com.dataStructure.linkedList;

import java.util.Objects;



public class LinkedList<T> implements List<T> {
	
	private Node head;
	
	private int size = 0;
	
	public LinkedList(){
		this.head = new Node(null,null);
	}
	
	public boolean add(T o){
		
		if(head.next == null){
			Node element = new Node(o,null);
			head.next = element;
			size++;
			return true;
		}
		
		Node current = head.next;
		while(current != null){
			if(current.next==null){
				Node element = new Node(o,null);
				current.next = element;
				size++;
				return true;
			}
			current = current.next;
		}
		
		return false;
	}
	

	private void rangeCheck(int index) {
		if (index < -1 || index > size - 1)
			throw new IndexOutOfBoundsException(" index ");
	}
	public boolean add(int index , T o){
		rangeCheck(index);
		
		Node node = getNode(index);
		Node pre = getNode(index-1);
		Node newNode = new Node(o,node);
		pre.next = newNode;
		size++;
		return true;
	}
	
	
	private Node getNode(int index){
		rangeCheck(index);
		Node current = head.next;
		int count = 0;
		while(current!=null){
			if(count==index){
				return current;
			}
			count++;
			current = current.next;
		}
		return null;
	}
	
	public T get(int index){
		Node node = getNode(index);
		return (T)node.data;
	}
	public T remove(int index){
		rangeCheck(index);
		
		Node pre = getNode(index-1);
		Node cur = getNode(index);
		Node next = cur.next;
		pre.next = next;
		cur.next = null;
		size--;
		return (T)cur.data;
	}
	
	
	public T remove(T o) {
		int index = 0;
		for (Node x = head.next; x != null; x = x.next) {
			if (Objects.deepEquals(x.data, o)) {
				return remove(index);
			}
			index++;
		}
		size--;

		return null;
	}
	
	@Override
	public T set(int index, T element) {
		Node node = getNode(index);
		node.data = element;
		
		return (T)node.data;
	}

	@Override
	public boolean contains(Object o) {
		
		return indexOf(o)!=-1;
	}

	@Override
	public int indexOf(Object o) {
		int index = 0;

		for (Node x = head.next; x != null; x = x.next) {
			if (Objects.deepEquals(x.data, o))
				return index;
			index++;
		}
		return -1;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		int i = 0;
		for(Node x = head.next; x != null; x = x.next){
			result[i++] = x.data;
		}
		return null;
	}

	@Override
	public void clear() {
		for(Node cur = head.next;cur!=null;cur = cur.next){
			Node x = cur;
			x.data = null;
			x.next = null;
		}
		head = null;
		size = 0;
	}

	
	public int size(){
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public void addFirst(Object o){
		Node newFirst = new Node(o,null);
		Node oldFirst = head.next;
		head.next = newFirst;
		newFirst.next = oldFirst;
		size++;
	}
	public void addLast(Object o){
		Node last = getNode(size-1);
		Node newLast = new Node(o,null);
		last.next = newLast;
		size++;
	}
	public T removeFirst(){
		Node oldFirst = head.next;
		Node nextNode = oldFirst.next;
		head.next = nextNode;
		size--;
		return (T)oldFirst;
	}
	public T removeLast(){
		Node x = getNode(size-2);//倒数第二个结点
		Node last = x.next;
		x.next = null;
		size--;
		return (T)last;
	}
	public Iterator<T> iterator(){
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<T> {
		int pos = 0;

		@Override
		public boolean hasNext() {
			return pos < size;
		}

		@Override
		public Object next() {
			if (pos > size)
				throw new IllegalArgumentException();
			return get(pos++);
		}
	}

	
	
	private static  class Node{
		Object data;
		Node next;
		private Node(Object data, Node next) {
			this.data = data;
			this.next = next;

		}
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  Node reverseFor(Node head){
		if(head==null){
			return null;
		}
		Node pre = null;
		Node curr = head;
		Node next = head.next;
		while(curr.next!=null){
			
			head.next = pre;
			pre = curr;
			curr = curr.next;
			next = next.next;
			head = curr;
		}
		pre = null;
		curr = null;
		return head;
	}
	/**
	 * 递归写法
	 * @param
	 * @return
	 */
	public Node reverseRecursion(Node current){
		if(current == null || current.next == null){
			return current;
		}
		Node nextNode = current.next;
		current = null;
		Node reverseNode = reverseRecursion(current.next);
		nextNode.next = current;
		
		
		return reverseNode;
		
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int delectLength = size/2;
		for(int i=0;i<delectLength;i++){
			Node x = (Node) removeFirst();
			x.data = null;
			x.next = null;
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if( i<0 && i>size-length){
			throw new IllegalArgumentException(i +" or "+length +" error");
		}
		for(int j=i;j<length+i+1;j++){
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
	public  int[] getElements(LinkedList list){
		if(list==null){
			throw new NullPointerException("List is null");
		}
		int[] result = new int[list.size()];
		int index = 0;
		for(Iterator iter = list.iterator();iter.hasNext();){
			int LinkIndex = (int)iter.next();
			result[index] = (Integer) get(LinkIndex);
		}
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		if(list == null){
			throw new NullPointerException("List is null");
		}
		int index = 0;
		for(Node cur = head.next ; cur !=null ; cur = cur.next){
			for(Node newList = list.head.next ; newList != null; newList = newList.next ){
				if(Objects.deepEquals(cur.data, newList.data)){
					remove(index);
				}
			}
			index++;
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		for(Node current=head.next;current!=null;current=current.next){
			Node nextNode = current.next;
			
			if(current.data.equals(nextNode.data)){
				Node nextNodeNext = nextNode.next;
				if(nextNodeNext==null){
					current.next = null;
				}else{
					current.next = nextNodeNext;
					nextNode.next = null;
				}
			}
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max) {
		if (min + max > size && min == max) {
			throw new IndexOutOfBoundsException("Arguement is Illegal");
		}
		int index = 0;
		for (Node curr = head.next; curr != null; curr = curr.next) {
			if(((int)curr.data>min) && ((int)curr.data<min)){
				remove(index);
			}
			index++;
			
		}

	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList cList = new LinkedList();
		for(Node a = head.next ; a!=null ;a = a.next){
			for(Node b = list.head.next; b != null ; b = b.next){
				if(Objects.deepEquals(a.data, b.data)){
					cList.add(a.data);
				}
			}
		}
		
		
		return cList;
	}

	
}
