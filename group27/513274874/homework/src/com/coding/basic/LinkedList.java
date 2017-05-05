
package com.coding.basic;

import java.util.NoSuchElementException;

/**
 * @author zhougd 20170306
 * 单向链表实现LinkedList
 */

public class LinkedList implements List {
	java.util.LinkedList a;
	/**
	 * 第一个元素
	 */
	private Node head;

	/**
	 * 最后一个元素
	 */
	private Node tail;

	/**
	 * 元素容量
	 */
	private int size = 0;
	
	public void add(Object o){
		Node node = new Node(o);
		//判断是否链表为空
		if(this.size() == 0){
			this.addFirst(node);
		}else{
			this.addLast(node);
		}

	}
	public void add(int index , Object o){
		checkIndex(index);

		Node oldNode= this.getNode(index);
		Object oldObject = oldNode.getData();
		Node next = oldNode.getNext();

		//将原位置修改为新元素
		oldNode.setData(o);
		//设置下一个元素
		oldNode.setNext(new Node(oldObject));
		//设置下一个元素的下一个元素
		oldNode.getNext().setNext(next);

		size ++;
	}

	public Object get(int index){
		checkIndex(index);
		return this.getNode(index).getData();
	}

	public Object remove(int index){
		checkIndex(index);
		//获取到当前元素和下一个元素
		//把当前元素的值设置成下一个元素的值，删除掉下一个元素，这样的话，不必管上一个元素是什么，是不是第一个元素
		Node node = this.getNode(index);
		Object data = node.getData();
		Node nextNode = this.getNode(index + 1);
		node.setData(nextNode.getData());
		node.setNext(nextNode.getNext());

		return data;
	}
	
	public int size(){
		return this.size();
	}
	
	public void addFirst(Object o){
		Node node = new Node(o);
		//原头变为第二
		Node temp = this.head;
		this.head = node;
		node.next = temp;
		size++;
	}
	public void addLast(Object o){
		Node node = new Node(o);
		Node t = this.tail;
		if(t == null){
			this.head = node;
		}else{
			this.tail.next = node;
			this.tail = node;
		}
		size++;
	}
	public Object removeFirst(){
		Node head = this.head;
		if(head == null){
			throw new NoSuchElementException("No such element !");
		}
		this.head = this.head.getNext();
		size--;
		return head ;
	}

	public Object removeLast(){
		Node node ;
		if(this.tail == null){
			throw new NoSuchElementException("No such element !");
		}
		node = this.tail;
		if(this.head ==this.tail){
			node = this.head;
			this.head = null;
			this.size = 0;
		}else{
			//获取尾元素的上一个元素
			this.tail = this.getNode(this.size-2);
			this.tail.setNext(null);
			this.size--;
		}

		return node;
	}

	public Iterator iterator(){
		return new LinkedListIterator();
	}

	private void checkIndex(int index){
		if(index < 0 || index >size()){
			throw new IndexOutOfBoundsException("Index out of bound !");
		}
	}

	private Node getNode(int index ){

		Node node = this.head;
		for(int i = 0 ;i<size();i++){
			node = node.next;
		}
		return node;
	}

	private class LinkedListIterator implements Iterator{

		private int currentIndex = 0;
		private int count = size();

		@Override
		public boolean hasNext() {
			return currentIndex < count-1;
		}

		@Override
		public Object next() {
			currentIndex++;
			return get(currentIndex);
		}
	}
	
	private static class Node{
		Object data;
		Node next;

		public Node(Object data) {
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
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]
	 * @param list
	 */
	public  int[] getElements(LinkedList list){
		return null;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素

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