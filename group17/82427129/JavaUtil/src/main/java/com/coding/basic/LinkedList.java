package com.coding.basic;

import java.util.LinkedHashSet;

public class LinkedList<E> implements List<E> {
	
	private Node<E> head;
	private int size;
	
	public LinkedList(){}
	
	public void add(E o){
		add(size, o);
	}
	public void add(int index , E o){
		rangeCheckforAdd(index);
		
		if(index == 0){
			addFirst(o);
		}else{
			addNext(index, o);
		}
	}

	private void addNext(int index, E o) {
		Node<E> newNode = new Node<E>(o, null);
		Node<E> prev = indexOf(index-1);
		Node<E> next = prev.next;
		newNode.next = next;
		prev.next = newNode;
		
		size++;
	}

	public void addFirst(E o) {
		Node<E> newNode = new Node<E>(o, null);
		Node<E> next = head;
		head = newNode;
		newNode.next = next;
		
		size++;
	}
	
	public void addLast(E o){
		add(o);
	}
	private Node<E> indexOf(int index){
		Node<E> node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}
	private void rangeCheck(int index){
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException("index:"+index+",size:"+size);
	}
	private void rangeCheckforAdd(int index){
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException("index:"+index+",size:"+size);
	}
	
	public E get(int index){
		rangeCheck(index);
		
		return indexOf(index).data;
	}
	public E remove(int index){
		rangeCheck(index);
		
		if(index == 0){
			return removeFirst();
		}else{
			return removeNext(index);
		}
	}
	public void clear(){
		for (Node<E> x = head; x!= null; ) {
			Node<E> next = x.next;
			x.data = null;
			x.next = null;
			x = next;
		}
		size = 0;
		head = null;
	}

	private E removeNext(int index) {
		final Node<E> rv = indexOf(index);
		final E element = rv.data;
		final Node<E> prev = indexOf(index-1);
		prev.next = rv.next;
		rv.next = null;
		size--;
		return element;
	}
	
	public int size(){
		return size;
	}
	
	public E removeFirst(){
		final E element = head.data;
		final Node<E> rv = head;
		final Node<E> next = rv.next;
		head = next;
		rv.next = null;
		size --;
		return element;
	}
	public E removeLast(){
		E e = remove(size-1);
		return e;
	}
	public Iterator iterator(){
		return null;
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append(get(i)).append(",");
		}
		return s.toString();
	}
	
	private static class Node<E>{
		E data;
		Node<E> next;
		public Node(E data,Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}
	public class ListItr implements Iterator {

		
		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public Object next() {
			return null;
		}
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse(){
		int i = 1;
		if(size == 1 || size == 0){
			return;
		}
		while(i < size){
			E e = remove(i);
			addFirst(e);
			i++;
		}
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public void removeFirstHalf(){
		int size = this.size>>1;
		for (int i = 0; i < size; i++) {
			removeFirst();
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){
		if(i < 0 )
			throw new IndexOutOfBoundsException("i requested >= 0 ,i:"+i);
		if(length < 0)
			throw new IndexOutOfBoundsException("length requested > 0 ,length:"+length);
		
		for (int j = 0; j < length&&j<size; j++) {
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
	public int[] getElements(LinkedList<Integer> list){
		int[] r = new int[list.size];
		for (int i = 0; i < r.length; i++) {
			int index = list.get(i);
			if(index >= this.size()){
				r[i] = 0;
				break;
			}else{
				r[i] = (int) this.get(index);
			}
		}
		return r;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public void subtract(LinkedList<E> list){
		int i = 0;//this list's index
		while(i < this.size){
			boolean flag = false;
			int j = 0;//parameter list's index
			E e = get(i);
			
			while(j< list.size){
				if(e.equals(list.get(j))){
					remove(i);
					flag = true;
					break;
				}else{
					j++;
				}
			}
			if(!flag){
				i++;
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public void removeDuplicateValues(){
		LinkedHashSet<E> set = new LinkedHashSet<E>();
		for (int i = 0; i < size; i++) {
			set.add(get(i));
		}
		clear();
		java.util.Iterator<E> iterator = set.iterator();
		while(iterator.hasNext()){
			add(iterator.next());
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public void removeRange(int min, int max){
		int i = 0;
		while(i < size){
			int e = (int)get(i);
			if(e > min && e < max){
				remove(i);
			}else{
				i++;
			}
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public LinkedList<E> intersection( LinkedList<E> list){
		LinkedList<E> linkedList = new LinkedList<E>();
		int i = 0;//this list's index
		int j = 0;//parameter list's index
		int j2= 0;
		while( i < this.size ){
			E e = get(i++);
			j = j2+1;
			while(j < list.size ){
				if(e.equals(list.get(j))){
					linkedList.add(e);
					j2 = j;
				}
				j++;
			}
		}
		return linkedList;
	}
}
