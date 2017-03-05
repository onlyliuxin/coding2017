package com.nitasty.util;

public class LinkedList implements List {

	private Node first;

	private Node last;

	int size = 0;

	public LinkedList() {

	}

	/**
	 * ��������
	 * 
	 * @param index
	 * @return
	 */
	public Node node(int index) {
		if (index < (size >> 1)) { // �������ܻ��ǰ�
			Node x = first;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;
		} else {
			Node x = last;
			for (int i = size-1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}

	}

	public Object get(int index) {
		checkElementIndex(index);
		return node(index).data;
	}

	public Object remove(int index) {
		checkElementIndex(index);
		return (unlink(node(index)));
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		final Node f = first;
		final Node newNode = new Node(null, o, f);
		first = newNode;

		if (f == null) {
			last = newNode;
		} else {
			f.prev = newNode;
		}
		size++;// ������
	}

	public void addLast(Object o) {
		final Node l = last;
		final Node newNode = new Node(l, o, null);
		last=newNode;
		if (l == null) {
			first = newNode;
		} else {
			l.next = newNode;
		}
		size++;
	}

	public void addBefore(Object o, Node succ) {
		final Node pred = succ.prev;
		final Node newNode = new Node(pred, o, succ);
		succ.prev = newNode;
		if (pred == null) {
			first = newNode;
		} else {
			pred.next = newNode;
		}
		size++;
	}

	public void addAfter(Object o, Node succ) {
		final Node nextd = succ.next;
		final Node newNode = new Node(succ, o, nextd);
		succ.next = newNode;
		if (nextd == null) {
			last = newNode;
		} else {
			nextd.prev = newNode;
		}
		size++;
	}

	private Object unlink(Node n) {

		Object data = n.data;
		final Node prev = n.prev;
		final Node next = n.next;

		/* �Լ�д�ľ�����ôbug */
		// if(n.prev==null){
		// first=n.next;
		// }else if(n.next==null){
		// last=n.prev;
		// }else {
		// n.prev.next=n.next;
		// n.next.prev=n.prev;
		// }
		//
		// n=null;

		if (prev == null) {
			first = next;
//			first.prev = null;// Դ��û�����д��� why�� �𣺵� data, prev, ��NullPointException
								// nextȫΪnullʱ����nodeΪnull��
		} else {
			prev.next = next;
			n.prev = null;
		}

		if (next == null) {
			last = prev;
//			last.next = null;// Դ��û�����д��� why�� ��NullPointException
		} else {
			next.prev = prev;
			n.next = null;
		}

		n.data = null; // Ϊʲô��ֱ�� node=null���������ν� data, prev, next ��Ϊnull
		size--; // ע��
		return data;
	}

	public Object removeFirst() {// ΪɶԴ���л���Ҫ���� node��

		final Object data = first.data;
		final Node next = first.next;
		first = next;

		if (next == null)
			first = null;
		else
			next.prev = null;

		size--;
		return data;
	}

	public Object removeLast() {// ΪɶԴ���л���Ҫ���� node��

		final Object data = last.data;
		final Node prev = last.prev;
		last = prev;

		if (prev == null)
			last = null;
		else
			prev.next = null;

		size--;
		return data;
	}

	private static class Node {
		Object data;
		Node next;
		Node prev;

		Node(Node prev, Object data, Node next) {
			this.prev = prev;
			this.data = data;
			this.next = next;
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public boolean add(Object o) {
		addLast(o);
		return true;
	}

	@Override
	public boolean add(int index, Object o) {
		checkPositionIndex(index);
		if (index == size)
			addLast(o);
		else
			addBefore(o, node(index));
		return true;
	}

	@Override
	public boolean addAll(Object[] o) {
		// add(size,o);
		// return true;

		return add(size, o);// ����
	}

	@Override
	public boolean addAll(int index, Object[] o) {// ͦ���Ѷ�
		checkPositionIndex(index);

		int numNew = o.length;
		if (numNew == 0)
			return false;

		Node pred, succ; // unlink�ڵ��ǰһ�ڵ㼰unlink�ڵ�
		if (index == size) {
			succ = null;
			pred = last;
		} else {
			succ = node(index);
			pred = succ.prev;
		}

		for (Object data : o) {
			Node newNode = new Node(pred, data, null);// ���½ڵ�link��ǰһ�ڵ�
			if (pred == null)
				first = newNode;
			else
				pred.next = newNode;
			pred = newNode;
			// size++;
		}

		if (succ == null) {// link��succ
			last = pred;
		} else {
//			last = succ; // Դ��δ���� why��      succ�ֲ������һ���� ��Ȼ�������ˡ�
//			succ.next = null; // Դ��δ���� why��
			pred.next = succ;
			succ.prev = pred;
			// size++;
		}

		size += numNew;

		return true;
	}

	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}

	private void checkElementIndex(int index) {
		if (!isElementIndex(index))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private void checkPositionIndex(int index) {
		if (!isElementIndex(index))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private boolean isElementIndex(int index) {
		return index >= 0 && index < size;
	}

	private boolean isPositionIndex(int index) {
		return index >= 0 && index <= size; // postion������ӵ����
	}

	@Override
	public boolean remove(Object o) {

		return false;
	}

	@Override
	public boolean removeAll(List list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object set(int index, Object o) {
		checkElementIndex(index);
		Node node=node(index);
		Object oldValue=node.data;
		node.data=o;
		return oldValue;
	}

	@Override
	public int indexOf(Object o) {
		int index = 0;
		if (o == null) {
			for (Node x = first; x != null; x = x.next) { // �µ�ѭ����ʽ
				if (x.data == null)
					return index;
				index++;
			}
		} else {
			for (Node x = first; x != null; x = x.next) { // �µ�ѭ����ʽ
				if (o.equals(x.data))
					return index;
				index++;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		int index = size-1;
		if (o == null) {
			for (Node x = last; x != null; x = x.prev) { // �µ�ѭ����ʽ
				if (x.data == null)
					return index;
				index--;
			}
		} else {
			for (Node x = last; x != null; x = x.prev) { // �µ�ѭ����ʽ
				if (o.equals(x.data))
					return index;
				index--;
			}
		}
		return -1;
	}

	@Override
	public Object[] toArray() {
		Object[] elementData = new Object[size];
		int i = 0;
		for (Node x = first; x != null; x = x.next) {
			elementData[i++] = x.data;
		}
		return elementData;
	}

	@Override
	public void clear() {
		// bugը��
		// for(Node x=first;x!=null;x=x.next){
		// x=null;
		// }

		for (Node x = first; x != null;) {
			Node next = x.next;
			x.prev = null;
			x.data = null;
			x.next = null;
			x = next;
		}
		size = 0;
		first = last = null;
	}
	
	public Iterator iterator(){
		return new Itr();
	}
	
	private class Itr implements Iterator{
		private Node lastRetured;
		private Node next;
		int cursor;
		
		@Override
		public boolean hasNext() {
			return cursor<size;
		}

		@Override
		public Object next() {
			checkElementIndex(cursor);
			next=node(cursor);
			lastRetured=next;
			next=next.next;
			cursor++;
			return lastRetured.data;
		}
		
	}
}
