package com.coding.basic;

import java.util.Collection;

import org.junit.Test;

/**
 * LinkedList (��ͷ���ĵ�����) ʵ�� ��14С�� 296933284
 * 
 * @author Tonnyson
 *
 */
public class LinkedList implements List {

	private Node head;
	private int size;

	public LinkedList() {
		super();
		this.head = new Node();
		this.size = 0;
	}

	public void add(Object obj) {
		addLast(obj);
	}

	public void add(int index, Object obj) {
		
		if (index == size + 1) {
			addLast(obj);
		} else {
			Node r = getPreNode(index);
			Node node = new Node();
			node.data = obj;
			node.next = r.next;
			r.next = node;
			size++;
		}
	}

	
	
	/**
	 * �������ײ�����ڵ�
	 * 
	 * @param obj ������ڵ�Ľڵ�ֵ
	 *            
	 */
	public void addFirst(Object obj) {
		Node node = new Node();
		node.data = obj;

		node.next = head.next;
		head.next = node;
		size++;
	}

	/**
	 * ������β������ڵ�
	 * 
	 * @param obj ������ڵ�Ľڵ�ֵ
	 * 
	 */
	public void addLast(Object obj) {
		
		Node node = new Node();
		node.data = obj;
		node.next = null;
		
		Node r = head;
		while (r.next != null) r = r.next;
		
		r.next = node;
		
		size++;

	}

	/**
	 * ������������Ԫ�ذ�˳����뵥����
	 * 
	 * @param c Ҫ���뵥���������Ԫ�صļ���
	 *            
	 */
	public void addAll(Collection c) {

		Iterator iter = (Iterator) c.iterator();

		while (iter.hasNext()) {
			addLast(iter.next());
		}
	}

	/**
	 * ��ȡָ��λ�õĽڵ�ֵ
	 */
	public Object get(int index) {
		// rangCheck(index);
		
		return getPreNode(index).next.data;
	}

	/**
	 * ɾ��ָ��λ�ýڵ㣬�����ؽڵ�ֵ
	 */
	public Object remove(int index) {
		rangCheck(index);
		
		Node r = getPreNode(index);
		
		Object result = r.next.data;
		
		r.next = r.next.next;
		size--;
		return result;
	}
	
	/**
	 * ɾ���������һ���ڵ㣬�����ؽڵ�ֵ
	 * 
	 * @return ��һ���ڵ��ֵ
	 */
	public Object removeFirst() {
		return remove(0);
	}
	
	/**
	 * ɾ�����������һ���ڵ㣬�����ؽڵ�ֵ
	 * 
	 * @return ���һ���ڵ��ֵ
	 */
	public Object removeLast() {
		return remove(size - 1);
	}
	
	// ��ȡָ��λ�õ�ǰ����㲢����
	private Node getPreNode(int index) {
		rangCheck(index);
		
		if (index == 0) {
			return head;
		} else {
			Node r = head;
			
			for (int i = 0; i < index; i++)
				r = r.next;
			
			return r;			
		}
		
	}
	
	/**
	 * ���ص�����ĳ���
	 */
	public int size() {
		return size;
	}

	/**
	 * ������
	 * 
	 * @return ����һ������������
	 */
	public Iterator iterator() {
		return new Iter();
	}

	// �������ڲ���
	private class Iter implements Iterator {
		int current = 0;

		@Override
		public boolean hasNext() {
			return current != size;
		}

		@Override
		public Object next() {
			int i = current;
			
			rangCheck(i);
			
			current++;

			return get(i);
		}

	}
	
	/**
	 * ����Ƿ�Խ��
	 * 
	 * @param index
	 */
	private void rangCheck(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException();
	}

	private static class Node {
		Object data;
		Node next;
		
		public Node() {
			super();
			this.data = null;
			this.next = null;
		}
		
		
	}
}