package com.zhous.DataStruct;

import javafx.beans.binding.ObjectExpression;



public class LinkedList implements List {
	
	private Node head = null;
	private int size = 0;

	public void add(Object o){
		addLast(o);
		this.size++;
	}

	//内部调用的方法：
	//2 返回添加到哪个Node
	private Node findBeforeInsertNode(int needInsertIndex){
		Node findGetNode = this.head;
		for ( int i=0; i< needInsertIndex - 1; i++ ) {
			findGetNode = findGetNode.next;
		}
		return findGetNode;
	}
	//3
	private void checkPositionIndex(int index) {
		if ( !( index >= 0 && index <= size ) )
			throw new IndexOutOfBoundsException("Invalid Index:"+index);
	}

	//4
	private void addBeforeNode(int index, Object o) {
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;

		Node beforeInserNode = this.head;


			beforeInserNode =  findBeforeInsertNode(index);

			newNode.next = beforeInserNode.next;
			beforeInserNode.next = newNode;

	}
	//5
	private Node findIndexNode(int index){
		Node findGetNode = this.head;
		for ( int i=0; i< index; i++ ) {
			findGetNode = findGetNode.next;
		}
		return findGetNode;
	}


	public void add(int index , Object o){
		checkPositionIndex(index);
		if( index == size()){
			addLast(o);
		} else if ( index == 0 ) {
			addFirst(o);
		} else{
			addBeforeNode(index,o);
		}
		this.size++;
	}




	public Object get(int index){
		checkElementIndex(index);
		return findIndexNode(index);
	}
	public Object remove(int index){
		checkElementIndex(index);
		Object deleteData = null;

		if(index == 0){
			deleteData = removeFirst();
		} else if(index == (size() - 1) ){
			deleteData = removeLast();
		} else {
			Node temp = findBeforeInsertNode(index);
			Node tempNext = temp.next;
			deleteData = tempNext.data;

			temp.next = tempNext.next;
			tempNext = null;
		}

		return deleteData;
	}

	//6
	private void checkElementIndex(int index) {
		if ( !( index >= 0 && index < size ) )
			throw new IndexOutOfBoundsException("Invalid Index:"+index);
	}

	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;
		if ( this.head == null  ) {
			this.head = newNode;
		} else {
			newNode.next = this.head;
			this.head = newNode;
		}
	}
	private void addLast(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;
		if ( head == null ) {
			head = newNode;
		} else{
			findBeforeInsertNode(this.size).next = newNode;
		}
	}
	public Object removeFirst(){
		Object data;
		Node newHead = new Node();
		newHead = this.head;
		this.head = newHead.next;

		data = newHead.data;
		newHead = null;
		return  data;
	}
	public Object removeLast(){
		Object data;
		Node last = findIndexNode(this.size() -1);
		data = last.data;
		last = null;

		return data;
	}
	public Iterator iterator()
	{
		return new LinkedListIterator();
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
		Node first ;
		int currentIndex = 1;
		this.head = findIndexNode(this.size - 1);
		if(this.size() <= 1){
			return;
		}
		while(currentIndex < this.size() ){
			first = findIndexNode(this.size() - currentIndex);
			first.next = findIndexNode(this.size - currentIndex - 1);
			currentIndex++;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		checkElementIndex(this.size());

		int mid = this.size / 2;
		Node temp = this.head;
		this.head = findIndexNode(mid);
		for (int i = 0; i < mid; i++) {
			Node T = temp.next;
			temp = null;
			temp = T;
		}

	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param index
	 * @param length
	 */
	public  void remove(int index, int length){
		checkElementIndex(length + index );

		Node before = findBeforeInsertNode(index);
		Node temp = findIndexNode(index);
		for (int i= 0; i < length; i++) {
			Node T = temp.next;
			temp = null;
			temp = T;
		}
		before.next = temp;
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
		Node listNode = list.head;
		Node myNode = this.head;
		int[] data = new int[list.size()];
		for (int i = 0; i < this.size(); i++) {
			if(i == (Integer) listNode.data){
				data[i] = (Integer)myNode.data;
			}
			myNode = myNode.next;
		}

		return data;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){

		int[] data = new int[list.size()];
		for (int i = 0,j = 0; i < this.size(); i++) {
			for (; j < list.size(); j++) {
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
	 * （选做）
	 */
	public  void removeDuplicateValues(){
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 * （选做）
	 */
	public  void removeRange(int min, int max){
		
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
        LinkedList listC = new LinkedList();

        for (int i = 0,j = 0; i < this.size(); i++) {
            for (; j < list.size(); j++) {
                if(this.get(i).equals(list.get(j))){
                    listC.add(this.get(i));
                    break;
                }
            }
        }



		return listC;
	}



	/************Iterator**************/
	private class LinkedListIterator implements Iterator{
		private Node currentNode;
		private int currentIndex;

		public LinkedListIterator() {
			// TODO Auto-generated constructor stub
			this.currentIndex = 0;
			this.currentNode = new Node();
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return currentIndex < size();
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			checkElementIndex(currentIndex);
			if ( currentNode.data == null ) {
				this.currentNode = findIndexNode(currentIndex);
			}
			Object value = currentNode.data;
			currentNode = currentNode.next;
			currentIndex++;
			return value;
		}
	}
}
