package com.coding;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {

	private int size;

	private Node<T> head;
	
	private int modCount;

	public LinkedList(){
		size = 0;
		head = null;
	}

	@Override
	public void add(T o){
		addFirst(o);
	}

	@Override
	public void add(int index , T o){
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> item = new Node<T>(o, null);
		if (index == 0) {
			item.next = head;
			head = item;
		} else {
			Node<T> temp = head;
			for (int i = 1; i < index; i++) {
				temp = temp.next;
			}
			item.next = temp.next;
			temp.next = item;
		}
		size++;
		modCount++;
	}

	@Override
	public T get(int index){
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> result = head;
		for (int i = 0; i < index; i++) {
			result = result.next;
		}
		return result.data;
	}

	@Override
	public T remove(int index){
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> result = head;
		if (index == 0) {
			head = head.next;
		} else {
			Node<T> temp = head;
			for (int i = 1; i < index; i++) {
				temp = temp.next;				
			}
			result = temp.next;
			temp.next = temp.next.next;
		}
		size--;
		modCount++;
		return result.data;
	}

	@Override
	public int size(){
		return size;
	}

	public void addFirst(T o){
        Node<T> item = new Node<T>(o, null);
        if (head == null) {
        	head = item;
        } else {
        	item.next = head;
        	head = item;
        }
        size++;
        modCount++;
	}
	
	public void addLast(T o){
		Node<T> item = new Node<T>(o, null);
		if(head == null) {
			head = item;
		} else {
			Node<T> tag = head;
			while (tag.next != null) {
				tag = tag.next;
			}
			tag.next = item;
		}
		size++;
		modCount++;
	}
	
	public T removeFirst(){
		if (size == 0) {
			throw new IndexOutOfBoundsException(); 
		}
		Node<T> result = head;
		head = head.next;
		size--;
		modCount++;
		return result.data;
	}
	
	public T removeLast(){
		if (size == 0) {
			throw new IndexOutOfBoundsException(); 
		}
		Node<T> result = head;
		if (size == 1) {
			head = null;
		} else {
			Node<T> temp = head;
			while (result.next != null) {
				temp = result;
				result = result.next;
			}
			temp.next = null;
		}
		size--;
		modCount++;
		return result.data;
	}

	public Iterator<T> iterator(){
		return new Iter();
	}
	
	private class Iter implements Iterator<T> {
		int cursor;        
        int expectedModCount = modCount;
        
        @Override
        public boolean hasNext() {
        	return cursor != size;
        }
        
        @Override
    	public T next() {
        	checkForComodification();
        	if (cursor >= size) {
        		throw new NoSuchElementException();
        	}
        	T item = get(cursor);
        	cursor++;
        	return item;
        }
        
        final void checkForComodification() 
        {
             if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
	}

	private static  class Node<T>{
		T data;
		Node<T> next;
		public Node(T data, Node<T> node) {
			this.data = data;
			this.next = node;
		}
	}

	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		if (size <= 1) {
			 return;
		 }
		 Node<T> node = head;
		 while (node.next != null) {
			 Node<T> temp = node.next;
			 node.next = temp.next;
			 temp.next = head;
			 head = temp;
		 }
	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if (size < 2) {
			return;
		}
		int delSize = (int)Math.floor(size/2);
		remove(0, delSize);
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if (i < 0 || i >= size || length < 0 || i + length > size) {
			throw new IndexOutOfBoundsException();
		}
		if (i == 0) {
			head = removeStartWith(head, length);
			return;
		}
		Node beforeStart = head;      //被删除元素的前一个
		for (int index = 1; index < i; index++) {
			beforeStart = beforeStart.next;
		}
        beforeStart.next = removeStartWith(beforeStart.next, length);
	}

	private Node<T> removeStartWith(Node<T> startNode, int length) {
		Node<T> node = null;
		for (int index = 1; index <= length; index++) {
			node = startNode;
			startNode = startNode.next;
			node.next = null;
			size--;
		}
		return startNode;
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
		if (size == 0 || list == null || list.size == 0) {
			return new int[0];
		}
		int[] result = new int[list.size];
		Node node = head;
		int index = 0;
		int resultIndex = 0;
		for (int i = 0; i < size; i++ ) {
			int listData = ((Integer)list.get(index)).intValue();
			if ( listData >= size) {
				throw new IndexOutOfBoundsException();
			}
			if (i == listData) {
				result[resultIndex++] = ((Integer)node.data).intValue();
				index++;
			}
           	if (index == list.size || listData == size) {
				break;
			}
			node = node.next;
		}
		return result;
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在list中出现的元素

	 * @param list
	 */

	public  void subtract(LinkedList list){
        if (list == null || list.size() == 0) {
            return;
        }
        Node node = head;
        Node beforeNode = null;
        Node temp = null;
        int j = 0;    //参数list索引
		for (;node != null && j < list.size() ;) {
            int paradata = ((Integer)list.get(j)).intValue();
            int data = ((Integer)node.data).intValue();
            if (data == paradata) {
                j++;
                size--;
                temp = node;
                if (beforeNode == null) {
                    head = node.next;
                    node = node.next;
                } else {;
                    beforeNode.next = node.next;
                    node = node.next;
                }
                temp.next = null;
            } else if (data < paradata) {
                beforeNode = node;
                node = node.next;
            } else {
                j++;
            }
        }
	}

	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		if (size < 2) {
			return;
		}
		Node node = head;
		Node delNode = null;
		while (node.next != null) {
			if (((Integer)node.next.data).equals(node.data)) {
				delNode = node.next;
				node.next = node.next.next;
				delNode.next = null;
				size--;
			} else {
				node = node.next;
			}
		}
	}

	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
 		if (min >= max) {
			return;
		}
		Node node = head;
		int delLen = 0;
		int startIndex = -1;
		for (int i = 0; i < size; i++) {
			int currentData = ((Integer)node.data).intValue();
			if (currentData > min && currentData < max) {
				if (delLen == 0) {
					startIndex = i;
				}
				delLen++;
			} else if (currentData >= max) {
				break;
			}
			node = node.next;
		}
		if (delLen > 0) {
			remove(startIndex, delLen);
		}
	}

	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		if (list.size() == 0 || size == 0) {
			return null;
		}
		LinkedList result = new LinkedList();
		Node node = head;
		Iterator listIter = list.iterator();
		while (listIter.hasNext()) {
			int listData = ((Integer)listIter.next()).intValue();
			for (;node != null;) {
				int currentData = ((Integer)node.data).intValue();
				if (currentData == listData) {
					result.addLast(currentData);
				} else if (currentData > listData) {
					break;
				}
				node = node.next;
			}
		}
		return result;
	}
}
