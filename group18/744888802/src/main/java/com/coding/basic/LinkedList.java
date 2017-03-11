package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private Node last;

	private int size = 0;
	
	public void add(Object o){
		addLast(o);

	}
	public void add(int index , Object o){

		Node node = new Node();
		node.data = o;
		if(size == 0)
		{
			throw new NullPointerException(" linked list is null");
		}
		if(index == 0)
		{
			node.next=head;
			head = node;
		}
		Node nodeNow = head;
		for(int i=1;i<size;i++)
		{
			Node nextNode = nodeNow.next;
			if(i == index)
			{
				node.next = nextNode;
				nodeNow.next = node;
				break;
			}
			nodeNow = nodeNow.next;
		}
		size++;

		
	}
	public Object get(int index){
		if(size == 0)
		{
			throw new NullPointerException("linked list is null");
		}
		if(index >=size)
		{
			throw new IndexOutOfBoundsException(" this index too big by this list");
		}

		Node nodeNow = head;
		for(int i=0;i<size;i++)
		{
			if(i == index)
			{
				return nodeNow.data;
			}
			nodeNow = nodeNow.next;
		}
		return null;
	}
	public Object remove(int index){

		if(index >=size)
		{
			throw new IndexOutOfBoundsException(" this index too big by this list");
		}
		if(size == 0)
		{
			throw new NullPointerException("linked list is null");
		}
		if(index == 0)
		{
			if(size == 1)
			{
				size = 0;
				return head.data;
			}
			Object o = head.data;
			head.next = null;

			head = head.next;
			return o;

		}
		Node result = null;


		Node beforeNode = head;
		Node nextNode = head.next;
		for(int i=1;i<size;i++)
		{
			if(index == i)
			{
				beforeNode.next = nextNode.next;
				size--;
				return nextNode.data;
			}
			beforeNode = nextNode;
			nextNode = nextNode.next;
		}


		return result;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		if(head == null)
		{
			Node node = new Node();
			node.data = o;
			head = node;
			last = node;
		}else{
			Node node = new Node();
			node.data = o;
			node.next = last;
			head = node;
		}
		size++;
	}
	public void addLast(Object o){
		if(head == null)
		{
			Node node = new Node();
			node.data = o;
			head = node;
			last = node;
		}else{
			Node node = new Node();
			node.data = o;
			last.next = node;
			last = node;
		}
		size++;
	}
	public Object removeFirst(){
		if(size == 0)
		{
			throw new NullPointerException(" this linkedlist is null");
		}
		if(size == 1)
		{
			size = 0;
		}

		head = head.next;
		size--;
		return head.data;
	}
	public Object removeLast(){

		if(size==0)
		{
			throw new NullPointerException("this linkedlist is null ");
		}

		if(size == 1)
		{
			size = 0;
			return head;
		}



		Node nowNode = head;
		Node beforeNode = head;
		for(int i=0;i<size;i++)
		{
			if(nowNode.next == null)
			{
				beforeNode.next = null;
				last = beforeNode;
				size--;
				return last;


			}
			nowNode = nowNode.next;
			beforeNode = nowNode;
		}




		size--;

		return last.data;

	}
	public Iterator iterator(){
		return new LinkedListIterator(this);
	}


	private static  class Node {
		Object data;
		Node next;
	}
	class LinkedListIterator implements Iterator{
		private LinkedList linkedList ;
		private int countNum = 0;
		private int indexNum = 0;

		public LinkedListIterator(LinkedList linkedList)
		{
			this.linkedList = linkedList;
			countNum = linkedList.size;
			this.indexNum = 0;
		}



		@Override
		public boolean hasNext() {
			if(this.indexNum >= this.countNum)
			{
				return false;
			}
			return true;
		}

		@Override
		public Object next() {

			if(indexNum >= countNum){
				return null;
			}
			Object obj = linkedList.get(indexNum);
			indexNum++;

			return obj;
		}
	}



	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		LinkedList newLinkedList = new LinkedList();
		for(int i=this.size-1 ;i>=0;i-- ){
			newLinkedList.add(this.get(i));
		}
		resetThis(newLinkedList);

	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		LinkedList newLinkedList = new LinkedList();
		int tempNum = size/2;
		for(int i=0;i<size;i++){
			if(i>=tempNum){
				newLinkedList.add(this.get(i));
			}
		}
		resetThis(newLinkedList);

	}

	private void resetThis(LinkedList linkedList){
		this.head = linkedList.head;
		this.last = linkedList.last;
		this.size = linkedList.size;
	}


	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		for(int m = 0 ;m <length ;m++){
			this.remove(i);
		}

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
		int [] res = new int[list.size];
		int index = 0;
		for(int i=0;i<list.size;i++)
		{
			int param = Integer.valueOf(list.get(i).toString());
			for(int j=0;j<size;j++){
				if(j == param){
					res[index] = Integer.valueOf(this.get(j).toString());
					index++;
					break;
				}

			}
		}



		return res;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素

	 * @param list
	 */

	public  void subtract(LinkedList list){

		for(int j=0;j<list.size;j++){
			for(int i=0;i<size;i++){
				if(this.get(i).equals(list.get(j))){
					this.remove(i);
					break;
				}
			}
		}
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Node node = head;
		for(int i=0;i<size;i++){
			fun1(node);
			if(node.next == null){
				return;
			}
			node = node.next;

		}

	}

	private void fun1(Node node){

		if(node.next == null){
			return;
		}
		if(node.data.equals(node.next.data)){
			node.next = node.next.next;
			size--;
			fun1(node);
		}else{
			return ;
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		Node node = head;
		for(int i=0;i<size;i++){
			fun2(node,min,max);
			if(node.next == null){
				return;
			}
			node = node.next;

		}
	}

	private void fun2(Node node,int min, int max ){
		if(node.next == null){
			return;
		}

		int nodeData = Integer.valueOf(node.next.data.toString());
		if(nodeData > min && nodeData < max){
			size--;
			if(node.next.next==null){
				node.next = null;
				return;
			}else{
				node.next = node.next.next;

			}

			fun2(node,min,max);
		}else{
			return;
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList newLinkedList = new LinkedList();
		for(int i=0;i<size;i++){
			for(int j=0;j<list.size;j++){
				if(this.get(i).equals(list.get(j))){
					newLinkedList.add(this.get(i));
				}
			}

		}
		return newLinkedList;
	}


}
