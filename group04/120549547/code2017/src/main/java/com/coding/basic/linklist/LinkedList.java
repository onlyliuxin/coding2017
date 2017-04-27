package com.coding.basic.linklist;

import com.coding.basic.Iterator;
import com.coding.basic.List;

import java.util.Objects;

public class LinkedList<T> implements List<T> {
	private int size;

	private Node<T> head;

	private Node<T> last;



	private static class Node<T> {
		T data;
		Node pre;
		Node next;

		Node(T data) {
			this.data = data;
		}


	}

	public LinkedList(){
		this.head = new Node<>(null);
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public boolean contains(Object o) {
		Node node = this.head;
		while (node.next != null){
			if (Objects.equals(node.data, o)){

				return true;
			}
			node = node.next;
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		Object[] dataArray  = new Object[size];
		Node node = head.next;
		for (int i = 0; i < size&& node != null; i++, node = node.next) {
			dataArray[i] = node.data;

		}
		return dataArray;
	}

	@Override
	public boolean add(T o) {
		if (this.last == null){
			this.last = new Node<>(o);
			this.last.pre = this.head;
			this.head.next = this.last;
		}else {
			Node oldLast = this.last;
			this.last = new Node<>(o);
			this.last.pre = oldLast;
			oldLast.next = this.last;

		}
		this.size++;
		return  true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(T o) {


		Node<T> curNode = head.next;
		Node<T> preNode;
		while (curNode != null){
			preNode = curNode.pre;  //指向前一个节点

			if (Objects.equals(curNode.data, o)){
				removeNode(preNode, curNode);
				return true;
			}
			curNode = curNode.next;
		}

		return false;
	}

	private void removeNode(Node<T> preNode, Node<T> node) {

		if (this.last == node){		//如果删除的是last节点的情况
			if (preNode == this.head){ //如果只有一个节点的情况
				this.last = null;
			}else {
				this.last = preNode;
			}
		}else {
			node.next.pre = node.pre;

		}
		preNode.next = node.next;




		this.size--;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		for (Node x = head; x != null;){
			Node<T> next = x.next;
			x.data = null;
			x.pre = null;
			x.next = null;
			x = next;
		}
		head = last = null;
		size = 0;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(int index) {
		return (T) getNode(index).data;
	}



	@Override
	public T set(int index, T o) {
		Node node = getNode(index);
		node.data = o;
		return o;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, T o) {
		ensureIndex(index);
		Node<T> newNode = new Node<>(o);
		Node curNode = getNode(index);
		Node pre = curNode.pre;

		newNode.next = curNode;
		newNode.pre = pre;
		curNode.pre = newNode;
		pre.next = newNode;

		size++;

	}

	@Override
	public T remove(int index) {
		Node node = getNode(index);
		Node pre = node.pre;
		if (node == last){ //如果是最后一个节点
			if (pre != head){ //如果是唯一节点
				last = null;
			}else {
				last = node.pre;
			}
		}

		pre.next = node.next;
		if (node.next != null){
			node.next.pre = pre;
		}
		size--;
		return (T) node.data;
	}

	@Override
	public int indexOf(T o) {
		Node node = head;
		int index = 0;

		while (node.next != null){
			node = node.next;
			if (Objects.equals(node.data, o)){
				return index;
			}
			index ++;
		}
		return index;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	@Override
	public void printf() {
		Node node = head.next;
		while (node != null){
			if (node.next != null) {
				System.out.print(node.data + " -> ");
			}else {
				System.out.print(node.data);
			}
			node = node.next;
		}
		System.out.println();
		System.out.println("head = " + head);
		System.out.println("last = " + last);

	}


	private Node getNode(int index) {
		ensureIndex(index);
		Node node = this.head;
		for (int i = 0; i <= index; i++) {
			node = node.next;
		}
		return node;
	}

	private void ensureIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){		
		if (head.next == null && head.next.next == null){ //如果链表为空或者只有一个元素,不做变换
			return;
		}

		last = head.next;

		Node pre = head.next;
		Node cur = pre.next;
		Node next;
		pre.next = null;
		while (cur != null){
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		head.next = pre;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if (isEmpty()){
			return;
		}
		int halfIndex = size / 2;

		if (halfIndex >= 0){
			head.next = getNode(halfIndex);
		}

		size = size - halfIndex;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		ensureIndex(i);
		ensureIndex(i + length - 1);

		for (int j = i; j < i + length; j++) {
			remove(i);
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

	private class LinkedListIterator implements Iterator<T> {
		private Node node;

		LinkedListIterator(){
			node = head;
		}
		@Override
		public boolean hasNext() {
			return node.next != null;
		}

		@Override
		public T next() {
			if (hasNext()){
				node = node.next;
				return (T) node.data;
			}
			return null;
		}
	}
}
