package com.github.xiaozi123.coding2017.basic;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class LinkedList implements List {

	private int size;
	private Node head;
	private Node last;

	public void add(Object o) {
		addLast(o);
	}

	public void addLast(Object o) {
		final Node l = last;
		final Node newNode = new Node(o, null);
		last = newNode;
		if (l == null)
			head = newNode;
		else
			l.next = newNode;
		size ++;
	}

	public void add(int index , Object o) {
		makeSure(index);
		if (index == 0){
			addFirst(o);
		}else {
			final Node preNode = getPreNode(index);
			final Node nextNode = preNode.next;
			final Node newNode = new Node(o, nextNode);
			preNode.next = newNode;
		}
	}

	public void addFirst(Object o){
		final Node h = head;
		final Node newNode = new Node(o, head);
		head = newNode;
		if (h == null)
			last = newNode;
		size++;
	}

	private Node getPreNode(int index) {
		Node preNode = head;
		for (int i = 0 ; i != index - 1 && preNode != null; ++i) {
			preNode = preNode.next;
		}
		return preNode;
	}

	private void makeSure(int index) {
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException();
	}

	public Object get(int index) {
		Object targetData = null;
		makeSure(index);
		Iterator iterator = iterator();
		for (int i = 0; iterator.hasNext(); ++i, iterator.next()) {
			if (i == index) {
				targetData = iterator.next();
				break;
			}
		}
		return targetData;
	}

	public Object remove(int index) {
		if (size <= 0)
			throw new IndexOutOfBoundsException();
		makeSure(index);
		Object oldData;
		if (index == 0) {
			oldData = removeFirst();
		} else if (index == size - 1) {
			oldData = removeLast();
		} else {
			final Node preNode = getPreNode(index);
			final Node nextNode = preNode.next.next;
			oldData = preNode.next.data;
			preNode.next = nextNode;
			size --;
		}
		return oldData;
	}

	public Object removeFirst() {
		Object oldData;
		final Node h = head.next;
		oldData = head.data;
		head = h;
		size --;
		return oldData;
	}

	public Object removeLast() {
		Object oldData;
		final Node l = getPreNode(size);
		oldData = last.data;
		last = l;
		size --;
		return oldData;
	}
	
	public int size(){
		return size;
	}


	public Iterator iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator{
		private Node pointer = head;
		private int nextIndex = 0;

		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public Object next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Object nodeData = pointer.data;
			pointer = pointer.next;
			nextIndex ++;
			return nodeData;
		}
	}

	private static class Node {
		Object data;
		Node next;
		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	/**
	 * �Ѹ���������
	 * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3
	 */
	public  void reverse() {
		Node node = last;
		Node preNode = head;
		last = head;
		head = node;
		node = preNode.next;
		Node nextNode;
		preNode.next = null;
		while (node != null) {
			nextNode = node.next;
			node.next = preNode;
			preNode = node;
			node = nextNode;
		}
	}
	
	/**
	 * ɾ��һ���������ǰ�벿��
	 * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8
	 * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10
	 */
	public  void removeFirstHalf() {
		int half = size / 2;
		Node node = head;
		while (half != 0 ) {
			head = head.next;
			node.next = null;
			node = head;
			half --;
		}
	}
	
	/**
	 * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length) {
		if (i + length != size)
			makeSure(i + length);
		Node node = head;
		Node preNode = head;

		if (i > 0) {
			for (int j = 0; j < i; ++j) {
				preNode = node;
				node = node.next;
			}
		}

		Node tempNode;
		for (int j = 0; j < length; ++j) {
			tempNode = node.next;
			node.next = null;
			node = tempNode;
			size --;
		}

		if (i == 0) {
			head = node;
		} else {
			preNode.next = node;
		}
	}
	/**
	 * �ٶ���ǰ�����list���������������е�����
	 * �ӵ�ǰ������ȡ����Щlist��ָ����Ԫ��
	 * ���統ǰ���� = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * ���صĽ��Ӧ����[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(LinkedList list) {
		int array[] = new int[list.size()];
		int index = 0;
		int i = 0;
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			i = (int)iterator.next();
			makeSure(i);
			array[index ++] = (int)get(i);
		}
		return array;
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * �ӵ�ǰ��������ɾ����list�г��ֵ�Ԫ�� 
	 *
	 * (������list �� List�����Ӽ�)
	 * @param list
	 */
	public void subtract(LinkedList list) {
		Iterator iterator = list.iterator();
		int index = 0;
		Object element;
		while (iterator.hasNext()) {
			element = iterator.next();
			for (int i = index; i < size; ++i) {
				if (element == get(i)) {
					index = i;
					break;
				}
			}
			remove(index);
		}
	}
	
	
	
	/**
	 * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
	 * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list) {
		LinkedList newLink = new LinkedList();
		Iterator it1 = iterator();
		Iterator it2 = list.iterator();
		int element1 = (int)it1.next();
		int element2 = (int)it2.next();
		while (it1.hasNext() && it2.hasNext()) {
			if (element1 < element2) {
				newLink.add(element1);
				element1 = (int) it1.next();
			} else {
				newLink.add(element2);
				element2 = (int) it2.next();
			}
		}

		while (it1.hasNext()) {
			if (element1 == 0)
				element1 = (int)it1.next();
			newLink.add(element1);
			element1 = 0;
		}
		while (it2.hasNext()) {
			if (element2 == 0)
				element2 = (int)it2.next();
			newLink.add(element2);
			element2 = 0;
		}
		if (element1 < element2)
			newLink.add(element2);
		else if (element2 < element1)
			newLink.add(element1);

		return newLink;
	}

	public static void main(String[] args) {
		LinkedList linkedList=new LinkedList();
		
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		
		System.out.println("���ָ���Ϊ3��"+linkedList.size());
		
		System.out.println("��ȡ������Ϊ1��"+linkedList.get(0)); 
		System.out.println("��ȡ������Ϊ2��"+linkedList.get(1)); 
		System.out.println("��ȡ������Ϊ3��"+linkedList.get(2)); 
		// add get remove size
		
		System.out.println("*************");
		
		System.out.println(linkedList.remove(0));//1
		System.out.println("��ȡ������Ϊ2��"+linkedList.get(0)); 
		System.out.println("��ȡ������Ϊ3��"+linkedList.get(1)); 
		System.out.println("���ָ���Ϊ2��"+linkedList.size());
		
	}
	
}

