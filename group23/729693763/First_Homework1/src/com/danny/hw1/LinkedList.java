package com.danny.hw1;

public class LinkedList implements List{

	private Node Head = null;
	private Node Tail = null;
	private int size = 0;
	
	@Override
	public void add(Object o) {
		// TODO Auto-generated method stub			
		addLastNode(o);		
	}

	@Override
	public void add(int index, Object o) {
		// TODO Auto-generated method stub
		checkPositionIndex(index);
		
		if ( index == size() ){
			addLastNode(o);
		} else { 
			addBeforeNode(index,o);
		}	
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		checkElementIndex(index);
		
		Node findGetNode = findIndexNode(index);
		return findGetNode.data;		
	}

	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		checkElementIndex(index);
		Node deleteNode = null;
		
		//delete all node
		if ( size() == 1 ) {
			deleteNode = this.Head;
			this.Head = null;
			this.Tail = null;			
		} else if ( index == 0 ) {
			//Remove Head
			deleteNode = this.Head;
			this.Head = this.Head.next;			
		} else if ( index == size() - 1) {
			//Remove Tail
			deleteNode = this.Tail;
			Node findDeleteNode = findIndexNode(index - 1);
			findDeleteNode.next = null;
			this.Tail = findDeleteNode;
		} else {
			//Remove Mid
			Node findDeleteNode = findIndexNode(index - 1);
			deleteNode = findDeleteNode.next;
			findDeleteNode.next = findDeleteNode.next.next;
		}
		this.size--;
		return deleteNode.data;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}
	
	@Override
	public Iterator iterator(){
		return new LinkedListIterator();
	}
	
	/************Other function (without Interface function)**************/
	
	public void addFirst(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = this.Head;
		this.Head = newNode;
	}
	
	public Object removeFirst(){
		checkElementIndex(0);
		
		Node deleteNode = this.Head;
		this.Head = this.Head.next;
		
		this.size--;
		return deleteNode.data;
	}
	
	public Object removeLast(){
		checkElementIndex(size() - 1);
		
		Node deleteNode = this.Tail;
		if ( this.Head == this.Tail ) {
			this.Head = this.Tail = null;
		} else {
			this.Tail = findIndexNode(size() - 2);
		}
		this.size--;
		return deleteNode.data;
		
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
		
	/************Node class for supporting LinkedList**************/
	private static class Node{
		Object data ;
		Node next ;
		public Node() {
			// TODO Auto-generated constructor stub
			data = null;
			next = null;
		}
	}
	
	/************Inner function **************/
	private void checkPositionIndex(int index){
		 if ( !( index >= 0 && index <= size ) )
			 throw new IndexOutOfBoundsException("Invalid Index:"+index);
	}
	
	private void checkElementIndex(int index){
		 if ( !( index >= 0 && index < size ) )
			 throw new IndexOutOfBoundsException("Invalid Index:"+index);
	}
		
	private void addLastNode(Object o){	
		Node newNode = new Node();
		newNode.data = o;
		
		if ( this.Head == null ) {
			this.Head = newNode;
			this.Tail = newNode;			
		} else {
			if(this.Head == this.Tail ){								
				this.Tail = newNode;
				this.Head.next = this.Tail;
			}
			else{
			//Tail and Head are different Object, 			
				this.Tail.next = newNode;
				this.Tail = newNode;
			}
		}
		this.size++;
	}
	
	private void addBeforeNode(int index,Object o){
		Node newNode = new Node();
		Node beforeInserNode = this.Head;
		newNode.data = o;

		// if index is in Head
		if ( index == 0 ) { 
			newNode.next = this.Head;
			this.Head = newNode;
		} else {
			for (int i=0; i<index ;i++ ){
				beforeInserNode = beforeInserNode.next;
			}
			newNode.next = beforeInserNode.next;
			beforeInserNode.next = newNode;
		}

		this.size++;
	}
	
	private Node findIndexNode(int index){
		Node findGetNode = this.Head;
		for ( int i=0; i< index; i++ ) {
			findGetNode = findGetNode.next;
		}
		return findGetNode;
	}

}









//
///**
// * 把该链表逆置
// * 例如链表为 3->7->10 , 逆置后变为  10->7->3
// */
//public  void reverse(){		
//	
//}
//
///**
// * 删除一个单链表的前半部分
// * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
// * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
//
// */
//public  void removeFirstHalf(){
//	
//}
//
///**
// * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
// * @param i
// * @param length
// */
//public  void remove(int i, int length){
//	
//}
///**
// * 假定当前链表和list均包含已升序排列的整数
// * 从当前链表中取出那些list所指定的元素
// * 例如当前链表 = 11->101->201->301->401->501->601->701
// * listB = 1->3->4->6
// * 返回的结果应该是[101,301,401,601]  
// * @param list
// */
//public static int[] getElements(LinkedList list){
//	return null;
//}
//
///**
// * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
// * 从当前链表中中删除在list中出现的元素 
//
// * @param list
// */
//
//public  void subtract(LinkedList list){
//	
//}
//
///**
// * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
// * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
// */
//public  void removeDuplicateValues(){
//	
//}
//
///**
// * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
// * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
// * @param min
// * @param max
// */
//public  void removeRange(int min, int max){
//	
//}
//
///**
// * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
// * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
// * @param list
// */
//public  LinkedList intersection( LinkedList list){
//	return null;
//}
