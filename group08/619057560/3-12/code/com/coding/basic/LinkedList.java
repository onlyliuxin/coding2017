package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o){
		Node pNewNode = new Node();
		pNewNode.data = o;
		
		Node pNode = head;
		
		if (head == null) {
			head = pNewNode;
			return;
		}
		
		while (pNode.next != null) {
			pNode = pNode.next;
		}
		
		pNode.next = pNewNode;
	}
	
	public void add(int index , Object o){
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		Node pNewNode = new Node();
		pNewNode.data = o;
		
		if (index == 0) {
			pNewNode.next = head;
			head = pNewNode;
			return;
		}
		
		Node pNode = head;
		while (--index > 0) {
			pNode = pNode.next;
		}
		pNewNode.next = pNode.next;
		pNode.next = pNewNode;
	}
	
	public Object get(int index){
		ensureBounds(index);
		
		Node pNode = head;
		while (index-- > 0) {
			pNode = pNode.next;
		}
		
		return pNode.data;
	}
	
	public Object remove(int index){
		ensureBounds(index);
		
		Node pNode = head;
		if (index == 0) {
			head = head.next;
			return pNode.data;
		}
		
		while (--index > 0) {
			pNode = pNode.next;
		}
		Node pTargetNode = pNode.next;
		pNode.next = pTargetNode.next;
		
		return pTargetNode.data;
	}

	private void ensureBounds(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public int size(){
		Node pNode = head;
		int num = 0;
		while (pNode != null) {
			pNode = pNode.next;
			num++;
		}
		return num;
	}
	
	public void addFirst(Object o){
		Node pNewNode = new Node();
		pNewNode.data = o;
		pNewNode.next = head;
		head = pNewNode;
	}
	
	public void addLast(Object o){
		add(o);
	}
	
	public Object removeFirst(){
		if (head == null) {
			throw new NoSuchElementException();
		}
		return remove(0);
	}
	
	public Object removeLast(){
		if (head == null) {
			throw new NoSuchElementException();
		}
		
		Node pNode = head;
		Node pPrevNode = null;
		while (pNode.next != null) {
			pPrevNode = pNode;
			pNode = pNode.next;
		}
		if (pPrevNode != null) {
			pPrevNode.next = pNode.next;
		}
		else {
			head = null;
		}
		return pNode.data;
	}
	
	public Iterator iterator(){
		return new Itr();
	}
	
	private class Itr implements Iterator {
		
		int index = 0;
		Node pCurr = head;
		Node pPrevPrev = null;
		boolean hasCalledRemove = false;

		@Override
		public boolean hasNext() {
			return (pCurr != null);
		}

		@Override
		public Object next() {
			if (pCurr == null) {
				throw new NoSuchElementException();
			}
			Object retOb = pCurr.data;
			pCurr = pCurr.next;
			index++;
			if (index == 2) {
				pPrevPrev = head;
			} else if (index > 2 && !hasCalledRemove) {
				pPrevPrev = pPrevPrev.next;
			}
			
			hasCalledRemove = false;
			
			return retOb;
		}

		@Override
		public void remove() {
			if (hasCalledRemove) {
				throw new IllegalStateException();
			}
			hasCalledRemove = true;
			
			if (index == 1) {
				head = head.next;
				index--;
			} else if (pPrevPrev != null) {
				pPrevPrev.next = pCurr;
				index--;
			} else {
				throw new IllegalStateException();
			}
		}
		
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
		if (head == null) {
			return;
		}
		
		Node pNode1 = head, pNode2 = head.next, tmpNode;
		
		head.next = null;
		while (pNode2 != null) {
			tmpNode = pNode2.next;
			pNode2.next = pNode1;
			pNode1 = pNode2;
			pNode2 = tmpNode;
		}
		
		head = pNode1;
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		int len = size() / 2;
		while (len-- > 0) {
			head = head.next;
		}
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		ensureBounds(i);
		Node pNode1 = head, pNode2;
		if (i == 0) {
			pNode2 = head;
			head = null;
		} else {
			while (--i > 0) {
				pNode1 = pNode1.next;
			}
			pNode2 = pNode1.next;
		}
		while (length-- > 0 && pNode2 != null) {
			pNode2 = pNode2.next;
		}
		
		if (head == null) {
			head = pNode2;
		} else {
			pNode1.next = pNode2;
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
	public int[] getElements(LinkedList list){
		if (list == null) {
			return new int[0];
		}
		int size = list.size();
		int index;
		int[] elements = new int[size];
		for (int i = 0; i < size; i++) {
			index = ((Integer)list.get(i)).intValue();
			elements[i] = ((Integer)get(index)).intValue();
		}
		return elements;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList origList){
		if (origList == null) {
			return;
		}
		
		LinkedList list = binaryTreeSort(origList);
		
		Iterator listItr = list.iterator();
		Iterator mainItr = iterator();
		
		intersectionIteration(new intersectionIterationCallback(){

			@Override
			public void onElementFound(Object element) {
				mainItr.remove();
			}
			
		}, listItr, mainItr);
		
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		Iterator itr = iterator();
		Integer lastInt = null, currInt;
		while (itr.hasNext()) {
			currInt = (Integer)itr.next();
			if (lastInt != null && lastInt.intValue() == currInt.intValue()) {
				itr.remove();
			}
			lastInt = currInt;
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		Node pNode = head, pNodeMark = null;
		
		while (pNode != null && (Integer)pNode.data <= min) {
			pNodeMark = pNode;
			pNode = pNode.next;
		}
		while (pNode != null && (Integer)pNode.data < max) {
			pNode = pNode.next;
		}
		
		if (pNodeMark == null) {
			head = pNode;
		} else {
			pNodeMark.next = pNode;
		}
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList newList = new LinkedList();
		
		if (list == null) {
			return newList;
		}
		
		Iterator listItr = list.iterator();
		Iterator mainItr = iterator();
		
		intersectionIteration(new intersectionIterationCallback(){

			@Override
			public void onElementFound(Object element) {
				newList.add(element);
			}
			
		}, listItr, mainItr);
		
		return newList;
	}
	
	public LinkedList binaryTreeSort(LinkedList list) {
		if (list == null) {
			return null;
		}
		
		Iterator listItr = list.iterator();
		BinaryTreeNode root = new BinaryTreeNode();
		while (listItr.hasNext()) {
			root.insert(listItr.next());
		}
		
		LinkedList sortedList = new LinkedList();
		addNodeToLinkedList(sortedList, root);
		
		return sortedList;
	}
	
	private void addNodeToLinkedList(LinkedList list, BinaryTreeNode node) {
		if (node == null || node.getData() == null) {
			return;
		}
		addNodeToLinkedList(list, node.getRight());
		list.addFirst(node.getData());
		addNodeToLinkedList(list, node.getLeft());
	}
	
	private interface intersectionIterationCallback {
		void onElementFound(Object element);
	}

	private void intersectionIteration(intersectionIterationCallback callback, Iterator listItr, Iterator mainItr) {
		if (!listItr.hasNext() || !mainItr.hasNext()) {
			return;
		}
		
		Integer listValue = (Integer)listItr.next();
		Integer mainValue = (Integer)mainItr.next();
		while (listItr.hasNext() || mainItr.hasNext()) {
			if (listValue < mainValue) {
				if (!listItr.hasNext()) {
					break;
				}
				listValue = (Integer)listItr.next();
			} else if (listValue > mainValue) {
				if (!mainItr.hasNext()) {
					break;
				}
				mainValue = (Integer)mainItr.next();
			} else {
				callback.onElementFound(mainValue);
				mainValue = (Integer)mainItr.next();
			}
		}
		if (listValue.intValue() == mainValue.intValue()) {
			callback.onElementFound(mainValue);
		}
	}
}
